package kr.co.kesti.config;

import kr.co.kesti.utils.MailUtils;
import kr.co.kesti.utils.MessageUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

@Configuration
public class UtilsConfig {
    @Resource(name = "mailSender")
    private JavaMailSender mailSender;
    @Resource(name = "messageSource")
    private MessageSource messageSource;

    @Bean
    public MailUtils mailUtil() {
        MailUtils mailUtil = new MailUtils();
        mailUtil.setMailSender(this.mailSender);
        return mailUtil;
    }

    @Bean
    public MessageUtils messageUtil() {
        MessageUtils messageUtil = new MessageUtils();
        messageUtil.setMessageSource(this.messageSource);
        return messageUtil;
    }
}