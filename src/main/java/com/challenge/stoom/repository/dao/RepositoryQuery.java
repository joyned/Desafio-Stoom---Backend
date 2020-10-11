package com.challenge.stoom.repository.dao;

import com.challenge.stoom.core.StoomException;
import com.challenge.stoom.repository.connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryQuery {

    private String sql;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public RepositoryQuery(StringBuilder sql) {
        this.sql = sql.toString();
    }

    public void execute() {
        try {
            if (this.connection == null) {
                this.connection = ConnectionManager.getConnection();
            }
            this.preparedStatement = this.connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
        } catch (Exception e) {
            throw new StoomException("Falha ao executar a consulta: " + this.sql);
        }
    }

    public int getInt(String columnName) {
        try {
            return this.resultSet.getInt(columnName);
        } catch (SQLException e) {
            throw new StoomException("Falha ao pegar o resultado da consulta. Coluna " + columnName + " não identificada.", e);
        }
    }

    public String getString(String columnName) {
        try {
            return this.resultSet.getString(columnName);
        } catch (SQLException e) {
            throw new StoomException("Falha ao pegar o resultado da consulta. Coluna " + columnName + " não identificada.", e);
        }
    }

    public boolean next() {
        try {
            return this.resultSet.next();
        } catch (SQLException e) {
            throw new StoomException("Falha ao pegar o próximo resultado.", e);
        }
    }


}
