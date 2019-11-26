package com.example.elnhrawy.famous.ui.populars

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.famousapp.famous.data.remote.response.PopularsResponse
import com.example.famousapp.famous.data.repository.PopularRepository
import com.example.famousapp.famous.ui.base.BaseViewModel
import com.example.famousapp.famous.utils.common.Resource
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class PopularsViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val popularRepository: PopularRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    var PAGE = 0;
    private val popularsLiveData: MutableLiveData<Resource<PopularsResponse>> = MutableLiveData()

    fun getPopulars(): LiveData<PopularsResponse> =
        Transformations.map(popularsLiveData) { it.data }


    override fun onCreate() {
        if (popularsLiveData.value == null && checkInternetConnectionWithMessage()) {
            popularsLiveData.postValue(Resource.loading())
            compositeDisposable.add(
                popularRepository.fetchPopular("en-US",++PAGE)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        { popularsLiveData.postValue(Resource.success(it)) },
                        {
                            handleNetworkError(it)
                            popularsLiveData.postValue(Resource.error())
                        })
            )
        }



    }



    fun getPopularsData(){
        if (checkInternetConnectionWithMessage()) {
            popularsLiveData.postValue(Resource.loading())
            compositeDisposable.add(
                popularRepository.fetchPopular("en-US",++PAGE)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        { popularsLiveData.postValue(Resource.success(it)) },
                        {
                            handleNetworkError(it)
                            popularsLiveData.postValue(Resource.error())
                        })
            )
        }

        Log.e("spage",""+PAGE)
    }
}
