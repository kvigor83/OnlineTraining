package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.UserService;
import by.ihi.onlinetraining.service.impl.UserServiceImpl;
import by.ihi.onlinetraining.web.auth.Encoder;
import by.ihi.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserEditCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private UserService userService = UserServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        String id = req.getParameter("userId");
        String fio = req.getParameter("fio");
        String login = req.getParameter("login");
        String password = (req.getParameter("password"));
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String status = user.getStatus();
        if (password == null) {
            password = user.getPassword();
        } else {
            password = Encoder.encode(password);
        }
        if (user != null & id != null) {
            try {
                long userId = user.getId();
                userService.update(Long.parseLong(id), fio, login, password, email, role, status);
                commandNext = "logout";
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error: User can not be update -> " + e.getMessage());
                commandNext = "error";
            }
        }
        return commandNext;
    }
}

