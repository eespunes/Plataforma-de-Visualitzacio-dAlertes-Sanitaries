package tfg.eespunes.domain;

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
}
