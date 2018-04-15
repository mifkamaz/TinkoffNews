package ru.mifkamaz.tinkoffnews.utlis

interface Converter<I, out O> {

    fun convert(i: I): O

    fun convertSafe(i: I?): O? = if (i == null) null else convert(i)

    fun convertList(i: List<I>): List<O> = ArrayList<O>().apply { i.forEach { add(convert(it)) } }

}