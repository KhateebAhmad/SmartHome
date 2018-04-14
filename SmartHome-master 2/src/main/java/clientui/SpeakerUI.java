/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import client.SpeakerClient;
import client.TVClient;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author khateebahmad and Muhammad Malik
 */
public class SpeakerUI extends ClientUI{
    
    private static final long serialVersionUID = -5318589393275157185L;
    private JButton volumeUP;
    private JButton volumeDown;
    private JButton mute;
    private JButton turnTVON;
    private JButton turnTVOFF;
    private JButton turnLightON;
    private JButton turnLightOFF;
    private JButton changeChannel;
    private final SpeakerClient parent;
    


    public SpeakerUI(SpeakerClient speakerClient) {
        super(speakerClient);
        parent = speakerClient;
        init();
    }

    /*
    * Initialise JFrame componenets
    */
    @Override
    public void init() {
        super.init();
        volumeUP = new JButton("VolumeUP");
        volumeDown = new JButton("VolumeDown");
        mute = new JButton("Mute");
        turnTVON = new JButton("Turn TV ON");
        turnTVOFF = new JButton("Turn TV OFF");
        changeChannel = new JButton("Change TV channel");
        turnLightON = new JButton("Turn Light ON");
        turnLightOFF = new JButton("Turn Light OFF");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{volumeUP});
        add(new JButton[]{volumeDown});
        add(new JButton[]{mute});
        add(new JButton[]{turnTVON});
        add(new JButton[]{turnTVOFF});
        add(new JButton[]{changeChannel});
        add(new JButton[]{turnLightON});
        add(new JButton[]{turnLightOFF});
        
        
    }

    /*
    *Perform appropriate action in parent class based on button clicked
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volumeUP) {
            parent.volumeUP();
        } else if (e.getSource() == volumeDown) {
            parent.volumeDown();
        } else if (e.getSource() == mute) {
            parent.speakerMute();
        } else if (e.getSource() == turnTVON){
            parent.turnONTV();
        } else if (e.getSource() == turnTVOFF){
            parent.turnOFFTV();
        } else if (e.getSource() == changeChannel){
            parent.changeChannel();
        } else if (e.getSource() == turnLightON){
            parent.turnONLight();
        }else if (e.getSource() == turnLightOFF){
            parent.turnOFFLight();
        }
    }
    
}
