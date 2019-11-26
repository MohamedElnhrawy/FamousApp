package com.example.famousapp.ui.main

import android.os.Bundle
import com.example.famousapp.R
import com.example.famousapp.famous.di.component.ActivityComponent
import com.example.famousapp.famous.ui.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {
    override fun setupView(savedInstanceState: Bundle?) {

    }

    companion object {
        const val TAG = "MainActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }


}