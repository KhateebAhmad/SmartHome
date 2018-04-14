package services;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class BedService.
 */
public class SpeakerService extends Service {

    private final Timer timer;
    private int lightStatus;
    private boolean turnOn;
    public SpeakerService(String name) {
        super(name, "_light._udp.local.");
        timer = new Timer();
        lightStatus = 0;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Light On")) {
            timer.schedule(new RemindTask(), 0, 200);
            sendBack("OK");
            ui.updateArea("Light On");
            turnOn = true;
        }else if(a.equals("Off")){
            timer.schedule(new RemindTask(), 0, 200);
            sendBack("OK");
            ui.updateArea("Light off");
            turnOn = true;
        }
        else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            if (turnOn == true) {
                
            }
            else if (turnOn == false){
                
            }
        }
    }

    @Override
    public String getStatus() {
        return "light " + turnOn + "% is on.";
    }

    public static void main(String[] args) {
        new SpeakerService("Light");
    }
}
