package com.anatolii.chub.mifarestorageapp.communication.profile.base

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField
import java.nio.charset.StandardCharsets.UTF_8

abstract class StringFieldConverter : ProfileFieldConverter<StringField>() {

    companion object{
        val LONG_EMPTY_STRING = " ".repeat(200)
    }


    override fun fromByte(offset: Int, content: ByteArray) =
        StringField(String(parseRawContent(offset, content), UTF_8).trim())

//    override fun pack(offset: Int, item: RawField, content: ByteArray) {
//        val formattedArray = LONG_EMPTY_STRING.toByteArray(encoding).copyOf(itemSize)
//        item.raw.copyInto(formattedArray, 0, 0, min(item.raw.size, itemSize))
//        formattedArray.copyInto(content, offset, 0, itemSize)
//    }
}