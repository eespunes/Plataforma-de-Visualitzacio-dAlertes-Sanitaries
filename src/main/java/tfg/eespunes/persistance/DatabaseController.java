package tfg.eespunes.persistance;

import com.sun.org.apache.bcel.internal.generic.RETURN;
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
    public int addHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        return databaseDAO.insertHealthcareInstitution(healthcareInstitution);
    }

    public void addRole(Role role) {
        databaseDAO.insertRole(role);
    }

    public void addEmployee(Employee employee) {
        databaseDAO.insertEmployee(employee);
    }

    public int addWarning(Warning warning) {
        return databaseDAO.insertWarning(warning);
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
    public Country getCountry(String countryId) {
        return databaseDAO.findCountry(countryId);
    }

    public HealthcareInstitution getHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return databaseDAO.findHealthcareInstitution(healthcareInstitutionID, countryID);
    }

    public Role getRole(String roleName, int healthcareInstitutionID, String countryID) {
        return databaseDAO.findRole(roleName, healthcareInstitutionID, countryID);
    }

    public Employee getEmployee(String username) {
        return databaseDAO.findEmployee(username);
    }

    public Warning getWarning(int id) {
        return databaseDAO.findWarning(id);
    }

    //EDIT
    public void editHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        databaseDAO.updateHealthcareInstitution(healthcareInstitution);
    }

    public void editRole(Role role) {
        databaseDAO.updateRole(role);
    }

    public void editEmployee(Employee employee) {
        databaseDAO.updateEmployee(employee);
    }

    public void editWarning(Warning warning) {
        databaseDAO.updateWarning(warning);
    }

    // DELETE
    public void deleteHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        databaseDAO.deleteHealthcareInstitution(healthcareInstitutionID, countryID);
    }

    public void deleteRole(String roleName, int healthcareInstitutionID, String countryID) {
        databaseDAO.deleteRole(roleName, healthcareInstitutionID, countryID);
    }

    public void deleteEmployee(String username) {
        databaseDAO.deleteEmployee(username);
    }

    public void deleteWarning(int id) {
        databaseDAO.deleteWarning(id);
    }

    //GET SOME
    public List<Role> getAllRolesOfHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return databaseDAO.findAllRolesOfHealthcareInstitution(healthcareInstitutionID, countryID);
    }

    public List<Warning> getAllWarningsOfRole(String roleName, int healthcareInstitutionID, String countryID) {
        return databaseDAO.findAllWarningsOfRole(roleName, healthcareInstitutionID, countryID);
    }

    public List<Employee> getAllEmployeesOfRole(String roleName, int healthcareInstitutionID, String countryID) {
        return databaseDAO.findAllEmployeesOfRole(roleName, healthcareInstitutionID, countryID);
    }

    public int changePassword(String username, String newPassword) {
        return databaseDAO.updatePassword(username, newPassword);
    }

    public void setWarningLastValue(int id, float value) {
        databaseDAO.updateWarningLastValue(id,value);
    }
}
