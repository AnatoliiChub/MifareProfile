package com.anatolii.chub.nfc.communication.writing

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.writing.impl.MifareClassicBlockWriterImpl
import com.anatolii.chub.nfc.communication.writing.impl.MifareClassicSectorWriterImpl
import com.anatolii.chub.nfc.model.MifareAuthException
import com.anatolii.chub.nfc.model.MifareSector
import com.anatolii.chub.nfc.log

interface MifareClassicSectorWriter {

    companion object{
        fun provide() = MifareClassicSectorWriterImpl(MifareClassicBlockWriter.provide())
    }

    fun write(mfc: MifareClassic, sector: MifareSector)

}