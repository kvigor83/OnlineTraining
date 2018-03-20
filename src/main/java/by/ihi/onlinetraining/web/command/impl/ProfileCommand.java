package by.ihi.onlinetraining.web.command.impl;

import by.ihi.onlinetraining.service.impl.UserServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import by.ihi.onlinetraining.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;

        return null;
    }
}
