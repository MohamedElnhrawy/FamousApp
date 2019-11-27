package com.example.famousapp.ui.populars

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.famousapp.R
import com.example.famousapp.data.model.Person
import com.example.famousapp.famous.di.component.ViewHolderComponent
import com.example.famousapp.famous.ui.base.BaseItemViewHolder
import com.example.famousapp.famous.utils.common.GlideHelper.GlideRequetOptions
import com.example.famousapp.famous.utils.interfaces.onViewItemClicked
import kotlinx.android.synthetic.main.item_view_person.view.*


class PopularItemViewHolder(parent: ViewGroup,private val itemListener : onViewItemClicked?) :
    BaseItemViewHolder<Person, PopularItemViewModel>(R.layout.item_view_person, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.name.observe(this, Observer {
            itemView.tv_head_line_dummy.text = it
        })

        viewModel.url.observe(this, Observer {
            Glide.with(itemView.context).load(it).apply(GlideRequetOptions(itemView.context).error(R.drawable.ic_broken_image)).into(itemView.iv_dummy)
        })
    }

    override fun setupView(view: View) {
        view.setOnClickListener {
            viewModel.onItemClick(adapterPosition)
            itemListener?.onViewItemClicked(adapterPosition)

        }
    }
}