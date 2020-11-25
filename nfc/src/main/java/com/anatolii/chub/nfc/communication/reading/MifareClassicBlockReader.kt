package com.anatolii.chub.nfc.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.reading.impl.MifareClassicBlockReaderImpl
import com.anatolii.chub.nfc.model.MifareBlock

interface MifareClassicBlockReader {

    companion object {
        fun provide(): MifareClassicBlockReader = MifareClassicBlockReaderImpl()
    }

    fun read(mfc: MifareClassic, absBlockNumber: Int): MifareBlock
}