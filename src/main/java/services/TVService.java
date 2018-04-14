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
public class TVService extends Service{
    
    
    
    private String tvStatus;
    private int channel;

    public TVService(String name) {
        super(name, "_tv._udp.local.");
        ui = new ServiceUI(this, name);
        tvStatus = "OFF";
        channel = 0;
    }

    @Override
    protected void performAction(String data) {
        if("TurnOnTV".equals(data)){
            tvStatus = "ON";
            sendBack("OK");
            ui.updateArea("TV turned ON");
        } else if("TurnOffTV".equals(data)){
            tvStatus = "OFF";
            channel = 0;
            sendBack("OK");
            ui.updateArea("TV turned OFF");
        } else if("ChangeChannel".equals(data)){
            channel = channel + 1; 
            sendBack("OK");
            ui.updateArea("Channel changed");
        } else if ("get_status".equals(data)){
            sendBack("OK");
            ui.updateArea(getStatus());
        } else{
            sendBack(BAD_COMMAND + " - " + data);
        }
    }
    
    
    protected void receiveAction(String data) {
        Gson gson = new Gson();
        TVData tvData = gson.fromJson(data, TVData.class);
         
        if("TurnOnTV".equals(tvData.actionToPerform)){
            tvStatus = "ON";
            sendBack("OK");
            ui.updateArea("TV turned ON");
        } else if("TurnOffTV".equals(tvData.actionToPerform)){
            tvStatus = "OFF";
            channel = 0;
            sendBack("OK");
            ui.updateArea("TV turned OFF");
        } else if("ChangeChannel".equals(tvData.actionToPerform)){
            channel = channel + 1; 
            sendBack("OK");
            ui.updateArea("Channel changed");
        } else if ("get_status".equals(tvData.actionToPerform)){
            sendBack("OK");
            ui.updateArea(getStatus());
        } else{
            sendBack(BAD_COMMAND + " - " + tvData.actionToPerform);
        }
    }

    @Override
    public String getStatus() {
        return "TV status is: " + tvStatus + " and channel is: " + channel;
        
    }
    
    public static void main(String[] args) {
        new TVService("SmartTV");
    }
}
