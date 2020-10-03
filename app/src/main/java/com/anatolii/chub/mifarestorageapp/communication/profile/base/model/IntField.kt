package com.anatolii.chub.mifarestorageapp.communication.profile.base.model

import java.nio.ByteBuffer

open class IntField(val value: Int) : ProfileField {

    companion object {
       const val SIZE = Int.SIZE_BYTES
    }

    override val raw = toByteArray()

    private fun toByteArray(): ByteArray {
        val buffer = ByteBuffer.allocate(SIZE)
        buffer.putInt(value)
        return buffer.array()
    }

    override fun toString() = "${this::class.java} [ $value ]."

}