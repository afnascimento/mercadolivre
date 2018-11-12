package com.github.afnascimento.mercadopago.ui.base

import io.reactivex.disposables.Disposable

import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView

/**
 * Created by andre.nascimento on 02/10/2017.
 */

interface BaseView {
    fun addDisposable(disposable: Disposable)
    fun onErrorHandler(e: Throwable, listener: ContentLoadView.OnClickListener)
    fun showContentLoading()
    fun hideContentLoading()
}