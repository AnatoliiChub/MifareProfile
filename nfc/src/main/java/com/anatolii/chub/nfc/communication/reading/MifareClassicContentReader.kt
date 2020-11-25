package com.anatolii.chub.nfc.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.Converter
import com.anatolii.chub.nfc.communication.reading.impl.MifareClassicContentReaderImpl
import io.reactivex.rxjava3.core.Single

interface MifareClassicContentReader<T> {

    companion object {
        fun <T> provide(converter: Converter<T>): MifareClassicContentReader<T> =
            MifareClassicContentReaderImpl(converter)
    }

    /**
     * Read all the content from the card.
     */
    fun read(mfc: MifareClassic): Single<T>
}