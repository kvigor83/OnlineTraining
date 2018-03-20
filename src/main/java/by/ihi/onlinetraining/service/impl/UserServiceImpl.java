package by.kastsiuchenka.onlinetraining.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import by.kastsiuchenka.onlinetraining.dao.DAOException;
import by.kastsiuchenka.onlinetraining.dao.UserDao;
//import entity.Product;
//import entity.Question;
import by.kastsiuchenka.onlinetraining.entity.User;
import by.kastsiuchenka.onlinetraining.service.ServiceException;
import by.kastsiuchenka.onlinetraining.service.TransactionHelper;
import by.kastsiuchenka.onlinetraining.service.UserService;

public class UserServiceImpl extends TransactionHelper implements UserService {
    private static final UserService INSTANCE = new UserServiceImpl();
    private UserDao userDao = UserDao.getInstance();

    @Override
    public User findByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException("Error in Dao: Error finding User: " + e.getMessage(), e);
        }
    }

    @Override
    public User createUser(String fio, String login, String password, String email) throws ServiceException {
        User user = new User();
        user.setFio(fio);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("user");
        user.setStatus(getFormattedDate());
        try {
            return userDao.save(user);
        } catch (DAOException e) {
            throw new ServiceException("Error in Dao: Error creating User: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(long id, String fio, String login, String password, String email, String role, String status) throws ServiceException {
        User user = new User();
        user.setId(id);
        user.setFio(fio);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        user.setStatus(status);
        try {
            userDao.update(user);
        } catch (DAOException e) {
            throw new ServiceException("Error updating User: " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Error in Dao: Error find Users: " + e.getMessage(), e);
        }
    }

    @Override
    public List<User> findByRole(String role) throws ServiceException {
        try {
            return userDao.findByRole(role);
        } catch (DAOException e) {
            throw new ServiceException("Error in Dao: Error find User: " + e.getMessage(), e);
        }

    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Error deleting User: " + e.getMessage(), e);
        }
    }

    private String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return sdf.format(new Date());
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
