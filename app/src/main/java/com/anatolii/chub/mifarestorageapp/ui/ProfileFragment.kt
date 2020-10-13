package com.anatolii.chub.mifarestorageapp.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.anatolii.chub.mifarestorageapp.R
import com.anatolii.chub.mifarestorageapp.model.User
import com.anatolii.chub.mifarestorageapp.ui.base.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val args by navArgs<ProfileFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showUser(args.user)
    }

    private fun showUser(it: User) {
        idLabel.text = "ID : \n${it.id} "
        name.text = "Given name: \n${it.name}"
        surname.text = "Surname : \n${it.surname}"
        dateOfBirth.text = "Date of birth : \n${
            android.text.format.DateFormat.getDateFormat(requireContext())
                .format(Date(it.birthDate))
        }"
        gender.text = "Gender : \n${it.gender}"
        nationality.text = "Nationality : \n${it.nationality}"
        countryCode.text = "Country code: \n${it.countryCode}"
        driverLicense.text = "Driver License: \n${it.driverLicense}"
        Glide.with(this).load(it.photo)
            .apply(
                RequestOptions()
                    .transform(CenterCrop(), RoundedCorners(50))
                    .error(R.drawable.ic_launcher_background)
            ).into(photo2)

        btn_scan.setOnClickListener { navigateToReadTag() }
    }

    private fun navigateToReadTag() {
        Navigation.findNavController(requireView()).navigate(ProfileFragmentDirections.readTag())
    }
}