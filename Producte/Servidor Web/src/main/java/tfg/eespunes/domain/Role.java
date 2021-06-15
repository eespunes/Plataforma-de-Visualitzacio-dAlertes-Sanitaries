package tfg.eespunes.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Role {
    @NotEmpty(message = "No es pot deixar el nom buit")
    @Size(min = 3, max = 32, message = "El nom ha de tenir entre 3 i 32 caràcters")
    private String name;
    @NotEmpty(message = "No es pot deixar la descripció buida")
    @Size(min = 1, max = 512, message = "La descripció ha de tenir entre 1 i 512 caràcters")
    private String description;
    private HealthcareInstitution healthcareInstitution;
    private String tempHealthcareInstitution;
    private String nameError = "";

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

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }
}
