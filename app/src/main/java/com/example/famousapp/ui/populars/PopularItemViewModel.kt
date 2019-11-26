package com.example.elnhrawy.famous.ui.populars

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.famousapp.BuildConfig
import com.example.famousapp.R
import com.example.famousapp.famous.data.model.Person
import com.example.famousapp.famous.ui.base.BaseItemViewModel
import com.example.famousapp.famous.utils.common.Resource
import com.example.famousapp.famous.utils.log.Logger
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PopularItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Person>(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "PopularItemViewModel"
    }

    val name: LiveData<String> = Transformations.map(data) { it.name }
    val url: LiveData<String?> = Transformations.map(data) { BuildConfig.BASE_IMAGE_URL + it.profile_path }

    fun onItemClick(position: Int) {
        if (networkHelper.isNetworkConnected()) {
            messageString.postValue(Resource.success("onItemClick at $position of ${data.value?.name}"))

        }else{
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
        }
        clickedpositionInt.postValue(position)
        Logger.d(TAG, "onItemClick at $position")
       // onViewClicked(position)
    }

    override fun onCreate() {
        Logger.d(TAG, "onCreate called")
    }

}