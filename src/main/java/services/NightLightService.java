package services;

import data.ClockData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;
import serviceui.ServiceUI;

/**
 * The Class NightLightService.
 */
public class NightLightService extends Service {

    private final Timer timer;
    private int lightStatus;
    //allow restricts timertask from repeatedly accessing run method
    private boolean allow;

    public NightLightService(String name) {
        super(name, "_nightlight._udp.local.");
        timer = new Timer();
        //lightStatus key 0 = off, 1 = on
        lightStatus = 0;
        ui = new ServiceUI(this, name);
    }
    /*
    * receiveTime() receives a String that was converted from Gson(Json)
    * using the fromJson method we can store back into java object
    * and access values
    */
    public void receiveTime(String jsonS){
        Gson gson = new Gson();
         ClockData cd = gson.fromJson(jsonS, ClockData.class);
         if(cd.timeclock == 6 && lightStatus == 1){
             try{
                performAction("Off");
             }catch(Exception e){
                 ui.updateArea("The following error occurred: "+e.toString());
                 ui.updateArea("Ensure that the nightlight and clock services are running and are able to communicate to SmartHome Manager");
             }
         }
         else if(cd.timeclock == 19 && lightStatus == 0){
             try{
                performAction("On");
             }catch(Exception e){
                 ui.updateArea("The following error occurred: "+e.toString());
                 ui.updateArea("Ensure that the nightlight and clock services are running and are able to communicate to SmartHome Manager");
             }
         }
    }

    /*
    * Turn on/off light or get status
    * 
    */
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
    /*
    * run method changes status of light by makin lightStatus 0 or 1
    */
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
        new NightLightService("SmartHome Nightlights");
    }
}
