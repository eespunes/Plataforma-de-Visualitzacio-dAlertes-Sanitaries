package tfg.eespunes.persistance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import tfg.eespunes.domain.*;

import java.util.List;

@Repository
public class DatabaseDAO {

    private final String INSERT_HEALTHCARE_INSTITUTION = "INSERT INTO HealthcareInstitutions VALUES (?,?,?,?,?,?);";
    private final String INSERT_ROLE = "INSERT INTO Roles VALUES (?,?,?,?);";
    private final String INSERT_EMPLOYEE = "INSERT INTO Employees VALUES (?,?,?,?,?,?,?);";
    private final String INSERT_WARNING = "INSERT INTO Warnings VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    private final String FIND_ALL_COUNTRIES = "SELECT * FROM Countries";
    private final String FIND_ALL_HEALTHCARE_INSTITUTIONS = "SELECT * FROM HealthcareInstitutions";
    private final String FIND_ALL_ROLES = "SELECT * FROM Roles";
    private final String FIND_ALL_EMPLOYEES = "SELECT * FROM Employees";
    private final String FIND_ALL_WARNINGS = "SELECT * FROM Warnings";

    private final String FIND_COUNTRY_BY_ID = "SELECT * FROM Countries WHERE cou_id=?";
    private final String FIND_HEALTHCARE_INSTITUTION_BY_ID = "SELECT * FROM HealthcareInstitutions WHERE ins_id=? and ins_countryid=?";
    private final String FIND_ROLE_BY_ID = "SELECT * FROM Roles WHERE rol_name=? and rol_institutionid=? and rol_countryid=?";
    private final String FIND_EMPLOYEE_BY_USERNAME = "SELECT * FROM Employees WHERE emp_username=?";
    private final String FIND_WARNING_BY_ID = "SELECT * FROM Warnings WHERE war_id=?";

    private final String DELETE_HEALTHCARE_INSTITUTION = "DELETE FROM HealthcareInstitutions WHERE ins_id=? and ins_countryid=?";
    private final String DELETE_ROLE = "DELETE FROM Roles WHERE rol_name=? and rol_institutionid=? and rol_countryid=?";
    private final String DELETE_ROLE_BY_HEALTHCARE_INSTITUTION = "DELETE FROM Roles WHERE rol_institutionid=? and rol_countryid=?";
    private final String DELETE_EMPLOYEE = "DELETE FROM Employees WHERE emp_username=?";
    private final String DELETE_EMPLOYEE_BY_HEALTHCARE_INSTITUTION = "DELETE FROM Employees WHERE emp_roleinstitutionid=? and emp_rolecountryid=?";
    private final String DELETE_EMPLOYEE_BY_ROLE = "DELETE FROM Employees WHERE emp_rolename=? and emp_roleinstitutionid=? and emp_rolecountryid=?";
    private final String DELETE_WARNING = "DELETE FROM Warnings WHERE war_id=?";
    private final String DELETE_WARNING_BY_HEALTHCARE_INSTITUTION = "DELETE FROM Warnings WHERE war_roleinstitutionid=? and war_rolecountryid=?";
    private final String DELETE_WARNING_BY_ROLE = "DELETE FROM Warnings WHERE war_rolename=? and war_roleinstitutionid=? and war_rolecountryid=?";


    private final String UPDATE_HEALTHCARE_INSTITUTION = "UPDATE HealthcareInstitutions SET ins_url=?, ins_username=?, ins_password=? WHERE ins_id=? and ins_countryid=?";
    private final String UPDATE_ROLE = "UPDATE Roles SET rol_description=? WHERE rol_name=? and rol_institutionid=? and rol_countryid=?";
    private final String UPDATE_EMPLOYEE = "UPDATE Employees SET emp_name=?, emp_surname=?  WHERE emp_username=?";
    private final String UPDATE_WARNING = "UPDATE Warnings SET war_description=?, war_uri=?, war_notificationmessage=?, war_greenvalue=?, war_yellowvalue=?, war_redvalue=?, war_refreshrate=? WHERE war_id=?";

    private final String FIND_ALL_ROLES_OF_HEALTHCARE_INSTITUTIONS = "SELECT * FROM Roles WHERE rol_institutionid=? and rol_countryid=?";
    private final String FIND_ALL_WARNINGS_OF_ROLE = "SELECT * FROM Warnings WHERE war_rolename=? and war_roleinstitutionid=? and war_rolecountryid=?";
    private final String FIND_ALL_EMPLOYEES_OF_ROLE = "SELECT * FROM Employees WHERE emp_rolename=? and emp_roleinstitutionid=? and emp_rolecountryid=?";

    private final String COUNT_HEALTHCARE_INSTITUTION_BY_COUNTRY = "SELECT COUNT(ins_id) FROM HealthcareInstitutions WHERE ins_countryid=?";
    private final String COUNT_WARNINGS = "SELECT COUNT(war_id) FROM Warnings";
    private final String UPDATE_PASSWORD = "UPDATE Employees SET emp_password=? WHERE emp_username=?";
    private final String UPDATE_WARNING_LAST_VALUE = "UPDATE Warnings SET war_lastvalue=? WHERE war_id=?";


