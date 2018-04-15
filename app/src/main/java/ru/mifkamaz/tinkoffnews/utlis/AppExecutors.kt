package ru.mifkamaz.tinkoffnews.utlis

import android.support.annotation.VisibleForTesting
import kotlinx.coroutines.experimental.ThreadPoolDispatcher
import kotlinx.coroutines.experimental.android.HandlerContext
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.newSingleThreadContext

class AppExecutors @VisibleForTesting internal constructor(
        private val diskIO: ThreadPoolDispatcher,
        private val networkIO: ThreadPoolDispatcher,
        private val mainThread: HandlerContext) {

    constructor() : this(
            newSingleThreadContext("DiskIO Thread"),
            newFixedThreadPoolContext(THREAD_COUNT, "Network Thread"),
            UI)

    fun diskIO(): ThreadPoolDispatcher = diskIO
    fun networkIO(): ThreadPoolDispatcher = networkIO
    fun mainThread(): HandlerContext = mainThread

    companion object {

        private const val THREAD_COUNT = 3
    }
}
