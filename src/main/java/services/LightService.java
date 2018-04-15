package services;

import com.google.gson.Gson;
import data.LightData;
import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class lightService.
 */
public class LightService extends Service {

    private final Timer timer;
    private int lightStatus;
    private boolean light;
    public LightService(String name) {
        super(name, "_light._udp.local.");
        timer = new Timer();
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
            ui.updateArea("Light On");
            light = true;
        }else if(a.equals("Off")){
            timer.schedule(new RemindTask(), 0, 200);
            sendBack("OK");
            ui.updateArea("Light off");
            light = true;
        }
        else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    void receiveFunctions(String data) {
        
        Gson gson = new Gson();
        LightData lightData = gson.fromJson(data, LightData.class);
        
        if("TurnOnLight".equals(lightData.actionToPerform)){
            lightStatus = 1;
            sendBack("OK");
            ui.updateArea("Light turned ON");
            
        } else if("TurnOffLight".equals(lightData.actionToPerform)){
            lightStatus = 0;
            sendBack("OK");
            ui.updateArea("Light turned OFF");
            
        }  else if ("get_status".equals(lightData.actionToPerform)){
            sendBack("OK");
            ui.updateArea(getStatus());
        } else{
            sendBack(BAD_COMMAND + " - " + lightData.actionToPerform);
        }
        
    }

    class RemindTask extends TimerTask {

        @Override
       public void run() {
            if (light == true){
                if (lightStatus == 0) {
                    lightStatus += 1;
                    light = false;
                }
                else if (lightStatus == 1){
                    lightStatus -= 1;
                    light = false;
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
        new LightService("Light");
    }
}
