package am.picsartacademy.hrmanagement.dao;

import am.picsartacademy.hrmanagement.entity.Project;
import am.picsartacademy.hrmanagement.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjectDao {

    public void saveProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Save the project object
            session.save(project);
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Update the project object
            session.update(project);
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Project findProjectById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Project.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteProject(Project project) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Delete the project object
            session.delete(project);
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Project> findAllProjects() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Project", Project.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Additional functionality: Find projects by name
    public List<Project> findProjectsByName(String projectName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT p FROM Project p WHERE p.projectName LIKE :projectName", Project.class)
                    .setParameter("projectName", "%" + projectName + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
