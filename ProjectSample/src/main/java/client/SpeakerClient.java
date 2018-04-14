/*
 * 
 */
package client;



import clientui.SpeakerUI;

/**
 * Bed Client.
 *
 * @author dominic
 */
public class SpeakerClient extends Client {

    private final String ON = "SpeakerOn";
    private final String OFF = "SpeakerOff";
    private boolean turnOn = false;

    /**
     * Bed Client Constructor.
     */
    public SpeakerClient() {
        super();
        serviceType = "_speaker._udp.local.";
        ui = new SpeakerUI(this);
        name = "Speaker";
    }

    /**
     * sends a message to warm the bed.
     */
    public void LightOn() {
        if (!turnOn) {
            String a = sendMessage(ON);
            if (a.equals(OK)) {
                turnOn = true;
                ui.updateArea("light is on");
            }
        }else if(turnOn){
            String a = sendMessage(OFF);
            if (a.equals(OK)) {
                turnOn = false;
                ui.updateArea("light is off");
        }
            
        }
        else {
            ui.updateArea("light is not working");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("light is on.")) {
            turnOn = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new SpeakerUI(this);
        turnOn = false;
    }
}
