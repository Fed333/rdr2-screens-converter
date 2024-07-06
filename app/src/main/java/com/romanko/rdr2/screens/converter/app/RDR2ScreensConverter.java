package com.romanko.rdr2.screens.converter.app;

import com.romanko.rdr2.screens.converter.app.runner.RDR2ScreensConverterRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class RDR2ScreensConverter {

    public static void main(String[] args) {
        log.info("RDR2ScreensConverter context initialization...");

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        RDR2ScreensConverterRunner applicationRunner = context.getBean(RDR2ScreensConverterRunner.class);
        applicationRunner.runScreensConversion(args);

        log.info("RDR2ScreensConverter has been exited!");

    }
}
