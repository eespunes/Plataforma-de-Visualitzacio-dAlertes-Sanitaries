package tfg.eespunes.domain;

import tfg.eespunes.persistance.DatabaseController;

import java.util.Objects;

public class WarningThread extends Thread {
    private ThreadCommon threadCommon;
    private Warning warning;
    private DatabaseController databaseController;
    private final boolean isGreater;

    public WarningThread(Warning warning, DatabaseController databaseController) {
        this.warning = warning;
        this.databaseController = databaseController;
        isGreater = this.warning.getGreenValue() < this.warning.getYellowValue();
    }

    public void run() {
        while (true) {
            float newLastValue = threadCommon.GetLastValueFromAPI();
            if (knowAlertColor(newLastValue) > knowAlertColor(this.warning.getLastValue())) {
//                SendNotification(
            }
            this.warning.setLastValue(newLastValue);
            System.out.println(warning.getName() + "-" + newLastValue);
            this.databaseController.setWarningLastValue(this.warning.getId(), newLastValue);

            try {
                Thread.sleep(this.warning.getRefreshRate() * 1000);
            } catch (InterruptedException e) {
            }
        }
    }
    private int knowAlertColor(float lastValue) {
        if (isGreater) {
            if (lastValue < this.warning.getGreenValue())
                return 0;
            else if (lastValue < this.warning.getRedValue())
                return 1;
            else {
                return 2;
            }
        } else {
            if (lastValue > this.warning.getGreenValue())
                return 0;
            else if (lastValue > this.warning.getRedValue())
                return 1;
            else {
                return 2;
            }
        }
    }

    public Warning getWarning() {
        return warning;
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
