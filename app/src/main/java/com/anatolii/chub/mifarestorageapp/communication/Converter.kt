package com.anatolii.chub.mifarestorageapp.communication

interface Converter<T> {
    fun populateFromBytes(array : ByteArray) : T

    fun toBytes(item : T) : ByteArray

    val size : Int

}