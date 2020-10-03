package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter

class SurnameField(value: String) : StringField(value)

class SurnameConverter(override val itemSize: Int = 40) : StringFieldConverter()