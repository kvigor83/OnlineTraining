package by.kastsiuchenka.onlinetraining.web.listener;

import by.kastsiuchenka.onlinetraining.db.DbConnectionManager;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PoolConnectionListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

        DbConnectionManager.releaseConnection();
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
