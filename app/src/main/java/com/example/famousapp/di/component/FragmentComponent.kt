package com.example.famousapp.famous.di.component

import com.example.elnhrawy.famous.ui.populars.PopularsFragment
import com.example.famousapp.famous.di.FragmentScope
import com.example.famousapp.famous.di.module.FragmentModule
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {
    fun inject(fragment: PopularsFragment)

}