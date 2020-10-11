package com.anatolii.chub.mifarestorageapp

fun ByteArray.insert(position: Int, raw : ByteArray) {
    System.arraycopy(raw, 0, this, position, raw.size)
}