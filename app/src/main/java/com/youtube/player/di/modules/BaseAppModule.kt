package com.youtube.player.di.modules

import android.app.Application
import com.youtube.player.di.scopes.BaseScope

@Module
class BaseAppModule(private var mApplication: Application) {

    @Provides
    @BaseScope
    fun provideApplication() = mApplication
}