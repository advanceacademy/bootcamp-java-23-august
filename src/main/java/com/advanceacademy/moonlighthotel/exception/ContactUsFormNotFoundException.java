package com.advanceacademy.moonlighthotel.exception;

import java.util.NoSuchElementException;

public class ContactUsFormNotFoundException extends NoSuchElementException {
    public ContactUsFormNotFoundException(String message) {
        super(message);

    }
}
