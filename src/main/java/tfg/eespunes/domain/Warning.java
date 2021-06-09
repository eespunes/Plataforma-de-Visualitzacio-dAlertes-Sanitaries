package tfg.eespunes.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Warning{
    private int id;
    @NotEmpty(message = "No es pot deixar el nom buit")
    @Size(min = 1, max = 128,message = "El nom ha de tenir entre 1 i 128 caràcters")
    private String name;
    @NotEmpty(message = "No es pot deixar el nom curt buit")
    @Size(min = 1, max = 64,message = "El nom ha de tenir entre 1 i 64 caràcters")
    private String shortName;
    @NotEmpty(message = "No es pot deixar la descripció buida")
    @Size(min = 1, max = 512,message = "La descripció ha de tenir entre 1 i 512 caràcters")
    private String description;
    @NotEmpty(message = "No es pot deixar la URI buida")
    @Size(min = 1, max = 256,message = "La URI ha de tenir entre 1 i 256 caràcters")
    private String uri;
    @NotEmpty(message = "No es pot deixar la descripció buida")
    @Size(min = 1, max = 256,message = "La notificació ha de tenir entre 1 i 265 caràcters")
    private String notificationMessage;

    private float greenValue;
    private float yellowValue;
    private float redValue;
    private float lastValue;
    private int refreshRate;

    private Role role;

    private String tempRoleName;

    public Warning(int id, String name, String shortName, String description, String uri, String notificationMessage, float greenValue, float yellowValue, float redValue, float lastValue, int refreshRate, Role role) {
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

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
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
