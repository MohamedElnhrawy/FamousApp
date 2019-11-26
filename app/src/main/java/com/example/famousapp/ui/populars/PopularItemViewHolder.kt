package com.example.elnhrawy.famous.ui.populars

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.famousapp.R
import com.example.famousapp.famous.data.model.Person
import com.example.famousapp.famous.di.component.ViewHolderComponent
import com.example.famousapp.famous.ui.base.BaseItemViewHolder
import com.example.famousapp.famous.utils.common.GlideHelper.GlideRequetOptions
import kotlinx.android.synthetic.main.item_view_person.view.*


class PopularItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<Person, PopularItemViewModel>(R.layout.item_view_person, parent) {

//    lateinit var  listener : onViewItemClicked
//
//    fun registerListener(listener : onViewItemClicked){
//        this.listener = listener
//    }

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
          //  listener.onViewItemClicked(adapterPosition)
            viewModel.onItemClick(adapterPosition)
        }
    }
}