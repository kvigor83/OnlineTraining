package by.kastsiuchenka.onlinetraining.web.listener;

import by.kastsiuchenka.onlinetraining.db.ConnectionPool;
import by.kastsiuchenka.onlinetraining.db.DbConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationExitListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getRootLogger();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            if (ConnectionPool.getInstanceCreated().get()) {
                ConnectionPool.getInstance().destroyConnections();
//                LOGGER.info("Listener: pool was created");
            } else {LOGGER.info("Listener: poll was not created");}
        } catch (DbConnectionException e) {
            LOGGER.error("Failed to destroy Connection pool with exit application ", e);
        }
//        LOGGER.info("Listener: pool free ");
    }
}
