package com.github.afnascimento.mercadopago.ui.widget;

/**
 * Created by Andre on 12/03/2018.
 */

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.afnascimento.mercadopago.R;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by andre.nascimento on 20/10/2017.
 */

public class ContentLoadView extends RelativeLayout {

    public ContentLoadView(Context context) {
        this(context, null);
    }

    public ContentLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private ProgressWheel loading;
    private ViewGroup transitionsContainer;
    private ViewGroup contentAlert;
    private ImageView image;
    private TextView title;
    private TextView message;
    private Button button;

    public ContentLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.widget_load_view, this);
        loading = findViewById(R.id.pgLoad);
        transitionsContainer = findViewById(R.id.transitionsContainer);
        contentAlert = findViewById(R.id.contentAlert);
        image = findViewById(R.id.imgError);
        title = findViewById(R.id.tvTitle);
        message = findViewById(R.id.tvMessage);
        button = findViewById(R.id.btnRefresh);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(GONE);
    }

    public void showLoading() {
        loading.setVisibility(VISIBLE);
        contentAlert.setVisibility(GONE);
        show();
    }

    public void hideLoading() {
        loading.setVisibility(GONE);
        contentAlert.setVisibility(VISIBLE);
        show();
    }

    private void updateView(@DrawableRes int imageRes, @NonNull CharSequence textTitle, @NonNull CharSequence textMessage, @NonNull CharSequence textButton, @NonNull final OnClickListener listener) {
        hideLoading();
        image.setImageResource(imageRes);
        if (StringUtils.isEmpty(textTitle)) {
            title.setVisibility(GONE);
        } else {
            title.setText(textTitle);
            title.setVisibility(VISIBLE);
        }
        if (StringUtils.isEmpty(textMessage)) {
            message.setVisibility(GONE);
        } else {
            message.setText(textMessage);
            message.setVisibility(VISIBLE);
        }
        if (StringUtils.isNotBlank(textButton)) {
            button.setText(textButton);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRefreshViewError();
            }
        });
        startAnimateImageView();
    }

    private void updateView(@DrawableRes int imageRes, @NonNull CharSequence textTitle, @NonNull CharSequence textMessage, @NonNull final OnClickListener listener) {
        updateView(imageRes, textTitle, textMessage, null, listener);
    }

    private void startAnimateImageView() {
        image.setVisibility(GONE);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                TransitionSet set = new TransitionSet()
                        .addTransition(new Scale(0.7f))
                        .addTransition(new Fade())
                        .setInterpolator(new FastOutLinearInInterpolator());
                TransitionManager.beginDelayedTransition(transitionsContainer, set);
                image.setVisibility(VISIBLE);
            }
        }, 50);
    }

    public void showOffline(@NonNull final OnClickListener listener) {
        showOffline(
                getContext().getText(R.string.v_error_offline_title),
                getContext().getText(R.string.v_error_offline_message),
                listener
        );
    }

    public void showOffline(@NonNull CharSequence textTitle, @NonNull CharSequence textMessage, @NonNull final OnClickListener listener) {
        updateView(
                R.drawable.v_error_wifi_off,
                textTitle,
                textMessage,
                listener
        );
    }

    public void showError(@NonNull CharSequence textTitle, @NonNull CharSequence textMessage, @NonNull final OnClickListener listener) {
        updateView(
                R.drawable.v_error_cancel,
                textTitle,
                textMessage,
                listener
        );
    }

    public void showEmpty(@NonNull CharSequence textTitle, @NonNull CharSequence textMessage, @NonNull final OnClickListener listener) {
        updateView(
                R.drawable.v_error_empty,
                textTitle,
                textMessage,
                listener
        );
    }

    public void showCustom(@DrawableRes int imageRes, @NonNull CharSequence textTitle, @NonNull CharSequence textMessage, @NonNull CharSequence textButton, @NonNull final OnClickListener listener) {
        updateView(
                imageRes,
                textTitle,
                textMessage,
                textButton,
                listener
        );
    }

    public interface OnClickListener {
        void onRefreshViewError();
    }
}

