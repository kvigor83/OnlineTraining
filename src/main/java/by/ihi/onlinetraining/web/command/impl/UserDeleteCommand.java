package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.UserService;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.impl.UserServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserDeleteCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private UserService userService = UserServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        String userToDeleteId = req.getParameter("userId");
        if (user != null & userToDeleteId != null) {
            try {
                userService.delete(Long.parseLong(userToDeleteId));
                commandNext = "users";
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error: User can not be delete");
                commandNext = "error";
            }
        }
        return commandNext;
    }

}
