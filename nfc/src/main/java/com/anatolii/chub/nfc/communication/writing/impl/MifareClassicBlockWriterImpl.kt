package com.anatolii.chub.nfc.communication.writing.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.writing.MifareClassicBlockWriter
import com.anatolii.chub.nfc.model.MifareBlock
import com.anatolii.chub.nfc.log

class MifareClassicBlockWriterImpl : MifareClassicBlockWriter {

    override fun write(mfc: MifareClassic, absBlockIndex: Int, block: MifareBlock) {
        log("write block : $block")
        mfc.writeBlock(absBlockIndex, block.data)
        log("blog' written successfully")
    }
}