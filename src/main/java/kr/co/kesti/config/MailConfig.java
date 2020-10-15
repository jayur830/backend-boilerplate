package kr.co.kesti.config;

import kr.co.kesti.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

@Configuration
public class MailConfig {
    @Resource(name = "mailSender")
    private JavaMailSender mailSender;

    @Bean
    public MailUtil mailUtil() {
        MailUtil mailUtil = new MailUtil();
        mailUtil.setMailSender(this.mailSender);
        return mailUtil;
    }
}