package com.anatolii.chub.profilereader.di

import android.nfc.NfcAdapter
import com.anatolii.chub.profilereader.ui.nfc.NfcSharedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { NfcSharedViewModel() }

    single<NfcAdapter> { NfcAdapter.getDefaultAdapter(get())}
}