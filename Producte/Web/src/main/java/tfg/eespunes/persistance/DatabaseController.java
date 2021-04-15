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
        public void addHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
            databaseDAO.insertHealthcareInstitution(healthcareInstitution);
        }
    public void addRole(Role role) {
        databaseDAO.insertRole(role);
    }
    public void addEmployee(Employee employee) {
        databaseDAO.insertEmployee(employee);
    }
    public void addWarning(Warning warning) {
        databaseDAO.insertWarning(warning);
    }

    public List<Country> getAllCountries() {
        return databaseDAO.findAllCountries();
    }
    public List<HealthcareInstitution> getAllHealthcareInstitutions() {
        return databaseDAO.findAllHealthcareInstitutions();
    }
    public List<Role> getAllRoles() {
        return databaseDAO.findAllRoles();
    }

    public List<Employee> getAllEmployees() {
        return databaseDAO.findAllEmployees();
    }

    public List<Warning> getAllWarnings() {
        return databaseDAO.findAllWarnings();
    }
}
