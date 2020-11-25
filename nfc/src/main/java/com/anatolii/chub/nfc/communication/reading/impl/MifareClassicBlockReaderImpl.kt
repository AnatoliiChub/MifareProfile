package com.anatolii.chub.nfc.communication.reading.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.reading.MifareClassicBlockReader
import com.anatolii.chub.nfc.log
import com.anatolii.chub.nfc.model.MifareBlock

internal class MifareClassicBlockReaderImpl : MifareClassicBlockReader {

    override fun read(mfc: MifareClassic, absBlockNumber: Int): MifareBlock {
        log("reading block, abs number : $absBlockNumber")
        val chunk: ByteArray = mfc.readBlock(absBlockNumber)

        log("read bytes :  + ${chunk.contentToString()}")

        return MifareBlock(chunk)
    }
}