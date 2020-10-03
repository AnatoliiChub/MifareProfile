package com.anatolii.chub.mifarestorageapp

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color.pack
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.nfc.NfcAdapter.EXTRA_TAG
import android.nfc.Tag
import android.nfc.tech.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anatolii.chub.mifarestorageapp.communication.profile.base.ProfileDataConverter
import com.anatolii.chub.mifarestorageapp.communication.profile.government.*
import com.anatolii.chub.mifarestorageapp.communication.reading.MifareClassicContentReader
import com.anatolii.chub.mifarestorageapp.communication.writing.MifareClassicContentWriter
import java.time.Instant.now


class MainActivity : AppCompatActivity() {
    val TAG = "123123"
    lateinit var adapter: NfcAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NfcAdapter.getDefaultAdapter(this)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Log.d(TAG, "new intent : action=${intent.action}, data=${intent.data}, intent=$intent")
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action ||
            NfcAdapter.ACTION_TAG_DISCOVERED == intent.action ||
            NfcAdapter.ACTION_TECH_DISCOVERED == intent.action
        ) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                messages.onEach { Log.d(TAG, "$it") }
            }
            val tag = (intent.getParcelableExtra(EXTRA_TAG) as Tag?)

            log((tag ?: "NONE").toString())
            log("id : ${String(tag?.id ?: "NULL".toByteArray())}")

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
            MifareClassicContentWriter().write(mfc, converter.toByte(profile))
        }


        val data = MifareClassicContentReader().read(mfc)


        converter.fromByte(profile, data).forEach {
            log(it.toString())
        }
    }


    override fun onResume() {
        super.onResume()

        val intent = Intent(this, javaClass).apply {
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED).apply {
            try {
                addDataType("*/*")    /* Handles all MIME based dispatches.
                                 You should specify only the ones that you need. */
            } catch (e: IntentFilter.MalformedMimeTypeException) {
                throw RuntimeException("fail", e)
            }
        }

        val intentFiltersArray = arrayOf(ndef)

        val techListsArray = arrayOf(
            arrayOf<String>(
                NdefFormatable::class.java.name
            ),
            arrayOf(IsoDep::class.java.name),
            arrayOf(MifareClassic::class.java.name),
            arrayOf(MifareUltralight::class.java.name),
            arrayOf(Ndef::class.java.name),
            arrayOf(NfcA::class.java.name),
            arrayOf(NfcB::class.java.name),
            arrayOf(NfcF::class.java.name),
            arrayOf(NfcV::class.java.name),
            arrayOf(NfcBarcode::class.java.name)
        )
        adapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray)
    }

    override fun onPause() {
        super.onPause()
        adapter.disableForegroundDispatch(this)
    }

}