package by.ihi.onlinetraining.web.listener;

import by.ihi.onlinetraining.db.ConnectionPool;
import by.ihi.onlinetraining.db.DbConnectionException;
import by.ihi.onlinetraining.db.DbConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PoolConnectionListener implements ServletRequestListener {
    private static final Logger LOGGER = LogManager.getRootLogger();
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        DbConnectionManager.releaseConnection();
        //add to deploy heroku
//        try {
//            if (ConnectionPool.getInstanceCreated().get()) {
//                ConnectionPool.getInstance().destroyConnections();
//            } else {LOGGER.info("Listener: poll was not created");}
//        } catch (DbConnectionException e) {
//            LOGGER.error("Failed ", e);
//        }
        //add to deploy heroku
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
