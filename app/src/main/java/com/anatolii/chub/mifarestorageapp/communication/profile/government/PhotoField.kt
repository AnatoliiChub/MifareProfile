package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter

class PhotoField(value: String) : StringField(value)

class PhotoConverter(override val itemSize: Int = 100) : StringFieldConverter()