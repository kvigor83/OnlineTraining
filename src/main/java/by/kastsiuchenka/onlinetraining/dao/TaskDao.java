package by.kastsiuchenka.onlinetraining.dao;


import by.kastsiuchenka.onlinetraining.entity.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskDao extends AbstractDao<Task> {
    private final static TaskDao INSTANCE = new TaskDao();

    private static final String SAVE_QUERY = "INSERT INTO tasks (title, body, start_time, end_time, Sid) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE tasks SET title=?, body=?, start_time=?, end_time=?, answer=?, mark=?, review=?, task_status=? WHERE Tid=?";
    private static final String FIND_TASK_BY_USER_QUERY = "SELECT Tid, title, body, start_time, end_time, answer, mark, review, task_status,tasks.Sid " +
            "FROM tasks JOIN subscription ON tasks.Sid= subscription.Sid WHERE Uid=? SORT BY Uid DESC";
    private static final String FIND_BY_SUBSCRIPTION_QUERY = "SELECT Tid, title, body, start_time, end_time, answer, mark, review, task_status " +
            "FROM tasks WHERE Sid=?";
    private static final String FIND_QUERY = "SELECT Tid, title, body, start_time, end_time, answer, mark, review, task_status FROM tasks WHERE Tid=?";
    private static final String FIND_AVG_MARK = "SELECT AVG(mark) AS avg_mark FROM tasks WHERE Sid=?";
    private static final String FIND_NUMBER_COMPLETED = "SELECT COUNT(*) AS number_completed FROM tasks WHERE NOT task_status='New' AND Sid=? ";
    private static final String FIND_NUMBER_TESTS = "SELECT COUNT(*) AS number_tasks FROM tasks WHERE Sid=?";
    private static final String DELETE_QUERY = "DELETE FROM tasks WHERE Tid=?";

    private TaskDao() {
    }

    public Task save(Task task, long subscriptionId) throws DAOException {
        try (PreparedStatement psSave = getPreparedStatement(SAVE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            psSave.setString(1, task.getTitle());
            psSave.setString(2, task.getBody());
            psSave.setString(3, task.getStartTime());
            psSave.setString(4, task.getEndTime());
            psSave.setLong(5, subscriptionId);
            psSave.executeUpdate();
            ResultSet rs = psSave.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getLong(1));
            }
            return task;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: task can not be created ", e);
        }
    }

    @Override
    Task save(Task task) throws DAOException {
        return null;
    }

    @Override
    public Task find(long id) throws DAOException {
        Task task=null;
        try (PreparedStatement psFindById = getPreparedStatement(FIND_QUERY)) {
            psFindById.setLong(1, id);
            psFindById.execute();
            ResultSet rs = psFindById.getResultSet();
            if (rs.next()) {
                task = populateEntity(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: task can not be view ", e);
        }
        return task;
    }

    @Override
    public void update(Task task) throws DAOException {
        try (PreparedStatement psUpdate = getPreparedStatement(UPDATE_QUERY)) {
            psUpdate.setString(1, task.getTitle());
            psUpdate.setString(2, task.getBody());
            psUpdate.setString(3, task.getStartTime());
            psUpdate.setString(4, task.getEndTime());
            psUpdate.setString(5, task.getAnswer());
            psUpdate.setInt(6, task.getMark());
            psUpdate.setString(7, task.getReview());
            psUpdate.setString(8, task.getStatus());
            psUpdate.setLong(9, task.getId());
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: task can not be update ", e);
        }
    }

    @Override
    public int delete(long id) throws DAOException {
        try (PreparedStatement psDelete = getPreparedStatement(DELETE_QUERY)) {
            psDelete.setLong(1, id);
            return psDelete.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: task can not be delete ", e);
        }
    }

    @Override
    public List<Task> findAll() {
        return null;
    }


    public List<Task> findBySubscription(long subscriptionId) throws DAOException {
        List<Task> list;
        try (PreparedStatement psGetAllBySubscription = getPreparedStatement(FIND_BY_SUBSCRIPTION_QUERY)) {
            psGetAllBySubscription.setLong(1, subscriptionId);
            psGetAllBySubscription.execute();
            ResultSet rs = psGetAllBySubscription.getResultSet();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(populateEntity(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: tasks can not be view by subscription ", e);
        }

    }

    public List<Task> findByUser(long userId) throws DAOException {
        List<Task> list;
        try (PreparedStatement psGetAllByUserId = getPreparedStatement(FIND_TASK_BY_USER_QUERY)) {
            psGetAllByUserId.setLong(1, userId);
            psGetAllByUserId.execute();
            ResultSet rs = psGetAllByUserId.getResultSet();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(populateEntity(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: tasks not found ", e);
        }

    }

    private Task populateEntity(ResultSet rs) throws DAOException {
        Task task = new Task();
        try {
            task.setId(rs.getLong("Tid"));
            task.setTitle(rs.getString("title"));
            task.setBody(rs.getString("body"));
            task.setStartTime(rs.getString("start_time"));
            task.setEndTime(rs.getString("end_time"));
            task.setAnswer(rs.getString("answer"));
            task.setMark(rs.getInt("mark"));
            task.setReview(rs.getString("review"));
            task.setStatus(rs.getString("task_status"));
            return task;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: entity Task can not be created ", e);
        }
    }

    public double findAvgMark(long subscriptionId) throws DAOException{
        double avgMark = 0;
        try (PreparedStatement psFindAvgMark = getPreparedStatement(FIND_AVG_MARK)) {
            psFindAvgMark.setLong(1, subscriptionId);
            psFindAvgMark.execute();
            ResultSet rs = psFindAvgMark .getResultSet();
            if (rs.next()) {
                avgMark = rs.getDouble("avg_mark");
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: task can not be view ", e);
        }
        return (double)Math.round(avgMark * 10d) / 10d;
    }
    public String findNumberCompleted(long subscriptionId) throws DAOException{
        int numberCompleted=0;
        int numberTasks = 0;
        try (PreparedStatement psFindNumberCompleted = getPreparedStatement(FIND_NUMBER_COMPLETED);
             PreparedStatement psTotalNumber = getPreparedStatement(FIND_NUMBER_TESTS)) {
            psFindNumberCompleted.setLong(1, subscriptionId);
            psFindNumberCompleted.execute();
            ResultSet rs = psFindNumberCompleted.getResultSet();
            if (rs.next()) {
                numberCompleted = rs.getInt("number_completed");
            }
            psTotalNumber.setLong(1, subscriptionId);
            psTotalNumber.execute();
            rs = psTotalNumber.getResultSet();
            if (rs.next()) {
                numberTasks = rs.getInt("number_tasks");
            }
            return numberCompleted+"/"+numberTasks;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: findNumberCompleted ", e);
        }
    }

    public static TaskDao getInstance() {
        return INSTANCE;
    }
}
