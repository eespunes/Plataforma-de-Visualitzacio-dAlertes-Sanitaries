package tfg.eespunes.domain;

public class Warning {
    private int id;
    private String name;
    private String shortName;
    private String description;
    private String uri;
    private String notificationMessage;

    private float greenValue;
    private float yellowValue;
    private float redValue;
    private float lastValue;
    private float refreshRate;

    private String roleName;
    private int roleInstitutionID;
    private String roleCountryID;


    public Warning(int id, String name, String shortName, String description, String uri, String notificationMessage, float greenValue, float yellowValue, float redValue, float lastValue, float refreshRate, String roleName, int roleInstitutionID, String roleCountryID) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.uri = uri;
        this.notificationMessage = notificationMessage;
        this.greenValue = greenValue;
        this.yellowValue = yellowValue;
        this.redValue = redValue;
        this.lastValue = lastValue;
        this.refreshRate = refreshRate;
        this.roleName = roleName;
        this.roleInstitutionID = roleInstitutionID;
        this.roleCountryID = roleCountryID;
    }

    public Warning() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public float getGreenValue() {
        return greenValue;
    }

    public void setGreenValue(float greenValue) {
        this.greenValue = greenValue;
    }

    public float getYellowValue() {
        return yellowValue;
    }

    public void setYellowValue(float yellowValue) {
        this.yellowValue = yellowValue;
    }

    public float getRedValue() {
        return redValue;
    }

    public void setRedValue(float redValue) {
        this.redValue = redValue;
    }

    public float getLastValue() {
        return lastValue;
    }

    public void setLastValue(float lastValue) {
        this.lastValue = lastValue;
    }

    public float getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(float refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleInstitutionID() {
        return roleInstitutionID;
    }

    public void setRoleInstitutionID(int roleInstitutionID) {
        this.roleInstitutionID = roleInstitutionID;
    }

    public String getRoleCountryID() {
        return roleCountryID;
    }

    public void setRoleCountryID(String roleCountryID) {
        this.roleCountryID = roleCountryID;
    }

    public void setCountryIDAndHealthcareInstitutionIDFromRoleName() {
        String[] splitted = roleName.split("-");

        if (splitted.length == 3) {
            this.roleName = splitted[2];
            this.roleInstitutionID = Integer.parseInt(splitted[1]);
            this.roleCountryID = splitted[0];
        }
    }
}
