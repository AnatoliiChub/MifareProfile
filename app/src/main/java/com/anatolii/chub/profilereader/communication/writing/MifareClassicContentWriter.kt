package com.anatolii.chub.profilereader.communication.writing

import android.nfc.tech.MifareClassic
import com.anatolii.chub.profilereader.communication.Converter
import com.anatolii.chub.profilereader.communication.model.DEFAULT_KEY_CONFIG
import com.anatolii.chub.profilereader.communication.model.MifareSector
import com.anatolii.chub.profilereader.communication.model.SectorData
import com.anatolii.chub.profilereader.log
import io.reactivex.rxjava3.core.Completable
import kotlin.math.min

class MifareClassicContentWriter<T>(private val converter : Converter<T>) {

    companion object {
        const val CONTENT_SECTOR_SIZE = 48
        const val SECTORS_FOR_CONTENT = 14
        const val FIRST_CONTENT_SECTOR_NUMBER = 2
        const val TOTAL_CONTENT_SIZE = CONTENT_SECTOR_SIZE * SECTORS_FOR_CONTENT
    }

    private val writer = MifareClassicWriter()

    fun write(mfc: MifareClassic, data: T): Completable = Completable.fromAction {
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