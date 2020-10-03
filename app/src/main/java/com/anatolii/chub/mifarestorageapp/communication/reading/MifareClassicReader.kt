package com.anatolii.chub.mifarestorageapp.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.mifarestorageapp.communication.model.MifarePosition
import com.anatolii.chub.mifarestorageapp.communication.model.MifareSector
import com.anatolii.chub.mifarestorageapp.log

class MifareClassicReader {

    val reader = MifareSectorReader()

    fun read(
        mfc: MifareClassic,
        position: MifarePosition
    ): List<MifareSector> {
        mfc.connect()

        val cardSize = mfc.size
        val availableSize =
            (position.endSectorIndex - position.startSectorIndex) * 4 * 16
        log("card size : $cardSize, sector count : ${mfc.sectorCount}, type : ${mfc.type}, available : $availableSize")


        val sectors = mutableListOf<MifareSector>()
        loop@ for (secNumber in position.startSectorIndex..position.endSectorIndex) {
            val sector = reader.read(mfc, secNumber)
            sectors.add(sector)
        }

        mfc.close()


        return sectors
    }


}