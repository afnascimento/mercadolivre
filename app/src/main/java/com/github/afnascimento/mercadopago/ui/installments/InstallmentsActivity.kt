package com.github.afnascimento.mercadopago.ui.installments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.github.afnascimento.mercadopago.R
import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.Installment
import com.github.afnascimento.mercadopago.data.model.PayerCost
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.RedirectIntents
import com.github.afnascimento.mercadopago.ui.base.BaseViewActivity
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import com.github.afnascimento.mercadopago.ui.widget.InstallmentsItemView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.installments_activity.*
import javax.inject.Inject

class InstallmentsActivity : BaseViewActivity(), InstallmentsView, InstallmentsItemView.Listener {

    @Inject
    lateinit var mPresenter : InstallmentsPresenter<InstallmentsView, InstallmentsInteractor>

    var mAmount = 0.0

    var mPaymentMethod : PaymentMethod = PaymentMethod()

    var mCardIssue : CardIssues = CardIssues()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.installments_activity)
        setSupportActionBar(toolbarInstallments)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)

        mAmount = RedirectIntents.getAmount(intent)

        mPaymentMethod = RedirectIntents.getPaymentMethod(intent)
        setPaymentMethod(mPaymentMethod)

        mCardIssue = RedirectIntents.getCardIssue(intent)
        setCardIssue(mCardIssue)

        mPresenter.getInstallments(mAmount, mPaymentMethod, mCardIssue)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        setResult(Activity.RESULT_CANCELED, returnIntent)
        finish()
    }

    fun setPaymentMethod(item: PaymentMethod) {
        Picasso.get()
                .load(item.imageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgPayment)
        tvTextPayment.text = item.name()
    }

    fun setCardIssue(item: CardIssues) {
        Picasso.get()
                .load(item.imageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgBank)
        tvBankText.text = item.name()
    }

    override fun getContentMain(): View? {
        return contentMain
    }

    override fun getWidgetLoadView(): ContentLoadView? {
        return contentLoad
    }

    override fun onClickInstallments(payerCost: PayerCost?) {
        val returnIntent = Intent()
        returnIntent.putExtra(RedirectIntents.EXTRA_PAYER_COAST, payerCost)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun setInstallments(items: MutableList<Installment>) {
        contentItems.removeAllViewsInLayout()

        if (items.isNotEmpty()) {
            val installment = items.get(0)

            for (it in installment.payerCosts) {
                val view = InstallmentsItemView(this)
                view.setPayerCost(it)
                view.setListener(this)
                contentItems.addView(view)
            }
        }
    }
}