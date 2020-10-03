package com.anatolii.chub.mifarestorageapp.communication.writing

import android.nfc.tech.MifareClassic
import com.anatolii.chub.mifarestorageapp.communication.model.DEFAULT_KEY_CONFIG
import com.anatolii.chub.mifarestorageapp.communication.model.MifareSector
import com.anatolii.chub.mifarestorageapp.communication.model.SectorData
import com.anatolii.chub.mifarestorageapp.log
import kotlin.math.min

class MifareClassicContentWriter {

    companion object {
        const val CONTENT_SECTOR_SIZE = 48
        const val SECTORS_FOR_CONTENT = 14
        const val FIRST_CONTENT_SECTOR_NUMBER = 2
        const val TOTAL_CONTENT_SIZE = CONTENT_SECTOR_SIZE * SECTORS_FOR_CONTENT
    }

    val writer = MifareClassicWriter()

    fun write(mfc: MifareClassic, content: ByteArray) {
        if (content.size > TOTAL_CONTENT_SIZE) {
            throw IndexOutOfBoundsException("Content size is so long: ${content.size}. Should not be greater than $TOTAL_CONTENT_SIZE")
        }
        val sectorsNumber = content.size / CONTENT_SECTOR_SIZE
        val sectors = IntRange(FIRST_CONTENT_SECTOR_NUMBER, FIRST_CONTENT_SECTOR_NUMBER + sectorsNumber).map {
            val startPos = (it - FIRST_CONTENT_SECTOR_NUMBER) * CONTENT_SECTOR_SIZE
            val endPosExclusive = min(((it - FIRST_CONTENT_SECTOR_NUMBER + 1) * CONTENT_SECTOR_SIZE) , content.size)
            log("content copied from $startPos to $endPosExclusive")
            MifareSector(
                it,
                SectorData(content.copyOfRange(startPos, endPosExclusive)),
                DEFAULT_KEY_CONFIG
            )
        }

        writer.write(mfc, sectors)

    }
}