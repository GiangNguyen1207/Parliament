package com.example.android.parliament.screens.memberList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.parliament.data.ParliamentMember
import com.example.android.parliament.databinding.MemberRowBinding

//Giang Nguyen - 29.09.2021

class MemberListAdapter(private val clickListener: MemberListener) :
    ListAdapter<ParliamentMember, MemberListAdapter.ViewHolder>(MemberListDiffCallBack()) {

    class ViewHolder private constructor(private val binding: MemberRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //bind the member and click listener to enable using data variable in file xml.
        fun bind(member: ParliamentMember, clickListener: MemberListener) {
            binding.member = member
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        //inflate layout for each row
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MemberRowBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val member = getItem(position)
        holder.bind(member, clickListener)
    }
}

//a callback class to observe the difference between the list of two adapters.
class MemberListDiffCallBack: DiffUtil.ItemCallback<ParliamentMember>() {
    override fun areItemsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem.personNumber == newItem.personNumber
    }

    override fun areContentsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem == newItem
    }

}

/*a class for action click listener. Its parameter is the function, which receives a person number
as a parameter. The only one method onClick is to call the function in parameter. */
class MemberListener(val clickListener: (personNumber: Int) -> Unit) {
    fun onClick(personNumber: Int) = clickListener(personNumber)
}