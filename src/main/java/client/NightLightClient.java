/*
 * 
 */
package client;

import clientui.NightLightUI;

/**
 * Bed Client.
 *
 * @author Aaron
 */
public class NightLightClient extends Client {

    private final String ON = "On";
    private final String OFF = "Off";
    //lightCondition: is light on or off
    private boolean lightCondition = false;

    /**
     * NightLight Client Constructor.
     */
    public NightLightClient() {
        super();
        serviceType = "_light._udp.local.";
        ui = new NightLightUI(this);
        name = "Nightlights";
    }

    /**
     * sends a message to change condition of the light and update ui.
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
        ui = new NightLightUI(this);
        lightCondition = false;
    }
}
