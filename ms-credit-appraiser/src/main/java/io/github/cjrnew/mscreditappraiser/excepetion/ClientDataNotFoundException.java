package io.github.cjrnew.mscreditappraiser.excepetion;

public class ClientDataNotFoundException extends Exception {

    public ClientDataNotFoundException() {
        super("Customer data not found for the CPF entered.");
    }
}
