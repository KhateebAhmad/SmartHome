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
