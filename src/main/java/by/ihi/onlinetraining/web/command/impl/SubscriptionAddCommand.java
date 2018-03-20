package by.ihi.onlinetraining.web.command.impl;

import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.SubscriptionService;
import by.ihi.onlinetraining.service.impl.SubscriptionServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import by.ihi.onlinetraining.entity.Subscription;
import by.ihi.onlinetraining.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscriptionAddCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private SubscriptionService subscriptionService = SubscriptionServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getSession().setAttribute("infoMsg", "message.login.need");
            commandNext = "login";
        } else {
            long userId = user.getId();
            long courseId = Long.parseLong(req.getParameter("courseId"));

            try {
                Subscription subscription = subscriptionService.createSubscription(userId, courseId);
                if (subscription != null) {
                    req.getSession().setAttribute("infoMsg", "message.subscription.add");
                    commandNext = "subscription";
                }
//                  else {
//                    req.setAttribute("errorMsg", "Подписка не добавлена.");
//                }
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.setAttribute("errorMsg", " Error creating a subscription");
                commandNext = "error";
            }

        }
        return commandNext;
    }
}
