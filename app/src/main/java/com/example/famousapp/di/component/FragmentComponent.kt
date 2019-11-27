package com.example.famousapp.famous.di.component

import com.example.famousapp.famous.di.FragmentScope
import com.example.famousapp.famous.di.module.FragmentModule
import com.example.famousapp.ui.popularDetails.PopularDetailsFragment
import com.example.famousapp.ui.populars.PopularsFragment
import com.example.famousapp.ui.previewImage.ImagePreviewFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(fragment: PopularsFragment)
    fun inject(fragment: PopularDetailsFragment)
    fun inject(fragment: ImagePreviewFragment)

}