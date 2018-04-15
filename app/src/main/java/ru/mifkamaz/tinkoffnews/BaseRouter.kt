package ru.mifkamaz.tinkoffnews

import android.content.Context
import android.content.Intent

abstract class BaseRouter(val context: Context) {

    fun start(intent: Intent) {
        context.startActivity(intent)
    }

}