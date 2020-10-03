package com.anatolii.chub.mifarestorageapp.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.mifarestorageapp.communication.model.MifarePosition
import com.anatolii.chub.mifarestorageapp.log
import java.nio.charset.StandardCharsets.UTF_8

class MifareClassicContentReader {

    val reader = MifareClassicReader()

    fun read(mfc: MifareClassic): ByteArray {
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

        return content
    }
}