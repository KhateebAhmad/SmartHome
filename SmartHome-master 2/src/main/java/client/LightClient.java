/*
 * 
 */
package client;


import clientui.LightUI;

/**
 * light Client.
 *
 * @author Muhammad Malik
 */
public class LightClient extends Client {

    private final String ON = "On";
    private final String OFF = "Off";
    private boolean turnOn = false;

    /**
     * light Client Constructor.
     */
    public LightClient() {
        super();
        serviceType = "_light._udp.local.";
        ui = new LightUI(this);
        name = "Light";
    }

    /**
     * sends a message turn on light.
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
        }else if(msg.equals("light is off")){
            turnOn = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new LightUI(this);
        turnOn = false;
    }
}
