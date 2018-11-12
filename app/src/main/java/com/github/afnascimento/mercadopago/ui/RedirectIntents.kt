package com.github.afnascimento.mercadopago.ui

import android.app.Activity
import android.content.Intent
import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.PayerCost
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.cardIssues.CardIssuesActivity
import com.github.afnascimento.mercadopago.ui.installments.InstallmentsActivity
import com.github.afnascimento.mercadopago.ui.main.MainActivity
import com.github.afnascimento.mercadopago.ui.methodPayment.MethodPaymentActivity
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat.startActivity



class RedirectIntents {

    companion object {
        val EXTRA_AMOUNT = "ExtraAmount";

        val EXTRA_PAYMENT_METHOD : String = "ExtraPaymentMethod"

        val EXTRA_CARD_ISSUE : String = "ExtraCardIssue"

        val EXTRA_PAYER_COAST : String = "PayerCost"

        val CODE_PAYMENT : Int = 1

        val CODE_CARD_ISSUES : Int = 2

        val CODE_INSTALLMENTS : Int = 3

        fun redirectMainActivity(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            activity.startActivity(intent)
            ActivityCompat.finishAffinity(activity)
        }

        fun redirectMethodPaymentForResult(activity: Activity, amount: Double) {
            val intent = Intent(activity, MethodPaymentActivity::class.java)
            intent.putExtra(EXTRA_AMOUNT, amount)
            activity.startActivityForResult(intent, CODE_PAYMENT)
        }

        fun redirectCardIssuesForResult(activity: Activity, amount: Double, paymentMethod: PaymentMethod) {
            val intent = Intent(activity, CardIssuesActivity::class.java)
            intent.putExtra(EXTRA_AMOUNT, amount)
            intent.putExtra(EXTRA_PAYMENT_METHOD, paymentMethod)
            activity.startActivityForResult(intent, CODE_CARD_ISSUES)
        }

        fun redirectInstallmentsResult(activity: Activity, amount: Double, paymentMethod: PaymentMethod, cardIssues: CardIssues) {
            val intent = Intent(activity, InstallmentsActivity::class.java)
            intent.putExtra(EXTRA_AMOUNT, amount)
            intent.putExtra(EXTRA_PAYMENT_METHOD, paymentMethod)
            intent.putExtra(EXTRA_CARD_ISSUE, cardIssues)
            activity.startActivityForResult(intent, CODE_INSTALLMENTS)
        }

        fun getAmount(intent: Intent): Double {
            return intent.getDoubleExtra(EXTRA_AMOUNT, 0.0)
        }

        fun getPaymentMethod(intent: Intent): PaymentMethod {
            return intent.getParcelableExtra(EXTRA_PAYMENT_METHOD)
        }

        fun getCardIssue(intent: Intent): CardIssues {
            return intent.getParcelableExtra(EXTRA_CARD_ISSUE)
        }

        fun getPayerCost(intent: Intent): PayerCost {
            return intent.getParcelableExtra(EXTRA_PAYER_COAST)
        }
    }
}