package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Session;
import java.util.ArrayList;
import jm.task.core.jdbc.util.Util;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory();) {
            User user = new User();
            transaction = session.beginTransaction();
            session.createSQLQuery(String.format("CREATE TABLE IF NOT EXISTS  %s ( id BIGINT not NULL AUTO_INCREMENT, name VARCHAR(40), lastName VARCHAR(40), age TINYINT, PRIMARY KEY ( id ))",
                    user.getClass().getSimpleName())).executeUpdate();
            transaction.commit();
            System.out.println("таблица пользователей создана");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory();) {
            User user = new User();
            transaction = session.beginTransaction();
            session.createSQLQuery ("drop table if exists " + user.getClass().getSimpleName()).executeUpdate();
            transaction.commit();
            System.out.println("таблица пользователей удалена");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory();) {
            User user = new User(name, lastName, age);
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("Сохранён пользователь " + name + " " + lastName);
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory();) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println(id + " удалён");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory();) {
            User user = new User();
            transaction = session.beginTransaction();
            result.addAll(session.createQuery("FROM " + user.getClass().getSimpleName()).getResultList());
            transaction.commit();
            System.out.println("\n");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory();) {
            User user = new User();
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM " + user.getClass().getSimpleName()).executeUpdate();
            transaction.commit();
            System.out.println("таблица очищена");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
