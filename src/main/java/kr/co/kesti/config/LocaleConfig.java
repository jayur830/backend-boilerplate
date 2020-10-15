package kr.co.kesti.config;

import kr.co.kesti.utils.LocaleUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class LocaleConfig {
    private static final String LOCALE_PATH = "/locale/";

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(LOCALE_PATH + "security");
        messageSource.setDefaultEncoding("utf-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public LocaleUtil localeUtils(MessageSourceAccessor messageSourceAccessor) {
        LocaleUtil localeUtils = new LocaleUtil();
        localeUtils.setMessageSourceAccessor(messageSourceAccessor);
        return localeUtils;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }
}