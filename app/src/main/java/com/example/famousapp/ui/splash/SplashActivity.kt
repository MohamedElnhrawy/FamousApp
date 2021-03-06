package com.example.famousapp.famous.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.example.famousapp.famous.di.component.ActivityComponent
import com.example.famousapp.famous.ui.base.BaseActivity
import com.example.famousapp.R
import com.example.famousapp.ui.main.MainActivity
import com.example.famousapp.utils.common.Event


class SplashActivity : BaseActivity<SplashViewModel>() {
    private val splasTime = 2000L
    private lateinit var myHandeler : Handler
    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)

    }

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_splash



    override fun setupView(savedInstanceState: Bundle?) {
        myHandeler = Handler()
    }

    override fun setupObservers() {
        // Event is used by the view model to tell the activity to launch another activity
        // view model also provided the Bundle in the event that is needed for the Activity
        viewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                myHandeler.postDelayed({
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                },splasTime)
            }
        })
    }
}