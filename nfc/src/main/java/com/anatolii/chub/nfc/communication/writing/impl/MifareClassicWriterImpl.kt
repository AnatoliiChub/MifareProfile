package com.anatolii.chub.nfc.communication.writing.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.writing.MifareClassicWriter
import com.anatolii.chub.nfc.communication.writing.MifareClassicWriter.Companion.MAX_SECTOR_INDEX
import com.anatolii.chub.nfc.log
import com.anatolii.chub.nfc.model.MifareSector
import com.anatolii.chub.nfc.model.NotEnoughSizeException

class MifareClassicWriterImpl(private val writer: MifareClassicSectorWriterImpl) :
    MifareClassicWriter {

    override fun write(mfc: MifareClassic, sectors: List<MifareSector>) {
        mfc.connect()

        val cardSize = mfc.size

        val lastSectorIndex = sectors.last().sectorNumber
        if (lastSectorIndex > MAX_SECTOR_INDEX) {
            throw IndexOutOfBoundsException(
                "last sector index = $lastSectorIndex" +
                        " greater than $MAX_SECTOR_INDEX"
            )
        }

        val dataSize = sectors.size * 4 * 16
        val availableSize = cardSize - (sectors.first().sectorNumber * 4 * 16)
        if (availableSize < dataSize) {
            throw NotEnoughSizeException(
                "Not enough size to write data. Data size : " +
                        "${dataSize}, available size : $availableSize," +
                        " started sector index : ${sectors.first().sectorNumber}"
            )
        }
        log(
            "card size : $cardSize, " +
                    "data size : ${dataSize}, " +
                    "available size : $availableSize, " +
                    "sector count : ${mfc.sectorCount}, " +
                    "type : ${mfc.type}"
        )

        sectors.forEach { writer.write(mfc, it) }

        mfc.close()


    }


}