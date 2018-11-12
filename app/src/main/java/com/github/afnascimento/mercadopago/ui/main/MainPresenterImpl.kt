package com.github.afnascimento.mercadopago.ui.main

import com.github.afnascimento.mercadopago.ui.base.BasePresenterImpl
import javax.inject.Inject

class MainPresenterImpl<V : MainView, I : MainInteractor>
@Inject
constructor(mView: V, mInteractor: I) : BasePresenterImpl<V, I>(mView, mInteractor), MainPresenter<V, I> {

}