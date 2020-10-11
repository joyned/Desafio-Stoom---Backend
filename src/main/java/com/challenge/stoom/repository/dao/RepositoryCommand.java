package com.challenge.stoom.repository.dao;

import com.challenge.stoom.core.StoomException;
import com.challenge.stoom.repository.connection.ConnectionManager;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class RepositoryCommand {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private String sql;
    private int index = 1;

    public RepositoryCommand(StringBuilder sql) {
        this.sql = sql.toString();
    }

    public void setNullableString(String string) {
        try {
            setPreparedStatement();
            if (StringUtils.isNotBlank(string)) {
                this.preparedStatement.setString(this.index, string);
            } else {
                this.preparedStatement.setNull(this.index, Types.VARCHAR);
            }
            this.index++;
        } catch (SQLException e) {
            throw new StoomException("Falha ao fazer bind do paramêtro null", e);
        }
    }

    public void setParameter(Object obj) {
        try {
            setPreparedStatement();
            this.preparedStatement.setObject(this.index, obj);
            this.index++;
        } catch (SQLException e) {
            throw new StoomException("Falha ao fazer bind do paramêtro null", e);
        }
    }


    public int execute() {
        try {
            setPreparedStatement();
            return this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new StoomException("Falha ao executar o comando: " + this.sql, e);
        }
    }


    private void setPreparedStatement() {
        try {
            setConnection();
            if (this.preparedStatement == null) {
                this.preparedStatement = this.connection.prepareStatement(this.sql);
            }
        } catch (SQLException e) {
            throw new StoomException("Falhar ao pegar o PreparedStatement.", e);
        }
    }

    private void setConnection() {
        if (this.connection == null) {
            this.connection = ConnectionManager.getConnection();
        }
    }


}
