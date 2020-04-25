package com.youtube.player.dashboard

/**
 * ViewsEvents sealed class used as enum for defining different events for views
 */
sealed class ViewsEvents {
    class CLICKED : ViewsEvents()
    class SHOW : ViewsEvents()
    class HIDE : ViewsEvents()
    class LONGPRESS : ViewsEvents()
    class NONE : ViewsEvents()
}