package com.divashchenko;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UserDao dao = new UserDao();
            dao.removeAll();

            User testUser = new User("Alex", 20);
            User testUser2 = new User("Olga", 25);

            dao.addUser(testUser);
            testUser.name = "Alex Test";
            dao.updateUser(testUser);
            dao.removeUser(0);

            dao.addUser(testUser);
            dao.addUser(testUser2);

            User user = dao.getUser(2);
            System.out.println(user);

            List<User> users = dao.getAllUsers();
            System.out.println(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
