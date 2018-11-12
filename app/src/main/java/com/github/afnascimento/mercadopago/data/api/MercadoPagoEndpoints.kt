package com.github.afnascimento.mercadopago.data.api

import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.Installment
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MercadoPagoEndpoints {

    @GET("payment_methods")
    fun getPaymentMethods(@Query("public_key") publicKey: String): Observable<MutableList<PaymentMethod>>

    @GET("payment_methods/card_issuers")
    fun getCardIssues(@Query("public_key") publicKey: String,
                      @Query("payment_method_id") paymentMethodId : String): Observable<MutableList<CardIssues>>

    @GET("payment_methods/installments")
    fun getInstallments(@Query("public_key") publicKey: String,
                        @Query("amount") amount: Double,
                        @Query("payment_method_id") paymentMethodId : String,
                        @Query("issuer.id") issuerId : Int) : Observable<MutableList<Installment>>
}