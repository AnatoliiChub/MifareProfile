package com.anatolii.chub.nfc.communication.writing

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.writing.impl.MifareClassicWriterImpl
import com.anatolii.chub.nfc.model.MifareSector

interface MifareClassicWriter {

    companion object {
        const val MAX_SECTOR_INDEX = 15

        fun provide(): MifareClassicWriter =
            MifareClassicWriterImpl(MifareClassicSectorWriter.provide())
    }

    fun write(mfc: MifareClassic, sectors: List<MifareSector>)
}