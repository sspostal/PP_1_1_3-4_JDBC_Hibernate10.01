package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {


        userService.createUsersTable();

        userService.saveUser("Lebron", "James", (byte) 40);
        userService.saveUser("Michael", "Jordan", (byte) 61);
        userService.saveUser("Kobe", "Bryant", (byte) 42);
        userService.saveUser("Stephen", "Curry", (byte) 36);

        userService.removeUserById(1);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}