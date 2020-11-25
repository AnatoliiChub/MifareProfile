package com.anatolii.chub.nfc.communication.writing

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.writing.impl.MifareClassicBlockWriterImpl
import com.anatolii.chub.nfc.model.MifareBlock
import com.anatolii.chub.nfc.log

interface MifareClassicBlockWriter {

    companion object{
        fun provide() = MifareClassicBlockWriterImpl()
    }

    fun write(mfc: MifareClassic, absBlockIndex: Int, block: MifareBlock)
}