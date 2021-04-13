package tfg.eespunes.persistance.controllers;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseDAO {

    private final String CREATE_HEALTHCARE_INSTITUTION = "INSERT INTO HealthcareInstitutions (ins_id, ins_countryID, ins_name, ins_URL, ins_username, ins_password) VALUES (?,?, ?, ?, ?, ?);";

    private JdbcTemplate jdbcTemplate;

    public DatabaseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
