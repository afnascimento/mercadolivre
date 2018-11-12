package com.github.afnascimento.mercadopago.dagger

import android.app.Application
import com.github.afnascimento.mercadopago.AppApplication

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by andre.nascimento on 28/09/2017.
 */

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class))
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(application: AppApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}
