package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.StringField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.StringFieldConverter

class CountryCodeField(value: String) : StringField(value)

class CountryCodeConverter(override val itemSize: Int = 6) : StringFieldConverter()