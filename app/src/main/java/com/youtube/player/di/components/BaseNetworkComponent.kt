package com.youtube.player.di.components

import android.app.Application
import player.base.api.ApiServices

@BaseScope
@Component(modules = [BaseAppModule::class, BaseNetworkModule::class])
interface BaseNetworkComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter
    val apiService: ApiServices
    val application: Application
    val retrofit: Retrofit
}