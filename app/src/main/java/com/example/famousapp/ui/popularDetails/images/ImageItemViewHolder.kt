package com.example.famousapp.ui.popularDetails.images

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.famousapp.R
import com.example.famousapp.famous.data.model.Profiles
import com.example.famousapp.famous.di.component.ViewHolderComponent
import com.example.famousapp.famous.ui.base.BaseItemViewHolder
import com.example.famousapp.famous.utils.common.GlideHelper.GlideRequetOptions
import kotlinx.android.synthetic.main.image_item_view.view.*

class ImageItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<Profiles, ImageItemViewModel>(R.layout.image_item_view, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

//        viewModel.name.observe(this, Observer {
//            itemView.tv_head_line_dummy.text = it
//        })

        viewModel.url.observe(this, Observer {
            Glide.with(itemView.context).load(it).apply(GlideRequetOptions(itemView.context)).into(itemView.iv_profile)
        })
    }

    override fun setupView(view: View) {
        view.setOnClickListener {
            viewModel.onItemClick(adapterPosition)
        }
    }
}