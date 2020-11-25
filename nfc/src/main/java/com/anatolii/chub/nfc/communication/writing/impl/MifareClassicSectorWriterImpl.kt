package com.anatolii.chub.nfc.communication.writing.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.writing.MifareClassicBlockWriter
import com.anatolii.chub.nfc.log
import com.anatolii.chub.nfc.model.MifareAuthException
import com.anatolii.chub.nfc.model.MifareSector

class MifareClassicSectorWriterImpl(private val writer: MifareClassicBlockWriter) {

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