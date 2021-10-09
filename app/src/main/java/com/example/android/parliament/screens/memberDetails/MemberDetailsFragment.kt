package com.example.android.parliament.screens.memberDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.parliament.MyApp
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentMemberDetailsBinding

//Giang Nguyen - 30.09.2021

class MemberDetailsFragment : Fragment() {
    private lateinit var memberDetailsVmFactory: MemberDetailsVmFactory
    private lateinit var memberDetailsViewModel: MemberDetailsViewModel

    private val args: MemberDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentMemberDetailsBinding>(
            inflater,
            R.layout.fragment_member_details, container, false
        )

        memberDetailsVmFactory = MemberDetailsVmFactory(args.personNumber)
        memberDetailsViewModel = ViewModelProvider(this, memberDetailsVmFactory)
            .get(MemberDetailsViewModel::class.java)

        binding.lifecycleOwner = this
        binding.memberDetailsViewModel = memberDetailsViewModel

        //handle the action when user clicks "See All Comments"
        binding.seeAllComments.setOnClickListener { memberDetailsViewModel.navigateToAllComments() }

        /* handle the action when user clicks "Submit" button.
        If the rate == 0, there will be an alert warning the user that the MP will get 0 star, and
        asking if they want to go back or continue:
            Go back -> Add rate again.
            Continue -> Submit the current rate and comment
        If the rate != 0, submit the rate and comment. */
        binding.btnSubmit.setOnClickListener {
            if (binding.gradingBar.rating == 0F) {
                val dialog = CustomDialogFragment()
                dialog.show(parentFragmentManager, "custom dialog")

                //receive the data from dialog fragment
                setFragmentResultListener("onBtnPositiveClick") { _, bundle ->
                    val isSubmitted = bundle.getBoolean("isSubmitted")

                    if (isSubmitted) {
                        handleSubmit(args.personNumber, binding, memberDetailsViewModel)
                    }
                }
            } else {
                handleSubmit(args.personNumber, binding, memberDetailsViewModel)
            }
        }

        //observe the change of member details
        memberDetailsViewModel.memberDetails.observe(viewLifecycleOwner, {})

        /* observe the change of average rate. If there is an average rate, show "Rating",
        otherwise, show "Not rated yet." */
        memberDetailsViewModel.averageRate.observe(viewLifecycleOwner, { rate ->
            binding.averageTitle.text =
                if (rate != null) getString(R.string.rating) else getString(R.string.not_rated_yet)
        })

        /* always show the only latest comment to member details screen.
        If the user wants to see all comments, they need to navigate to another fragment*/
        memberDetailsViewModel.latestComment.observe(viewLifecycleOwner, { comment ->
            if (comment != null) {
                binding.commentSv.visibility = View.VISIBLE
                binding.dateTime.text = comment.dateTime
                binding.latestComment.text = comment.comment
            }
        })

        //observe the isNavigated value to move to another fragment to see all comments
        memberDetailsViewModel.isNavigated.observe(viewLifecycleOwner, { isNavigated ->
            if (isNavigated) {
                this.findNavController()
                    .navigate(
                        MemberDetailsFragmentDirections.actionMemberDetailsFragmentToAllCommentsFragment(
                            args.personNumber
                        )
                    )
                memberDetailsViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}

/*function to handle action of add rating and comment when user clicks Submit button.
When user clicks the button, it will collect the rating and comment and pass into the function
inside VM. Then, notify the user that rating and comment has been added by Toast
and remove current rating and comment in Grading Bar and Edit Text.
*/
fun handleSubmit(
    personNumber: Int,
    binding: FragmentMemberDetailsBinding,
    memberDetailsViewModel: MemberDetailsViewModel
) {
    memberDetailsViewModel.addRatingComment(
        personNumber,
        binding.gradingBar.rating,
        binding.commentEditText.text.toString()
    )

    Toast.makeText(MyApp.appContext, "Rated Successfully", Toast.LENGTH_SHORT).show()

    binding.commentEditText.text = null
    binding.gradingBar.rating = 0F
}
