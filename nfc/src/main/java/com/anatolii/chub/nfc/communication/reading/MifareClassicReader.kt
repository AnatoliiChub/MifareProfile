package com.anatolii.chub.nfc.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.reading.impl.MifareClassicReaderImpl
import com.anatolii.chub.nfc.model.MifarePosition
import com.anatolii.chub.nfc.model.MifareSector

interface MifareClassicReader {

    companion object {
        fun provide(): MifareClassicReader = MifareClassicReaderImpl()
    }

    fun read(mfc: MifareClassic, position: MifarePosition): List<MifareSector>

}