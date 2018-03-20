package by.kastsiuchenka.onlinetraining.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private static ConnectionPool connectionPool;
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private ArrayBlockingQueue<ProxyConnection> connectionQueue;

    private ConnectionPool() {
        ConnectorDB connectorDB = new ConnectorDB();
        connectionQueue = new ArrayBlockingQueue<>(connectorDB.getPoolSize());

        for (int i = 0; i < connectorDB.getPoolSize(); i++) {
            try {
                ProxyConnection connection = connectorDB.createConnection();
                connectionQueue.offer(connection);
            } catch (DbConnectionException e) {
                LOGGER.error("ConnectionPool creation error: connection " + i + " is not created. ", e);
            }
        }

        //!!!!!!!!
//        if (connectionQueue.size() < 1) {
//            LOGGER.fatal("ConnectionPool creation error: there are not enough connections for work. ");
//            throw new RuntimeException("ConnectionPool creation error: there are not enough connections for work. ");
//        }
    }

    public static ConnectionPool getInstance() throws DbConnectionException {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (!instanceCreated.get()) {
                    connectionPool = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } catch (Exception e) {
                throw new DbConnectionException("Connection pool error: ConnectionPool is not available. ", e);
            } finally {
                lock.unlock();
            }
        }
        return connectionPool;
    }

    public static AtomicBoolean getInstanceCreated() {
        return instanceCreated;
    }

    ProxyConnection getConnection() throws DbConnectionException {
        ProxyConnection connection;
        try {
            connection = connectionQueue.take();
            System.out.println(String.valueOf(connectionQueue.size()));                               ////////////////////////
            return connection;
        } catch (InterruptedException e) {
            throw new DbConnectionException("Connection can not be received from ConnectionPool. ", e);
        }

    }

    void returnConnection(ProxyConnection connection) throws DbConnectionException {
        try {
            connection.setAutoCommit(true);
            connectionQueue.put(connection);
            System.out.println(String.valueOf(connectionQueue.size()));                               ////////////////////////
        } catch (SQLException | InterruptedException e) {
            throw new DbConnectionException("Connection can not be returned in ConnectionPool. ", e);
        }

    }

    public void destroyConnections() {
        for (int i = 0; i < connectionQueue.size(); i++) {
            try {
                ProxyConnection connection = connectionQueue.take();
                connection.closeConnection();
            } catch (SQLException | InterruptedException e) {
                LOGGER.error("ConnectionPool destruction error: connection " + i + "is not destroyed. ", e);
            }
        }
        deregistrateAllDrivers();
        instanceCreated.set(false);   //??
    }

    private void deregistrateAllDrivers() {
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            LOGGER.error("Deregistration driver error: ", e);
        }
    }

}