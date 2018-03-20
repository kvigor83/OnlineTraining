package by.kastsiuchenka.onlinetraining.web.command.impl;

import by.kastsiuchenka.onlinetraining.service.UserService;
import by.kastsiuchenka.onlinetraining.service.impl.UserServiceImpl;
import by.kastsiuchenka.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;

        return null;
    }
}
