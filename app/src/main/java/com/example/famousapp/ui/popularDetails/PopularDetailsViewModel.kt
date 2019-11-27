package com.example.famousapp.ui.popularDetails

import androidx.lifecycle.*
import com.example.famousapp.famous.data.remote.response.ImagesResponse
import com.example.famousapp.famous.data.remote.response.PersonProfileResponse
import com.example.famousapp.famous.data.repository.PopularRepository
import com.example.famousapp.famous.ui.base.BaseViewModel
import com.example.famousapp.famous.utils.common.Resource
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularDetailsViewModel (
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val popularRepository: PopularRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val imagesLiveData: MutableLiveData<Resource<ImagesResponse>> = MutableLiveData()
    private val profileLiveData: MutableLiveData<Resource<PersonProfileResponse>> = MutableLiveData()

    override fun onCreate() {


    }

    fun getImages(): LiveData<ImagesResponse> =
        Transformations.map(imagesLiveData) { it.data }

    fun getProfile(): LiveData<PersonProfileResponse> =
        Transformations.map(profileLiveData) { it.data }


    fun fetchPersonImages(person_id : Int){
        if (imagesLiveData.value == null && checkInternetConnectionWithMessage()) {
            imagesLiveData.postValue(Resource.loading())
            compositeDisposable.add(
                popularRepository.fetchPersonImages(person_id)
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        { imagesLiveData.postValue(Resource.success(it)) },
                        {
                            handleNetworkError(it)
                            imagesLiveData.postValue(Resource.error())
                        })
            )
        }
    }

    fun fetchPenrsonProfile(person_id : Int){
        if ( checkInternetConnectionWithMessage()) {
            profileLiveData.postValue(Resource.loading())
            compositeDisposable.add(
                popularRepository.fetchPersonProfile(person_id , "en-US")
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        {
                            profileLiveData.postValue(Resource.success(it))
                            fetchPersonImages(person_id)
                        },
                        {
                            handleNetworkError(it)
                            profileLiveData.postValue(Resource.error())
                        })
            )
        }
    }
}
