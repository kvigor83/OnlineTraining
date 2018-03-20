package by.kastsiuchenka.onlinetraining.web.command.impl;


import by.kastsiuchenka.onlinetraining.dao.DAOException;
import by.kastsiuchenka.onlinetraining.entity.Course;
import by.kastsiuchenka.onlinetraining.entity.User;
import by.kastsiuchenka.onlinetraining.service.CourseService;
import by.kastsiuchenka.onlinetraining.service.ServiceException;
import by.kastsiuchenka.onlinetraining.service.UserService;
import by.kastsiuchenka.onlinetraining.service.impl.CourseServiceImpl;
import by.kastsiuchenka.onlinetraining.service.impl.UserServiceImpl;
import by.kastsiuchenka.onlinetraining.web.command.Command;
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
