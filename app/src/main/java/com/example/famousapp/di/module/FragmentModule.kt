package com.example.famousapp.famous.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elnhrawy.famous.ui.populars.PopularsAdapter
import com.example.elnhrawy.famous.ui.populars.PopularsViewModel
import com.example.famousapp.famous.data.repository.PopularRepository
import com.example.famousapp.famous.ui.base.BaseFragment
import com.example.famousapp.famous.utils.ViewModelProviderFactory
import com.example.famousapp.famous.utils.network.NetworkHelper
import com.example.famousapp.famous.utils.rx.SchedulerProvider
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
    fun providePopularsAdapter() = PopularsAdapter(fragment.lifecycle, ArrayList(),ArrayList())

}