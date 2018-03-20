package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.entity.Course;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.impl.CourseServiceImpl;
import by.ihi.onlinetraining.service.CourseService;
import by.ihi.onlinetraining.web.command.Command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CoursesCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private CourseService courseService = CourseServiceImpl.getInstance();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        try {
            List<Course> courseList = courseService.findAll();
            req.setAttribute("courseList", courseList);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            req.getSession().setAttribute("errorMsg", " Error view courses -> "+e.getMessage());
            commandNext = "error";
        }
        return commandNext;
    }
}
