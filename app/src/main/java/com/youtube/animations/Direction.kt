package com.youtube.animations

sealed class Direction {
    class LEFT : Direction()
    class RIGHT : Direction()
    class UP : Direction()
    class DOWN : Direction()
    class NONE : Direction()
}