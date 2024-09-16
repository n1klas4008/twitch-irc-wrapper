package com.n1klas4008.test;

import com.n1klas4008.Bot;
import com.n1klas4008.data.Capability;
import com.n1klas4008.data.Environment;
import com.n1klas4008.events.EventHandler;
import com.n1klas4008.events.impl.MessageEvent;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // load our .env file to access its values
        // without leaking them on stream
        Environment environment = new Environment();
        // check if the needed values are present
        if (!environment.isProperlyConfigured()) {
            System.err.println("""
                    Environment is not properly configured
                    Please add the following entries:
                    ACCESS_TOKEN, USERNAME
                    """);
            System.exit(1);
        }
        try {
            Bot bot = Bot.connect(environment, Capability.values());

            bot.register(MessageEvent.class, new EventHandler<MessageEvent>() {
                @Override
                public void onEvent(MessageEvent event) {
                    System.err.println(event.getMessage());
                }
            });
        } catch (IOException e) {
            System.err.println("Failed to connect: " + e.getMessage());
        }
    }
}
