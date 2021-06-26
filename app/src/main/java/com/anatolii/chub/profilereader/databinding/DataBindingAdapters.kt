package com.anatolii.chub.profilereader.databinding

import android.text.format.DateFormat
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.anatolii.chub.profilereader.ui.GlideApp
import java.util.*


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    GlideApp.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("app:formattedDate")
fun formattedText(view: TextView, date: Long) {
    val formattedDate = DateFormat.getDateFormat(view.context)
        .format(Date(date))
    view.text = formattedDate
}