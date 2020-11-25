package com.anatolii.chub.profilereader.ui.base

import android.app.Application
import com.anatolii.chub.profilereader.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ReaderApp : Application() {

    override fun onCreate() {
        super.onCreate()

        configKoin()
    }

    private fun configKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@ReaderApp)
            modules(appModule)
        }
    }
}