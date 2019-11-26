package com.example.famousapp.famous.di.component

import com.example.famousapp.famous.di.ViewModelScope
import com.example.famousapp.famous.di.module.ViewHolderModule
import com.example.famousapp.ui.popularDetails.images.ImageItemViewHolder
import com.example.famousapp.ui.populars.PopularItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {
    fun inject(viewHolder: PopularItemViewHolder)
    fun inject(viewHolder: ImageItemViewHolder)

}