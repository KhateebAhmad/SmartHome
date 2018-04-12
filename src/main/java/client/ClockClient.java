/*
 * 
 */
package client;

import clientui.ClockUI;

/**
 * clockClock Client.
 *
 * @author dominic
 */
public class ClockClient extends Client {

    private final String Check = "Check";

    /**
     * clockClock Client Constructor.
     */
    public ClockClient() {
        super();
        serviceType = "_clock._udp.local.";
        ui = new ClockUI(this);
        name = "Clock";
    }

    /**
     * sends a message to warm the clock.
     */
    public void checkTime() {
            String a = sendMessage(Check);
            if (a.equals(OK)) {
                ui.updateArea("Checking Time");
            }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Clock is on")) {
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new ClockUI(this);
    }
}
