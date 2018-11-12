package com.github.afnascimento.mercadopago.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.github.afnascimento.mercadopago.R
import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.PayerCost
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.RedirectIntents
import com.github.afnascimento.mercadopago.ui.base.BaseViewActivity
import com.github.afnascimento.mercadopago.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_activity.*
import org.apache.commons.lang3.StringUtils

class MainActivity : BaseViewActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        contentChoice.setOnClickListener {
            contentChoiceClick(editValue.text.toString())
        }

        tvChangePayment.setOnClickListener {
            RedirectIntents.redirectMainActivity(this)
        }

        btnNext.setOnClickListener {
            Utils.showToast(this, getString(R.string.payment_send_sucess))
            RedirectIntents.redirectMainActivity(this)
        }
    }

    fun contentChoiceClick(text : String) {
        if (StringUtils.isEmpty(text)) {
            Utils.showToast(this, getString(R.string.choice_payment_value_empty))
            return
        }
        if (text.toDouble() <= 0.0) {
            Utils.showToast(this, getString(R.string.choice_payment_value_invalid))
            return
        }
        RedirectIntents.redirectMethodPaymentForResult(this, text.toDouble())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RedirectIntents.CODE_PAYMENT) {
                val paymentMethod = RedirectIntents.getPaymentMethod(data)
                val cardIssue = RedirectIntents.getCardIssue(data)
                val payerCost = RedirectIntents.getPayerCost(data)
                finishMethodPayments(paymentMethod, cardIssue, payerCost)
            }
        }
    }

    fun finishMethodPayments(paymentMethod : PaymentMethod, cardIssue : CardIssues, payerCost : PayerCost) {
        editValue.isEnabled = false
        btnNext.isEnabled = true
        contentChoice.visibility = View.GONE
        tvChangePayment.visibility = View.VISIBLE

        contentMethodPayment.visibility = View.VISIBLE
        Picasso.get()
                .load(paymentMethod.imageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgMethodPayment)
        tvMethodPayment.text = paymentMethod.name()

        contentBank.visibility = View.VISIBLE
        Picasso.get()
                .load(cardIssue.imageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgBank)
        tvBank.text = cardIssue.name()

        contentInstallments.visibility = View.VISIBLE
        tvTextInstallment.text = StringUtils.trimToEmpty(payerCost.recommendedMessage)
    }
}
