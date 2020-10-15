package com.anatolii.chub.profilereader.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.anatolii.chub.profilereader.R
import com.anatolii.chub.profilereader.model.User
import com.anatolii.chub.profilereader.ui.base.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
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
        Glide.with(this).load(it.photo)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(
                RequestOptions()
                    .transform(CenterCrop())
                    .placeholder(R.drawable.progress)
                    .error(R.drawable.ic_launcher_background)
            ).priority(Priority.LOW)
            .into(photo2)

        btn_scan.setOnClickListener { navigateToReadTag() }
    }

    private fun navigateToReadTag() {
        Navigation.findNavController(requireView()).navigate(ProfileFragmentDirections.readTag())
    }
}