package com.youtube.player.base.repo

import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.logging.Handler

data class AppExecutors
@JvmOverloads
constructor(val diskIO: Executor = Executors.newSingleThreadExecutor(),
            val networkIO: Executor = Executors.newFixedThreadPool(3),
            val mainThread: Executor = MainThreadExecutor()
)

private class MainThreadExecutor : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        mainThreadHandler.post(command)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
