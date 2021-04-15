package tfg.eespunes.domain;

public class HealthcareInstitution {
    private int id;
    private String countryID;
    private String name;
    private String url;
    private String username;
    private String password;

    public HealthcareInstitution(int id, String countryID, String name, String url, String username, String password) {
        this.id = id;
        this.countryID = countryID;
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

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
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
}
