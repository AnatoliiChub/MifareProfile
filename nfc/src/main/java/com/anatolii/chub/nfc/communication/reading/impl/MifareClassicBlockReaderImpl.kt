package com.anatolii.chub.nfc.communication.reading.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.reading.MifareClassicBlockReader
import com.anatolii.chub.nfc.model.MifareBlock
import java.util.logging.Logger

internal class MifareClassicBlockReaderImpl : MifareClassicBlockReader {

    companion object {
        val LOGGER: Logger = Logger.getLogger(this::class.java.declaringClass.simpleName)
    }

    override fun read(mfc: MifareClassic, absBlockNumber: Int): MifareBlock {
        LOGGER.info("reading block, abs number : $absBlockNumber")
        val chunk: ByteArray = mfc.readBlock(absBlockNumber)

        LOGGER.info("read bytes :  + ${chunk.contentToString()}")

        return MifareBlock(chunk)
    }
}