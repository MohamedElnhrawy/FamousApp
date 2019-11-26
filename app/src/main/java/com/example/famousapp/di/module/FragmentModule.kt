package com.example.famousapp.famous.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.famousapp.famous.data.repository.PopularRepository
import com.example.famousapp.famous.ui.base.BaseFragment
import com.example.famousapp.famous.utils.ViewModelProviderFactory
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
import com.example.famousapp.ui.popularDetails.PopularDetailsViewModel
import com.example.famousapp.ui.popularDetails.images.ImagesAdapter
import com.example.famousapp.ui.populars.PopularsAdapter
import com.example.famousapp.ui.populars.PopularsFragment
import com.example.famousapp.ui.populars.PopularsViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideGridLayoutManager(): GridLayoutManager = GridLayoutManager(fragment.context,2)

    @Provides
    fun providePopularsViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        popularRepository: PopularRepository
    ): PopularsViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(PopularsViewModel::class) {
                PopularsViewModel(schedulerProvider, compositeDisposable, networkHelper, popularRepository)
            }
        ).get(PopularsViewModel::class.java)

    @Provides
    fun providePopularDetailsViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        popularRepository: PopularRepository
    ): PopularDetailsViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(PopularDetailsViewModel::class) {
                PopularDetailsViewModel(schedulerProvider, compositeDisposable, networkHelper, popularRepository)
            }
        ).get(PopularDetailsViewModel::class.java)


    @Provides
    fun providePopularsAdapter() = PopularsAdapter(fragment.lifecycle, ArrayList(),ArrayList(), fragment as? PopularsFragment)
    @Provides
    fun provideImagedAdapter() = ImagesAdapter(fragment.lifecycle, ArrayList(),ArrayList())
}