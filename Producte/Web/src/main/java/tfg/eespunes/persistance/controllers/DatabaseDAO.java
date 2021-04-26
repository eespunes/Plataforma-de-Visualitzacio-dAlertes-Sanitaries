package tfg.eespunes.persistance.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tfg.eespunes.domain.*;

import java.util.List;

@Repository
public class DatabaseDAO {

    private final String INSERT_HEALTHCARE_INSTITUTION = "INSERT INTO HealthcareInstitutions VALUES (?,?,?,?,?,?);";
    private final String INSERT_ROLE = "INSERT INTO Roles VALUES (?,?,?,?);";
    private final String INSERT_EMPLOYEE = "INSERT INTO Employees VALUES (?,?,?,?,?,?,?,?);";
    private final String INSERT_WARNING = "INSERT INTO Warnings VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    private final String FIND_ALL_COUNTRIES = "SELECT * FROM Countries";
    private final String FIND_ALL_HEALTHCARE_INSTITUTIONS = "SELECT * FROM HealthcareInstitutions";
    private final String FIND_ALL_ROLES = "SELECT * FROM Roles";
    private final String FIND_ALL_EMPLOYEES = "SELECT * FROM Employees";
    private final String FIND_ALL_WARNINGS = "SELECT * FROM Warnings";

    private final String FIND_HEALTHCARE_INSTITUTION_BY_ID = "SELECT * FROM HealthcareInstitutions WHERE ins_id=? and ins_countryid=?";
    private final String FIND_ROLE_BY_ID = "SELECT * FROM Roles WHERE rol_name=? and rol_institutionid=? and rol_countryid=?";
    private final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM Employees WHERE emp_id=?";
    private final String FIND_WARNING_BY_ID = "SELECT * FROM Warnings WHERE war_id=?";

    private final String DELETE_HEALTHCARE_INSTITUTION = "DELETE FROM HealthcareInstitutions WHERE ins_id=? and ins_countryid=?";
    private final String DELETE_ROLE = "DELETE FROM Roles WHERE rol_name=? and rol_institutionid=? and rol_countryid=?";
    private final String DELETE_EMPLOYEE = "DELETE FROM Employees WHERE emp_id=?";
    private final String DELETE_WARNING = "DELETE FROM Warnings WHERE war_id=?";

    private final String FIND_ALL_ROLES_OF_HEALTHCARE_INSTITUTIONS = "SELECT * FROM Roles WHERE rol_institutionid=? and rol_countryid=?";
    private final String FIND_ALL_WARNINGS_OF_ROLE = "SELECT * FROM Warnings WHERE war_name=? and war_roleinstitutionid=? and war_rolecountryid=?";

    private final String COUNT_HEALTHCARE_INSTITUTION_BY_COUNTRY = "SELECT COUNT(ins_id) FROM HealthcareInstitutions WHERE ins_countryid=?";
    private final String COUNT_EMPLOYEES = "SELECT COUNT(emp_id) FROM Employees";
    private final String COUNT_WARNINGS = "SELECT COUNT(war_id) FROM Warnings";

    private final JdbcTemplate jdbcTemplate;

    public DatabaseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //MAPPERS
    private RowMapper<Country> countryMapper = (resultSet, i) -> {
        Country country = new Country(
                resultSet.getString("cou_continentID"),
                resultSet.getString("cou_id"),
                resultSet.getString("cou_name"));
        return country;
    };

    private RowMapper<HealthcareInstitution> healthcareInstitutionMapper = (resultSet, i) -> {
        HealthcareInstitution healthcareInstitution = new HealthcareInstitution(
                resultSet.getInt("ins_id"),
                resultSet.getString("ins_countryid"),
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
                resultSet.getString("rol_institutionID"),
                resultSet.getString("rol_countryID"));
        return role;
    };

