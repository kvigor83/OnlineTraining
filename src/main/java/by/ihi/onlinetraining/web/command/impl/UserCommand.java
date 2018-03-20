package by.kastsiuchenka.onlinetraining.web.command.impl;

import by.kastsiuchenka.onlinetraining.dao.DAOException;
import by.kastsiuchenka.onlinetraining.entity.User;
import by.kastsiuchenka.onlinetraining.service.ServiceException;
import by.kastsiuchenka.onlinetraining.service.UserService;
import by.kastsiuchenka.onlinetraining.service.impl.UserServiceImpl;
import by.kastsiuchenka.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
//        User user = (User) req.getSession().getAttribute("user");
        try {
            List<User> userList = userService.findAll();
            req.setAttribute("userList", userList);

        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            req.getSession().setAttribute("errorMsg", " Error view users");
            commandNext = "error";
        }

        return commandNext;
    }
}
