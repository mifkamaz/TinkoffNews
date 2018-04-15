package ru.mifkamaz.tinkoffnews.utlis

interface UpdateRule<T> {

    fun isNeedUpdate(old: T, new: T) : Boolean

}