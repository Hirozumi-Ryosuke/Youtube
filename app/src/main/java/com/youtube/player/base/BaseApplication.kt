package com.youtube.player.base

import android.app.Application
import androidx.multidex.BuildConfig
import com.youtube.player.di.components.BaseNetworkComponent
import com.youtube.player.di.modules.BaseAppModule
import com.youtube.player.timber.Timber


open class BaseApplication : Application() {

    protected lateinit var baseDaggerNetworkComponent: BaseNetworkComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        baseDaggerNetworkComponent =
            DaggerBaseNetworkComponent.builder()
                .baseAppModule(BaseAppModule(this))
                .build()
    }
}