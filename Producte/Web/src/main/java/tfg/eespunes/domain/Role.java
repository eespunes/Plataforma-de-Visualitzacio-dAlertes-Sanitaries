package tfg.eespunes.domain;

public class Role {
    private String name;
    private String description;
    private HealthcareInstitution healthcareInstitution;

    public Role(String name, String description, HealthcareInstitution healthcareInstitution) {
        this.name = name;
        this.description = description;
        this.healthcareInstitution = healthcareInstitution;
    }
}
