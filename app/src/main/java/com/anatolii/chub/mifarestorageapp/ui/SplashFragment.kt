package com.anatolii.chub.mifarestorageapp.ui

import android.os.Bundle
import androidx.navigation.Navigation
import com.anatolii.chub.mifarestorageapp.R
import com.anatolii.chub.mifarestorageapp.ui.base.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val disposables = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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