package com.anatolii.chub.nfc.communication.reading

import android.nfc.tech.MifareClassic
import com.anatolii.chub.nfc.communication.reading.impl.MifareClassicBlockReaderImpl
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*

class MifareClassicBlockReaderTest {

    companion object {
        const val BLOCK_NUMBER = 47
        val RESULT = ByteArray(16) { index -> index.toByte() }
    }

    private val reader = MifareClassicBlockReaderImpl()
    val mfc = mock(MifareClassic::class.java)

    @Test
    fun `Read block`() {
        `when`(mfc.readBlock(BLOCK_NUMBER)).thenReturn(RESULT)

        val result = reader.read(mfc, BLOCK_NUMBER)

        assertArrayEquals(RESULT, result.data)
        verify(mfc).readBlock(BLOCK_NUMBER)
    }

    @Test
    fun `Read block error`() {
        val exception = RuntimeException()
        `when`(mfc.readBlock(BLOCK_NUMBER)).thenThrow(exception)

        assertThrows<RuntimeException> {
            reader.read(mfc, BLOCK_NUMBER)
        }

        verify(mfc).readBlock(BLOCK_NUMBER)
    }
}