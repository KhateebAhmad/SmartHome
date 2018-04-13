/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.TVUI;



/**
 *
 * @author khateebahmad
 */
public class TVClient extends Client{
    
    private final String ON = "ON";
    private final String OFF = "OFF";

    /**
     * TV Client Constructor.
     */
    public TVClient() {
        super();
        serviceType = "_tv._udp.local.";
        ui = new TVUI(this);
        name = "TV";
    }

    /**
     * sends a message to start the TV.
     */
    public void turnON() {
            String a = sendMessage(ON);
            if (a.equals(OK)) {
                ui.updateArea("Turning ON");
            }
    }
    
    /**
     * sends a message to turn OFF the TV.
     */
    public void turnOFF() {
            String a = sendMessage(OFF);
            if (a.equals(OK)) {
                ui.updateArea("Turning OFF");
            }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("TV is ON")) {
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new TVUI(this);
    }
}
