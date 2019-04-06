package com.divashchenko;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FileStorage implements Storage {
    private String fileName;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private int id = 0;
    private Map<Integer, User> userMap = new LinkedHashMap<>();

    public FileStorage(String fileName) {
        this.fileName = fileName;
        usersToJson();
    }

    public void removeAll() {
        fromJsonToUsers();
        userMap.clear();
        usersToJson();
    }

    public void removeUser(int id) {
        fromJsonToUsers();
        userMap.remove(id);
        usersToJson();
    }

    public void removeUserByName(String name) {
        fromJsonToUsers();
        List<User> users = new ArrayList<>(userMap.values());
        for (User user : users) {
            if (user.name.equals(name)) {
                userMap.remove(user.id);
            }
        }
        usersToJson();
    }

    public void addUser(User user) {
        fromJsonToUsers();
        user.id = id++;
        userMap.put(user.id, user);
        usersToJson();
    }

    public void updateUser(User user) {
        fromJsonToUsers();
        userMap.put(user.id, user);
        usersToJson();
    }

    public User getUser(int id) {
        fromJsonToUsers();
        return userMap.get(id);
    }

    public List<User> getAllUsers() {
        fromJsonToUsers();
        return new ArrayList<>(userMap.values());
    }

    private void usersToJson() {
        createFile(gson.toJson(userMap));
    }

    private Map<Integer, User> fromJsonToUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            Type type = new TypeToken<Map<Integer, User>>() {
            }.getType();
            return gson.fromJson(br, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createFile(String fileData) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
