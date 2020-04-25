package com.youtube.player.di.components

import player.dashboard.DashboardViewModel
import player.di.modules.AppComponent
import player.di.scopes.AppScope

@AppScope
@Component(dependencies = [BaseNetworkComponent::class], modules = [AppComponent::class])
interface ActivityComponent {
    fun inject(dashboardViewModel: DashboardViewModel)
}