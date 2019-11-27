package com.example.famousapp.ui.popularDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.famousapp.R
import com.example.famousapp.data.model.Person
import com.example.famousapp.famous.di.component.FragmentComponent
import com.example.famousapp.famous.ui.base.BaseFragment
import com.example.famousapp.famous.utils.interfaces.onViewItemClicked
import com.example.famousapp.ui.popularDetails.images.ImagesAdapter
import kotlinx.android.synthetic.main.popular_details_fragment.*
import javax.inject.Inject


class PopularDetailsFragment :  BaseFragment<PopularDetailsViewModel>()  ,onViewItemClicked {
    override fun onViewItemClicked(position: Int) {
        val bundle = bundleOf("Profiles" to imagesAdapter.profiles.get(position))
        performNavigationToDestination(R.id.action_popularDetailsFragment_to_imagePreviewFragment,bundle)

    }

    lateinit var person : Person

    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    private val personId: MutableLiveData<Int> = MutableLiveData()



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
        personId.postValue(person.id)
        rv_images.adapter = imagesAdapter

    }

    override fun setupObservers() {
     viewModel.getImages().observe(this, Observer { it?.run {
             imagesAdapter.appendData(this.profiles)
         toggleEmptyViewsState(this.profiles.isEmpty())

     } })

        viewModel.getProfile().observe(this , Observer { it?.run {
            tv_desc.text = this.biography
            tv_person_name.text = this.name
            hideLoading()
        } })

        personId.observe(this , Observer {
            showLoading()
            viewModel.fetchPenrsonProfile(it)
             })
    }

    private fun toggleEmptyViewsState(status : Boolean) {
        if (status) {
            tv_empty.visibility = View.VISIBLE
            rv_images.visibility = View.GONE
        }else{
            tv_empty.visibility = View.GONE
            rv_images.visibility = View.VISIBLE
        }
    }





}
