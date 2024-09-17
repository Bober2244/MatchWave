package dev.bober.matchwave.di

import dev.bober.auth.di.authModule
import org.koin.dsl.module

val appModule = module {
    includes(authModule)
}