package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField

class PhotoField(value: String) : StringField(value)

class PhotoConverter(override val itemSize: Int = 100) : StringFieldConverter<PhotoField>() {
    override val type: Class<PhotoField> = PhotoField::class.java
}