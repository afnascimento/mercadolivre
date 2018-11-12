package com.github.afnascimento.mercadopago.data

import com.github.afnascimento.mercadopago.data.api.MercadoPagoApi

class DataHelperImpl(var mercadoPagoApi: MercadoPagoApi) : DataHelper {

    override fun api(): MercadoPagoApi {
        return mercadoPagoApi
    }
}