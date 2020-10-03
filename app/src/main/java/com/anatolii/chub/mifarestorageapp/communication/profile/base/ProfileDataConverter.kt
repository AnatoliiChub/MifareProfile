package com.anatolii.chub.mifarestorageapp.communication.profile.base

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.CardProfile
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.ProfileNotFilledException
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.ProfileField
import com.anatolii.chub.mifarestorageapp.log

class ProfileDataConverter<T : CardProfile<out ProfileField>> {

    fun toByte(profile: CardProfile<out ProfileField>): ByteArray {
        with(profile) {
            val orderList = spec
            val fieldList = orderList.map {
                try {
                    Pair(map[it.key]!!, it.value)
                } catch (exception: NullPointerException) {
                    throw ProfileNotFilledException("Profile not filled exception. $it field is absent")
                }
            }
            val contentSize =
                orderList.values.onEach { log("item ${it::class.java} v  size : ${it.itemSize}") }
                    .map { it.itemSize }
                    .sum()
            var contentOffset = 0

            val content = ByteArray(contentSize) { ' '.toByte() }
            fieldList.forEach {
                log("pack item offset : $contentOffset, content size : ${content.size}")
                it.second.toByte(contentOffset, it.first, content)
                contentOffset += it.second.itemSize
            }


            log("Content size : $contentSize, content : ${String(content)}")
            return content
        }
    }

    fun fromByte(profile: CardProfile<out ProfileField>, content: ByteArray): List<ProfileField> {
        var cursor = 0
        val orderList = profile.spec
        return orderList.map {
            val item = it.value.fromByte(cursor, content)
            log("Parsed item : $item")
            cursor += it.value.itemSize
            item
        }
    }

}