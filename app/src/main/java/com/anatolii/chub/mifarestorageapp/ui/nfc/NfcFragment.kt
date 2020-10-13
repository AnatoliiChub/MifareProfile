package com.anatolii.chub.mifarestorageapp.ui.nfc

import android.content.Context
import android.nfc.NfcAdapter
import androidx.annotation.LayoutRes
import com.anatolii.chub.mifarestorageapp.extensions.enableForegroundDispatch
import com.anatolii.chub.mifarestorageapp.log
import com.anatolii.chub.mifarestorageapp.ui.base.BaseFragment

abstract class NfcFragment(@LayoutRes layout: Int) : BaseFragment(layout) {

    protected lateinit var nfc: NfcAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        nfc = NfcAdapter.getDefaultAdapter(requireContext())
    }

    override fun onResume() {
        super.onResume()
        log("resume")
        nfc.enableForegroundDispatch(requireActivity())
    }

    override fun onPause() {
        super.onPause()
        log("pause")
        nfc.disableForegroundDispatch(requireActivity())
    }
}