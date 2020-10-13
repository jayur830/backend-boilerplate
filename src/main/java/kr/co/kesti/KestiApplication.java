package kr.co.kesti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class KestiApplication {
    public static void main(String[] args) {
        SpringApplication.run(KestiApplication.class, args);
    }
}