package com.github.afnascimento.mercadopago.exception;

/**
 * Created by Andre on 12/03/2018.
 */

public class InternetConnectionException extends Exception {

    public InternetConnectionException() {
    }

    public InternetConnectionException(String message) {
        super(message);
    }

    public InternetConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
