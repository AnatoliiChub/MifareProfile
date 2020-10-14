package com.anatolii.chub.profilereader.communication.model

//Transportation key config
data class Key(
    val first: Byte,
    val second: Byte,
    val third: Byte,
    val fourth: Byte,
    val fifth: Byte,
    val sixth: Byte
) {
    constructor(same: Byte) : this(same, same, same, same, same, same)
}

data class KeyConditions(
    val first: Byte,
    val second: Byte,
    val third: Byte,
)

data class KeyConfig(val keyA: Key, val keyB: Key, val conditions: KeyConditions) {

    fun toByteArray() = byteArrayOf(
        keyA.first,
        keyA.second,
        keyA.third,
        keyA.fourth,
        keyA.fifth,
        keyA.sixth,
        conditions.first,
        conditions.second,
        conditions.third,
        105.toByte(),
        keyB.first,
        keyB.second,
        keyB.third,
        keyB.fourth,
        keyB.fifth,
        keyB.sixth,
    )
}

val DEFAULT_KEY_CONFIG = KeyConfig(
    Key(
        //Key A
        0.toByte(),
        0.toByte(),
        0.toByte(),
        0.toByte(),
        0.toByte(),
        0.toByte(),
    ),
    //Key B / Data  - optional
    Key(
        (-1).toByte(),
        (-1).toByte(),
        (-1).toByte(),
        (-1).toByte(),
        (-1).toByte(),
        (-1).toByte(),
    ),
    //Conditions
    KeyConditions(
        (-1).toByte(),
        7.toByte(),
        (-128).toByte(),
    )
)