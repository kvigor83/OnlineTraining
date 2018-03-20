package by.kastsiuchenka.onlinetraining.web.command.impl;


import by.kastsiuchenka.onlinetraining.dao.DAOException;
import by.kastsiuchenka.onlinetraining.entity.Task;
import by.kastsiuchenka.onlinetraining.entity.User;
import by.kastsiuchenka.onlinetraining.service.ServiceException;
import by.kastsiuchenka.onlinetraining.service.TaskService;
import by.kastsiuchenka.onlinetraining.service.impl.TaskServiceImpl;
import by.kastsiuchenka.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TasksCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private TaskService taskService = TaskServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        String subscriptionId = req.getParameter("subscriptionId");
        if(subscriptionId==null){
            subscriptionId=(String)req.getSession().getAttribute("subscriptionId");
        }
        req.getSession().setAttribute("subscriptionId", subscriptionId );
        if (user != null & subscriptionId != null) {
//            long userId = user.getId();
            try {
                List<Task> taskList = taskService.findBySubscription(subscriptionId);
//                req.getSession().setAttribute("taskList", taskList);
                req.setAttribute("taskList", taskList);
                req.getSession().setAttribute("subscriptionId", subscriptionId );
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error view tasks -> "+e.getMessage());
                commandNext = "error";
            }

        }
        return commandNext;
    }
}

