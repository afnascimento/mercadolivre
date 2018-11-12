package com.github.afnascimento.mercadopago.ui.cardIssues

import com.github.afnascimento.mercadopago.data.model.CardIssues
import javax.inject.Inject

import com.github.afnascimento.mercadopago.ui.base.BasePresenterImpl
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CardIssuesPresenterImpl<V : CardIssuesView, I : CardIssuesInteractor>
@Inject
constructor(mView: V, mInteractor: I) : BasePresenterImpl<V, I>(mView, mInteractor), CardIssuesPresenter<V, I> {

    override fun getCardIssues(paymentMethodId: String) {
        getView().addDisposable(
                getInteractor().getCardIssues(paymentMethodId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .doOnSubscribe {
                            getView().showContentLoading()
                        }
                        .subscribeWith(object : DisposableObserver<MutableList<CardIssues>>() {
                            override fun onNext(items: MutableList<CardIssues>) {
                                getView().setCardIssues(items)
                            }

                            override fun onComplete() {
                                getView().hideContentLoading()
                            }

                            override fun onError(e: Throwable) {
                                getView().hideContentLoading()
                                getView().onErrorHandler(e, ContentLoadView.OnClickListener { getCardIssues(paymentMethodId) })
                            }
                        })
        )
    }
}