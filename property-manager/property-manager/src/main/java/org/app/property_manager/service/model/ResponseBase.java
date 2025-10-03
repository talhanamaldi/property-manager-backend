package org.app.property_manager.service.model;

import org.springframework.context.MessageSource;

public class ResponseBase {
    private static MessageSource messageSource;

    public static void SetMessageSource(MessageSource ms) {
        messageSource = ms;
    }
    public ResponseBase() {}

    public String infoMessage;
    public String warningMessage;
    public String errorMessage;

    public ResponseBase(String infoMessage) {
        this.infoMessage = infoMessage;
    }
}
