package com.github.afnascimento.mercadopago.ui.installments

import com.github.afnascimento.mercadopago.dagger.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class InstallmentsModule {

    @ActivityScoped
    @Binds
    internal abstract fun getView(activity: InstallmentsActivity): InstallmentsView

    @ActivityScoped
    @Binds
    internal abstract fun getPresenter(presenter: InstallmentsPresenterImpl<InstallmentsView, InstallmentsInteractor>): InstallmentsPresenter<InstallmentsView, InstallmentsInteractor>

    @ActivityScoped
    @Binds
    internal abstract fun getInteractor(interactor: InstallmentsInteractorImpl): InstallmentsInteractor
}