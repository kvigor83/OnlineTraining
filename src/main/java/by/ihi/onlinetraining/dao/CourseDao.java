package by.kastsiuchenka.onlinetraining.dao;

import by.kastsiuchenka.onlinetraining.entity.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDao extends AbstractDao<Course> {
    private final static CourseDao INSTANCE = new CourseDao();

    private static final String SAVE_COURSE_QUERY = "INSERT INTO course (course_name, hours, description, cost, Uid) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_COURSE_QUERY = "UPDATE course SET course_name=?, hours=?, description=?, cost=? WHERE Cid=?";
    private static final String FIND_COURSE_QUERY = "SELECT * FROM course WHERE Cid=?";
    private static final String FIND_ALL_COURSES_QUERY = "SELECT * FROM course";
    private static final String FIND_COURSES_BY_LIST_QUERY = "SELECT * FROM course WHERE Cid IN (";
    private static final String FIND_BY_TUTOR_QUERY = "SELECT * FROM course WHERE Uid=?";
    private static final String DELETE_COURSE_QUERY = "DELETE FROM course WHERE Cid=?";

    private CourseDao() {
    }

    @Override
    Course save(Course course) throws DAOException {
        return null;
    }
    public Course save(Course course,long id) throws DAOException {
        PreparedStatement psSave = getPreparedStatement(SAVE_COURSE_QUERY, Statement.RETURN_GENERATED_KEYS);
        try {
            psSave.setString(1, course.getCourseName());
            psSave.setInt(2, course.getHours());
            psSave.setString(3, course.getDescription());
            psSave.setInt(4, course.getCost());
            psSave.setLong(5, id);
            psSave.executeUpdate();
            ResultSet rs = psSave.getGeneratedKeys();
            if (rs.next()) {
                course.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public Course find(long id) throws DAOException {
        try(PreparedStatement psFind = getPreparedStatement(FIND_COURSE_QUERY)) {
            psFind.setLong(1, id);
            psFind.executeQuery();
            ResultSet rs = psFind.getResultSet();
            if (rs.next()) {
                return populateCourse(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: error getting course: ", e);
        }
        return null;
    }

    @Override
    public void update(Course course) throws DAOException {
        PreparedStatement psUpdate = getPreparedStatement(UPDATE_COURSE_QUERY);
        try {
            psUpdate.setString(1, course.getCourseName());
            psUpdate.setInt(2, course.getHours());
            psUpdate.setString(3, course.getDescription());
            psUpdate.setInt(4, course.getCost());
            psUpdate.setLong(5, course.getId());
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: course can not be update ", e);
        }
    }

    @Override
    public int delete(long id) throws DAOException {

        try(PreparedStatement psDelete = getPreparedStatement(DELETE_COURSE_QUERY)) {
            psDelete.setLong(1, id);
            return psDelete.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: course can not be delete ", e);
        }

    }

    public List<Course>  findByTutor(long tutorId) throws DAOException {
        List<Course> list = new ArrayList<>();
        try (PreparedStatement psGetByTutorId = getPreparedStatement(FIND_BY_TUTOR_QUERY)) {
            psGetByTutorId.setLong(1, tutorId);
            psGetByTutorId.execute();
            ResultSet rs = psGetByTutorId.getResultSet();

            while (rs.next()) {
                list.add(populateCourse(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("Error in DAO: error getting courses by Tutor: ", e);
        }
        return list;
    }

    @Override
    public List<Course> findAll() throws DAOException {
        List<Course> list = new ArrayList<>();
        try (PreparedStatement psGetAll = getPreparedStatement(FIND_ALL_COURSES_QUERY)) {
            psGetAll.execute();
            ResultSet rs = psGetAll.getResultSet();
            while (rs.next()) {
                list.add(populateCourse(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: Error getting Courses ", e);
        }
        return list;
    }

    public List<Course> findByList(String[] value) throws DAOException {
        List<Course> list = new ArrayList<>();
        int length = value.length;
        StringBuilder resQueryString = new StringBuilder();
        resQueryString.append(FIND_COURSES_BY_LIST_QUERY);
        for (int i = 0; i < length - 1; i++) {
            resQueryString.append(value[i]).append(",");
        }
        resQueryString.append(value[length - 1]).append(")");
        String getByListQuery = resQueryString.toString();
        try (PreparedStatement psGetByList = getPreparedStatement(getByListQuery)) {
            psGetByList.execute();
            ResultSet rs = psGetByList.getResultSet();
            while (rs.next()) {
                list.add(populateCourse(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: Error getting Courses by list ", e);
        }
        return list;
    }

    private Course populateCourse(ResultSet rs) throws DAOException{
        Course course = new Course();
        try {
            course.setId(rs.getLong(1));
            course.setCourseName(rs.getString(2));
            course.setHours(rs.getInt(3));
            course.setDescription(rs.getString(4));
            course.setCost(rs.getInt(5));
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: Error creating entity User", e);
        }
        return course;
    }

    public static CourseDao getInstance() {
        return INSTANCE;
    }
}
