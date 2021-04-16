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

    //CREATE
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

    //GET ALL
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

// GET ONE
    public HealthcareInstitution getHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return databaseDAO.findHealthcareInstitution(healthcareInstitutionID, countryID);
    }

    public Role getRole(String roleName, int healthcareInstitutionID, String countryID) {
        return databaseDAO.findRole(roleName, healthcareInstitutionID, countryID);
    }

    public Employee getEmployee(int id) {
        return databaseDAO.findEmployee(id);
    }

    public Warning getWarning(int id) {
        return databaseDAO.findWarning(id);
    }


    public List<Role> getAllRolesOfHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return databaseDAO.findAllRolesOfHealthcareInstitution(healthcareInstitutionID, countryID);
    }

    public List<Warning> getAllWarningsOfRole(String roleName, int healthcareInstitutionID, String countryID) {
        return databaseDAO.findAllWarningsOfRole(roleName, healthcareInstitutionID, countryID);
    }
}
