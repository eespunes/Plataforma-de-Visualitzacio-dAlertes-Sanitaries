package tfg.eespunes.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class HealthcareInstitution {
    private int id;
    private Country country;
    @NotEmpty(message = "No es pot deixar el nom buit")
    @Size(min = 2, max = 64,message = "El nom ha de tenir entre 2 i 64 caràcters")
    private String name;
    @NotEmpty(message = "No es pot deixar la URL buida")
    @Size(min = 4, max = 256,message = "La URL ha de tenir entre 4 i 256 caràcters")
    private String url;
    @NotEmpty(message = "No es pot deixar el nom d'usuari buit")
    @Size(min = 4, max = 32,message = "El nom d'usuari ha de tenir entre 4 i 32 caràcters")
    private String username;
    @NotEmpty(message = "No es pot deixar la contrasenya buida")
    @Size(min = 2, max =32,message = "La contrasenya ha de tenir entre 2 i 32 caràcters")
    private String password;
    private String tempCountry;

    public HealthcareInstitution(int id, Country country, String name, String url, String username, String password) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public HealthcareInstitution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempCountry() {
        return tempCountry;
    }

    public void setTempCountry(String tempCountry) {
        this.tempCountry = tempCountry;
    }
}
