package com.anatolii.chub.mifarestorageapp.ui.nfc

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.anatolii.chub.mifarestorageapp.R
import com.anatolii.chub.mifarestorageapp.extensions.observeSafe
import com.anatolii.chub.mifarestorageapp.model.User
import com.anatolii.chub.mifarestorageapp.ui.base.utils.Event

class ReadTagFragment : NfcFragment(R.layout.fragment_read_tag) {

    private val model: NfcSharedViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        model.user.observeSafe(this, { event -> navigateToProfile(event.getContentIfNotHandled()) })
        model.error.observeSafe(this, { event -> showError(event) })
    }

    private fun navigateToProfile(user: User?) {
        user?.let {
            Navigation.findNavController(requireView())
                .navigate(ReadTagFragmentDirections.profileFragment(user))
        }
    }

    private fun showError(event: Event<String>) {
        event.getContentIfNotHandled()?.let {
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(it)
                .show()
        }
    }
}