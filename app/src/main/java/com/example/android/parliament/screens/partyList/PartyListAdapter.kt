package com.example.android.parliament.screens.partyList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.parliament.R
import com.example.android.parliament.data.Party
import com.example.android.parliament.databinding.PartyRowBinding

class PartyListAdapter(private val clickListener: PartyListener) :
    ListAdapter<Party, PartyListAdapter.ViewHolder>(PartyListDiffCallBack()) {

    class ViewHolder private constructor(private val binding: PartyRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //bind the party and click listener to enable using data variable in file xml.
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
        holder.bind(getItem(position), clickListener)
    }
}

//a callback class to observe the difference between two adapters.
class PartyListDiffCallBack: DiffUtil.ItemCallback<Party>() {
    override fun areItemsTheSame(oldItem: Party, newItem: Party): Boolean {
        return oldItem.party == newItem.party
    }

    override fun areContentsTheSame(oldItem: Party, newItem: Party): Boolean {
        return oldItem == newItem
    }
}

/*a class for action click listener. Its parameter is the function, which receives a party name
as a parameter. The only one method onClick is to call that function in parameter. */
class PartyListener(val clickListener: (party: String) -> Unit) {
    fun onClick(party: String) = clickListener(party)
}