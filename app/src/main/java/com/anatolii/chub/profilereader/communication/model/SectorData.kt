package com.anatolii.chub.profilereader.communication.model

/**
 * The class contains only 3 blocks, as 4 block is a trailer block in a sector.
 */
class SectorData(data: ByteArray) {

    val blocks = MutableList(3) {
        var array = data
        if(array.size < 48) {
            array = ByteArray(48)
            data.copyInto(array)
        }
        MifareBlock(array.copyOfRange(it * 16, (it + 1) * 16))
    }
}