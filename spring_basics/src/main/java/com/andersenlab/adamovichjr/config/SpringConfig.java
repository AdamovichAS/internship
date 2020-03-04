package com.andersenlab.adamovichjr.config;

import com.andersenlab.adamovichjr.*;
import com.andersenlab.adamovichjr.post_processor.ProfilingHandlerBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.andersenlab.adamovichjr")
public class SpringConfig {


    @Bean
    public Quoter quoter(){
        return new QuoterImpl("I'll be back");
    }


    @Bean
    public ProfilingHandlerBeanPostProcessor profilingHandlerBeanPostProcessor() throws Exception {
        return new ProfilingHandlerBeanPostProcessor();
    }
}
