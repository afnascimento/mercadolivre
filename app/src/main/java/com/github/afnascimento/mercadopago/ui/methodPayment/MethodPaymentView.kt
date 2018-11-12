package com.github.afnascimento.mercadopago.ui.methodPayment

import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.base.BaseView

interface MethodPaymentView : BaseView {
    fun setMethodPayments(items: MutableList<PaymentMethod>)
}