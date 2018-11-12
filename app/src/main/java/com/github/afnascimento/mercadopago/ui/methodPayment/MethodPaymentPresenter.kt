package com.github.afnascimento.mercadopago.ui.methodPayment

import com.github.afnascimento.mercadopago.ui.base.BasePresenter

interface MethodPaymentPresenter<V : MethodPaymentView, I : MethodPaymentInteractor> : BasePresenter<V, I> {
    fun getMethodPayments()
}