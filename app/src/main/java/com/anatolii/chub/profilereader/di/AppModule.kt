package com.anatolii.chub.profilereader.di

import android.nfc.NfcAdapter
import com.anatolii.chub.nfc.communication.Converter
import com.anatolii.chub.nfc.communication.reading.MifareClassicContentReader
import com.anatolii.chub.nfc.communication.writing.MifareClassicContentWriter
import com.anatolii.chub.profilereader.UserConverter
import com.anatolii.chub.profilereader.model.User
import com.anatolii.chub.profilereader.ui.nfc.NfcSharedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

@OptIn(KoinApiExtension::class)
val appModule = module {
    viewModel { NfcSharedViewModel() }

    single<NfcAdapter> { NfcAdapter.getDefaultAdapter(get()) }

    single<MifareClassicContentReader<User>> { MifareClassicContentReader.provide(get()) }

    single<MifareClassicContentWriter<User>> { MifareClassicContentWriter.provide(get()) }

    single<Converter<User>> { UserConverter() }
}