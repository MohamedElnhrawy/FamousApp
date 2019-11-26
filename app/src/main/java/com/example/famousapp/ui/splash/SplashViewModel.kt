package com.example.famousapp.famous.ui.splash

import androidx.lifecycle.MutableLiveData
import com.example.famousapp.famous.data.repository.UserRepository
import com.example.famousapp.famous.ui.base.BaseViewModel
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
import com.example.famousapp.utils.common.Event
import io.reactivex.disposables.CompositeDisposable


class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    // Event is used by the view model to tell the activity to launch another Activity
    // view model also provided the Bundle in the event that is needed for the Activity
    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        // Empty Bundle passed to Activity in Event that is needed by the other Activity
        // Here in actual application we will decide which screen to open based on
        // either the user is logged in or not
        launchMain.postValue(Event(emptyMap()))
    }
}