package by.ihi.onlinetraining.service;

import by.ihi.onlinetraining.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> findByTutor(long id) throws ServiceException;

    List<Course> findAll() throws ServiceException;

    Course find(long id) throws ServiceException;

    void update(long courseId, String courseName, int hours, String description, int cost) throws ServiceException;

    void delete(long courseId) throws ServiceException;

    Course createCourse(String courseName, String hours, String cost, String description, String tutorId) throws ServiceException;
}
