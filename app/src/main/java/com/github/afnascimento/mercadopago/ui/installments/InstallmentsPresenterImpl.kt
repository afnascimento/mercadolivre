package com.github.afnascimento.mercadopago.ui.installments

import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.Installment
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.base.BasePresenterImpl
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InstallmentsPresenterImpl<V : InstallmentsView, I : InstallmentsInteractor>
@Inject
constructor(mView: V, mInteractor: I) : BasePresenterImpl<V, I>(mView, mInteractor), InstallmentsPresenter<V, I> {

    override fun getInstallments(amount : Double, paymentMethod : PaymentMethod, cardIssue : CardIssues) {
        getView().addDisposable(
                getInteractor().getInstallments(amount, paymentMethod.id ?: "", cardIssue.id?.toInt() ?: 0)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .doOnSubscribe {
                            getView().showContentLoading()
                        }
                        .subscribeWith(object : DisposableObserver<MutableList<Installment>>() {
                            override fun onNext(items: MutableList<Installment>) {
                                getView().setInstallments(items)
                            }

                            override fun onComplete() {
                                getView().hideContentLoading()
                            }

                            override fun onError(e: Throwable) {
                                getView().hideContentLoading()
                                getView().onErrorHandler(e, ContentLoadView.OnClickListener { getInstallments(amount, paymentMethod, cardIssue) })
                            }
                        })
        )
    }
}