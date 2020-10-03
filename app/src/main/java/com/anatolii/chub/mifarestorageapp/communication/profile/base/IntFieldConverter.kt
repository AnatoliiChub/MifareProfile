package com.anatolii.chub.mifarestorageapp.communication.profile.base

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.IntField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.IntField.Companion.SIZE
import java.nio.ByteBuffer

abstract class IntFieldConverter : ProfileFieldConverter<IntField>() {

    override val itemSize = SIZE

    override fun fromByte(offset: Int, content: ByteArray): IntField {
        val raw = parseRawContent(offset, content)
        val buffer = ByteBuffer.allocate(itemSize)
        buffer.put(raw, 0, raw.size)
        buffer.flip()
        return IntField(buffer.int)
    }

}