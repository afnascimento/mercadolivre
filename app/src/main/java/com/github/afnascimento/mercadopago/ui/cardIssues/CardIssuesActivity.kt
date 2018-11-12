package com.github.afnascimento.mercadopago.ui.cardIssues

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.view.View
import com.github.afnascimento.mercadopago.R
import com.github.afnascimento.mercadopago.data.model.CardIssues
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.RedirectIntents
import com.github.afnascimento.mercadopago.ui.adapter.CardItemsAdapter
import com.github.afnascimento.mercadopago.ui.base.BaseViewActivity
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_issues_activity.*
import javax.inject.Inject
import android.app.Activity
import android.content.Intent
import android.R.attr.data
import com.github.afnascimento.mercadopago.ui.methodPayment.MethodPaymentActivity


class CardIssuesActivity : BaseViewActivity(), CardIssuesView, CardItemsAdapter.Listener<CardIssues> {

    @Inject
    lateinit var mPresenter : CardIssuesPresenter<CardIssuesView, CardIssuesInteractor>

    lateinit var mAdapter : CardItemsAdapter<CardIssues>

    var mAmount = 0.0

    var mPaymentMethod : PaymentMethod = PaymentMethod()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_issues_activity)
        setSupportActionBar(toolbarCardIssues)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)

        mAdapter = CardItemsAdapter(this)

        val recyclerView = rcviewItems
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        mAmount = RedirectIntents.getAmount(intent)

        mPaymentMethod = RedirectIntents.getPaymentMethod(intent)
        setPaymentMethod(mPaymentMethod)

        mPresenter.getCardIssues(mPaymentMethod.id ?: "")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RedirectIntents.CODE_INSTALLMENTS) {

                val returnIntent = Intent()
                returnIntent.putExtra(RedirectIntents.EXTRA_CARD_ISSUE, mCardIssues)

                val payerCost = RedirectIntents.getPayerCost(data)
                returnIntent.putExtra(RedirectIntents.EXTRA_PAYER_COAST, payerCost)

                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }

    fun setPaymentMethod(paymentMethod: PaymentMethod) {
        Picasso.get()
                .load(paymentMethod.imageUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgPayment)
        tvTextPayment.text = paymentMethod.name()
    }

    override fun getContentMain(): View? {
        return rcviewItems
    }

    override fun getWidgetLoadView(): ContentLoadView? {
        return contentLoad
    }

    override fun setCardIssues(items: MutableList<CardIssues>) {
        mAdapter.setItems(items)
    }

    var mCardIssues : CardIssues = CardIssues()

    override fun onCardItemClick(item: CardIssues) {
        mCardIssues = item
        RedirectIntents.redirectInstallmentsResult(this, mAmount, mPaymentMethod, mCardIssues)
    }
}