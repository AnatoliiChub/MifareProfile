package com.anatolii.chub.profilereader.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.anatolii.chub.profilereader.R
import com.anatolii.chub.profilereader.databinding.FragmentProfileBinding
import com.anatolii.chub.profilereader.model.User
import com.anatolii.chub.profilereader.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*

class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    private val args by navArgs<ProfileFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = args.user
    }

    private fun showUser(it: User) {
        identifier.text = it.id
        name.text = it.name
        surname.text = it.surname
        dateOfBirth.text = android.text.format.DateFormat.getDateFormat(requireContext())
            .format(Date(it.birthDate))
        gender.setText(it.gender.value)
        nationality.text = it.nationality
        countryCode.text = it.countryCode
        driverLicense.text = it.driverLicense
        expirationDate.text = android.text.format.DateFormat.getDateFormat(requireContext())
            .format(Date(it.expirationDate))
        GlideApp.with(requireContext()).load(it.photo)
            .into(photo2)

        btn_scan.setOnClickListener { navigateToReadTag() }
    }

    private fun navigateToReadTag() {
        Navigation.findNavController(requireView()).navigate(ProfileFragmentDirections.readTag())
    }
}