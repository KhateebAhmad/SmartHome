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

    private final String WARM = "Warm";
    private boolean isWarming = false;

    /**
     * Bed Client Constructor.
     */
    public LightClient() {
        super();
        serviceType = "_bed._udp.local.";
        ui = new LightUI(this);
        name = "Light";
    }

    /**
     * sends a message to warm the bed.
     */
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("light is on");
            }
        } else {
            ui.updateArea("light is no");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("light is on.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new LightUI(this);
        isWarming = false;
    }
}
