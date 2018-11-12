package com.github.afnascimento.mercadopago.ui.base

import com.github.afnascimento.mercadopago.data.DataHelper
import com.github.afnascimento.mercadopago.data.api.MercadoPagoApi
import javax.inject.Inject

/**
 * Created by andre.nascimento on 02/10/2017.
 */

open class BaseInteractorImpl
@Inject
constructor(private val dataHelper: DataHelper) : BaseInteractor {

    fun mercadoPagoApi(): MercadoPagoApi {
        return dataHelper.api()
    }
}