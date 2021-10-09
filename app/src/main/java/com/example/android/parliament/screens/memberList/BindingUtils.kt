package com.example.android.parliament.screens.memberList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.android.parliament.R

//Giang Nguyen - 8.10.2021

//Setting Party Image
@BindingAdapter("partyImage")
fun ImageView.setPartyImage(party: String) {
    setImageResource(
        when (party) {
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
