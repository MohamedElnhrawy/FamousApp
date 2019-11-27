package com.example.famousapp.ui.popularDetails.images

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.famousapp.famous.data.model.Profiles
import com.example.famousapp.famous.ui.base.BaseAdapter
import com.example.famousapp.famous.utils.interfaces.onViewItemClicked

class ImagesAdapter(
    parentLifecycle: Lifecycle,
    val profiles : ArrayList<Profiles>, val filterList : ArrayList<Profiles>, private val itemListener: onViewItemClicked?) : BaseAdapter<Profiles, ImageItemViewHolder>(parentLifecycle, profiles,filterList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageItemViewHolder(parent,itemListener)
}