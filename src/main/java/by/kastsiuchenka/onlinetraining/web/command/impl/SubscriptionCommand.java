package by.kastsiuchenka.onlinetraining.web.command.impl;


import by.kastsiuchenka.onlinetraining.dao.DAOException;
import by.kastsiuchenka.onlinetraining.entity.Subscription;
import by.kastsiuchenka.onlinetraining.entity.User;
import by.kastsiuchenka.onlinetraining.service.ServiceException;
import by.kastsiuchenka.onlinetraining.service.SubscriptionService;
import by.kastsiuchenka.onlinetraining.service.impl.SubscriptionServiceImpl;
import by.kastsiuchenka.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubscriptionCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private SubscriptionService subscriptionService = SubscriptionServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        List<Subscription> subscriptionList = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            long userId = user.getId();
            try {
                subscriptionList = subscriptionService.findByStudent(userId);
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.setAttribute("errorMsg", " Error creating a subscription");
                commandNext = "error";
            }

            if (subscriptionList != null) {
//                req.getSession().setAttribute("subscriptionList", subscriptionList);
                req.setAttribute("subscriptionList", subscriptionList);
            } else {
                req.setAttribute("errorMsg", "No available subscriptions");
            }
        } else{
            req.getSession().setAttribute("infoMsg", "Необходимо войти в систему для продолжения");
            commandNext = "login";
        }
        return commandNext;
    }
}
