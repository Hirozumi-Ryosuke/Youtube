package com.youtube.player.animations

sealed class Direction {
    class LEFT : Direction()
    class RIGHT : Direction()
    class UP : Direction()
    class DOWN : Direction()
    class NONE : Direction()
}