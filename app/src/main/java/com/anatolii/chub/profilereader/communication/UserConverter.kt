package com.anatolii.chub.profilereader.communication

import com.anatolii.chub.profilereader.extensions.insert
import com.anatolii.chub.profilereader.model.Gender
import com.anatolii.chub.profilereader.model.User
import java.nio.ByteBuffer

class UserConverter : Converter<User> {

    companion object {
        const val ID_SIZE = 8
        const val NAME_SIZE = 30
        const val SURNAME_SIZE = 30
        const val GENDER_SIZE = 1
        const val NATIONALITY_SIZE = 20
        const val COUNTRY_CODE_SIZE = 4
        const val BIRTH_DATE_SIZE = 8
        const val PHOTO_URL_SIZE = 100
        const val DRIVER_LICENSE_SIZE = 16
        const val EXP_DATE_SIZE = 8
    }

    override fun populateFromBytes(array: ByteArray): User {
        var cursor = 0
        val id = String(array.copyOfRange(cursor, cursor + ID_SIZE))
        cursor += ID_SIZE

        val name = String(array.copyOfRange(cursor, cursor + NAME_SIZE))
        cursor += NAME_SIZE

        val surname = String(array.copyOfRange(cursor, cursor + SURNAME_SIZE))
        cursor += SURNAME_SIZE

        val gender = Gender.values()[array.copyOfRange(cursor, cursor + GENDER_SIZE)[0].toInt()]
        cursor += GENDER_SIZE

        val nationality = String(array.copyOfRange(cursor, cursor + NATIONALITY_SIZE))
        cursor += NATIONALITY_SIZE

        val countryCode = String(array.copyOfRange(cursor, cursor + COUNTRY_CODE_SIZE))
        cursor += COUNTRY_CODE_SIZE

        val birthDateArray = array.copyOfRange(cursor, cursor + BIRTH_DATE_SIZE)
        val birthDate = ByteBuffer.wrap(birthDateArray).long
        cursor += BIRTH_DATE_SIZE

        val photo = String(array.copyOfRange(cursor, cursor + PHOTO_URL_SIZE))
        cursor += PHOTO_URL_SIZE

        val driverLicense = String(array.copyOfRange(cursor, cursor + DRIVER_LICENSE_SIZE))
        cursor+= DRIVER_LICENSE_SIZE

        val expDateArray = array.copyOfRange(cursor, cursor + EXP_DATE_SIZE)
        val expDate = ByteBuffer.wrap(expDateArray).long

        return User(
            id.trim(), name.trim(), surname.trim(), gender,
            nationality.trim(), countryCode.trim(), birthDate, photo.trim(), driverLicense.trim(),
            expDate
        )
    }

    override fun toBytes(item: User): ByteArray {
        var cursor = 0
        val array = ByteArray(size) { ' '.toByte() }

        array.insert(cursor, item.id.toByteArray())
        cursor += ID_SIZE

        array.insert(cursor, item.name.toByteArray())
        cursor += NAME_SIZE

        array.insert(cursor, item.surname.toByteArray())
        cursor += SURNAME_SIZE

        array.insert(cursor, ByteArray(1) { item.gender.ordinal.toByte() })
        cursor += GENDER_SIZE

        array.insert(cursor, item.nationality.toByteArray())
        cursor += NATIONALITY_SIZE

        array.insert(cursor, item.countryCode.toByteArray())
        cursor += COUNTRY_CODE_SIZE

        val birthDateArray = ByteBuffer.allocate(java.lang.Long.BYTES)
            .putLong(item.birthDate)
            .array()
        array.insert(cursor, birthDateArray)
        cursor += BIRTH_DATE_SIZE

        array.insert(cursor, item.photo.toByteArray())
        cursor += PHOTO_URL_SIZE

        array.insert(cursor, item.driverLicense.toByteArray())
        cursor+= DRIVER_LICENSE_SIZE

        val expDateArray = ByteBuffer.allocate(java.lang.Long.BYTES)
            .putLong(item.expirationDate)
            .array()
        array.insert(cursor, expDateArray)

        return array
    }


    override val size = ID_SIZE + NAME_SIZE + SURNAME_SIZE + GENDER_SIZE +
            NATIONALITY_SIZE + COUNTRY_CODE_SIZE + BIRTH_DATE_SIZE + PHOTO_URL_SIZE +
            DRIVER_LICENSE_SIZE + EXP_DATE_SIZE

}