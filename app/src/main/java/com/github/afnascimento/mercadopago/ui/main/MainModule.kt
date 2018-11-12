package com.github.afnascimento.mercadopago.ui.main

import com.github.afnascimento.mercadopago.dagger.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {

    @ActivityScoped
    @Binds
    internal abstract fun getView(activity: MainActivity): MainView

    @ActivityScoped
    @Binds
    internal abstract fun getPresenter(presenter: MainPresenterImpl<MainView, MainInteractor>): MainPresenter<MainView, MainInteractor>

    @ActivityScoped
    @Binds
    internal abstract fun getInteractor(interactor: MainInteractorImpl): MainInteractor
}