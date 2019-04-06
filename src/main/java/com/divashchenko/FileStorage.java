package com.divashchenko;

import java.util.List;

public class FileStorage implements Storage {
    private String fileName;

    public FileStorage(String fileName) {
        this.fileName = fileName;
    }

    public void removeAll() {

    }

    public void removeUser(int id) {

    }

    public void removeUserByName(String name) {

    }

    public void addUser(User user) {

    }

    public void updateUser(User user) {

    }

    public User getUser(int id) {
        return null;
    }

    public List<User> getAllUsers() {
        return null;
    }
}
