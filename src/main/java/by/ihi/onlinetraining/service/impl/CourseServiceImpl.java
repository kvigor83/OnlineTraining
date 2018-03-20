package by.ihi.onlinetraining.service.impl;

import by.ihi.onlinetraining.entity.Course;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.dao.DAOException;
import by.ihi.onlinetraining.dao.CourseDao;
import by.ihi.onlinetraining.service.CourseService;
import by.ihi.onlinetraining.service.TransactionHelper;

import java.util.List;


public class CourseServiceImpl extends TransactionHelper implements CourseService {
    private static final CourseService INSTANCE = new CourseServiceImpl();
    private CourseDao courseDao = CourseDao.getInstance();

    public List<Course> findByTutor(long tutorId) throws ServiceException {
        try {
            return courseDao.findByTutor(tutorId);
        } catch (DAOException e) {
            throw new ServiceException("Error : can not find Course: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> findAll() throws ServiceException {
        try {
            return courseDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Error :can not find Courses: " + e.getMessage(), e);
        }
    }

    public Course find(long id) throws ServiceException {
        try {
            return courseDao.find(id);
        } catch (DAOException e) {
            throw new ServiceException("Error can not find Course: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(long courseId, String courseName, int hours, String description, int cost) throws ServiceException {
        Course course = new Course();
        course.setId(courseId);
        course.setCourseName(courseName);
        course.setHours(hours);
        course.setDescription(description);
        course.setCost(cost);
        try {
            courseDao.update(course);
        } catch (DAOException e) {
            throw new ServiceException("Error updating Course: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            courseDao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Error deleting Course: " + e.getMessage(), e);
        }
    }

    @Override
    public Course createCourse(String courseName, String hours, String cost, String description, String tutorId) throws ServiceException {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setHours(Integer.parseInt(hours));
        course.setCost(Integer.parseInt(cost));
        try {
            course = courseDao.save(course, Long.parseLong(tutorId));
        } catch (DAOException e) {
            throw new ServiceException("Error creating Course: " + e.getMessage(), e);
        }
        return course;
    }

    public static CourseService getInstance() {
        return INSTANCE;
    }

}
