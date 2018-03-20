package by.ihi.onlinetraining.web.command.impl;

import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.impl.UserServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import by.ihi.onlinetraining.service.UserService;
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
