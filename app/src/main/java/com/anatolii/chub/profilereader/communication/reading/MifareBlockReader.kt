package com.anatolii.chub.profilereader.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.profilereader.communication.model.MifareBlock
import com.anatolii.chub.profilereader.log

class MifareBlockReader {

    fun read(mfc: MifareClassic, absBlockNumber: Int): MifareBlock {
        log("reading block, abs number : $absBlockNumber")
        val chunk: ByteArray = mfc.readBlock(absBlockNumber)

        log("read bytes :  + ${chunk.contentToString()}")

        return MifareBlock(chunk)
    }
}