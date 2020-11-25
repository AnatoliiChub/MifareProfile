package com.anatolii.chub.nfc.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.reading.impl.MifareClassicSectorReaderImpl
import com.anatolii.chub.nfc.model.MifareSector

interface MifareClassicSectorReader {

    companion object {
        fun provide(): MifareClassicSectorReader = MifareClassicSectorReaderImpl()
    }

    fun read(mfc: MifareClassic, sectorNumber: Int): MifareSector
}