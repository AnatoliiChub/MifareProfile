package com.anatolii.chub.profilereader.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.profilereader.communication.model.MifarePosition
import com.anatolii.chub.profilereader.communication.model.MifareSector
import com.anatolii.chub.profilereader.log

class MifareClassicReader {

    private val reader = MifareSectorReader()

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