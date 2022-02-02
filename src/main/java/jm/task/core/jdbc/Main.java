package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Создание таблицы User(ов)
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
//        Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userService.saveUser("Иван", "Иванов", (byte) 18);
        userService.saveUser("Петр", "Петров", (byte) 19);
        userService.saveUser("Степан", "Степанов", (byte) 20);
        userService.saveUser("Александр", "Александров", (byte) 21);
//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        System.out.println(userService.getAllUsers());
//        Очистка таблицы User(ов)
        userService.cleanUsersTable();
//        Удаление таблицы
        userService.dropUsersTable();
    }
}
