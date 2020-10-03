package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField

class NationalityField(value: String) : StringField(value)

class NationalityConverter(override val itemSize: Int = 20) :
    StringFieldConverter<NationalityField>() {
    override val type: Class<NationalityField> = NationalityField::class.java
}