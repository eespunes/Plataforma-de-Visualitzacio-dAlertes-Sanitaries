package tfg.eespunes.domain;

public class Country {
    private String continentID;
    private String id;
    private String name;

    public Country(String continentID, String id, String name) {
        this.continentID = continentID;
        this.id = id;
        this.name = name;
    }

    public Country() {
    }

    public String getContinentID() {
        return continentID;
    }

    public void setContinentID(String continentID) {
        this.continentID = continentID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id;
    }
}
