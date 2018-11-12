package com.github.afnascimento.mercadopago.ui.cardIssues

import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.ui.base.BaseInteractor
import io.reactivex.Observable

interface CardIssuesInteractor : BaseInteractor {
    fun getCardIssues(paymentMethodId: String): Observable<MutableList<CardIssues>>
}