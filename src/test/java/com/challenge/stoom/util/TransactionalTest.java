package com.challenge.stoom.util;

import com.challenge.stoom.core.StoomException;
import com.challenge.stoom.repository.connection.ConnectionManager;

import java.util.function.Consumer;

public class TransactionalTest {

    public static void handle(Object obj, Consumer<Object> consumer) {
        try {
            ConnectionManager.getConnection();
            ConnectionManager.transactional();
            consumer.accept(obj);
        } catch (Exception e) {
            throw new StoomException("Falha ao realizar transação.", e);
        } finally {
            ConnectionManager.rollback();
        }
    }

}
