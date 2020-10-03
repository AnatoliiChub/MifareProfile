package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.IntFieldConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.IntField

enum class Gender {
    Male,
    Female,
    Other
}

class GenderField(value: Gender) : IntField(value.ordinal)

class GenderConverter : IntFieldConverter() {
    override fun fromByte(offset: Int, content: ByteArray): GenderField {
        return GenderField(Gender.values()[super.fromByte(offset, content).value % 3])
    }
}