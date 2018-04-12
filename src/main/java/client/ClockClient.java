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

    private final String WARM = "Warm";
    private boolean isWarming = false;

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
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("Clock is Warming");
            }
        } else {
            ui.updateArea("Clock already Warming");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Clock is 100% warmed.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new ClockUI(this);
        isWarming = false;
    }
}
