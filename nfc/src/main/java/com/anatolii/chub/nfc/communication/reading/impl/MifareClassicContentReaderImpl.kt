package com.anatolii.chub.nfc.communication.reading.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.Converter
import com.anatolii.chub.nfc.communication.reading.MifareClassicContentReader
import com.anatolii.chub.nfc.model.MifarePosition
import io.reactivex.rxjava3.core.Single

internal class MifareClassicContentReaderImpl<T>(private val converter: Converter<T>) :
    MifareClassicContentReader<T> {

    private val reader = MifareClassicReaderImpl()

    override fun read(mfc: MifareClassic): Single<T> = Single.fromCallable {
        var content = ByteArray(0)

        reader.read(mfc, MifarePosition(2, 15))
            .map { sector ->
                sector.blocks.removeLast()
                sector.blocks.forEach {
                    content += it.data
                }
                sector
            }.toList()
        converter.populateFromBytes(content)
    }
}