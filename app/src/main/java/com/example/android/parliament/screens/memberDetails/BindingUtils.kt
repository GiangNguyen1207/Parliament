package com.example.android.parliament.screens.memberDetails

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.android.parliament.MyApp
import com.example.android.parliament.R
import com.example.android.parliament.data.ParliamentMember
import org.w3c.dom.Text
import java.util.*

//Giang Nguyen - 8.10.2021

//Binding Name
@BindingAdapter("memberName")
fun TextView.setMemberName(member: ParliamentMember?) {
    member?.let {
        text = MyApp.appContext.getString(R.string.name, member.first, member.last)
    }
}

//Binding Title - Minister or Member of Parliament
@BindingAdapter("memberTitle")
fun TextView.setMemberTitle(member: ParliamentMember?) {
    member?.let {
        text =
            MyApp.appContext.getString(R.string.title, if (member.minister) "Minister" else "Member of Parliament")
    }
}

//Binding Age
@BindingAdapter("memberAge")
fun TextView.setMemberAge(member: ParliamentMember?) {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    member?.let {
        text = MyApp.appContext.getString(R.string.age, currentYear - member.bornYear)
    }
}

//Binding Constituency
@BindingAdapter("memberConstituency")
fun TextView.setMemberConstituency(member: ParliamentMember?) {
    member?.let {
        text = MyApp.appContext.getString(R.string.constituency, member.constituency)
    }
}

//Binding Image
@BindingAdapter("memberImage")
fun ImageView.setMemberImage(member: ParliamentMember?) {
    member?.let {
        Glide
            .with(this)
            .load("https://avoindata.eduskunta.fi/${member.picture}")
            .placeholder(R.drawable.loading_animation)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}

//Binding Average Rate for a MP
@BindingAdapter("averageRate")
fun TextView.setAverageRate(rate: Double?) {
    rate?.let {
        text = MyApp.appContext.getString(R.string.average_rate, rate)
    }
}
