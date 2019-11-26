package com.example.famousapp.famous.di.component

import com.example.famousapp.famous.di.ActivityScope
import com.example.famousapp.famous.di.module.ActivityModule
import com.example.famousapp.famous.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: SplashActivity)

}