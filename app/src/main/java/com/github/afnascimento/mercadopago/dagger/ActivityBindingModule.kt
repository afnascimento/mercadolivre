package com.github.afnascimento.mercadopago.dagger

import com.github.afnascimento.mercadopago.ui.cardIssues.CardIssuesActivity
import com.github.afnascimento.mercadopago.ui.cardIssues.CardIssuesModule
import com.github.afnascimento.mercadopago.ui.installments.InstallmentsActivity
import com.github.afnascimento.mercadopago.ui.installments.InstallmentsModule
import com.github.afnascimento.mercadopago.ui.main.MainActivity
import com.github.afnascimento.mercadopago.ui.main.MainModule
import com.github.afnascimento.mercadopago.ui.methodPayment.MethodPaymentActivity
import com.github.afnascimento.mercadopago.ui.methodPayment.MethodPaymentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by andre.nascimento on 28/09/2017.
 */
@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    internal abstract fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MethodPaymentModule::class))
    internal abstract fun methodPaymentActivity(): MethodPaymentActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(CardIssuesModule::class))
    internal abstract fun cardIssuesActivity(): CardIssuesActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(InstallmentsModule::class))
    internal abstract fun installmentsActivity(): InstallmentsActivity
}