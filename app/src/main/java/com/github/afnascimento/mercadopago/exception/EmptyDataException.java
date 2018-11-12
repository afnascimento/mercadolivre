package com.github.afnascimento.mercadopago.exception;

/**
 * Created by Andre on 12/03/2018.
 */

public class EmptyDataException extends Exception {

    public EmptyDataException() {
    }

    public EmptyDataException(String message) {
        super(message);
    }

    public EmptyDataException(Throwable cause) {
        super(cause);
    }

    public EmptyDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
