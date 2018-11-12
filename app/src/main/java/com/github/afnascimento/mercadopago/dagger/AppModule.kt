package com.github.afnascimento.mercadopago.dagger

import com.github.afnascimento.mercadopago.data.DataHelper
import com.github.afnascimento.mercadopago.data.DataHelperImpl
import com.github.afnascimento.mercadopago.data.api.MercadoPagoApi
import com.github.afnascimento.mercadopago.data.api.MercadoPagoApiImpl

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by andre.nascimento on 27/09/2017.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideMercadoPagoApi(): MercadoPagoApi {
        return MercadoPagoApiImpl()
    }

    @Provides
    @Singleton
    internal fun provideDataHelper(api: MercadoPagoApi): DataHelper {
        return DataHelperImpl(api)
    }
}