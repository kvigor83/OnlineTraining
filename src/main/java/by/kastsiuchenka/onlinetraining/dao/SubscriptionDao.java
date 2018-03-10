package by.kastsiuchenka.onlinetraining.dao;


import by.kastsiuchenka.onlinetraining.entity.Subscription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDao extends AbstractDao<Subscription> {
    private final static SubscriptionDao INSTANCE = new SubscriptionDao();
    private static final String SAVE_SUBSCRIPTION_QUERY = "INSERT INTO subscription (Uid, Cid, date_start) VALUES (?,?,?)";
    private static final String UPDATE_SUBSCRIPTION_QUERY = "UPDATE subscription SET date_stop = ?, avg_mark = ?, number_completed=?, status=? WHERE Sid=?";
    private static final String FIND_QUERY = "SELECT Sid, date_start, date_stop, avg_mark, number_completed, status, 'course','fio' FROM subscription WHERE Sid=?";
    private static final String FIND_ALL_SUBSCRIPTION_QUERY = "SELECT Sid,date_start, date_stop, avg_mark,number_completed, status, course_name, fio " +
            "FROM subscription JOIN course ON subscription.Cid = course.Cid " +
            "JOIN users ON subscription.Uid = users.Uid";
    private static final String FIND_BY_STUDENT_QUERY = "SELECT Sid,date_start, date_stop, avg_mark,number_completed, subscription.status, course_name, fio " +
            "FROM subscription JOIN course ON subscription.Cid = course.Cid " +
            "JOIN users ON subscription.Uid = users.Uid " +
            "WHERE subscription.Uid =?";
    private static final String FIND_BY_TUTOR_QUERY = "SELECT Sid,date_start, date_stop, avg_mark,number_completed, subscription.status, course_name, fio " +
            "FROM subscription JOIN course ON subscription.Cid = course.Cid " +
            "JOIN users ON subscription.Uid = users.Uid " +
            "WHERE subscription.Cid IN (SELECT Cid FROM course where Uid = ?) ORDER BY fio";

    private SubscriptionDao() {
    }

    @Override
    public Subscription save(Subscription subscription) throws DAOException {
//        try (PreparedStatement psSave = getPreparedStatement(SAVE_SUBSCRIPTION_QUERY, Statement.RETURN_GENERATED_KEYS)) {
//            psSave.setLong(1, subscription.getUserId());
//            psSave.setLong(2, subscription.getCourseId());
//            psSave.setString(3, subscription.getDateStart());
//            psSave.executeUpdate();
//            ResultSet rs = psSave.getGeneratedKeys();
//            if (rs.next()) {
//                subscription.setId(rs.getLong(1));
//            }
//        } catch (SQLException e) {
//            throw new DAOException("Error in DAO: error saving Subscription ", e);
//        }
        return null;
    }

    public Subscription save(Subscription subscription, long userId, long courseId) throws DAOException {
        try (PreparedStatement psSave = getPreparedStatement(SAVE_SUBSCRIPTION_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            psSave.setLong(1, userId);
            psSave.setLong(2, courseId);
            psSave.setString(3, subscription.getDateStart());
            psSave.executeUpdate();
            ResultSet rs = psSave.getGeneratedKeys();
            if (rs.next()) {
                subscription.setId(rs.getLong(1));
//                subscription.setNumberCompleted(rs.getString(2));
//                subscription.setStatus(rs.getString(3));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: error saving Subscription ", e);
        }
        return subscription;
    }

    @Override
    public Subscription find (long Sid) throws DAOException {
       Subscription subscription=null;
        try (PreparedStatement psFIND = getPreparedStatement(FIND_QUERY)) {
            psFIND.setLong(1,  Sid);
            psFIND.executeQuery();
            ResultSet rs = psFIND.getResultSet();
            if (rs.next()) {
                subscription = populateEntity(rs);
            }
        } catch (SQLException e) {
            throw new SecurityException("Error in DAO: error getting subscription ", e);
        }
        return subscription;
    }

    @Override
    public void update(Subscription subscription) throws DAOException {
        try (PreparedStatement psUpdate = getPreparedStatement(UPDATE_SUBSCRIPTION_QUERY)) {
            psUpdate.setString(1, subscription.getDateStop());
            psUpdate.setDouble(2, subscription.getAvgMark());
            psUpdate.setString(3, subscription.getNumberCompleted());
            psUpdate.setString(4, subscription.getStatus());
            psUpdate.setLong(5, subscription.getId());
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: subscription can not be update ", e);
        }

    }

    @Override
    public int delete(long id) throws DAOException {
        PreparedStatement psDelete;
//        psDelete = getPreparedStatement(DELETE_SUBSCRIPTION_QUERY);
//        psDelete.setLong(1, (long)qid);
//        return psDelete.executeUpdate();
        return 0;
    }

    @Override
    public List<Subscription> findAll() {
        return null;
    }


    public List<Subscription> findByUser(long userId) throws DAOException {
        List<Subscription> list = new ArrayList<>();
        try (PreparedStatement psGetAll = getPreparedStatement(FIND_BY_STUDENT_QUERY)) {
            psGetAll.setLong(1, userId);
            psGetAll.execute();
            ResultSet rs = psGetAll.getResultSet();
            while (rs.next()) {
                list.add(populateEntity(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: Subscriptions can not be found by user", e);
        }
        return list;
    }

    public List<Subscription> findByTutor(long userId) throws DAOException {
        List<Subscription> list = new ArrayList<>();
        try (PreparedStatement psGetAll = getPreparedStatement(FIND_BY_TUTOR_QUERY)) {
            psGetAll.setLong(1, userId);
            psGetAll.execute();
            ResultSet rs = psGetAll.getResultSet();
            while (rs.next()) {
                list.add(populateEntity(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: Subscriptions can not be found by tutor", e);
        }
        return list;
    }

    private Subscription populateEntity(ResultSet rs) throws DAOException {
        Subscription entity = new Subscription();
        try {
            entity.setId(rs.getLong(1));
            entity.setDateStart(rs.getString(2));
            entity.setDateStop(rs.getString(3));
            entity.setAvgMark(rs.getDouble(4));
            entity.setNumberCompleted(rs.getString(5));
            entity.setStatus(rs.getString(6));
            entity.setLinkedCourse(rs.getString(7));
            entity.setLinkedStudent(rs.getString(8));
        } catch (SQLException e) {
            throw new DAOException("Error creating entity Subscription ", e);
        }
        return entity;
    }

    public static SubscriptionDao getInstance() {
        return INSTANCE;
    }
}
