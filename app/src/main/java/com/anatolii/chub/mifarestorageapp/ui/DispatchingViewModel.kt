package com.anatolii.chub.mifarestorageapp.ui

import android.nfc.tech.MifareClassic
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anatolii.chub.mifarestorageapp.communication.profile.base.ProfileDataConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.government.*
import com.anatolii.chub.mifarestorageapp.communication.reading.MifareClassicContentReader
import com.anatolii.chub.mifarestorageapp.communication.writing.MifareClassicContentWriter
import com.anatolii.chub.mifarestorageapp.log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneOffset
import java.util.*

class DispatchingViewModel : ViewModel() {

    private val converter = ProfileDataConverter<GovernmentIdProfile>()

    private val profile = GovernmentIdProfile()

    private val disposables = CompositeDisposable()

    val user = MutableLiveData(User())
    val error = MutableLiveData<Event<String>>()

    fun readProfile(mfc: MifareClassic) {
        disposables.add(MifareClassicContentReader().read(mfc)
            .flatMap { converter.fromByte(profile, it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                user.postValue(User(list))
                list.forEach {
                    log(it.toString())
                }
            }, { it.printStackTrace()
                error.postValue(Event(it.message ?: "Unknown error"))
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun populateProfile(
        profile: GovernmentIdProfile,
        mfc: MifareClassic,
        converter: ProfileDataConverter<GovernmentIdProfile>
    ) {
        with(profile) {
            put(IdField("A007"))
            put(
                BirthDateField(GregorianCalendar(1969,8,25,).time.time)
            )
            put(CountryCodeField("UKR"))
            put(GenderField(Gender.Male))
            put(GivenNameField("James"))
            put(SurnameField("Bond"))
            put(NationalityField("UA"))
            put(PhotoField("https://www.meme-arsenal.com/memes/afa6939d93a82c8c8493058fb97a92f5.jpg"))
            MifareClassicContentWriter().write(mfc, converter.toByte(profile).blockingGet())
                .blockingAwait()
        }
    }

}