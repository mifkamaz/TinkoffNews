package ru.mifkamaz.tinkoffnews

import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout

class DefaultActivityUi : AnkoComponent<AppCompatActivity> {

    companion object {
        const val FRAME_ID = 1;
    }

    override fun createView(ui: AnkoContext<AppCompatActivity>) = with(ui) {
        frameLayout {
            id = FRAME_ID
        }
    }
}