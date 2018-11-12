package com.github.afnascimento.mercadopago.ui.main

import com.github.afnascimento.mercadopago.data.DataHelper
import com.github.afnascimento.mercadopago.ui.base.BaseInteractorImpl
import javax.inject.Inject

class MainInteractorImpl
@Inject
constructor(dataHelper: DataHelper) : BaseInteractorImpl(dataHelper), MainInteractor {

}