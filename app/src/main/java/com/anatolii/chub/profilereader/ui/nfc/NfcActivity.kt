package com.anatolii.chub.profilereader.ui.nfc

import android.content.Intent
import android.nfc.tech.MifareClassic
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anatolii.chub.profilereader.R
import com.anatolii.chub.profilereader.extensions.processNfcIntent

class NfcActivity : AppCompatActivity() {

    private val model: NfcSharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        processNfcIntent(intent) {
            if (it?.techList?.contains(MifareClassic::class.java.name) == true) {
                val mfc = MifareClassic.get(it)
                model.readProfile(mfc)
            }
        }
    }

}
