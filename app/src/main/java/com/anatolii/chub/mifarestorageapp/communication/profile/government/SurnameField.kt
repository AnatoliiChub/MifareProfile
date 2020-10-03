package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField

class SurnameField(value: String) : StringField(value)

class SurnameConverter(override val itemSize: Int = 40) : StringFieldConverter<SurnameField>() {
    override val type: Class<SurnameField> = SurnameField::class.java
}