package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.entity.Task;
import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.TaskService;
import by.ihi.onlinetraining.service.impl.TaskServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TaskEditUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private TaskService taskService = TaskServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        String subscriptionId = (String)req.getSession().getAttribute("subscriptionId");
        String id=req.getParameter("taskId");
        String title=req.getParameter("title");
        String body=req.getParameter("body");
        String start=req.getParameter("start");
        String deadline=req.getParameter("deadline");
        String answer=req.getParameter("answer");
        String mark=req.getParameter("mark");
        String review=req.getParameter("review");
        String status=req.getParameter("status");
        if(answer!=null & status.equals("New")& user.getRole().equals("user")){
            status="Passed";
        }
        if (user != null & subscriptionId != null) {
            try {
                taskService.update(id,title,body,start,deadline,answer,mark,review,status,subscriptionId );
                List<Task> taskList = taskService.findBySubscription(subscriptionId);
                req.getSession().setAttribute("taskList", taskList);
                commandNext = "tasks";
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error: Task can not be update -> "+e.getMessage());
                commandNext = "error";
            }

        }
        return commandNext;
    }
}

