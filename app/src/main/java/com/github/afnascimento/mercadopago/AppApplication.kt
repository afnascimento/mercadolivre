package com.github.afnascimento.mercadopago

import android.content.Context
import android.support.multidex.MultiDex
import com.github.afnascimento.mercadopago.dagger.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by Andre on 11/03/2018.
 */
class AppApplication : DaggerApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}