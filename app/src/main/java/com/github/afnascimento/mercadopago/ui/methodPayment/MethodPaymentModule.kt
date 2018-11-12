package com.github.afnascimento.mercadopago.ui.methodPayment

import com.github.afnascimento.mercadopago.dagger.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class MethodPaymentModule {

    @ActivityScoped
    @Binds
    internal abstract fun getView(activity: MethodPaymentActivity): MethodPaymentView

    @ActivityScoped
    @Binds
    internal abstract fun getPresenter(presenter: MethodPaymentPresenterImpl<MethodPaymentView, MethodPaymentInteractor>): MethodPaymentPresenter<MethodPaymentView, MethodPaymentInteractor>

    @ActivityScoped
    @Binds
    internal abstract fun getInteractor(interactor: MethodPaymentInteractorImpl): MethodPaymentInteractor
}