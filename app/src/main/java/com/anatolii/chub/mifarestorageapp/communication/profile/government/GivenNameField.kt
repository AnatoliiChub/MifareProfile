package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField

class GivenNameField(value: String) : StringField(value)

class GivenNameConverter(override val itemSize: Int = 40) : StringFieldConverter<GivenNameField>() {
    override val type: Class<GivenNameField> = GivenNameField::class.java
}