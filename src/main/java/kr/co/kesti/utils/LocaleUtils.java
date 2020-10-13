package kr.co.kesti.utils;

import lombok.Setter;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

public class LocaleUtils {
    private static MessageSourceAccessor messageSourceAccessor;

    public void setMessageSourceAccessor(MessageSourceAccessor _messageSourceAccessor) {
        messageSourceAccessor = _messageSourceAccessor;
    }

    public static String getMessage(final String code) {
        return messageSourceAccessor.getMessage(code, Locale.getDefault());
    }

    public static String getMessage(final String code, Object[] objects) {
        return messageSourceAccessor.getMessage(code, objects, Locale.getDefault());
    }
}