package com.anatolii.chub.mifarestorageapp.ui


data class User(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val gender: Gender = Gender.Other,
    val nationality: String = "",
    val countryCode: String = "",
    val birthDate: Long = 0,
    val photo: String = "",
    val driverLicense: String = "N/A"
)

enum class Gender {
    Male,
    Female,
    Other
}