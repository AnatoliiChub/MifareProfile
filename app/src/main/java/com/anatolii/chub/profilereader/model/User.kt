package com.anatolii.chub.profilereader.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.StringRes
import com.anatolii.chub.profilereader.R


data class User(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val gender: Gender = Gender.Other,
    val nationality: String = "",
    val countryCode: String = "",
    val birthDate: Long = 0,
    val photo: String = "",
    val driverLicense: String = "N/A",
    val expirationDate: Long = 0,
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
        parcel.readString() ?: "",
        parcel.readLong()
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
        parcel.writeLong(expirationDate)
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

enum class Gender(@StringRes val value: Int) {
    Male(R.string.gender_male),
    Female(R.string.gender_female),
    Other(R.string.gender_other)
}