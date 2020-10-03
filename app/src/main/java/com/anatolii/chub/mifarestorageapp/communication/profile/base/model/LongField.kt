package com.anatolii.chub.mifarestorageapp.communication.profile.base.model

import java.nio.ByteBuffer

open class LongField(val value: Long) : ProfileField {

    companion object {
        const val SIZE = Long.SIZE_BYTES
    }

    override val raw = toByteArray()

    private fun toByteArray(): ByteArray {
        val buffer = ByteBuffer.allocate(SIZE)
        buffer.putLong(value)
        return buffer.array()
    }

    override fun toString() = "${this::class.java} [ $value ]."
}