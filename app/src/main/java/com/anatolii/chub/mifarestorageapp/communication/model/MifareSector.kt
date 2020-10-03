package com.anatolii.chub.mifarestorageapp.communication.model

data class MifareSector(
    val sectorNumber: Int,
    val blocks: MutableList<MifareBlock> = MutableList(4)
    { MifareBlock() }
) {

    constructor(sectorNumber: Int, data: SectorData, keyConfig: KeyConfig) : this(sectorNumber,
        mutableListOf<MifareBlock>().apply {
            addAll(data.blocks)
            add(MifareBlock(keyConfig.toByteArray()))
        }
    )
}