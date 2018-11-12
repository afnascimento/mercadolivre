package com.github.afnascimento.mercadopago.ui.base

import dagger.android.support.DaggerAppCompatActivity
import android.view.View
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import io.reactivex.disposables.Disposable

/**
 * Created by andre.nascimento on 02/10/2017.
 */

abstract class BaseViewActivity : DaggerAppCompatActivity(), BaseView {

    private val mBaseView = BaseViewImpl()

    override fun onDestroy() {
        mBaseView.onDestroy()
        super.onDestroy()
    }

    override fun addDisposable(disposable: Disposable) {
        mBaseView.addDisposable(disposable)
    }

    override fun showContentLoading() {
        mBaseView.showContentLoading(this, getContentMain(), getWidgetLoadView())
    }

    override fun hideContentLoading() {
        mBaseView.hideContentLoading(this, getContentMain(), getWidgetLoadView())
    }

    open fun getContentMain(): View? {
        return null
    }

    open fun getWidgetLoadView(): ContentLoadView? {
        return null
    }

    override fun onErrorHandler(e: Throwable, listener : ContentLoadView.OnClickListener) {
        mBaseView.onErrorHandler(this, getContentMain(), getWidgetLoadView(), e, listener)
    }
}
