package am.picsartacademy.hrmanagement.service;

import am.picsartacademy.hrmanagement.dao.ManagerDao;
import am.picsartacademy.hrmanagement.entity.Manager;

import java.util.List;

public class ManagerService {

    private final ManagerDao managerDao;

    public ManagerService() {
        this.managerDao = new ManagerDao();
    }

    public void saveManager(Manager manager) {
        if (validateManager(manager)) {
            managerDao.saveManager(manager);
            System.out.println("Manager saved successfully.");
        } else {
            System.out.println("Manager validation failed.");
        }
    }

    public void updateManager(Manager manager) {
        if (validateManager(manager)) {
            managerDao.updateManager(manager);
            System.out.println("Manager updated successfully.");
        } else {
            System.out.println("Manager validation failed.");
        }
    }

    public Manager findManagerById(Long id) {
        return managerDao.findManagerById(id);
    }

    public void deleteManager(Long id) {
        Manager manager = managerDao.findManagerById(id);
        if (manager != null) {
            managerDao.deleteManager(manager);
            System.out.println("Manager deleted successfully.");
        } else {
            System.out.println("Manager not found.");
        }
    }

    public List<Manager> findAllManagers() {
        return managerDao.findAllManagers();
    }

    // Validation logic for Manager
    private boolean validateManager(Manager manager) {
        if (manager.getName() == null || manager.getName().trim().isEmpty()) {
            System.out.println("Manager name is required.");
            return false;
        }

        if (manager.getManagementLevel() == null) {
            System.out.println("Manager management level is required.");
            return false;
        }
        return true;
    }
}
