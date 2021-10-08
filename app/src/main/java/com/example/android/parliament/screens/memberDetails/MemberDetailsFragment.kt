package com.example.android.parliament.screens.memberDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.android.parliament.R
import com.example.android.parliament.databinding.FragmentMemberDetailsBinding
import java.util.*

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
        binding.btnSubmit.setOnClickListener {
            memberDetailsViewModel.onButtonClick()
            binding.commentEditText.text = null
        }
        binding.seeAllComments.setOnClickListener { memberDetailsViewModel.navigateToAllComments() }
        binding.memberDetailsViewModel = memberDetailsViewModel


        memberDetailsViewModel.memberDetails.observe(viewLifecycleOwner, {})

        memberDetailsViewModel.isClicked.observe(viewLifecycleOwner, { click ->
            if (click) {
                memberDetailsViewModel.addRatingComment(
                    args.personNumber,
                    binding.gradingBar.rating,
                    binding.commentEditText.text.toString()
                )
                Toast.makeText(context, "Graded Successfully", Toast.LENGTH_SHORT).show()
                memberDetailsViewModel.doneClick()
            }
        })

        memberDetailsViewModel.rates.observe(viewLifecycleOwner, {
            memberDetailsViewModel.calculateAverageRate(it)
        })

        memberDetailsViewModel.averageRate.observe(viewLifecycleOwner, { rate ->
            binding.averageRate.text = getString(R.string.average_rate, rate)
        })

        memberDetailsViewModel.latestComment.observe(viewLifecycleOwner, { comment ->
            if (comment != null) {
                binding.commentSv.visibility = View.VISIBLE
                binding.dateTime.text = comment.dateTime
                binding.latestComment.text = comment.comment
            }
        })

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