package com.github.afnascimento.mercadopago.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.afnascimento.mercadopago.R;
import com.github.afnascimento.mercadopago.data.model.PayerCost;

import org.apache.commons.lang3.StringUtils;

public class InstallmentsItemView extends RelativeLayout implements View.OnClickListener {

    public InstallmentsItemView(Context context) {
        this(context, null);
    }

    public InstallmentsItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private TextView tvTextInstallment;

    private View line;

    public InstallmentsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.installments_item, this);

        findViewById(R.id.contentClick).setOnClickListener(this);

        this.tvTextInstallment = findViewById(R.id.tvTextInstallment);
        this.line = findViewById(R.id.line);
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onClickInstallments(mPayerCost);
        }
    }

    private PayerCost mPayerCost;

    public void setPayerCost(PayerCost payerCost) {
        if (payerCost != null) {
            tvTextInstallment.setText(StringUtils.trimToEmpty(payerCost.getRecommendedMessage()));
        }
        this.mPayerCost = payerCost;
    }

    private Listener mListener;

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public interface Listener {
        void onClickInstallments(PayerCost payerCost);
    }
}
