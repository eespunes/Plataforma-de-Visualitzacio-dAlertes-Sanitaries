package tfg.eespunes.persistance.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tfg.eespunes.domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    private final String COUNT_HEALTHCARE_INSTITUTION_BY_COUNTRY = "SELECT COUNT(ins_id) FROM HealthcareInstitutions WHERE ins_countryid=?";
    private final String COUNT_EMPLOYEES = "SELECT COUNT(emp_id) FROM Employees";
    private final String COUNT_WARNINGS = "SELECT COUNT(war_id) FROM Warnings";

    private final JdbcTemplate jdbcTemplate;

    public DatabaseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Country countryMapper(ResultSet resultSet) throws SQLException {
        Country country = new Country(resultSet.getString(0), resultSet.getString(1), resultSet.getString(2));
        return country;
    }

    private RowMapper<Country> countryMapperList = (resultSet, i) -> {
        Country country = new Country(
                resultSet.getString("cou_continentID"),
                resultSet.getString("cou_id"),
                resultSet.getString("cou_name"));
        return country;
    };

    private RowMapper<HealthcareInstitution> healthcareInstitutionsMapperList = (resultSet, i) -> {
        HealthcareInstitution healthcareInstitution = new HealthcareInstitution(
                resultSet.getInt("ins_id"),
                resultSet.getString("ins_countryid"),
                resultSet.getString("ins_name"),
                resultSet.getString("ins_url"),
                resultSet.getString("ins_username"),
                resultSet.getString("ins_password"));
        return healthcareInstitution;
    };

    private RowMapper<Role> rolesMapperList = (resultSet, i) -> {
        Role role = new Role(
                resultSet.getString("rol_name"),
                resultSet.getString("rol_description"),
                resultSet.getString("rol_institutionID"),
                resultSet.getString("rol_countryID"));
        return role;
    };

    public List<Country> findAllCountries() {
        return jdbcTemplate.query(FIND_ALL_COUNTRIES, new Object[]{}, countryMapperList);
    }

    public int insertHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        return jdbcTemplate.update(INSERT_HEALTHCARE_INSTITUTION, getHealthcareInstitutionCountByCountry(healthcareInstitution.getCountryID()), healthcareInstitution.getCountryID(), healthcareInstitution.getName(), healthcareInstitution.getUrl(), healthcareInstitution.getUsername(), healthcareInstitution.getPassword());
    }

    private int getHealthcareInstitutionCountByCountry(String countryID) {
        return jdbcTemplate.queryForObject(COUNT_HEALTHCARE_INSTITUTION_BY_COUNTRY, new Object[]{countryID}, Integer.class);
    }

    public List<HealthcareInstitution> findAllHealthcareInstitutions() {
        return jdbcTemplate.query(FIND_ALL_HEALTHCARE_INSTITUTIONS, new Object[]{}, healthcareInstitutionsMapperList);
    }

    public int insertRole(Role role) {
        return jdbcTemplate.update(INSERT_ROLE, role.getName(), Integer.parseInt(role.getHealthcareInstitutionID()), role.getCountryID(), role.getDescription());
    }

    public int insertEmployee(Employee employee) {
        int id = jdbcTemplate.queryForObject(COUNT_EMPLOYEES, new Object[]{}, Integer.class);
        return jdbcTemplate.update(INSERT_EMPLOYEE, id, employee.getUsername(), employee.getPassword(), employee.getName(), employee.getSurname(), employee.getRoleName(), employee.getRoleInstitutionID(), employee.getRoleCountryID());
    }

    public List<Role> findAllRoles() {
        return jdbcTemplate.query(FIND_ALL_ROLES, new Object[]{}, rolesMapperList);
    }

    public int insertWarning(Warning warning) {
        int id = jdbcTemplate.queryForObject(COUNT_WARNINGS, new Object[]{}, Integer.class);
        return jdbcTemplate.update(INSERT_WARNING, id, warning.getName(), warning.getShortName(), warning.getDescription(), warning.getUri(), warning.getNotificationMessage(), warning.getGreenValue(), warning.getYellowValue(), warning.getRedValue(), 0, warning.getRefreshRate(), warning.getRoleName(), warning.getRoleInstitutionID(), warning.getRoleCountryID());

    }
}