    private RowMapper<Employee> employeeMapper = (resultSet, i) -> {
        Employee employee = new Employee(
                resultSet.getInt("emp_id"),
                resultSet.getString("emp_username"),
                resultSet.getString("emp_password"),
                resultSet.getString("emp_name"),
                resultSet.getString("emp_surname"),
                resultSet.getString("emp_rolename"),
                resultSet.getInt("emp_roleinstitutionid"),
                resultSet.getString("emp_rolecountryid")
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
                resultSet.getString("war_rolename"),
                resultSet.getInt("war_roleinstitutionid"),
                resultSet.getString("war_rolecountryid")
        );
        return warning;
    };

    //CREATE
    public int insertHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        return jdbcTemplate.update(INSERT_HEALTHCARE_INSTITUTION, getHealthcareInstitutionCountByCountry(healthcareInstitution.getCountryID()), healthcareInstitution.getCountryID(), healthcareInstitution.getName(), healthcareInstitution.getUrl(), healthcareInstitution.getUsername(), healthcareInstitution.getPassword());
    }

    public int insertRole(Role role) {
        return jdbcTemplate.update(INSERT_ROLE, role.getName(), Integer.parseInt(role.getHealthcareInstitutionID()), role.getCountryID(), role.getDescription());
    }

    public int insertEmployee(Employee employee) {
        int id = jdbcTemplate.queryForObject(COUNT_EMPLOYEES, new Object[]{}, Integer.class);
        return jdbcTemplate.update(INSERT_EMPLOYEE, id, employee.getUsername(), employee.getPassword(), employee.getName(), employee.getSurname(), employee.getRoleName(), employee.getRoleInstitutionID(), employee.getRoleCountryID());
    }

    private int getHealthcareInstitutionCountByCountry(String countryID) {
        return jdbcTemplate.queryForObject(COUNT_HEALTHCARE_INSTITUTION_BY_COUNTRY, new Object[]{countryID}, Integer.class);
    }

    public int insertWarning(Warning warning) {
        int id = jdbcTemplate.queryForObject(COUNT_WARNINGS, new Object[]{}, Integer.class);
        return jdbcTemplate.update(INSERT_WARNING, id, warning.getName(), warning.getShortName(), warning.getDescription(), warning.getUri(), warning.getNotificationMessage(), warning.getGreenValue(), warning.getYellowValue(), warning.getRedValue(), 0, warning.getRefreshRate(), warning.getRoleName(), warning.getRoleInstitutionID(), warning.getRoleCountryID());
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
    public HealthcareInstitution findHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.queryForObject(FIND_HEALTHCARE_INSTITUTION_BY_ID, new Object[]{healthcareInstitutionID, countryID}, healthcareInstitutionMapper);
    }

    public Role findRole(String roleName, int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.queryForObject(FIND_ROLE_BY_ID, new Object[]{roleName, healthcareInstitutionID, countryID}, roleMapper);
    }

    public Employee findEmployee(int id) {
        return jdbcTemplate.queryForObject(FIND_EMPLOYEE_BY_ID, new Object[]{id}, employeeMapper);
    }

    public Warning findWarning(int id) {
        return jdbcTemplate.queryForObject(FIND_WARNING_BY_ID, new Object[]{id}, warningMapper);
    }

    //DELETE
    public int deleteHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.update(DELETE_HEALTHCARE_INSTITUTION, healthcareInstitutionID, countryID);
    }

    public int deleteRole(String roleName, int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.update(DELETE_ROLE, roleName, healthcareInstitutionID, countryID);
    }

    public int deleteEmployee(int id) {
        return jdbcTemplate.update(DELETE_EMPLOYEE, id);
    }

    public int deleteWarning(int id) {
        return jdbcTemplate.update(DELETE_WARNING, id);
    }

    //FIND BY
    public List<Role> findAllRolesOfHealthcareInstitution(int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.query(FIND_ALL_ROLES_OF_HEALTHCARE_INSTITUTIONS, new Object[]{healthcareInstitutionID, countryID}, roleMapper);
    }

    public List<Warning> findAllWarningsOfRole(String roleName, int healthcareInstitutionID, String countryID) {
        return jdbcTemplate.query(FIND_ALL_WARNINGS_OF_ROLE, new Object[]{roleName, healthcareInstitutionID, countryID}, warningMapper);
    }
}
