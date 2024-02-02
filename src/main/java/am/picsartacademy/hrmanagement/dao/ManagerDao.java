package am.picsartacademy.hrmanagement.dao;

import am.picsartacademy.hrmanagement.entity.Manager;
import am.picsartacademy.hrmanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManagerDao {

    public void saveManager(Manager manager) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Save the manager object
            session.save(manager);
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateManager(Manager manager) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Update the manager object
            session.update(manager);
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Manager findManagerById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Manager.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteManager(Manager manager) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Delete the manager object
            session.delete(manager);
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Manager> findAllManagers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Manager", Manager.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Example of a custom query to find managers by their management level
    public List<Manager> findManagersByLevel(Manager.ManagementLevel level) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT m FROM Manager m WHERE m.managementLevel = :level", Manager.class)
                    .setParameter("level", level)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
