/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.SpeakerUI;


/**
 *
 * @author farhanahmad
 */
public class SpeakerClient extends Client{
    
    private final String INCREASE_VOLUME = "VolumeUP";
    private final String DECREASE_VOLUME = "VolumeDown";
    private final String MUTE = "Mute";
    private final String TURNONTV = "TurnOnTV";
    private final String TURNOFFTV = "TurnOffTV";
    private final String CHANGE_CHANNEL = "ChangeChannel";
    
    /**
     * Speaker Client Constructor.
     */
    public SpeakerClient() {
        super();
        serviceType = "_speaker._udp.local.";
        ui = new SpeakerUI(this);
        name = "Speaker";
    }

    /**
     * sends a message to turn speaker volume UP.
     */
    public void volumeUP() {
            String a = sendMessage(INCREASE_VOLUME);
            if (a.equals(OK)) {
                ui.updateArea("Turning speaker volume UP");
            }
    }
    
    /**
     * sends a message to turn speaker volume down.
     */
    public void volumeDown() {
            String a = sendMessage(DECREASE_VOLUME);
            if (a.equals(OK)) {
                ui.updateArea("Turning speaker volume DOWN");
            }
    }
    
    /**
     * sends a message to turn speaker mute.
     */
    public void speakerMute() {
            String a = sendMessage(MUTE);
            if (a.equals(OK)) {
                ui.updateArea("Speaker muted");
            }
    }
    
    /**
     * sends a message to turn TV ON.
     */
    public void turnONTV() {
            String a = sendMessage(TURNONTV);
            if (a.equals(OK)) {
                ui.updateArea("Turning TV ON");
            }
    }
    
    /**
     * sends a message to turn TV OFF.
     */
    public void turnOFFTV() {
            String a = sendMessage(TURNOFFTV);
            if (a.equals(OK)) {
                ui.updateArea("Turning TV OFF");
            }
    }

    /**
     * sends a message to change TV channel.
     */
    public void changeChannel() {
            String a = sendMessage(CHANGE_CHANNEL);
            if (a.equals(OK)) {
                ui.updateArea("Changing Channel");
            }
    }
    
    
    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Speaker is ON")) {
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new SpeakerUI(this);
    }
}
