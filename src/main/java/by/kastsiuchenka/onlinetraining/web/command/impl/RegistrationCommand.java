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
            resp.setHeader("errorMsg", "Invalid Login or Password");
            return null;
        }
        try {
            password = Encoder.encode(password);
            User user = userService.findByLogin(login);
            if (user == null) {
                userService.createUser(fio, login, password, email);
                req.setAttribute("infoMsg", " Пользователь успешно создан. Войдите для продолжения");
                commandNext="login";
            } else {
                resp.setHeader("errorMsg", "Пользователь с таким именем существует");
                req.setAttribute("errorMsg", "Пользователь с таким именем существует");
            }
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            req.setAttribute("errorMsg", " Error creating a user");
            commandNext="error";
        }
        return commandNext;
    }
}
