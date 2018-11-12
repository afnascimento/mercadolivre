package com.github.afnascimento.mercadopago.data.api

import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.Installment
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import io.reactivex.Observable

interface MercadoPagoApi {

    fun getPaymentMethods(): Observable<MutableList<PaymentMethod>>
    fun getCardIssues(paymentMethodId: String): Observable<MutableList<CardIssues>>
    fun getInstallments(amount: Double, paymentMethodId: String, issuerId: Int): Observable<MutableList<Installment>>
}