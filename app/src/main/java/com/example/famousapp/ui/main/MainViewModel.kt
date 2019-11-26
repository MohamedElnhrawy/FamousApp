package com.example.famousapp.ui.main


import com.example.famousapp.famous.data.repository.PopularRepository
import com.example.famousapp.famous.ui.base.BaseViewModel
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val popularRepository: PopularRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {
        // do something
    }
}