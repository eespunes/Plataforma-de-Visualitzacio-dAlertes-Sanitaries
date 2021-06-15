package tfg.eespunes.domain.threads;

import io.github.jav.exposerversdk.PushClientException;
import tfg.eespunes.domain.Warning;
import tfg.eespunes.persistance.DatabaseController;

import java.util.Objects;

public class WarningThread extends Thread {
    private Warning warning;
    private DatabaseController databaseController;
    private NotificationMonitor notificationMonitor;


    private final boolean isGreater;

    public WarningThread(Warning warning, DatabaseController databaseController, NotificationMonitor notificationMonitor) {
        this.warning = warning;
        this.databaseController = databaseController;
        this.notificationMonitor = notificationMonitor;
        isGreater = this.warning.getGreenValue() < this.warning.getYellowValue();
    }

    public void run() {
        while (true) {
            float newLastValue = notificationMonitor.getLastValueFromAPI(warning);

            int newWarningColor = knowWarningColor(newLastValue);
            if (newWarningColor != notificationMonitor.GREEN && newWarningColor != knowWarningColor(this.warning.getLastValue())) {
                try {
                    notificationMonitor.sendNotifications(
                            databaseController.getAllEmployeesOfRole(warning.getRole().getName(),
                                    warning.getRole().getHealthcareInstitution().getId(),
                                    warning.getRole().getHealthcareInstitution().getCountry().getId()),
                            newWarningColor,
                            warning);
                } catch (PushClientException e) {
                }
            }
            this.warning.setLastValue(newLastValue);
            this.databaseController.setWarningLastValue(this.warning.getId(), newLastValue);

            try {
                Thread.sleep(this.warning.getRefreshRate() * 1000L);
            } catch (InterruptedException e) {
            }
        }
    }

    private int knowWarningColor(float lastValue) {
        if (isGreater) {
            if (lastValue < this.warning.getGreenValue())
                return notificationMonitor.GREEN;
            else if (lastValue < this.warning.getRedValue())
                return notificationMonitor.YELLOW;
            else {
                return notificationMonitor.RED;
            }
        } else {
            if (lastValue > this.warning.getGreenValue())
                return notificationMonitor.GREEN;
            else if (lastValue > this.warning.getRedValue())
                return notificationMonitor.YELLOW;
            else {
                return notificationMonitor.RED;
            }
        }
    }

    public Warning getWarning() {
        return warning;
    }

    public void setWarning(Warning warning) {
        this.warning = warning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarningThread that = (WarningThread) o;
        return warning.getId() == that.warning.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(warning);
    }
}
