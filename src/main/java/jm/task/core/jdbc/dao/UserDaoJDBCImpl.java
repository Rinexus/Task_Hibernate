package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.executeUpdate("create table if not exists Users (id bigint not null auto_increment," +
                    " name char(30) not null, lastName char(30) not null,age tinyint (3) not null, primary key (id) );");
        } catch (SQLException e) {
            System.out.println("При создании таблицы возникла ошибка");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.executeUpdate("drop table if exists Users");
        } catch (SQLException e) {
            System.out.println("При удалении таблицы возникла ошибка");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String SQL_INSERT = "insert into Users (name, lastName, age) VALUES (?,?,?)";
        try (PreparedStatement statement = Util.connect().prepareStatement(SQL_INSERT)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("При добавлении пользователя возникла ошибка");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        final String SQL_DELETE = "DELETE FROM Users where id = ?";
        try (PreparedStatement statement = Util.connect().prepareStatement(SQL_DELETE)) {
            statement.setLong(1, id);
//            System.out.println("User удален");
//            Util.connect().close();
//            statement.close();
        } catch (SQLException e) {
            System.out.println("При удалении пользователя возникла ошибка");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = Util.connect().createStatement()) {
            ResultSet rs = statement.executeQuery("select * from Users");
            List<User> usersTable = new ArrayList<>();
            while (rs.next()) {
                usersTable.add(new User(rs.getString("name"), rs.getString("lastName"),
                        rs.getByte("age"), rs.getLong("id")));
            }
            return usersTable;
        } catch (SQLException e) {
            System.out.println("При получении таблицы возникла ошибка");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.connect().createStatement()) {
            statement.executeUpdate("DELETE from Users");
//            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.out.println("При очистке таблицы возникла ошибка");
            e.printStackTrace();
        }
    }
}
