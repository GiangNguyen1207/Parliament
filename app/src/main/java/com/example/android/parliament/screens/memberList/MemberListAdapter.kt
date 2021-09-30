package com.example.android.parliament.screens.memberList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.parliament.data.ParliamentMember
import com.example.android.parliament.databinding.MemberRowBinding

class MemberListAdapter(private val clickListener: MemberListener) :
    ListAdapter<ParliamentMember, MemberListAdapter.ViewHolder>(MemberListDiffCallBack()) {

    class ViewHolder private constructor(private val binding: MemberRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(member: ParliamentMember, clickListener: MemberListener) {
            binding.member = member
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

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

class MemberListDiffCallBack: DiffUtil.ItemCallback<ParliamentMember>() {
    override fun areItemsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem.personNumber == newItem.personNumber
    }

    override fun areContentsTheSame(oldItem: ParliamentMember, newItem: ParliamentMember): Boolean {
        return oldItem == newItem
    }

}

class MemberListener(val clickListener: (personNumber: Int) -> Unit) {
    fun onClick(personNumber: Int) = clickListener(personNumber)
}