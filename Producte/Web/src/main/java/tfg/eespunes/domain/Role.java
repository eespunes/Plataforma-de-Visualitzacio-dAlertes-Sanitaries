package tfg.eespunes.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Role {
    private String name;
    private String description;
    private String healthcareInstitutionID;
    private String countryID;

    public Role(String name, String description, String healthcareInstitutionID, String countryID) {
        this.name = name;
        this.description = description;
        this.healthcareInstitutionID = healthcareInstitutionID;
        this.countryID = countryID;
    }

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHealthcareInstitutionID() {
        return healthcareInstitutionID;
    }

    public void setHealthcareInstitutionID(String healthcareInstitutionID) {
        this.healthcareInstitutionID = healthcareInstitutionID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public void setCountryIDFromHealthcareInstitution() {
        String[] splitted = healthcareInstitutionID.split("-");

        if (splitted.length == 2) {
            this.healthcareInstitutionID = splitted[1];
            this.countryID = splitted[0];
        }
    }
}
