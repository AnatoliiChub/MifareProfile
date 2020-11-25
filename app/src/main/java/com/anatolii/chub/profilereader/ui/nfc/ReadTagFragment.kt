package com.anatolii.chub.profilereader.ui.nfc

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.anatolii.chub.profilereader.R
import com.anatolii.chub.profilereader.extensions.observeSafe
import com.anatolii.chub.profilereader.log
import com.anatolii.chub.profilereader.model.User
import com.anatolii.chub.profilereader.ui.base.utils.Event
import kotlinx.android.synthetic.main.fragment_read_tag.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ReadTagFragment : NfcFragment(R.layout.fragment_read_tag) {

    private val model: NfcSharedViewModel by sharedViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        model.user.observeSafe(this, { event -> navigateToProfile(event.getContentIfNotHandled()) })
        model.error.observeSafe(this, { event -> showError(event) })
        model.isInProgress.observeSafe(this) {
            progress.isVisible = it.getContentIfNotHandled() ?: false
        }
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
                .setTitle(R.string.error)
                .setMessage(R.string.read_tag_error_message)
                .show()
        }
    }
}