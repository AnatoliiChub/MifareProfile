package com.anatolii.chub.mifarestorageapp.communication.profile.base

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.LongField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.LongField.Companion.SIZE
import java.nio.ByteBuffer

abstract class LongFieldConverter: ProfileFieldConverter<LongField>() {

    override val itemSize = SIZE

    override fun fromByte(offset: Int, content: ByteArray): LongField {
        val raw = parseRawContent(offset, content)
        val buffer = ByteBuffer.allocate(SIZE)
        buffer.put(raw, 0, raw.size)
        buffer.flip()
        return LongField(buffer.long)
    }

}