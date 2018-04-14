/*
 * 
 */
package client;

import clientui.ClockUI;

/**
 * clockClock Client.
 *
 * @author Aaron
 */
public class ClockClient extends Client {

    private final String Check = "Check";
    private final String Reset = "Reset";

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
     * sends a message to start and check the time.
     */
    public void checkTime() {
            String a = sendMessage(Check);
            if (a.equals(OK)) {
                ui.updateArea("Checking Time");
            }
    }
    
    /**
     * sends a message to reset the clock time.
     */
    public void resetTime() {
            String a = sendMessage(Reset);
            if (a.equals(OK)) {
                ui.updateArea("Resetting Time");
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
