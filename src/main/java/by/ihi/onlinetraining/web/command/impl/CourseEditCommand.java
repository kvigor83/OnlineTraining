package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.service.CourseService;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.impl.CourseServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import by.ihi.onlinetraining.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseEditCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private CourseService courseService = CourseServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        String courseId = req.getParameter("courseId");
        String courseName = req.getParameter("courseName");
        String hours = req.getParameter("hours");
        String description = req.getParameter("description");
        String cost = req.getParameter("cost");
        if (user != null & courseId != null) {
            try {
                long userId = user.getId();
                courseService.update(Long.parseLong(courseId), courseName, Integer.parseInt(hours), description, Integer.parseInt(cost));
//                List<Course> courseList = courseService.findAll();
//                req.getSession().setAttribute("courseList", courseList);
//                req.setAttribute("courseList", courseList);
                commandNext="courseTutor";
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error: Course can not be update -> " + e.getMessage());
                commandNext = "error";
            }
        }
        return commandNext;
    }
}

