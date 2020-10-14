package com.anatolii.chub.profilereader.extensions

import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.util.Log
import androidx.annotation.MainThread
import com.anatolii.chub.profilereader.TAG
import com.anatolii.chub.profilereader.log

@MainThread
fun NfcAdapter.enableForegroundDispatch(activity: Activity) {
    val intent = Intent(activity, activity.javaClass).apply {
        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(activity, 0, intent, 0)
    val techListsArray = arrayOf(arrayOf(MifareClassic::class.java.name))
    this.enableForegroundDispatch(activity, pendingIntent, null, techListsArray)
}

fun processNfcIntent(intent: Intent, callback: (Tag?) -> Unit) {
    Log.d(
        TAG,
        "new intent : action=${intent.action}, data=${intent.data}, intent=$intent"
    )
    if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action ||
        NfcAdapter.ACTION_TAG_DISCOVERED == intent.action ||
        NfcAdapter.ACTION_TECH_DISCOVERED == intent.action
    ) {
        val tag = (intent.getParcelableExtra(NfcAdapter.EXTRA_TAG) as Tag?)

        callback.invoke(tag)

        intent.extras?.keySet()?.onEach { log(it) }
    }
}