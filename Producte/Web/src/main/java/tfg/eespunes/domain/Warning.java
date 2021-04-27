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

    private Role role;

    private String tempRoleName;

    public Warning(int id, String name, String shortName, String description, String uri, String notificationMessage, float greenValue, float yellowValue, float redValue, float lastValue, float refreshRate, Role role) {
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
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTempRoleName() {
        return tempRoleName;
    }

    public void setTempRoleName(String tempRoleName) {
        this.tempRoleName = tempRoleName;
    }
}
