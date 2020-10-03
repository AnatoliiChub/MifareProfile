package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField

class IdField(value: String) : StringField(value)

class IdConverter(override val itemSize: Int = 16) : StringFieldConverter<IdField>() {

    override val type: Class<IdField> = IdField::class.java
}