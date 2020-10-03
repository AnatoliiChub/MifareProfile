package com.anatolii.chub.mifarestorageapp.communication.model


data class MifareBlock(val data: ByteArray = ByteArray(MIFARE_BLOCK_SIZE)) {

    companion object {
        const val MIFARE_BLOCK_SIZE = 16
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MifareBlock

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

}