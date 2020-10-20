package kr.co.kesti.config;

import kr.co.kesti.utils.MailUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

@Configuration
public class MailConfig {
    @Resource(name = "mailSender")
    private JavaMailSender mailSender;

    @Bean
    public MailUtils mailUtil() {
        MailUtils mailUtil = new MailUtils();
        mailUtil.setMailSender(this.mailSender);
        return mailUtil;
    }
}