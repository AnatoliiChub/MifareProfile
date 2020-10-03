package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter

class GivenNameField(value: String) : StringField(value)

class GivenNameConverter(override val itemSize: Int = 40) : StringFieldConverter()