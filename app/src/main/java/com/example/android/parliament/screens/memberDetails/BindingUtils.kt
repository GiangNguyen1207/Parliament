package com.example.android.parliament.screens.memberDetails

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.parliament.MyApp
import com.example.android.parliament.R
import com.example.android.parliament.data.ParliamentMember
import java.util.*

@BindingAdapter("memberName")
fun TextView.setMemberName(member: ParliamentMember?) {
    member?.let {
        text = MyApp.appContext.getString(R.string.name, member.first, member.last)
    }
}

@BindingAdapter("memberTitle")
fun TextView.setMemberTitle(member: ParliamentMember?) {
    member?.let {
        text =
            MyApp.appContext.getString(R.string.title, if (member.minister) "Minister" else "Member of Parliament")
    }
}

@BindingAdapter("memberAge")
fun TextView.setMemberAge(member: ParliamentMember?) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    member?.let {
        text = MyApp.appContext.getString(R.string.age, currentYear - member.bornYear)
    }
}

@BindingAdapter("memberConstituency")
fun TextView.setMemberConstituency(member: ParliamentMember?) {
    member?.let {
        text = MyApp.appContext.getString(R.string.constituency, member.constituency)
    }
}

/*@BindingAdapter("memberImage")
fun ImageView.setMemberImage(view: ImageView, member: ParliamentMember) {
    Glide
        .with(this)
        .load("https://avoindata.eduskunta.fi/${member.picture}")
        .circleCrop()
        .placeholder(R.drawable.loading_animation)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}*/
