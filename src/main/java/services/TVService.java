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
    private int channel = 0;

    public TVService(String name) {
        super(name, "_tv._udp.local.");
        ui = new ServiceUI(this, name);
        tvStatus = "OFF";
    }

    @Override
    protected void performAction(String data) {
        Gson gson = new Gson();
        TVData tvData = gson.fromJson(data, TVData.class);
         String action = tvData.getActionToPerform();
         
        if("TurnOnTV".equals(action)){
            tvStatus = "ON";
            sendBack("OK");
            ui.updateArea("TV turned ON");
        } else if("TurnOffTV".equals(action)){
            tvStatus = "OFF";
            sendBack("OK");
            ui.updateArea("TV turned OFF");
        } else if("ChangeChannel".equals(action)){
            channel =+ 1; 
            sendBack("OK");
            ui.updateArea("Channel changed");
        } else{
            sendBack(BAD_COMMAND + " - " + action);
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
