package com.andersenlab.adamovichjr;

import com.andersenlab.adamovichjr.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.getBean(Quoter.class).sayQuote();
        Thread.sleep(1000);
    }
}
