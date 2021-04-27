package tfg.eespunes.domain;

public class HealthcareInstitution {
    private int id;
    private Country country;
    private String name;
    private String url;
    private String username;
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
