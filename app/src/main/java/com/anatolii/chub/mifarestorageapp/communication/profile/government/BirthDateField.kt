package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.LongField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.LongFieldConverter

class BirthDateField(value: Long) : LongField(value)

class BirthDateConverter : LongFieldConverter()