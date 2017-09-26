/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.util.exception;

/**
 *
 * @author gabriel
 */
public class RNException extends Exception {

    public RNException() {
    }

    public RNException(String message) {
        super(message);
    }

    public RNException(Throwable cause) {
        super(cause);
    }

    public RNException(String message, Throwable cause) {
        super(message, cause);
    }

    public RNException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
