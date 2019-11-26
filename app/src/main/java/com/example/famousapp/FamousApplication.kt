package com.example.famousapp

import android.app.Application

class FamousApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {

    }
}