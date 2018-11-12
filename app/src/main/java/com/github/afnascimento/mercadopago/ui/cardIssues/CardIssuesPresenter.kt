package com.github.afnascimento.mercadopago.ui.cardIssues

import com.github.afnascimento.mercadopago.ui.base.BasePresenter

interface CardIssuesPresenter<V : CardIssuesView, I : CardIssuesInteractor> : BasePresenter<V, I> {
    fun getCardIssues(paymentMethodId: String)
}