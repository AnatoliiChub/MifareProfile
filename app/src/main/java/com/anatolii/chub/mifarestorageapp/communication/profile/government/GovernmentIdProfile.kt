package com.anatolii.chub.mifarestorageapp.communication.profile.government

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.CardProfile
import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.ProfileField
import com.anatolii.chub.mifarestorageapp.communication.profile.base.ProfileFieldConverter
import kotlin.collections.HashMap

class GovernmentIdProfile : CardProfile<ProfileField>() {

    override val spec =
        HashMap<Class<out ProfileField>, ProfileFieldConverter<out ProfileField>>().apply {
            this[IdField::class.java] = IdConverter()
            this[NationalityField::class.java] = NationalityConverter()
            this[SurnameField::class.java] = SurnameConverter()
            this[GivenNameField::class.java] = GivenNameConverter()
            this[PhotoField::class.java] = PhotoConverter()
            this[BirthDateField::class.java] = BirthDateConverter()
            this[CountryCodeField::class.java] = CountryCodeConverter()
            this[GenderField::class.java] = GenderConverter()
        }

}