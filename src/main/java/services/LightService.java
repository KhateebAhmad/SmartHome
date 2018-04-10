package services;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class BedService.
 */
public class LightService extends Service {

    private final Timer timer;
    private int lightStatus;
    //allow restricts timertask from repeatedly accessing run method
    private boolean allow;

    public LightService(String name) {
        super(name, "_light._udp.local.");
        timer = new Timer();
        //lightStatus key 0 = off, 1 = on
        lightStatus = 0;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("On")) {
            timer.schedule(new RemindTask(), 0, 200);
            sendBack("OK");
            ui.updateArea("Turning On Light");
            allow = true;
        } else if (a.equals("Off")) {
            timer.schedule(new RemindTask(), 0, 200);
            sendBack("OK");
            ui.updateArea("Turning Off Light");
            allow = true;
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            if (allow == true){
                if (lightStatus == 0) {
                    lightStatus += 1;
                    allow = false;
                }
                else if (lightStatus == 1){
                    lightStatus -= 1;
                    allow = false;
                }
            }
        }
    }

    @Override
    public String getStatus() {
        switch (lightStatus) {
            case 0:
                return "Light is off";
            case 1:
                return "Light is on";
            default:
                return "Please wait";
        }
    }

    public static void main(String[] args) {
        new LightService("Lights");
    }
}
