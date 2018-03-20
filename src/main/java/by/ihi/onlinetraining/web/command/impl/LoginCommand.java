package by.ihi.onlinetraining.web.command.impl;

import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.impl.UserServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.UserService;
import by.ihi.onlinetraining.web.auth.Encoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private UserService userService = UserServiceImpl.getInstance();
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = null;
        String login = req.getParameter(PARAM_NAME_LOGIN);
        String password = req.getParameter(PARAM_NAME_PASSWORD);

        if (login == null || password == null) {  //first enter
            resp.setHeader("errorMsg", "message.login.invalid-pass-login");
            return null;
        }
        try {
            user = userService.findByLogin(login);
            if (user != null && user.getPassword().equals(Encoder.encode(password))) {
                req.getSession().setAttribute("user", user);
                if (user.getRole().equals("user")) {
                    commandNext = "courses";
                } else if (user.getRole().equals("tutor")||user.getRole().equals("admin")) {
                    commandNext = "courseTutor";
                }
            } else {
                req.setAttribute("errorMsg", "message.login.invalid-pass-login");
            }
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            req.setAttribute("errorMsg", e.getMessage());
            commandNext = "error";
        }
        return commandNext;
    }

}
