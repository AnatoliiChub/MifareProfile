package com.anatolii.chub.profilereader.ui

import androidx.navigation.Navigation
import com.anatolii.chub.profilereader.R
import com.anatolii.chub.profilereader.databinding.FragmentSplashBinding
import com.anatolii.chub.profilereader.ui.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashFragment : BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private val disposables = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        disposables.clear()
        disposables.add(Completable.timer(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Navigation.findNavController(requireView())
                    .navigate(SplashFragmentDirections.readTag())
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}