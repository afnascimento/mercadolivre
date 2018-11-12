package com.github.afnascimento.mercadopago.ui.installments

import javax.inject.Inject

import com.github.afnascimento.mercadopago.data.DataHelper
import com.github.afnascimento.mercadopago.data.model.Installment
import com.github.afnascimento.mercadopago.exception.EmptyDataException
import com.github.afnascimento.mercadopago.ui.base.BaseInteractorImpl
import io.reactivex.Observable

class InstallmentsInteractorImpl
@Inject
constructor(dataHelper: DataHelper) : BaseInteractorImpl(dataHelper), InstallmentsInteractor {

    override fun getInstallments(amount: Double, paymentMethodId: String, issuerId: Int) : Observable<MutableList<Installment>> {
        return mercadoPagoApi().getInstallments(amount, paymentMethodId, issuerId)
                .flatMap { values ->
                    if (values.isEmpty()) {
                        throw EmptyDataException()
                    }
                    Observable.just(values)
                }
    }
}