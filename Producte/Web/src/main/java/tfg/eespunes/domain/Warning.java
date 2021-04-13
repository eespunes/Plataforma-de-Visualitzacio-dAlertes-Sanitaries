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
}
