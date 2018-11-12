package com.github.afnascimento.mercadopago.ui.base

import javax.inject.Inject

/**
 * Created by andre.nascimento on 02/10/2017.
 */

open class BasePresenterImpl<V : BaseView, I : BaseInteractor>
@Inject
constructor(private val mView: V, private val mInteractor: I) : BasePresenter<V, I> {

    fun getView(): V {
        return mView
    }

    fun getInteractor(): I {
        return mInteractor
    }
}