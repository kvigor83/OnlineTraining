package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.entity.Course;
import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.CourseService;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.impl.CourseServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private CourseService courseService = CourseServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        String courseId = req.getParameter("courseId");
        if (courseId == null) {
            courseId = (String) req.getSession().getAttribute("courseId");
        }

        if (user != null & courseId != null) {
            try {
                Course course = courseService.find(Long.parseLong(courseId));
                req.setAttribute("currentCourse", course);
                req.getSession().setAttribute("courseId", courseId);
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error edit course -> " + e.getMessage());
                commandNext = "error";
            }
        }
        return commandNext;
    }
}