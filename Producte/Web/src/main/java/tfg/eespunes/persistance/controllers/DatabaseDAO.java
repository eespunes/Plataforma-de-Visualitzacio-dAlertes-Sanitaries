package tfg.eespunes.persistance.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tfg.eespunes.domain.Country;
import tfg.eespunes.domain.HealthcareInstitution;
import tfg.eespunes.domain.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DatabaseDAO {

    private final String INSERT_HEALTHCARE_INSTITUTION = "INSERT INTO HealthcareInstitutions (ins_id, ins_countryID, ins_name, ins_URL, ins_username, ins_password) VALUES (?,?, ?, ?, ?, ?);";
    private final String INSERT_ROLE = "INSERT INTO Roles VALUES (?,?, ?, ?);";
    private final String FIND_ALL_COUNTRIES = "SELECT * FROM Countries";
    private final String FIND_ALL_HEALTHCARE_INSTITUTIONS = "SELECT * FROM HealthcareInstitutions*";
    private final String FIND_ALL_HEALTHCARE_INSTITUTIONS_BY_COUNTRY = "SELECT COUNT(ins_id) FROM HealthcareInstitutions WHERE ins_countryid=?";

    private final JdbcTemplate jdbcTemplate;

    public DatabaseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Country countryMapper(ResultSet resultSet) throws SQLException {
        Country country = new Country(resultSet.getString(0), resultSet.getString(1), resultSet.getString(2));
        return country;
    }

    private RowMapper<Country> countryMapperList = (resultSet, i) -> {
        Country country = new Country(resultSet.getString("cou_continentID"), resultSet.getString("cou_id"), resultSet.getString("cou_name"));
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

    public List<Country> findAllCountries() {
        return jdbcTemplate.query(FIND_ALL_COUNTRIES, new Object[]{}, countryMapperList);
    }

    public int insertHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        return jdbcTemplate.update(INSERT_HEALTHCARE_INSTITUTION, getHealthcareInstitutionCountByCountry(healthcareInstitution.getCountryID()), healthcareInstitution.getCountryID(), healthcareInstitution.getName(), healthcareInstitution.getUrl(), healthcareInstitution.getUsername(), healthcareInstitution.getPassword());
    }

    private int getHealthcareInstitutionCountByCountry(String countryID) {
        return jdbcTemplate.queryForObject(FIND_ALL_HEALTHCARE_INSTITUTIONS_BY_COUNTRY, new Object[]{countryID}, Integer.class);
    }

    public List<HealthcareInstitution> findAllHealthcareInstitutions() {
        return jdbcTemplate.query(FIND_ALL_HEALTHCARE_INSTITUTIONS, new Object[]{}, healthcareInstitutionsMapperList);
    }

    public int insertRole(Role role) {
        return jdbcTemplate.update(INSERT_ROLE, role.getName(), role.getHealthcareInstitutionID(), role.getCountryID(), role.getDescription());
    }

    public HealthcareInstitution findHealthcareInstitutionByID(String healthcareInstitutionID) {
        return null;
    }
}
