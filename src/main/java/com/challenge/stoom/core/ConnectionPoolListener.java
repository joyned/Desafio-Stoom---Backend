package com.challenge.stoom.core;

import com.challenge.stoom.repository.connection.ConnectionManager;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConnectionPoolListener {

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        ConnectionManager.getConnection();
    }

}
