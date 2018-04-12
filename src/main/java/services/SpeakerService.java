/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import serviceui.ServiceUI;

/**
 *
 * @author khateebahmad
 */
public class SpeakerService extends Service{
    
    private int speakerVolume;

    public SpeakerService(String name) {
        super(name, "_bed._udp.local.");
        ui = new ServiceUI(this, name);
        speakerVolume = 20;
    }

    @Override
    protected void performAction(String action) {
        if("VolumeUP".equals(action)){
            speakerVolume =+ 10;
        } else if("VolumeDown".equals(action)){
            speakerVolume =- 10;
        } else if("Mute".equals(action)){
            speakerVolume = 0;
        } else if("TurnOnTV".equals(action)){
            
        } else if("TurnOffTV".equals(action)){
            
        } else if("ChangeChannel".equals(action)){
            
        }//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStatus() {
        return "Speaker Volume is: " + speakerVolume;
        
    }
    
    public static void main(String[] args) {
        new SpeakerService("SmartSpeaker");
    }
    
}
