package sn.firdawsy.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * by osow on 15/11/17.
 * for kiss-api
 */
@Configuration
@ComponentScan(basePackages = {"sn.firdawsy.webapi"})
public class ApplicationTest {
    public static void main(String... args) {
        new SpringApplication(ApplicationTest.class).run(args);
    }

}
