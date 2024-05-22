package com.netflix.store.Netflix.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        System.out.println("|-----------|Application started|-----------|");
    }
}
