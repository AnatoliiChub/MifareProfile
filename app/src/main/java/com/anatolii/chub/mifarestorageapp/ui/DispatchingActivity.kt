package com.anatolii.chub.mifarestorageapp.ui

import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.NfcAdapter.EXTRA_TAG
import android.nfc.Tag
import android.nfc.tech.MifareClassic
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.anatolii.chub.mifarestorageapp.R
import com.anatolii.chub.mifarestorageapp.log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.util.*


class DispatchingActivity : AppCompatActivity() {

    private lateinit var nfc: NfcAdapter

    private lateinit var model: DispatchingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = defaultViewModelProviderFactory.create(DispatchingViewModel::class.java)
        setContentView(R.layout.activity_main)
        nfc = NfcAdapter.getDefaultAdapter(this)

        model.user.observe(this, { showUser(it) })
        model.error.observe(this, { event -> showError(event) })
    }

    private fun showError(event: Event<String>) {
        event.getContentIfNotHandled()?.let {
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(it)
                .show()
        }
    }

    private fun showUser(it: User) {
        instructions.visibility = if (it.id.isEmpty()) VISIBLE else GONE

        id.text = "ID : ${it.id} "
        name.text = "GIVEN NAME : \n ${it.name}"
        surname.text = "SURNAME : \n ${it.surname}"
        dateOfBirth.text = "DATE OF BIRTH : \n ${
            android.text.format.DateFormat.getDateFormat(this).format(Date(it.birthDate))
        }"
        gender.text = "GENDER : \n ${it.gender}"
        nationality.text = "NATIONALITY : \n ${it.nationality}"
        Glide.with(this).load(it.photo).centerCrop().into(photo)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        Log.d(
            com.anatolii.chub.mifarestorageapp.TAG,
            "new intent : action=${intent.action}, data=${intent.data}, intent=$intent"
        )
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action ||
            NfcAdapter.ACTION_TAG_DISCOVERED == intent.action ||
            NfcAdapter.ACTION_TECH_DISCOVERED == intent.action
        ) {
            val tag = (intent.getParcelableExtra(EXTRA_TAG) as Tag?)

            if (tag?.techList?.contains(MifareClassic::class.java.name) == true) {
                val mfc = MifareClassic.get(tag)
                model.readProfile(mfc)
            }

            intent.extras?.keySet()?.onEach { log(it) }
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