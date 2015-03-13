package com.gaodashang.demo.client;

/**
 * comments.
 *
 * @author eva
 */
public class SimpleGreetingService implements GreetingService {
    @Override
    public String getGreeting() {
        return "Hello world!";
    }
}
