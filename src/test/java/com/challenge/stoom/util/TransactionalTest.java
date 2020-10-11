package com.challenge.stoom.util;

import com.challenge.stoom.repository.connection.ConnectionManager;

import java.util.function.Consumer;

public class TransactionalTest {

    public static void handle(Object obj, Consumer<Object> consumer) {
        try {
            ConnectionManager.getConnection();
            ConnectionManager.transactional();
            consumer.accept(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.rollback();
        }
    }

}
