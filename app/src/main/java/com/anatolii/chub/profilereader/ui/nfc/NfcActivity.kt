package com.anatolii.chub.profilereader.ui.nfc

import android.content.Intent
import android.nfc.tech.MifareClassic
import android.os.Bundle
import com.anatolii.chub.profilereader.R
import com.anatolii.chub.profilereader.extensions.processNfcIntent
import com.anatolii.chub.profilereader.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class NfcActivity : BaseActivity() {

    private val model: NfcSharedViewModel by viewModel()

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
