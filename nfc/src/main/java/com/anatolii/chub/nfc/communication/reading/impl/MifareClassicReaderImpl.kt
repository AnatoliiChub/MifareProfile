package com.anatolii.chub.nfc.communication.reading.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.reading.MifareClassicReader
import com.anatolii.chub.nfc.communication.reading.MifareClassicSectorReader
import com.anatolii.chub.nfc.log
import com.anatolii.chub.nfc.model.MifarePosition
import com.anatolii.chub.nfc.model.MifareSector

internal class MifareClassicReaderImpl(
    private val reader: MifareClassicSectorReader = MifareClassicSectorReaderImpl()
) : MifareClassicReader {


    override fun read(
        mfc: MifareClassic,
        position: MifarePosition
    ): List<MifareSector> {
        mfc.connect()

        val cardSize = mfc.size
        val availableSize =
            (position.endSectorIndex - position.startSectorIndex) * 4 * 16
        log("card size : $cardSize, sector count : ${mfc.sectorCount}, type : ${mfc.type}, available : $availableSize")

        val sectors = mutableListOf<MifareSector>()

        mfc.use {
            loop@ for (secNumber in position.startSectorIndex..position.endSectorIndex) {
                val sector = reader.read(it, secNumber)
                sectors.add(sector)
            }
        }


        return sectors
    }


}