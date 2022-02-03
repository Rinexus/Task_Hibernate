package jm.task.core.jdbc.util;

import org.hibernate.*;
import jm.task.core.jdbc.model.User;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {

    public static Connection connect() {
        String userName = "root";
        String password = "1234";
        String connectionUrl = "jdbc:mysql://localhost:3306/mysql";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =  DriverManager.getConnection(connectionUrl, userName, password);
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mysql");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                config.setProperties(settings);
                config.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties()).build();
                sessionFactory = config.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

