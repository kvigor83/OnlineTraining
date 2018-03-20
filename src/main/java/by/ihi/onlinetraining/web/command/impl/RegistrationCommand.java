package by.ihi.onlinetraining.web.command.impl;

import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.impl.UserServiceImpl;
import by.ihi.onlinetraining.web.auth.Encoder;
import by.ihi.onlinetraining.web.command.Command;
import by.ihi.onlinetraining.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        String fio = req.getParameter("fio");
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        String email = req.getParameter("email");

        if (fio == null || login == null || password == null) {
            resp.setHeader("errorMsg", "message.login.invalid-pass-login");
            return null;
        }
        try {
            password = Encoder.encode(password);
            User user = userService.findByLogin(login);
            if (user == null) {
                userService.createUser(fio, login, password, email);
                req.getSession().setAttribute("infoMsg", "message.registration.success");
                commandNext="login";
            } else {
                resp.setHeader("infoMsg", "message.registration.duplicate");
                req.setAttribute("infoMsg", "message.registration.duplicate");
            }
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            req.setAttribute("errorMsg", " Error creating a user");
            commandNext="error";
        }
        return commandNext;
    }
}
