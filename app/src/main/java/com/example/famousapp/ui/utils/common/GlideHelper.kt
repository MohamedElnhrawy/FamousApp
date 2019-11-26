package com.example.famousapp.famous.utils.common

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions



object GlideHelper {

    fun getProtectedUrl(url: String, headers: Map<String, String>): GlideUrl {
        val builder = LazyHeaders.Builder()
        for (entry in headers) builder.addHeader(entry.key, entry.value)
        return GlideUrl(url, builder.build())
    }

    fun GlideRequetOptions(context : Context): RequestOptions {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 8f
        circularProgressDrawable.centerRadius = 50f
        circularProgressDrawable.start()
        val requestOptions = RequestOptions()
        requestOptions.placeholder(circularProgressDrawable)
        return requestOptions
    }
}
