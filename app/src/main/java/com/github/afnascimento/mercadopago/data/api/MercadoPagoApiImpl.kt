package com.github.afnascimento.mercadopago.data.api

import android.util.Log
import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.Installment
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MercadoPagoApiImpl : MercadoPagoApi {

    val URL = "https://api.mercadopago.com/v1/"

    val PUBLIC_KEY = "444a9ef5-8a6b-429f-abdf-587639155d88";

    val mMercadoPagoEndpoints : MercadoPagoEndpoints
    init {
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("MERCADOPAGO-API", message) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()

        mMercadoPagoEndpoints = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL)
                .client(httpClient)
                .build()
                .create(MercadoPagoEndpoints::class.java)
    }

    override fun getPaymentMethods(): Observable<MutableList<PaymentMethod>> {
        return mMercadoPagoEndpoints.getPaymentMethods(PUBLIC_KEY)
    }

    override fun getCardIssues(paymentMethodId : String): Observable<MutableList<CardIssues>> {
        return mMercadoPagoEndpoints.getCardIssues(PUBLIC_KEY, paymentMethodId)
    }

    override fun getInstallments(amount: Double, paymentMethodId : String, issuerId : Int): Observable<MutableList<Installment>> {
        return mMercadoPagoEndpoints.getInstallments(PUBLIC_KEY, amount, paymentMethodId, issuerId)
    }
}