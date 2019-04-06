package com.divashchenko;

public class Main {
    public static void main(String[] args) {
        FileStorage fileStorage = new FileStorage("Test.txt");

        User user1 = new User("Alex", 20);
        User user2 = new User("Mark", 30);
        User user3 = new User("Kate", 25);
        User user4 = new User("Nika", 35);

        System.out.println("addUser test");
        fileStorage.addUser(user1);
        fileStorage.addUser(user2);
        fileStorage.addUser(user3);
        fileStorage.addUser(user4);
        System.out.println(fileStorage.getAllUsers().toString());

        System.out.println();
        System.out.println("removeUser test");
        fileStorage.removeUser(2);
        fileStorage.removeUserByName("Nika");
        System.out.println(fileStorage.getAllUsers().toString());

        System.out.println();
        System.out.println("removeAll test");
        fileStorage.removeAll();
        System.out.println(fileStorage.getAllUsers().toString());

        System.out.println();
        System.out.println("addUser with new ID test");
        fileStorage.addUser(user3);
        fileStorage.addUser(user4);
        System.out.println(fileStorage.getAllUsers().toString());

        System.out.println();
        System.out.println("updateUser test");
        user4.age = 100500;
        fileStorage.updateUser(user4);
        System.out.println(fileStorage.getAllUsers().toString());

        System.out.println();
        System.out.println("getUser test");
        User user5 = fileStorage.getUser(5);
        System.out.println(user5);
    }
}
