package com.anatolii.chub.profilereader.model

import android.os.Parcel
import android.os.Parcelable


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
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        Gender.values()[parcel.readInt()],
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeInt(gender.ordinal)
        parcel.writeString(nationality)
        parcel.writeString(countryCode)
        parcel.writeLong(birthDate)
        parcel.writeString(photo)
        parcel.writeString(driverLicense)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

enum class Gender {
    Male,
    Female,
    Other
}