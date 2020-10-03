package com.anatolii.chub.mifarestorageapp.ui

import com.anatolii.chub.mifarestorageapp.communication.profile.base.model.ProfileField
import com.anatolii.chub.mifarestorageapp.communication.profile.government.*

class User(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val gender: Gender = Gender.Other,
    val nationality: String = "",
    val countryCode: String = "",
    val birthDate: Long = 0,
    val photo: String = ""
) {
    constructor(
        list: List<ProfileField>
    ) : this(
        list.filterIsInstance<IdField>().first().value,
        list.filterIsInstance<GivenNameField>().first().value,
        list.filterIsInstance<SurnameField>().first().value,
        Gender.values()[list.filterIsInstance<GenderField>().first().value],
        list.filterIsInstance<NationalityField>().first().value,
        list.filterIsInstance<CountryCodeField>().first().value,
        list.filterIsInstance<BirthDateField>().first().value,
        list.filterIsInstance<PhotoField>().first().value,
    )


}