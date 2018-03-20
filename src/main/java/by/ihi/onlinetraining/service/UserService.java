package by.ihi.onlinetraining.service;


import by.ihi.onlinetraining.entity.User;

import java.util.List;


public interface UserService {

    User findByLogin(String login)throws ServiceException;
    void update(long id,String fio, String login, String password, String email,String role, String status) throws ServiceException;
    User createUser(String fio, String login, String password, String email) throws ServiceException;
    List<User> findAll() throws ServiceException;
    List<User> findByRole(String role) throws ServiceException;
    void delete(long userId) throws ServiceException;

}
