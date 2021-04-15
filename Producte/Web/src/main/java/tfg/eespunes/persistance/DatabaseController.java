package tfg.eespunes.persistance;

import org.springframework.stereotype.Service;
import tfg.eespunes.domain.Country;
import tfg.eespunes.domain.HealthcareInstitution;
import tfg.eespunes.domain.Role;
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

    public HealthcareInstitution getHealthcareInstitutionByID(String healthcareInstitutionID) {
        return databaseDAO.findHealthcareInstitutionByID(healthcareInstitutionID);
    }
}
