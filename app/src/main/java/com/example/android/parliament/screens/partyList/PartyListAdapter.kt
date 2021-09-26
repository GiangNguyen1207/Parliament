package com.example.android.parliament.screens.partyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.parliament.R
import com.example.android.parliament.data.Party
import com.example.android.parliament.databinding.PartyRowBinding

class PartyListAdapter(private val clickListener: PartyListener) : RecyclerView.Adapter<PartyListAdapter.ViewHolder>() {
    var partyList = listOf<Party>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder private constructor(private val binding: PartyRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(party: Party, clickListener: PartyListener) {
            binding.party = party
            binding.clickListener = clickListener
            binding.partyImage.setImageResource(
                when (party.party) {
                    "ps" -> R.drawable.ps_logo
                    "sd" -> R.drawable.sdp_logo
                    "vihr" -> R.drawable.vih_logo
                    "kok" -> R.drawable.kok_logo
                    "r" -> R.drawable.r_logo
                    "kd" -> R.drawable.kd_logo
                    "vas" -> R.drawable.vas_logo
                    "liik" -> R.drawable.liik_logo
                    else -> R.drawable.keskusta_logo
                }
            )
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PartyRowBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(partyList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return partyList.size
    }
}

class PartyListener(val clickListener: (party: String) -> Unit) {
    fun onClick(party: Party) = clickListener(party.party)
}