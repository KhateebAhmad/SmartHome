/*
 * 
 */
package client;

import clientui.LightUI;

/**
 * Bed Client.
 *
 * @author dominic
 */
public class LightClient extends Client {

    private final String ON = "On";
    private final String OFF = "Off";
    //lightCondition: is light on or off
    private boolean lightCondition = false;

    /**
     * Bed Client Constructor.
     */
    public LightClient() {
        super();
        serviceType = "_light._udp.local.";
        ui = new LightUI(this);
        name = "Home Lighting";
    }

    /**
     * sends a message to change condition of the light.
     */
    public void on() {
        if (!lightCondition) {
            String a = sendMessage(ON);
            if (a.equals(OK)) {
                lightCondition = true;
                ui.updateArea("Light is turning on");
            }
        } else if(lightCondition) {
            String a = sendMessage(OFF);
            if (a.equals(OK)) {
                lightCondition = false;
                ui.updateArea("Light is turning off");
            }
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Light is on")) {
            lightCondition = true;
        }else if (msg.equals("Light is off")) {
            lightCondition = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new LightUI(this);
        lightCondition = false;
    }
}
