package com.andersenlab.adamovichjr;

import com.andersenlab.adamovichjr.annotations.InjectRandomInt;
import com.andersenlab.adamovichjr.annotations.PostProxy;
import com.andersenlab.adamovichjr.annotations.Profiling;

import javax.annotation.PostConstruct;

@Profiling
public class QuoterImpl implements Quoter {
    private String message;

    //в фазе 1 переменная не будет инициализирована
    @InjectRandomInt(min=2, max=7)
    private int repeat;

    public QuoterImpl(String message) {
        System.out.println("phase 1");
        System.out.println(" repeat = " + repeat);
        this.message = message;
    }

    @PostConstruct
    public void init(){
        System.out.println("phase 2");
        System.out.println(" repeat = " + repeat);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sayQuote() {
        for (int i = 0; i < repeat; i++) {
            System.out.println(message);
        }
    }
}
