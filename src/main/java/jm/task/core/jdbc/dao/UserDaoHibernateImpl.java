package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(30) NOT NULL," +
                " lastName VARCHAR(30) NOT NULL," +
                " age TINYINT (3) NOT NULL," +
                " PRIMARY KEY (id))");

        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        NativeQuery sqlQuery = session.createNativeQuery("DROP TABLE IF EXISTS Users");
        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();

        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();

        transaction.commit();
        session.close();
    }
}
