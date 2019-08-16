package com.sun.funnytoeic.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sun.funnytoeic.R

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun <T> MutableList<T>.replaceAll(data: List<T>) {
    clear()
    addAll(data)
}

fun ImageView.setImageUrl(url: String, cornerRadius: Int = 0) = Glide.with(context)
    .load(url)
    .apply { if (cornerRadius > 0) transform(CenterCrop(), RoundedCorners(cornerRadius)) }
    .placeholder(R.drawable.bg_rounded_corner)
    .into(this)
