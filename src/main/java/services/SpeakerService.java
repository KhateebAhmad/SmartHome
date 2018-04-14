/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import data.TVData;
import serviceui.ServiceUI;

/**
 *
 * @author khateebahmad
 */
public class SpeakerService extends Service {

    private int speakerVolume;
    TVService tvService = new TVService("SmartTV");

    public SpeakerService(String name) {
        super(name, "_speaker._udp.local.");
        ui = new ServiceUI(this, name);
        speakerVolume = 20;
    }

    @Override
    protected void performAction(String action) {
        if ("VolumeUP".equals(action)) {
            if (speakerVolume < 100) {
                speakerVolume = speakerVolume + 10;
                sendBack("OK");
                ui.updateArea("Speaker Volume increased. Volume is: " + speakerVolume);
            }else{
                sendBack("OK");
                ui.updateArea("Volume already maximum");
            }
        } else if ("VolumeDown".equals(action)) {
            if (speakerVolume > 0) {
                speakerVolume = speakerVolume - 10;
                sendBack("OK");
                ui.updateArea("Speaker volume decreased. Volume is: " + speakerVolume);
            }else{
                sendBack("OK");
                ui.updateArea("Volume already minimum");
            }
        } else if ("Mute".equals(action)) {
            speakerVolume = 0;
            sendBack("OK");
            ui.updateArea("Speaker muted");
        } else if ("get_status".equals(action)){
            sendBack("OK");
            ui.updateArea("Speaker volume is: "+ speakerVolume);
        }else if ("TurnOnTV".equals(action)) {
            sendBack("OK");
            tvFunctions(action);
        } else if ("TurnOffTV".equals(action)) {
            sendBack("OK");
            tvFunctions(action);
        } else if ("ChangeChannel".equals(action)) {
            sendBack("OK");
            tvFunctions(action);
        } else {
            sendBack(BAD_COMMAND + " - " + action);
        }
    }

    public void tvFunctions(String functionToPerform) {
        TVData tvData = new TVData(functionToPerform);
        //tvData.setActionToPerform(functionToPerform);
        Gson gson = new Gson();
        String params = gson.toJson(tvData);
        tvService.receiveAction(params);
    }

    @Override
    public String getStatus() {
        return "Speaker Volume is: " + speakerVolume;

    }

    public static void main(String[] args) {
        new SpeakerService("SmartSpeaker");
    }

}
