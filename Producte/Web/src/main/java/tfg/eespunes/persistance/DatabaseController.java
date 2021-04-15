package tfg.eespunes.persistance;

import org.springframework.stereotype.Service;
import tfg.eespunes.domain.*;
import tfg.eespunes.persistance.controllers.DatabaseDAO;

import java.util.List;

@Service("DatabaseController")
public class DatabaseController {

    private final DatabaseDAO databaseDAO;

    public DatabaseController(DatabaseDAO databaseDAO) {
        this.databaseDAO = databaseDAO;
    }

    public List<Country> getAllCountries() {
        return databaseDAO.findAllCountries();
    }

    public int addHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        return databaseDAO.insertHealthcareInstitution(healthcareInstitution);
    }

    public List<HealthcareInstitution> getAllHealthcareInstitutions() {
        return databaseDAO.findAllHealthcareInstitutions();
    }

    public int addRole(Role role) {
        return databaseDAO.insertRole(role);
    }

    public int addEmployee(Employee employee) {
        return databaseDAO.insertEmployee(employee);
    }

    public List<Role> getAllRoles() {
        return databaseDAO.findAllRoles();
    }

    public int addWarning(Warning warning) {
        return databaseDAO.insertWarning(warning);
    }
}
