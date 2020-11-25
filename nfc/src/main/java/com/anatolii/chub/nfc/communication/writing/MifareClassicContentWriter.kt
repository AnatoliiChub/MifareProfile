package com.anatolii.chub.nfc.communication.writing

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.Converter
import com.anatolii.chub.nfc.communication.writing.impl.MifareClassicContentWriterImpl
import io.reactivex.rxjava3.core.Completable

interface MifareClassicContentWriter<T> {

    companion object {
        const val CONTENT_SECTOR_SIZE = 48
        const val SECTORS_FOR_CONTENT = 14
        const val FIRST_CONTENT_SECTOR_NUMBER = 2
        const val TOTAL_CONTENT_SIZE = CONTENT_SECTOR_SIZE * SECTORS_FOR_CONTENT

        fun <T> provide(converter: Converter<T>) = MifareClassicContentWriterImpl(converter)
    }

    fun write(mfc: MifareClassic, data: T): Completable
}