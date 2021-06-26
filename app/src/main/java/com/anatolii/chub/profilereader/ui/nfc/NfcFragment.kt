package com.anatolii.chub.profilereader.ui.nfc

import android.nfc.NfcAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.anatolii.chub.profilereader.extensions.enableForegroundDispatch
import com.anatolii.chub.profilereader.ui.base.BaseFragment
import org.koin.android.ext.android.inject

abstract class NfcFragment<T : ViewDataBinding>(@LayoutRes layout: Int) : BaseFragment<ViewDataBinding>(layout) {

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