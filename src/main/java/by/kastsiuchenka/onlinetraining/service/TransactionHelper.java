package by.kastsiuchenka.onlinetraining.service;

import java.sql.SQLException;

import by.kastsiuchenka.onlinetraining.db.DbConnectionException;
import by.kastsiuchenka.onlinetraining.db.DbConnectionManager;
import by.kastsiuchenka.onlinetraining.service.ServiceException;

public abstract class TransactionHelper {

    public   void beginTransaction() throws ServiceException {
        try {
            DbConnectionManager.getConnection().setAutoCommit(false);
        } catch (SQLException | DbConnectionException e) {
            throw new ServiceException("Start transaction error", e);
        }
    }

    public void endTransaction() throws ServiceException {
        try {
            DbConnectionManager.getConnection().setAutoCommit(true);

        } catch (SQLException | DbConnectionException e) {
            throw new ServiceException("Transaction finish error ", e);
        }
    }

    public void commitTransaction() throws ServiceException {
        try {
            DbConnectionManager.getConnection().commit();
        } catch (SQLException | DbConnectionException e) {
            throw new ServiceException("Commit transaction error", e);
        }
    }

    public void rollbackTransaction() throws ServiceException {
        try {
            DbConnectionManager.getConnection().rollback();
        } catch (SQLException | DbConnectionException e) {
            throw new ServiceException("Rollback transaction error", e);
        }
    }
}
