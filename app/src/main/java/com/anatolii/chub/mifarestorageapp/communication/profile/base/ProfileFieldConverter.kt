package com.anatolii.chub.mifarestorageapp.communication.profile.base

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.ProfileField
import kotlin.math.min

abstract class ProfileFieldConverter<T : ProfileField> {

    abstract fun fromByte(offset: Int, content: ByteArray): T

    protected fun parseRawContent(offset: Int, content: ByteArray) =
        content.copyOfRange(offset, offset + itemSize)

    open fun toByte(offset: Int, item: ProfileField, content: ByteArray) {
        item.raw.copyInto(content, offset, 0, min(itemSize, item.raw.size))
    }

    abstract val itemSize: Int
}