package com.anatolii.chub.mifarestorageapp.ui

import android.nfc.tech.MifareClassic
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anatolii.chub.mifarestorageapp.communication.UserConverter
import com.anatolii.chub.mifarestorageapp.communication.reading.MifareClassicContentReader
import com.anatolii.chub.mifarestorageapp.communication.writing.MifareClassicContentWriter
import com.anatolii.chub.mifarestorageapp.log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Supplier
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class DispatchingViewModel : ViewModel() {

    private val converter = UserConverter()

    private val disposables = CompositeDisposable()

    val user = MutableLiveData(User())
    val error = MutableLiveData<Event<String>>()

    fun readProfile(mfc: MifareClassic) {
        disposables.add(

            Single.fromCallable {
                populateProfile(mfc)

            }.flatMap{

            MifareClassicContentReader(converter).read(mfc)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    log(it.toString())
                    user.postValue(it)
                }, {
                    it.printStackTrace()
                    error.postValue(Event(it.message ?: "Unknown error"))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun populateProfile(
        mfc: MifareClassic,
    ) {
        val item = User(
            "A007",
            "James",
            "Bond22",
            Gender.Male,
            "Ukrainian",
            "UKR",
            GregorianCalendar(1969,8,25,).time.time,
            "https://www.meme-arsenal.com/memes/afa6939d93a82c8c8493058fb97a92f5.jpg",
            "A/B"
        )

            MifareClassicContentWriter(converter).write(mfc, item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .blockingAwait()

    }

}