package com.github.afnascimento.mercadopago.ui.methodPayment

import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.base.BasePresenterImpl
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MethodPaymentPresenterImpl<V : MethodPaymentView, I : MethodPaymentInteractor>
@Inject
constructor(mView: V, mInteractor: I) : BasePresenterImpl<V, I>(mView, mInteractor), MethodPaymentPresenter<V, I> {

    override fun getMethodPayments() {
        getView().addDisposable(
                getInteractor().getMethodPayments()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .doOnSubscribe {
                            getView().showContentLoading()
                        }
                        .subscribeWith(object : DisposableObserver<MutableList<PaymentMethod>>() {
                            override fun onNext(items: MutableList<PaymentMethod>) {
                                getView().setMethodPayments(items)
                            }

                            override fun onComplete() {
                                getView().hideContentLoading()
                            }

                            override fun onError(e: Throwable) {
                                getView().hideContentLoading()
                                getView().onErrorHandler(e, ContentLoadView.OnClickListener { getMethodPayments() })
                            }
                        })
        )
    }
}