    private final JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DatabaseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //MAPPERS
    private final RowMapper<Country> countryMapper = (resultSet, i) -> {
        Country country = new Country(
                resultSet.getString("cou_continentID"),
                resultSet.getString("cou_id"),
                resultSet.getString("cou_name"));
        return country;
    };

    private final RowMapper<HealthcareInstitution> healthcareInstitutionMapper = (resultSet, i) -> {
        HealthcareInstitution healthcareInstitution = new HealthcareInstitution(
                resultSet.getInt("ins_id"),
                findCountry(resultSet.getString("ins_countryid")),
                resultSet.getString("ins_name"),
                resultSet.getString("ins_url"),
                resultSet.getString("ins_username"),
                resultSet.getString("ins_password"));
        return healthcareInstitution;
    };

    private RowMapper<Role> roleMapper = (resultSet, i) -> {
        Role role = new Role(
                resultSet.getString("rol_name"),
                resultSet.getString("rol_description"),
                findHealthcareInstitution(resultSet.getInt("rol_institutionID"), resultSet.getString("rol_countryID")));
        return role;
    };

    private RowMapper<Employee> employeeMapper = (resultSet, i) -> {
        Employee employee = new Employee(
                resultSet.getString("emp_username"),
                resultSet.getString("emp_password"),
                resultSet.getString("emp_name"),
                resultSet.getString("emp_surname"),
                findRole(resultSet.getString("emp_rolename"), resultSet.getInt("emp_roleinstitutionid"), resultSet.getString("emp_rolecountryid"))
        );
        return employee;
    };

    private RowMapper<Warning> warningMapper = (resultSet, i) -> {
        Warning warning = new Warning(
                resultSet.getInt("war_id"),
                resultSet.getString("war_name"),
                resultSet.getString("war_shortname"),
                resultSet.getString("war_description"),
                resultSet.getString("war_uri"),
                resultSet.getString("war_notificationmessage"),
                resultSet.getFloat("war_greenvalue"),
                resultSet.getFloat("war_yellowvalue"),
                resultSet.getFloat("war_redvalue"),
                resultSet.getFloat("war_lastvalue"),
                resultSet.getInt("war_refreshrate"),
                findRole(resultSet.getString("war_rolename"), resultSet.getInt("war_roleinstitutionid"), resultSet.getString("war_rolecountryid"))
        );
        return warning;
    };

    //CREATE
    public int insertHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        int id = getHealthcareInstitutionCountByCountry(healthcareInstitution.getCountry().getId());
        healthcareInstitution.setId(id);
        jdbcTemplate.update(INSERT_HEALTHCARE_INSTITUTION, id, healthcareInstitution.getCountry().getId(), healthcareInstitution.getName(), healthcareInstitution.getUrl(), healthcareInstitution.getUsername(), healthcareInstitution.getPassword());
        Role role = new Role("WEB-ADMIN", "The web administrator of this healthcare institution", healthcareInstitution);
        insertRole(role);
        insertEmployee(new Employee("ADMIN-" + id + "-" + healthcareInstitution.getCountry().getId(), passwordEncoder.encode("admin"), "admin", "admin", role));

