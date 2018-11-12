package com.github.afnascimento.mercadopago.ui.base

import android.view.View
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.Disposable

/**
 * Created by Andre on 15/03/2018.
 */
abstract class BaseViewFragment : DaggerFragment(), BaseView {

    private val mBaseView = BaseViewImpl()

    override fun onDestroy() {
        mBaseView.onDestroy()
        super.onDestroy()
    }

    override fun addDisposable(disposable: Disposable) {
        mBaseView.addDisposable(disposable)
    }

    override fun showContentLoading() {
        mBaseView.showContentLoading(activity!!, getContentMain(), getWidgetLoadView())
    }

    override fun hideContentLoading() {
        mBaseView.hideContentLoading(activity!!, getContentMain(), getWidgetLoadView())
    }

    fun getWidgetLoadView(): ContentLoadView? {
        return null
    }

    fun getContentMain(): View? {
        return null
    }

    override fun onErrorHandler(e: Throwable, listener : ContentLoadView.OnClickListener) {
        mBaseView.onErrorHandler(activity!!, getContentMain(), getWidgetLoadView(), e, listener)
    }
}