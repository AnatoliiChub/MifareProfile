package com.anatolii.chub.mifarestorageapp

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcAdapter.EXTRA_TAG
import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anatolii.chub.mifarestorageapp.communication.profile.base.ProfileDataConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.government.*
import com.anatolii.chub.mifarestorageapp.communication.reading.MifareClassicContentReader
import com.anatolii.chub.mifarestorageapp.communication.writing.MifareClassicContentWriter
import java.time.Instant.now


class MainActivity : AppCompatActivity() {

    private lateinit var nfc: NfcAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nfc = NfcAdapter.getDefaultAdapter(this)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Log.d(TAG, "new intent : action=${intent.action}, data=${intent.data}, intent=$intent")
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action ||
            NfcAdapter.ACTION_TAG_DISCOVERED == intent.action ||
            NfcAdapter.ACTION_TECH_DISCOVERED == intent.action
        ) {
            val tag = (intent.getParcelableExtra(EXTRA_TAG) as Tag?)

            if (tag?.techList?.contains(MifareClassic::class.java.name) == true) {
                val mfc = MifareClassic.get(tag)
                communicate(mfc)

            }

            intent.extras?.keySet()?.onEach { log(it) }
        }
    }

    private fun communicate(mfc: MifareClassic) {
        val converter = ProfileDataConverter<GovernmentIdProfile>()
        val profile = GovernmentIdProfile()
        with(profile) {
            put(IdField("A007"))
            put(BirthDateField(now().epochSecond))
            put(CountryCodeField("UKR"))
            put(GenderField(Gender.Male))
            put(GivenNameField("James"))
            put(SurnameField("Bond"))
            put(NationalityField("UA"))
            put(PhotoField("https://www.meme-arsenal.com/memes/afa6939d93a82c8c8493058fb97a92f5.jpg"))
            MifareClassicContentWriter().write(mfc, converter.toByte(profile).blockingGet())
        }


        val data = MifareClassicContentReader().read(mfc).blockingGet()


        converter.fromByte(profile, data).blockingGet().forEach {
            log(it.toString())
        }
    }


    override fun onResume() {
        super.onResume()
        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val techListsArray = arrayOf(arrayOf(MifareClassic::class.java.name))
        nfc.enableForegroundDispatch(this, pendingIntent, null, techListsArray)
    }

    override fun onPause() {
        super.onPause()
        nfc.disableForegroundDispatch(this)
    }

}