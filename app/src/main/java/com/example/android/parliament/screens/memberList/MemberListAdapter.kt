package com.example.android.parliament.screens.memberList

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.parliament.data.ParliamentMember
import com.example.android.parliament.data.Party
import com.example.android.parliament.databinding.MemberRowBinding

class MemberListAdapter : RecyclerView.Adapter<MemberListAdapter.ViewHolder>() {
    var memberList = listOf<ParliamentMember>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder private constructor(private val binding: MemberRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(member: ParliamentMember) {
            binding.member = member
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
        holder.bind(memberList[position])
    }

    override fun getItemCount(): Int {
        return memberList.size
    }
}

class PartyListener(val clickListener: (party: String) -> Unit) {
    fun onClick(party: Party) = clickListener(party.party)
}