package by.kastsiuchenka.onlinetraining.service.impl;


import by.kastsiuchenka.onlinetraining.dao.DAOException;
import by.kastsiuchenka.onlinetraining.dao.SubscriptionDao;
import by.kastsiuchenka.onlinetraining.dao.TaskDao;
import by.kastsiuchenka.onlinetraining.entity.Subscription;
import by.kastsiuchenka.onlinetraining.entity.Task;
import by.kastsiuchenka.onlinetraining.service.TaskService;
import by.kastsiuchenka.onlinetraining.service.ServiceException;
import by.kastsiuchenka.onlinetraining.service.TransactionHelper;

import java.util.List;

public class TaskServiceImpl extends TransactionHelper implements TaskService {
    private static final TaskService INSTANCE = new TaskServiceImpl();

    private TaskDao taskDao = TaskDao.getInstance();
    private SubscriptionDao subscriptionDao = SubscriptionDao.getInstance();

    @Override
    public Task createTask(String title, String body, String startTime, String endTime, String idSubscription) throws ServiceException {
        Task task = new Task();
        task.setTitle(title);
        task.setBody(body);
        task.setStartTime(startTime);
        task.setEndTime(endTime);
        beginTransaction();
        try {
            task = taskDao.save(task, Long.parseLong(idSubscription));
            Subscription subscription = subscriptionDao.find(Long.parseLong(idSubscription));
            subscription.setAvgMark(taskDao.findAvgMark(Long.parseLong(idSubscription)));
            subscription.setNumberCompleted(taskDao.findNumberCompleted(Long.parseLong(idSubscription)));
            subscriptionDao.update(subscription);
            commitTransaction();
        } catch (DAOException e) {
            rollbackTransaction();
            throw new ServiceException("Error creating Task: " + e.getMessage(), e);
        } finally {
            endTransaction();
        }
        return task;
    }

    @Override
    public Task find(String id) throws ServiceException {
        try {
            return taskDao.find(Long.parseLong(id));
        } catch (DAOException e) {
            throw new ServiceException("Error find Task: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(String id, String title, String body, String startTime, String endTime, String answer, String mark, String review, String status, String subscriptionId) throws ServiceException {
        Task task = new Task();
        task.setId(Long.parseLong(id));
        task.setTitle(title);
        task.setBody(body);
        task.setStartTime(startTime);
        task.setEndTime(endTime);
        task.setAnswer(answer);
        task.setMark(Integer.parseInt(mark));
        task.setReview(review);
        task.setStatus(status);
        beginTransaction();
        try {
            taskDao.update(task);
            Subscription subscription = subscriptionDao.find(Long.parseLong(subscriptionId));
            subscription.setAvgMark(taskDao.findAvgMark(Long.parseLong(subscriptionId)));
            subscription.setNumberCompleted(taskDao.findNumberCompleted(Long.parseLong(subscriptionId)));
            subscriptionDao.update(subscription);
            commitTransaction();
        } catch (DAOException e) {
            rollbackTransaction();
            throw new ServiceException("Error updating Task: " + e.getMessage(), e);
        } finally {
            endTransaction();
        }
    }

    @Override
    public int delete(long id) throws ServiceException {
        try {
            return taskDao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Error deleting Task: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Task> findBySubscription(String subscriptionId) throws ServiceException {
        try {
            return taskDao.findBySubscription(Long.parseLong(subscriptionId));
        } catch (DAOException e) {
            throw new ServiceException("Error find Tasks: " + e.getMessage(), e);
        }
    }

    public static TaskService getInstance() {
        return INSTANCE;
    }
}
