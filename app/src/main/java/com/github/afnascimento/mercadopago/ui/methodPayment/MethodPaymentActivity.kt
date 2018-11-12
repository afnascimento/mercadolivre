package com.github.afnascimento.mercadopago.ui.methodPayment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.view.View
import com.github.afnascimento.mercadopago.R
import com.github.afnascimento.mercadopago.data.model.PaymentMethod
import com.github.afnascimento.mercadopago.ui.RedirectIntents
import com.github.afnascimento.mercadopago.ui.adapter.CardItemsAdapter
import com.github.afnascimento.mercadopago.ui.base.BaseViewActivity
import com.github.afnascimento.mercadopago.ui.widget.ContentLoadView
import kotlinx.android.synthetic.main.method_payment_activity.*
import javax.inject.Inject

class MethodPaymentActivity : BaseViewActivity(), MethodPaymentView, CardItemsAdapter.Listener<PaymentMethod> {

    @Inject
    lateinit var mPresenter : MethodPaymentPresenter<MethodPaymentView, MethodPaymentInteractor>

    lateinit var mAdapter : CardItemsAdapter<PaymentMethod>

    var mAmount = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.method_payment_activity)
        setSupportActionBar(toolbarMethodPayment)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)

        mAmount = RedirectIntents.getAmount(intent)

        mAdapter = CardItemsAdapter(this)

        val recyclerView = rcviewItems
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        //recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        mPresenter.getMethodPayments()
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
            if (requestCode == RedirectIntents.CODE_CARD_ISSUES) {

                val returnIntent = Intent()

                returnIntent.putExtra(RedirectIntents.EXTRA_PAYMENT_METHOD, mPaymentMethod)

                val cardIssue = RedirectIntents.getCardIssue(data)
                returnIntent.putExtra(RedirectIntents.EXTRA_CARD_ISSUE, cardIssue)

                val payerCost = RedirectIntents.getPayerCost(data)
                returnIntent.putExtra(RedirectIntents.EXTRA_PAYER_COAST, payerCost)

                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }
    }

    override fun getContentMain(): View? {
        return rcviewItems
    }

    override fun getWidgetLoadView(): ContentLoadView? {
        return contentLoad
    }

    override fun setMethodPayments(items: MutableList<PaymentMethod>) {
        mAdapter.setItems(items)
    }

    var mPaymentMethod : PaymentMethod = PaymentMethod()

    override fun onCardItemClick(item: PaymentMethod) {
        mPaymentMethod = item
        RedirectIntents.redirectCardIssuesForResult(this, mAmount, mPaymentMethod)
    }
}
