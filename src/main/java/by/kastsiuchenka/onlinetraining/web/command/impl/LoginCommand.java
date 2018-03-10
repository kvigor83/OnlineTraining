package by.kastsiuchenka.onlinetraining.web.command.impl;

import by.kastsiuchenka.onlinetraining.dao.DAOException;
import by.kastsiuchenka.onlinetraining.entity.User;
import by.kastsiuchenka.onlinetraining.service.ServiceException;
import by.kastsiuchenka.onlinetraining.service.UserService;
import by.kastsiuchenka.onlinetraining.service.impl.UserServiceImpl;
import by.kastsiuchenka.onlinetraining.web.auth.Encoder;
import by.kastsiuchenka.onlinetraining.web.command.Command;
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
            resp.setHeader("errorMsg", "Invalid Login or Password");
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
                req.setAttribute("errorMsg", "Incorrect Login or Password");
            }
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            req.setAttribute("errorMsg", e.getMessage());
            commandNext = "error";
        }
        return commandNext;
    }

}
