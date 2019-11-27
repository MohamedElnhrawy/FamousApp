package com.example.famousapp.ui.previewImage

import androidx.lifecycle.ViewModel
import com.example.famousapp.famous.data.repository.PopularRepository
import com.example.famousapp.famous.ui.base.BaseViewModel
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ImagePreviewViewModel (
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val popularRepository: PopularRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    override fun onCreate() {
    }


}
