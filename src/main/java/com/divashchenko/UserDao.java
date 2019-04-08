package com.divashchenko;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Storage {
    private Connection connection;
    private int id = 0;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public UserDao() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "root");
        createUsersTable();
    }

    public void removeAll() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users;");
        }
    }

    public void removeUser(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE _id = '" + id + "'");
        }
    }

    public void removeUserByName(String name) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE name = '" + name + "'");
        }
    }

    public void addUser(User user) throws SQLException {
        user.id = id++;

        try (Statement statement = connection.createStatement()) {
            String request = String.format("INSERT INTO users VALUES ('%d', '%s', '%d');", user.id, user.name, user.age);
            statement.execute(request);
        }
    }

    public void updateUser(User user) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE users SET name = '" + user.name + "', age = '" + user.age + "' WHERE _id = " + user.id);
        }
    }

    public User getUser(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String request = String.format("SELECT * FROM users WHERE _id = '%d';", id);
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                int userId = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                return new User(userId, name, age);
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String request = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                int userId = resultSet.getInt("_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                users.add(new User(userId, name, age));
            }
        }
        return users;
    }

    private void createUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (\n" +
                    "_id int PRIMARY KEY,\n" +
                    "name varchar(100),\n" +
                    "age int\n" +
                    ");");
        }
    }
}
