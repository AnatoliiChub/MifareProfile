package com.anatolii.chub.profilereader.communication.writing

import android.nfc.tech.MifareClassic
import com.anatolii.chub.profilereader.communication.model.MifareAuthException
import com.anatolii.chub.profilereader.communication.model.MifareSector
import com.anatolii.chub.profilereader.log

class MifareSectorWriter {

    fun write(
        mfc: MifareClassic,
        sector: MifareSector
    ) = with(sector) {
        log("There are ${mfc.getBlockCountInSector(sectorNumber)} blocks in sector $sectorNumber")
        val authb = mfc.authenticateSectorWithKeyB(sectorNumber, MifareClassic.KEY_DEFAULT)
        log("auth b : $authb, sector $sectorNumber")
        if (authb) {
            blocks.forEachIndexed { currentBlockNumber, block ->
                log("writing block $currentBlockNumber in sector $sectorNumber : $block")
                try {
                    val writer = MifareBlockWriter()
                    writer.write(mfc, mfc.sectorToBlock(sectorNumber) + currentBlockNumber, block)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            throw MifareAuthException("failed authentication in sector $sectorNumber")
        }
    }

}