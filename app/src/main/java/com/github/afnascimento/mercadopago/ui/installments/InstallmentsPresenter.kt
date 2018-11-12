package com.github.afnascimento.mercadopago.ui.installments

import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.base.BasePresenter

interface InstallmentsPresenter<V : InstallmentsView, I : InstallmentsInteractor> : BasePresenter<V, I> {
    fun getInstallments(amount: Double, paymentMethod: PaymentMethod, cardIssue: CardIssues)
}