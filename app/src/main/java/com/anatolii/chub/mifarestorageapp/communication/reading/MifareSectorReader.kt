package com.anatolii.chub.mifarestorageapp.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.mifarestorageapp.communication.model.MifareAuthException
import com.anatolii.chub.mifarestorageapp.communication.model.MifareBlock
import com.anatolii.chub.mifarestorageapp.communication.model.MifareSector
import com.anatolii.chub.mifarestorageapp.log

class MifareSectorReader {

    private val reader = MifareBlockReader()

    fun read(
        mfc: MifareClassic,
        sectorNumber: Int,
    ): MifareSector {
        val blocks = mutableListOf<MifareBlock>()
        log("There are ${mfc.getBlockCountInSector(sectorNumber)} blocks in sector $sectorNumber")

        val authb = mfc.authenticateSectorWithKeyB(sectorNumber, MifareClassic.KEY_DEFAULT)
        log("auth b : $authb, sector $sectorNumber")
        if (authb) {
            for (blockNumber in 0..3) {

                val absBlockNumber = mfc.sectorToBlock(sectorNumber) + blockNumber

                val block = reader.read(mfc, absBlockNumber)
                blocks.add(block)
            }
        } else {
            throw MifareAuthException("failed authentication in sector $sectorNumber")
        }
        return MifareSector(sectorNumber, blocks)
    }
}