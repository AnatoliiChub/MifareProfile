package com.anatolii.chub.profilereader.ui.nfc

import android.nfc.NfcAdapter
import android.nfc.tech.NfcA
import androidx.annotation.LayoutRes
import com.anatolii.chub.profilereader.extensions.enableForegroundDispatch
import com.anatolii.chub.profilereader.ui.base.BaseFragment
import org.koin.android.ext.android.inject

abstract class NfcFragment(@LayoutRes layout: Int) : BaseFragment(layout) {

    protected val nfc: NfcAdapter by inject()

    override fun onResume() {
        super.onResume()
        nfc.enableForegroundDispatch(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        nfc.disableForegroundDispatch(requireActivity())
    }
}