package dev.bober.matchwave

import android.app.Application
import dev.bober.auth.di.apiModule
import dev.bober.auth.di.authModule
import dev.bober.matchwave.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(
                appModule,
                authModule,
                apiModule
            )
        }
    }
}