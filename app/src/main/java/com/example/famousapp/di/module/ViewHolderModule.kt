package com.example.famousapp.famous.di.module

import androidx.lifecycle.LifecycleRegistry
import com.example.famousapp.famous.di.ViewModelScope
import com.example.famousapp.famous.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}