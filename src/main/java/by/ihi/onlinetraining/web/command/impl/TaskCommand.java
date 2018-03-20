package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.entity.Task;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.impl.TaskServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaskCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private TaskService taskService = TaskServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        String taskId = req.getParameter("taskId");
        if (user != null & taskId != null) {
            try {
                Task task = taskService.find(taskId);
                req.setAttribute("currentTask", task);
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error view task -> "+e.getMessage());
                commandNext = "error";
            }

        }
        return commandNext;
    }
}

