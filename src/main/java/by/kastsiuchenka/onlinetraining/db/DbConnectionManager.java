package by.kastsiuchenka.onlinetraining.db;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbConnectionManager {
    private static ThreadLocal<ProxyConnection> tl = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getRootLogger();

    public static ProxyConnection getConnection() throws DbConnectionException {
        if (tl.get() == null) {
            tl.set(ConnectionPool.getInstance().getConnection());
        }
        return tl.get();
    }

    public static void releaseConnection() {  // возможно не надо,так как есть метод закрытия у объекта proxy
        try {
            if (tl.get() != null) {
                ConnectionPool.getInstance().returnConnection(tl.get());
                tl.remove();
            }
        } catch (DbConnectionException e) {
            LOGGER.error("Error: connection can not be return to ConnectionPool ", e);
        }
    }
}




