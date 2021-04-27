package tfg.eespunes.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Role {
    private String name;
    private String description;
    private HealthcareInstitution healthcareInstitution;
    private String tempHealthcareInstitution;

    public Role(String name, String description, HealthcareInstitution healthcareInstitution) {
        this.name = name;
        this.description = description;
        this.healthcareInstitution = healthcareInstitution;
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

    public HealthcareInstitution getHealthcareInstitution() {
        return healthcareInstitution;
    }

    public void setHealthcareInstitution(HealthcareInstitution healthcareInstitution) {
        this.healthcareInstitution = healthcareInstitution;
    }

    public String getTempHealthcareInstitution() {
        return tempHealthcareInstitution;
    }

    public void setTempHealthcareInstitution(String tempHealthcareInstitution) {
        this.tempHealthcareInstitution = tempHealthcareInstitution;
    }
}
