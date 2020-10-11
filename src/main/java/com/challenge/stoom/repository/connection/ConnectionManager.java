package com.challenge.stoom.repository.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    public static Connection con = null;

    static {
        config.setJdbcUrl("jdbc:sqlserver://localhost;databaseName=Stoom");
        config.setUsername("SEU USUÁRIO");
        config.setPassword("SUA SENHA");
        config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        config.setMinimumIdle(100);
        config.setMaximumPoolSize(1000);
        config.setConnectionTestQuery("Select 1");
        ds = new HikariDataSource(config);
    }

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        try {
            if(con == null){
                con = ds.getConnection();
            }
            return con;
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao pegar conexão com o banco de dados.", e);
        }
    }

    public static void transactional() {
        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
