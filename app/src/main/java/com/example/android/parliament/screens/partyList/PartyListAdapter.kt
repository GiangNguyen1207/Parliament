package com.example.android.parliament.screens.partyList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.parliament.R

class PartyListAdapter(private val context: Context) :
    RecyclerView.Adapter<PartyListAdapter.ViewHolder>() {
    var partyList = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var partyImage: ImageView = itemView.findViewById(R.id.party_image)
        var finPartyName: TextView = itemView.findViewById(R.id.fin_party_name)
        var engPartyName: TextView = itemView.findViewById(R.id.eng_party_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.party_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.partyImage.setImageResource(setPartyImage(partyList[position]))
        holder.finPartyName.text = context.getString(displayFinName(partyList[position]))
        holder.engPartyName.text = context.getString(displayEngName(partyList[position]))
    }

    override fun getItemCount(): Int {
        return partyList.size
    }

    private fun displayFinName(partyName: String): Int {
        return when (partyName) {
            "ps" -> R.string.ps
            "sd" -> R.string.sd
            "vihr" -> R.string.vihr
            "kok" -> R.string.kok
            "r" -> R.string.r
            "kd" -> R.string.kd
            "vas" -> R.string.vas
            "liik" -> R.string.liik
            else -> R.string.kesk
        }
    }

    private fun displayEngName(partyName: String): Int {
        return when (partyName) {
            "ps" -> R.string.ps_eng
            "sd" -> R.string.sd_eng
            "vihr" -> R.string.vihr_eng
            "kok" -> R.string.kok_eng
            "r" -> R.string.r_eng
            "kd" -> R.string.kd_eng
            "vas" -> R.string.vas_eng
            "liik" -> R.string.liik_eng
            else -> R.string.kesk_eng
        }
    }

    private fun setPartyImage(partyName: String): Int {
        return when (partyName) {
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
    }
}