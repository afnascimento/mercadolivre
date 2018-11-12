package com.github.afnascimento.mercadopago.ui.cardIssues

import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.ui.base.BaseView

interface CardIssuesView : BaseView {

    fun setCardIssues(items: MutableList<CardIssues>)
}