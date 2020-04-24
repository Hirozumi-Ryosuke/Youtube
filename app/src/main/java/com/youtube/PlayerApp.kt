package com.youtube

import android.content.Context

open class PlayerApp : BaseApplication() {

    companion object {
        lateinit var baseNetworkComponent: BaseNetworkComponent
    }

    override fun onCreate() {
        super.onCreate()
        baseNetworkComponent = baseDaggerNetworkComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}