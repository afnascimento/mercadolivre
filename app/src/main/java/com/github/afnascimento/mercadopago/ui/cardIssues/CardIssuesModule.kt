package com.github.afnascimento.mercadopago.ui.cardIssues

import com.github.afnascimento.mercadopago.dagger.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class CardIssuesModule {

    @ActivityScoped
    @Binds
    internal abstract fun getView(activity: CardIssuesActivity): CardIssuesView

    @ActivityScoped
    @Binds
    internal abstract fun getPresenter(presenter: CardIssuesPresenterImpl<CardIssuesView, CardIssuesInteractor>): CardIssuesPresenter<CardIssuesView, CardIssuesInteractor>

    @ActivityScoped
    @Binds
    internal abstract fun getInteractor(interactor: CardIssuesInteractorImpl): CardIssuesInteractor
}