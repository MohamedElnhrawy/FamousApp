package com.example.famousapp.ui.popularDetails.images

import androidx.lifecycle.LiveData
import com.example.famousapp.BuildConfig
import androidx.lifecycle.Transformations
import com.example.famousapp.famous.data.model.Profiles
import com.example.famousapp.famous.ui.base.BaseItemViewModel
import com.example.famousapp.famous.utils.log.Logger
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ImageItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Profiles>(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "PopularItemViewModel"
    }

   // val name: LiveData<String> = Transformations.map(data) { it.name }
    val url: LiveData<String?> = Transformations.map(data) { BuildConfig.BASE_IMAGE_URL + it.file_path }

    fun onItemClick(position: Int) {
       // messageString.postValue(Resource.success("onItemClick at $position of ${data.value?.name}"))
        Logger.d(TAG, "onItemClick at $position")
       // onViewClicked(position)
    }

    override fun onCreate() {
        Logger.d(TAG, "onCreate called")
    }

}