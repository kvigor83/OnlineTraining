package by.kastsiuchenka.onlinetraining.service;


import by.kastsiuchenka.onlinetraining.entity.Task;

import java.util.List;

public interface TaskService {


    Task createTask(String title, String body, String startTime, String endTime, String idSubscription) throws ServiceException;

    Task find(String id) throws ServiceException;

    int delete(long id) throws ServiceException;

    void update(String id, String title, String body, String startTime, String endTime, String answer, String mark, String review, String status, String subscriptionId) throws ServiceException;

    List<Task> findBySubscription(String subscriptionId) throws ServiceException;
}
