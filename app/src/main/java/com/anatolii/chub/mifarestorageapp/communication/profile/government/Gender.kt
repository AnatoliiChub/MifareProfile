package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.IntField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.IntFieldConverter

enum class Gender {
    Male,
    Female,
    Other
}

class GenderField(value: Gender) : IntField(value.ordinal)

class GenderConverter : IntFieldConverter()