package by.kastsiuchenka.onlinetraining.web.command.impl;


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

public class TaskAddCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private TaskService taskService = TaskServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        String subscriptionId = (String)req.getSession().getAttribute("subscriptionId");
        String title=req.getParameter("title");
        String body=req.getParameter("body");
        String start=req.getParameter("start");
        String deadline=req.getParameter("deadline");
        if (user != null & subscriptionId != null) {
            try {
                taskService.createTask(title,body,start,deadline,subscriptionId);
                List<Task> taskList = taskService.findBySubscription(subscriptionId);
                req.getSession().setAttribute("taskList", taskList);
                commandNext = "tasks";
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error: Task can not be add");
                commandNext = "error";
            }

        }
        return commandNext;
    }
}

