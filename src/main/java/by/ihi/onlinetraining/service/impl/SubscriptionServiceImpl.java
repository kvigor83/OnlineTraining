package by.ihi.onlinetraining.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import by.ihi.onlinetraining.dao.SubscriptionDao;
import by.ihi.onlinetraining.dao.DAOException;
import by.ihi.onlinetraining.entity.Subscription;
import by.ihi.onlinetraining.service.ServiceException;
import by.ihi.onlinetraining.service.SubscriptionService;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private static final SubscriptionService INSTANCE = new SubscriptionServiceImpl();
    private SubscriptionDao subscriptionDao = SubscriptionDao.getInstance();

    private SubscriptionServiceImpl() {
    }

    @Override
    public Subscription createSubscription(long userId, long courseId) throws ServiceException {
        Subscription subscription = new Subscription();
        subscription.setDateStart(getFormattedDate());
        try {
            return subscriptionDao.save(subscription, userId, courseId);
        } catch (DAOException e) {
            throw new ServiceException("Error create Subscription: " + e.getMessage(), e);
        }
    }

    @Override
    public Subscription getSubscription(long id) throws ServiceException {
        try {
            return subscriptionDao.find(id);
        } catch (DAOException e) {
            throw new ServiceException("Error find Subscription: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Subscription subscription) throws ServiceException {
        try {
            subscriptionDao.update(subscription);
        } catch (DAOException e) {
            throw new ServiceException("Error update Subscription: " + e.getMessage(), e);
        }
    }

    @Override
    public int delete(long id) throws ServiceException {
        try {
            return subscriptionDao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Error delete Subscription: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Subscription> findByStudent(long userId) throws ServiceException {
        try {
            return subscriptionDao.findByUser(userId);
        } catch (DAOException e) {
            throw new ServiceException("Error find Subscription: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Subscription> findByTutor(long tutorId) throws ServiceException {
        try {
            return subscriptionDao.findByTutor(tutorId);
        } catch (DAOException e) {
            throw new ServiceException("Error find Subscription: " + e.getMessage(), e);
        }
    }

    private String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(new Date());
    }

    public static SubscriptionService getInstance() {
        return INSTANCE;
    }

}
