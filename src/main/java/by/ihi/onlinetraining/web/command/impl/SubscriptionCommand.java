package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.entity.Subscription;
import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.SubscriptionService;
import by.ihi.onlinetraining.service.impl.SubscriptionServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
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
                req.setAttribute("errorMsg", "message.subscription.no-course");
            }
        } else{
            req.getSession().setAttribute("infoMsg", "message.login.need");
            commandNext = "login";
        }
        return commandNext;
    }
}
