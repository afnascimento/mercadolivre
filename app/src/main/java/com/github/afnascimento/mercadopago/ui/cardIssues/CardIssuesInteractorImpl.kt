package com.github.afnascimento.mercadopago.ui.cardIssues

import com.github.afnascimento.mercadopago.data.DataHelper
import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.exception.EmptyDataException
import com.github.afnascimento.mercadopago.ui.base.BaseInteractorImpl
import io.reactivex.Observable
import javax.inject.Inject

class CardIssuesInteractorImpl
@Inject
constructor(dataHelper: DataHelper) : BaseInteractorImpl(dataHelper), CardIssuesInteractor {

    override fun getCardIssues(paymentMethodId: String) : Observable<MutableList<CardIssues>> {
        return mercadoPagoApi().getCardIssues(paymentMethodId)
                .flatMap { values ->
                    if (values.isEmpty()) {
                        throw EmptyDataException()
                    }
                    Observable.just(values)
                }
    }
}