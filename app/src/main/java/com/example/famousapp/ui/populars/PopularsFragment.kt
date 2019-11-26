package com.example.elnhrawy.famous.ui.populars

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.famousapp.R
import com.example.famousapp.famous.di.component.FragmentComponent
import com.example.famousapp.famous.ui.base.BaseFragment
import kotlinx.android.synthetic.main.populars_fragment.*
import kotlinx.android.synthetic.main.search_bar.view.*
import javax.inject.Inject



class PopularsFragment :  BaseFragment<PopularsViewModel>()  {

    private var isLoading : Boolean = false


    companion object {

        const val TAG = "PopularsFragment"

        fun newInstance(): PopularsFragment {
            val args = Bundle()
            val fragment = PopularsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var popularsAdapter: PopularsAdapter


    override fun provideLayoutId(): Int = R.layout.populars_fragment


    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

        rv_populars.layoutManager = linearLayoutManager
        rv_populars.adapter = popularsAdapter



        rv_populars.addOnScrollListener(object  : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                                if (dy > 0) {
                val visibleItemCount = linearLayoutManager.childCount
                val pastVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                val total = popularsAdapter.itemCount

                if (!isLoading && search_view.et_search_bar.text.isEmpty()) {

                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        if (viewModel.checkInternetConnectionWithMessage()) {
                            isLoading = true
                            pb_loading.visibility = View.VISIBLE
                            viewModel.getPopularsData()
                        }else showMessage(R.string.network_connection_error)
                    }

                }
                }
            }
        })
        search_view.et_search_bar.addTextChangedListener(object  : TextWatcher{
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    popularsAdapter.FilterList(s.toString())
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
    override fun setupObservers() {
        viewModel.getPopulars().observe(this, Observer {
            it?.run {
                isLoading = false
                pb_loading.visibility = View.GONE
                popularsAdapter.appendData(this.results)
            }
        })


    }


}
