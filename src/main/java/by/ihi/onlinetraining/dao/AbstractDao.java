package by.kastsiuchenka.onlinetraining.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import by.kastsiuchenka.onlinetraining.db.DbConnectionException;
import by.kastsiuchenka.onlinetraining.db.DbConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

abstract class AbstractDao<T> {
    private static final Logger LOGGER = LogManager.getRootLogger();

    abstract T save(T t) throws DAOException;

    abstract T find(long id) throws DAOException;

    abstract void update(T t) throws DAOException;

    abstract int delete(long id) throws DAOException;

    abstract List<T> findAll() throws DAOException;

    PreparedStatement getPreparedStatement(String query) throws DAOException {
        try {
            return DbConnectionManager.getConnection().prepareStatement(query);
        } catch (DbConnectionException e) {
            throw new DAOException("Error: connection can not be received from ConnectionPool", e);
        } catch (SQLException e) {
            throw new DAOException("Error: prepared statement can not be received from Connection", e);
        }
    }

    PreparedStatement getPreparedStatement(String query, int flag) throws DAOException {
        try {
            return DbConnectionManager.getConnection().prepareStatement(query, flag);
        } catch (DbConnectionException e) {
            throw new DAOException("Error: connection to DataBase can not be received from ConnectionPool", e);
        } catch (SQLException e) {
            throw new DAOException("Error: prepared statement can not be received from Connection", e);
        }
    }

    void closeStatement(Statement st) {   // не надо бросать, вполне можно работать
        try {
            if (st != null)
                st.close();
        } catch (SQLException e) {
            LOGGER.error("Statement can not be closed", e);
        }
    }
}
