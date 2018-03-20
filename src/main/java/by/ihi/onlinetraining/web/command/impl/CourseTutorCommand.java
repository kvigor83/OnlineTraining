package by.ihi.onlinetraining.web.command.impl;


import by.ihi.onlinetraining.entity.Course;
import by.ihi.onlinetraining.entity.User;
import by.ihi.onlinetraining.service.CourseService;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.UserService;
import by.ihi.onlinetraining.service.impl.CourseServiceImpl;
import by.ihi.onlinetraining.service.impl.UserServiceImpl;
import by.ihi.onlinetraining.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CourseTutorCommand implements Command {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private CourseService courseService = CourseServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            long userId = user.getId();
            try {
                List<Course> courseList=null;
                if (user.getRole().equals("tutor")) {
                    courseList = courseService.findByTutor(userId);
                } else if (user.getRole().equals("admin")) {
                    courseList = courseService.findAll();
                    List<User> tutorList=userService.findByRole("tutor");
                    req.setAttribute("tutorList", tutorList);
                }
                req.setAttribute("courseList", courseList);
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                req.getSession().setAttribute("errorMsg", " Error view courses");
                commandNext = "error";
            }
        } else

        {
            req.getSession().setAttribute("infoMsg", "Необходимо войти в систему для продолжения");
            commandNext = "login";
        }
        return commandNext;
    }
}
