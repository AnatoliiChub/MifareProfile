package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter

class NationalityField(value: String) : StringField(value)

class NationalityConverter(override val itemSize: Int = 20) : StringFieldConverter()