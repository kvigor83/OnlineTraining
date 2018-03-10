package by.kastsiuchenka.onlinetraining.service;

import by.kastsiuchenka.onlinetraining.entity.Subscription;


import java.util.List;

public interface SubscriptionService {

    Subscription createSubscription(long userId, long courseId) throws ServiceException;

    Subscription getSubscription(long id) throws ServiceException;

    void update(Subscription subscription) throws ServiceException;

    int delete(long id) throws ServiceException;

    List<Subscription> findByStudent(long studentId) throws ServiceException;

    List<Subscription> findByTutor(long tutorId) throws ServiceException;
}