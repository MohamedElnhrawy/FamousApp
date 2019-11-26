package com.example.famousapp.ui.popularDetails

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.famousapp.R
import com.example.famousapp.famous.data.model.Person
import com.example.famousapp.famous.di.component.FragmentComponent
import com.example.famousapp.famous.ui.base.BaseFragment
import com.example.famousapp.famous.utils.interfaces.onViewItemClicked
import com.example.famousapp.ui.popularDetails.images.ImagesAdapter
import kotlinx.android.synthetic.main.popular_details_fragment.*
import javax.inject.Inject


class PopularDetailsFragment :  BaseFragment<PopularDetailsViewModel>()  {

    lateinit var person : Person
    lateinit var listener : onViewItemClicked

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var imagesAdapter: ImagesAdapter



    companion object {

        const val TAG = "PopularDetailsFragment"

        fun newInstance(): PopularDetailsFragment {
            val args = Bundle()
            val fragment = PopularDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun provideLayoutId(): Int  = R.layout.popular_details_fragment

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)   }

    override fun setupView(view: View) {
        person = arguments!!.getParcelable("person")!!
        viewModel.fetchPersonProfile(person.id)
        viewModel.fetchPersonImages(person.id)

        rv_images.layoutManager = gridLayoutManager
        rv_images.adapter = imagesAdapter

    }

    override fun setupObservers() {
     viewModel.getImages().observe(this, Observer { it?.run {
         this.profiles
        imagesAdapter.appendData(this.profiles)

     } })

        viewModel.getProfile().observe(this , Observer { it?.run {
            Log.e("pp",this.birthday);
            tv_desc.text = this.biography
            tv_person_name.text = this.name
        } })

    }





}
