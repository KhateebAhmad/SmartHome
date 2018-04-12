package services;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class ClockService.
 */
public class ClockService extends Service {

    private final Timer timer;
    private int currentTime;

    public ClockService(String name) {
        super(name, "_clock._udp.local.");
        timer = new Timer();
        currentTime = 0;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Check")) {
            timer.schedule(new RemindTask(), 0, 10);
            sendBack("OK");
            ui.updateArea("Checking the Time");
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            if (currentTime >= 0 && currentTime <= 22) {
                currentTime += 1;
            }
            else if (currentTime == 23){
                currentTime = 0;
            }
            getStatus();
        }
    }

    @Override
    public String getStatus() {
        return "The time is: " + currentTime + ":00.";
    }

    public static void main(String[] args) {
        new ClockService("Aaron's");
    }
}
