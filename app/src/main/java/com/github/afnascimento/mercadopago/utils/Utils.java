package com.github.afnascimento.mercadopago.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
