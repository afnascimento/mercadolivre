package com.github.afnascimento.mercadopago.data

import com.github.afnascimento.mercadopago.data.api.MercadoPagoApi

/**
 * Created by andre.nascimento on 22/12/2017.
 */
interface DataHelper {

    fun api(): MercadoPagoApi
}