package com.youtube.player.di.modules

import android.app.Application

@Module
class BaseAppModule(private var mApplication: Application) {

    @Provides
    @BaseScope
    fun provideApplication() = mApplication
}