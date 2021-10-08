package com.example.android.parliament.screens.partyList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.android.parliament.R
import com.example.android.parliament.data.Party

@BindingAdapter("partyImage")
fun ImageView.setPartyImage(party: Party) {
    setImageResource(
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

