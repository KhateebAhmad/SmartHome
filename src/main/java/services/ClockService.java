package services;

import com.google.gson.Gson;
import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;
/**
 * The Class ClockService.
 */
public class ClockService extends Service {

    //Timer to tell when to run the TimerTask
    //currentTime to hold the current time for the clock
    private final Timer timer;
    private int currentTime;
    NightLightService ls = new NightLightService("SmartHome Nightlights");
    public ClockService(String name) {
        super(name, "_clock._udp.local.");
        timer = new Timer();
        currentTime = 0;
        ui = new ServiceUI(this, name);
    }

    /*Get the status of the clock to return, alert that the time is being checked/reset
    *
    */
    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Check")) {
            timer.schedule(new RemindTask(), 0, 3000);
            sendBack("OK");
            ui.updateArea("Checking the Time");
        } else if (a.equals("Reset")) {
            currentTime = 0;
            sendBack("OK");
            ui.updateArea("Resetting the Time");
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }
    //Changes the time iteratively based on current value of currentTime
    class RemindTask extends TimerTask {
        @Override
        public void run() {
            if (currentTime >= 0 && currentTime <= 22) {
                currentTime += 1;
                if(currentTime == 19){
                    //true = turn on
                    lightChange();
                }
                if(currentTime == 6){
                    //false = turn off
                    lightChange();
                }
            }
            else if (currentTime == 23){
                currentTime = 0;
            }
        }
    }
    /*
    * lightChange() facilitates data communication (via Gson(Json)) between clock and light services
    */
    public void lightChange(){
        ClockData cd = new ClockData(currentTime);
        Gson gson = new Gson();
        String params = gson.toJson(cd);
        ls.receiveTime(params);
    }
    @Override
    public String getStatus() {
        return "The time is: " + currentTime + ":00.";
    }

    public static void main(String[] args) {
        new ClockService("SmartHome Clock");
    }
}
