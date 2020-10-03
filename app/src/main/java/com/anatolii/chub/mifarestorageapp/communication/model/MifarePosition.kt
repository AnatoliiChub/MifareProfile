package com.anatolii.chub.mifarestorageapp.communication.model

/**
 * The class represents the position of the data on the MifareCard
 */
data class MifarePosition(
    val startSectorIndex: Int,
    val endSectorIndex: Int,
) {
    val absStartBlock = startSectorIndex * 4
    val absEndBlock = endSectorIndex * 4
    val value = ByteArray((absEndBlock - absStartBlock + 1) * 16)

    init {
        if (absEndBlock < absStartBlock) {
            throw IndexOutOfBoundsException("absolute end block number less than absolute start block number : $absEndBlock < $absStartBlock")
        }
    }
}