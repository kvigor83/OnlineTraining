package by.ihi.onlinetraining.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.ihi.onlinetraining.entity.User;


public class UserDao extends AbstractDao<User> {
    private final static UserDao INSTANCE = new UserDao();

    private static final String FIND_USER_BY_LOGIN_QUERY = "SELECT * FROM users WHERE login=?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET fio=?, login=?,password=?, email=?, role=? ,status=? WHERE Uid=?";
    private static final String SAVE_USER_QUERY = "INSERT INTO users(fio, login, password, email, role, status) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE UiD=?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    private static final String FIND_USERS_BY_ROLE_QUERY = "SELECT * FROM users WHERE role=?";
    private static final String FIND_USERS_BY_LIST_QUERY = "SELECT * FROM users WHERE Uid IN (";

    private UserDao() {

    }

    public User findByLogin(String login) throws DAOException {
        PreparedStatement psFindByLogin = getPreparedStatement(FIND_USER_BY_LOGIN_QUERY);
        try {
            psFindByLogin.setString(1, login);
            ResultSet rs = psFindByLogin.executeQuery();
            if (rs.next()) {
                return populateUser(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: User can not be found by login " + login, e);
        } finally {
            closeStatement(psFindByLogin);
        }
    }

    @Override
    public User save (User user) throws DAOException {
        PreparedStatement psSave = getPreparedStatement(SAVE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
        try {
            psSave.setString(1, user.getFio());
            psSave.setString(2, user.getLogin());
            psSave.setString(3, user.getPassword());
            psSave.setString(4, user.getEmail());
            psSave.setString(5, user.getRole());
            psSave.setString(6, user.getStatus());
            psSave.executeUpdate();
            ResultSet rs = psSave.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DAOException("Error saving User ", e);
        } finally {
            closeStatement(psSave);
        }
        return new User();
    }

    @Override
    public User find(long id) throws DAOException {
        return null;
    }

    @Override
    public void update(User user) throws DAOException {
        PreparedStatement psUpdate = getPreparedStatement(UPDATE_USER_QUERY);
        try {
            psUpdate.setString(1, user.getFio());
            psUpdate.setString(2, user.getLogin());
            psUpdate.setString(3, user.getPassword());
            psUpdate.setString(4, user.getEmail());
            psUpdate.setString(5, user.getRole());
            psUpdate.setString(6, user.getStatus());
            psUpdate.setLong(7, user.getId());
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: user can not be update ", e);
        }

    }

    @Override
    public int delete(long id) throws DAOException {
        try(PreparedStatement psDelete = getPreparedStatement(DELETE_USER_QUERY)) {
            psDelete.setLong(1, id);
            return psDelete.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: user can not be delete ", e);
        }
    }

    private User populateUser(ResultSet rs) throws DAOException {
        User entity = new User();
        try {
            entity.setId(rs.getLong(1));
            entity.setFio(rs.getString(2));
            entity.setLogin(rs.getString(3));
            entity.setPassword(rs.getString(4));
            entity.setEmail(rs.getString(5));
            entity.setRole(rs.getString(6));
            entity.setStatus(rs.getString(7));
        } catch (SQLException e) {
            throw new DAOException("Error creating entity User ", e);
        }
        return entity;
    }

    @Override
    public List<User> findAll() throws DAOException {
        PreparedStatement psGetAll = getPreparedStatement(FIND_ALL_USERS_QUERY);
        List<User> list = new ArrayList<>();
        try {
            psGetAll.execute();
            ResultSet rs = psGetAll.getResultSet();
            while (rs.next()) {
                list.add(populateUser(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error getting Users", e);
        } finally {
            closeStatement(psGetAll);
        }
        return list;
    }

    public List<User> findByRole(String role) throws DAOException {
        PreparedStatement psFindByRole = getPreparedStatement(FIND_USERS_BY_ROLE_QUERY );
        List<User> list = new ArrayList<>();
        try {
            psFindByRole.setString(1, role);
            psFindByRole.execute();
            ResultSet rs = psFindByRole.getResultSet();
            while (rs.next()) {
                list.add(populateUser(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error in DAO: User can not be found by role " + role, e);
        } finally {
            closeStatement(psFindByRole);
        }
        return list;
    }

    public List<User> findByList(String[] value) throws DAOException {
        List<User> list = new ArrayList<>();
        int length = value.length;
        StringBuilder getByListQuery  = new StringBuilder(FIND_USERS_BY_LIST_QUERY);
        for (int i = 0; i < length - 1; i++) {
            getByListQuery.append(value[i]).append(",");
        }
        getByListQuery.append(value[length - 1]).append(")");
        PreparedStatement psGetByList = getPreparedStatement(getByListQuery.toString());
        try {
            psGetByList.execute();
            ResultSet rs = psGetByList.getResultSet();
            while (rs.next()) {
                list.add(populateUser(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Error getting Users by list Id", e);
        } finally {
            closeStatement(psGetByList);
        }
        return list;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

}