        return id;
    }

    public void insertRole(Role role) {
        jdbcTemplate.update(INSERT_ROLE, role.getName(), role.getHealthcareInstitution().getId(), role.getHealthcareInstitution().getCountry().getId(), role.getDescription());
    }

    public void insertEmployee(Employee employee) {
        jdbcTemplate.update(INSERT_EMPLOYEE, employee.getUsername(), employee.getPassword(), employee.getName(), employee.getSurname(), employee.getRole().getName(), employee.getRole().getHealthcareInstitution().getId(), employee.getRole().getHealthcareInstitution().getCountry().getId());
    }

    private int getHealthcareInstitutionCountByCountry(String countryID) {
        return jdbcTemplate.queryForObject(COUNT_HEALTHCARE_INSTITUTION_BY_COUNTRY, new Object[]{countryID}, Integer.class);
    }

    public int insertWarning(Warning warning) {
        int id = jdbcTemplate.queryForObject(COUNT_WARNINGS, new Object[]{}, Integer.class);
        jdbcTemplate.update(INSERT_WARNING, id, warning.getName(), warning.getShortName(), warning.getDescription(), warning.getUri(), warning.getNotificationMessage(), warning.getGreenValue(), warning.getYellowValue(), warning.getRedValue(), 0, warning.getRefreshRate(), warning.getRole().getName(), warning.getRole().getHealthcareInstitution().getId(), warning.getRole().getHealthcareInstitution().getCountry().getId());
        return id;
    }

    //FIND ALL
    public List<Country> findAllCountries() {
        return jdbcTemplate.query(FIND_ALL_COUNTRIES, new Object[]{}, countryMapper);
    }

    public List<HealthcareInstitution> findAllHealthcareInstitutions() {
        return jdbcTemplate.query(FIND_ALL_HEALTHCARE_INSTITUTIONS, new Object[]{}, healthcareInstitutionMapper);
    }

    public List<Role> findAllRoles() {
        return jdbcTemplate.query(FIND_ALL_ROLES, new Object[]{}, roleMapper);
    }

    public List<Employee> findAllEmployees() {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEES, new Object[]{}, employeeMapper);
    }

    public List<Warning> findAllWarnings() {
        return jdbcTemplate.query(FIND_ALL_WARNINGS, new Object[]{}, warningMapper);
    }

    //FIND ONE
    public Country findCountry(String id) {
        return jdbcTemplate.queryForObject(FIND_COUNTRY_BY_ID, new Object[]{id}, countryMapper);
    }

    public HealthcareInstitution findHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.queryForObject(FIND_HEALTHCARE_INSTITUTION_BY_ID, new Object[]{healthcareInstitutionID, countryID}, healthcareInstitutionMapper);
    }

    public Role findRole(String roleName, int healthcareInstitutionID, String countryID) {
        try {
            return jdbcTemplate.queryForObject(FIND_ROLE_BY_ID, new Object[]{roleName, healthcareInstitutionID, countryID}, roleMapper);
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    public Employee findEmployee(String username) {
        try {
            return jdbcTemplate.queryForObject(FIND_EMPLOYEE_BY_USERNAME, new Object[]{username}, employeeMapper);
        } catch (EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    public Warning findWarning(int id) {
        return jdbcTemplate.queryForObject(FIND_WARNING_BY_ID, new Object[]{id}, warningMapper);
    }

    //DELETE
    public void deleteHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        jdbcTemplate.update(DELETE_WARNING_BY_HEALTHCARE_INSTITUTION, healthcareInstitutionID, countryID);
        jdbcTemplate.update(DELETE_EMPLOYEE_BY_HEALTHCARE_INSTITUTION, healthcareInstitutionID, countryID);
        jdbcTemplate.update(DELETE_ROLE_BY_HEALTHCARE_INSTITUTION, healthcareInstitutionID, countryID);
        jdbcTemplate.update(DELETE_HEALTHCARE_INSTITUTION, healthcareInstitutionID, countryID);
    }

    public void deleteRole(String roleName, int healthcareInstitutionID, String countryID) {
        jdbcTemplate.update(DELETE_WARNING_BY_ROLE, roleName, healthcareInstitutionID, countryID);
        jdbcTemplate.update(DELETE_EMPLOYEE_BY_ROLE, roleName, healthcareInstitutionID, countryID);
        jdbcTemplate.update(DELETE_ROLE, roleName, healthcareInstitutionID, countryID);
    }

    public void deleteEmployee(String username) {
        jdbcTemplate.update(DELETE_EMPLOYEE, username);
    }

    public void deleteWarning(int id) {
        jdbcTemplate.update(DELETE_WARNING, id);
    }

    //UPDATE
    public void updateHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        jdbcTemplate.update(UPDATE_HEALTHCARE_INSTITUTION, healthcareInstitution.getUrl(), healthcareInstitution.getUsername(), healthcareInstitution.getPassword(), healthcareInstitution.getId(), healthcareInstitution.getCountry().getId());
    }

    public void updateRole(Role role) {
        jdbcTemplate.update(UPDATE_ROLE, role.getDescription(), role.getName(), role.getHealthcareInstitution().getId(), role.getHealthcareInstitution().getCountry().getId());
    }

    public void updateEmployee(Employee employee) {
        jdbcTemplate.update(UPDATE_EMPLOYEE, employee.getName(), employee.getSurname(), employee.getUsername());
    }

    public void updateWarning(Warning warning) {
        jdbcTemplate.update(UPDATE_WARNING, warning.getDescription(), warning.getUri(), warning.getNotificationMessage(), warning.getGreenValue(), warning.getYellowValue(), warning.getRedValue(), warning.getRefreshRate(), warning.getId());
    }

    //FIND BY
    public List<Role> findAllRolesOfHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.query(FIND_ALL_ROLES_OF_HEALTHCARE_INSTITUTIONS, new Object[]{healthcareInstitutionID, countryID}, roleMapper);
    }

    public List<Warning> findAllWarningsOfRole(String roleName, int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.query(FIND_ALL_WARNINGS_OF_ROLE, new Object[]{roleName, healthcareInstitutionID, countryID}, warningMapper);
    }

    public List<Employee> findAllEmployeesOfRole(String roleName, int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.query(FIND_ALL_EMPLOYEES_OF_ROLE, new Object[]{roleName, healthcareInstitutionID, countryID}, employeeMapper);
    }

    public int updatePassword(String username, String newPassword) {
        return jdbcTemplate.update(UPDATE_PASSWORD, passwordEncoder.encode(newPassword), username);
    }

    public void updateWarningLastValue(int id, float value) {
        System.out.println(value);
        jdbcTemplate.update(UPDATE_WARNING_LAST_VALUE, value, id);

    }
}
