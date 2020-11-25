package com.anatolii.chub.nfc.communication.reading.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.reading.MifareClassicBlockReader
import com.anatolii.chub.nfc.communication.reading.MifareClassicSectorReader
import com.anatolii.chub.nfc.log
import com.anatolii.chub.nfc.model.DEFAULT_KEY_CONFIG
import com.anatolii.chub.nfc.model.MifareAuthException
import com.anatolii.chub.nfc.model.MifareBlock
import com.anatolii.chub.nfc.model.MifareSector

internal class MifareClassicSectorReaderImpl(private val reader: MifareClassicBlockReader = MifareClassicBlockReaderImpl()) :
    MifareClassicSectorReader {


    override fun read(
        mfc: MifareClassic,
        sectorNumber: Int,
    ): MifareSector {
        val blocks = mutableListOf<MifareBlock>()
        log("There are ${mfc.getBlockCountInSector(sectorNumber)} blocks in sector $sectorNumber")

        val authb = mfc.authenticateSectorWithKeyB(sectorNumber, DEFAULT_KEY_CONFIG.keyB.toByte())
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