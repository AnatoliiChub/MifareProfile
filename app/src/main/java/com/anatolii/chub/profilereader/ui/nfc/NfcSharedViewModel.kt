package com.anatolii.chub.profilereader.ui.nfc

import android.nfc.tech.MifareClassic
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anatolii.chub.nfc.communication.reading.MifareClassicContentReader
import com.anatolii.chub.nfc.communication.writing.MifareClassicContentWriter
import com.anatolii.chub.nfc.log
import com.anatolii.chub.profilereader.model.Gender
import com.anatolii.chub.profilereader.model.User
import com.anatolii.chub.profilereader.ui.base.utils.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*
import java.util.concurrent.TimeUnit

@KoinApiExtension
class NfcSharedViewModel : ViewModel(), KoinComponent {

    private val disposables = CompositeDisposable()

    val user = MutableLiveData<Event<User>>()
    val error = MutableLiveData<Event<String>>()
    val isInProgress = MutableLiveData<Event<Boolean>>()

    private val contentReader by inject<MifareClassicContentReader<User>>()
    private val contentWriter by inject<MifareClassicContentWriter<User>>()

    fun readProfile(mfc: MifareClassic) {
        disposables.add(
            contentReader.read(mfc)
                .doOnError { log(it.stackTraceToString()) }
                .retryWhen { it.take(5).delay(100, TimeUnit.MILLISECONDS) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { isInProgress.postValue(Event(true)) }
                .doOnEvent { _, _ -> isInProgress.postValue(Event(false)) }
                .subscribe({
                    log(it.toString())
                    user.postValue(Event(it))
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
            "Bond",
            Gender.Male,
            "Ukrainian",
            "UKR",
            GregorianCalendar(1969, 8, 25).time.time,
            "https://www.meme-arsenal.com/memes/afa6939d93a82c8c8493058fb97a92f5.jpg",
            "A/B",
            GregorianCalendar(2025, 0, 1).time.time,
        )

        contentWriter
            .write(mfc, item)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .blockingAwait()
    }

}