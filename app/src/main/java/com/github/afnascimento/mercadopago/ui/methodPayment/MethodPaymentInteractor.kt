package com.github.afnascimento.mercadopago.ui.methodPayment

import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.base.BaseInteractor
import io.reactivex.Observable

interface MethodPaymentInteractor : BaseInteractor {
    fun getMethodPayments(): Observable<MutableList<PaymentMethod>>
}