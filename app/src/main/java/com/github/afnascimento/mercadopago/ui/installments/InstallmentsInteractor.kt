package com.github.afnascimento.mercadopago.ui.installments

import com.github.afnascimento.mercadopago.data.model.Installment
import com.github.afnascimento.mercadopago.ui.base.BaseInteractor
import io.reactivex.Observable

interface InstallmentsInteractor : BaseInteractor {
    fun getInstallments(amount: Double, paymentMethodId: String, issuerId: Int): Observable<MutableList<Installment>>
}