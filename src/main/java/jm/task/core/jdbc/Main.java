package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Создание таблицы User(ов)
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
//        Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        us.saveUser("Иван", "Иванов", (byte) 18);
        us.saveUser("Петр", "Петров", (byte) 19);
        us.saveUser("Степан", "Степанов", (byte) 20);
        us.saveUser("Александр", "Александров", (byte) 21);
//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        System.out.println(us.getAllUsers());
//        Очистка таблицы User(ов)
        us.cleanUsersTable();
//        Удаление таблицы
        us.dropUsersTable();
    }
}
