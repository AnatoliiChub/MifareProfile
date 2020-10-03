package com.anatolii.chub.mifarestorageapp.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.mifarestorageapp.communication.model.MifarePosition
import io.reactivex.rxjava3.core.Single

class MifareClassicContentReader {

    val reader = MifareClassicReader()

    fun read(mfc: MifareClassic): Single<ByteArray> = Single.fromCallable {
        var content = ByteArray(0)

        reader.read(mfc, MifarePosition(2, 15))
            .map { sector ->
                sector.also {
                    it.blocks.removeLast()
                }
            }.toList()
            .forEach { sector ->
                sector.blocks.forEach {
                    content += it.data
                }
            }

        content
    }
}