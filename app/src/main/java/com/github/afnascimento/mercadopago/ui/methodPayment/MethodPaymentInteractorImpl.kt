package com.github.afnascimento.mercadopago.ui.methodPayment

import com.github.afnascimento.mercadopago.data.DataHelper
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.base.BaseInteractorImpl
import io.reactivex.Observable
import javax.inject.Inject

class MethodPaymentInteractorImpl
@Inject
constructor(dataHelper: DataHelper) : BaseInteractorImpl(dataHelper), MethodPaymentInteractor {

    override fun getMethodPayments() : Observable<MutableList<PaymentMethod>> {
        return mercadoPagoApi().getPaymentMethods()
    }
}