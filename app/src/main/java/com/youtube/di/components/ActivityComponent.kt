package com.youtube.di.components

import com.youtube.dashboard.DashboardViewModel

@AppScope
@Component(dependencies = [BaseNetworkComponent::class], modules = [AppComponent::class])
interface ActivityComponent {
    fun inject(dashboardViewModel: DashboardViewModel)
}