package com.github.afnascimento.mercadopago.ui.installments

import com.github.afnascimento.mercadopago.data.model.Installment
import com.github.afnascimento.mercadopago.ui.base.BaseView

interface InstallmentsView : BaseView {
    fun setInstallments(items: MutableList<Installment>)
}