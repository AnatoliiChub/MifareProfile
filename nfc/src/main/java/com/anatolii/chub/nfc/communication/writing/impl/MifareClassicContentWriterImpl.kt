package com.anatolii.chub.nfc.communication.writing.impl

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.Converter
import com.anatolii.chub.nfc.communication.writing.MifareClassicContentWriter
import com.anatolii.chub.nfc.communication.writing.MifareClassicContentWriter.Companion.CONTENT_SECTOR_SIZE
import com.anatolii.chub.nfc.communication.writing.MifareClassicContentWriter.Companion.FIRST_CONTENT_SECTOR_NUMBER
import com.anatolii.chub.nfc.communication.writing.MifareClassicContentWriter.Companion.TOTAL_CONTENT_SIZE
import com.anatolii.chub.nfc.communication.writing.MifareClassicWriter
import com.anatolii.chub.nfc.log
import com.anatolii.chub.nfc.model.DEFAULT_KEY_CONFIG
import com.anatolii.chub.nfc.model.MifareSector
import com.anatolii.chub.nfc.model.SectorData
import io.reactivex.rxjava3.core.Completable
import kotlin.math.min

class MifareClassicContentWriterImpl<T>(
    private val converter: Converter<T>,
    private val writer: MifareClassicWriter = MifareClassicWriter.provide()
) : MifareClassicContentWriter<T> {


    override fun write(mfc: MifareClassic, data: T): Completable = Completable.fromAction {
        val content = converter.toBytes(data)
        if (content.size > TOTAL_CONTENT_SIZE) {
            throw IndexOutOfBoundsException("Content size is so long: ${content.size}. Should not be greater than $TOTAL_CONTENT_SIZE")
        }
        val sectorsNumber = content.size / CONTENT_SECTOR_SIZE
        val sectors =
            IntRange(FIRST_CONTENT_SECTOR_NUMBER, FIRST_CONTENT_SECTOR_NUMBER + sectorsNumber).map {
                val startPos = (it - FIRST_CONTENT_SECTOR_NUMBER) * CONTENT_SECTOR_SIZE
                val endPosExclusive = min(
                    ((it - FIRST_CONTENT_SECTOR_NUMBER + 1) * CONTENT_SECTOR_SIZE),
                    content.size
                )
                log("content copied from $startPos to $endPosExclusive")
                MifareSector(
                    it,
                    SectorData(content.copyOfRange(startPos, endPosExclusive)),
                    DEFAULT_KEY_CONFIG
                )
            }

        writer.write(mfc, sectors)
    }
}