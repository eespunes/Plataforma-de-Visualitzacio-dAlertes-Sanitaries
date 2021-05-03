package tfg.eespunes.domain;

import java.util.Date;

public class WarningLog {
    private Warning warning;
    private Date date;
    private float value;
    private String state;
    private String message;

    public WarningLog(Warning warning, Date date, float value, String state, String message) {
        this.warning = warning;
        this.date = date;
        this.value = value;
        this.state = state;
        this.message = message;
    }
}
