package com.youtube.player

import com.youtube.player.base.BaseApplication
import com.youtube.player.di.components.BaseNetworkComponent

class PlayerApp : BaseApplication() {

    companion object {
        lateinit var baseNetworkComponent: BaseNetworkComponent
    }

    override fun onCreate() {
        super.onCreate()
        baseNetworkComponent = baseDaggerNetworkComponent
    }
}