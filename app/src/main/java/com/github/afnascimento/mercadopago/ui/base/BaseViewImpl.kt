package com.github.afnascimento.mercadopago.ui.base

import android.app.Activity
import android.view.View
import com.github.afnascimento.mercadopago.R
import com.github.afnascimento.mercadopago.exception.EmptyDataException
import com.github.afnascimento.mercadopago.exception.InternetConnectionException
import com.github.afnascimento.mercadopago.exception.TimeoutConnectionException
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.apache.commons.lang3.StringUtils

/**
 * Created by Andre on 15/03/2018.
 */
class BaseViewImpl {

    val compositeDisposable = CompositeDisposable()

    fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun getCompositeDisposables(): CompositeDisposable {
        return compositeDisposable
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun showContentLoading(activity: Activity, contentMain : View?, contentLoadView: ContentLoadView?) {
        activity.runOnUiThread {
            contentMain?.visibility = View.GONE
            contentLoadView?.showLoading()
        }
    }

    fun hideContentLoading(activity: Activity, contentMain: View?, contentLoadView: ContentLoadView?) {
        activity.runOnUiThread {
            contentMain?.visibility = View.VISIBLE
            contentLoadView?.hide()
        }
    }

    fun showErrorOffline(activity: Activity, contentMain: View?, contentLoadView: ContentLoadView?,
                         title: String, text: String, listener : ContentLoadView.OnClickListener) {
        activity.runOnUiThread {
            contentMain?.visibility = View.GONE
            contentLoadView?.showOffline(title, text, listener)
        }
    }

    fun showEmpty(activity: Activity, contentMain: View?, contentLoadView: ContentLoadView?,
                  title: String, text: String, listener : ContentLoadView.OnClickListener) {
        activity.runOnUiThread {
            contentMain?.visibility = View.GONE
            contentLoadView?.showEmpty(title, text, listener)
        }
    }

    fun showErrorAlert(activity: Activity, contentMain: View?, contentLoadView: ContentLoadView?,
                       title: String, text: String, listener : ContentLoadView.OnClickListener) {
        activity.runOnUiThread {
            contentMain?.visibility = View.GONE
            contentLoadView?.showEmpty(title, text, listener)
        }
    }

    fun onErrorHandler(activity: Activity, contentMain: View?, contentLoadView: ContentLoadView?,
                       e: Throwable, listener : ContentLoadView.OnClickListener) {
        if (e is InternetConnectionException) {
            showErrorOffline(
                    activity, contentMain, contentLoadView,
                    activity.getString(R.string.msg_error_offline_title),
                    activity.getString(R.string.msg_error_offline_text),
                    listener
            )
        } else if (e is TimeoutConnectionException) {
            showErrorAlert(
                    activity, contentMain, contentLoadView,
                    activity.getString(R.string.msg_error_timeout_title),
                    activity.getString(R.string.msg_error_timeout_text),
                    listener)
        } else if (e is EmptyDataException) {
            val title = activity.getString(R.string.msg_error_empty_title)
            val text = if (StringUtils.isBlank(e.message)) {
                activity.getString(R.string.msg_error_empty_text)
            } else {
                StringUtils.trimToEmpty(e.message)
            }
            showEmpty(activity, contentMain, contentLoadView, title, text, listener)
        } else {
            showErrorAlert(
                    activity, contentMain, contentLoadView,
                    activity.getString(R.string.msg_error_generic_title),
                    activity.getString(R.string.msg_error_generic_text),
                    listener
            )
        }
    }
}