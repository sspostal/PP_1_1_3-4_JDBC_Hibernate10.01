package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


public class Main {
    public static void main(String[] args) {


    UserDao userDao = new UserDaoJDBCImpl();

    userDao.createUsersTable();

    userDao.saveUser("dff", "fff", (byte) 20);
    userDao.saveUser("fff", "Gof", (byte) 13);
    userDao.saveUser("Name3", "LastName3", (byte) 31);
    userDao.saveUser("Name4", "LastName4", (byte) 38);

    userDao.removeUserById(1);
    userDao.getAllUsers();
    userDao.cleanUsersTable();
    userDao.dropUsersTable();

    }
}