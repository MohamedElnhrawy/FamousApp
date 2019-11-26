package com.example.famousapp

import android.app.Application
import com.example.famousapp.famous.di.component.ApplicationComponent
import com.example.famousapp.famous.di.component.DaggerApplicationComponent
import com.example.famousapp.famous.di.module.ApplicationModule

class FamousApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}