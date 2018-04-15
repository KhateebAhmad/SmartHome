/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import client.ClockClient;
import client.TVClient;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author khateebahmad
 */
public class TVUI extends ClientUI{
    
    private static final long serialVersionUID = -5318589393275157185L;
    private JButton ON;
    private JButton OFF;
    private JButton CHANGE_CHANNEL;
    private final TVClient parent;
    


    public TVUI(TVClient tvClient) {
        super(tvClient);
        parent = tvClient;
        init();
    }

    /*
    * Initialise JFrame componenets
    */
    @Override
    public void init() {
        super.init();
        ON = new JButton("ON");
        OFF = new JButton("OFF");
        CHANGE_CHANNEL = new JButton("Change Channel");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{ON});
        add(new JButton[]{OFF});
        add(new JButton[]{CHANGE_CHANNEL});
    }

    /*
    *Perform appropriate action in parent class based on button clicked
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ON) {
            parent.turnON();
        } else if (e.getSource() == OFF) {
            parent.turnOFF();
        } else if (e.getSource() == CHANGE_CHANNEL) {
            parent.changeChannel();
        }
    }
    
}
