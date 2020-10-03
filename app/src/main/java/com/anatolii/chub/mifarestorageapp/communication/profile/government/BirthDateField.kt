package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.LongFieldConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.LongField

class BirthDateField(value: Long) : LongField(value)

class BirthDateConverter : LongFieldConverter() {
    override fun fromByte(offset: Int, content: ByteArray): BirthDateField {
        return BirthDateField(super.fromByte(offset, content).value)
    }
}