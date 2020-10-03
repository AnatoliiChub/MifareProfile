package com.anatolii.chub.mifarestorageapp.communication.writing

import android.nfc.tech.MifareClassic
import com.anatolii.chub.mifarestorageapp.communication.model.MifareBlock
import com.anatolii.chub.mifarestorageapp.log

class MifareBlockWriter {

    fun write(mfc: MifareClassic, absBlockIndex: Int, block: MifareBlock) {
        log("write block : $block")
        mfc.writeBlock(absBlockIndex, block.data)
        log("blog' written successfully")
    }
}