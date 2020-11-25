package com.anatolii.chub.nfc

import android.util.Log
import java.util.logging.Level.*
import java.util.logging.Logger

val TAG = "Global TAG"

fun log(string: String) {

    Log.d(TAG, string)
}

fun Logger.warn(string: String) {
    this.log(WARNING, string)
}

fun Logger.error(string: String) {
    this.log(SEVERE, string)
}