package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl process;
    public  UserServiceImpl() {
        process = new UserDaoJDBCImpl();
    }

    public void createUsersTable() {
        process.createUsersTable();
    }

    public void dropUsersTable() {
        process.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        process.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        process.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return process.getAllUsers();
    }

    public void cleanUsersTable() {
        process.cleanUsersTable();
    }
}
