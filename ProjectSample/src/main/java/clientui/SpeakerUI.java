package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.BedClient;
import client.LightClient;
import client.SpeakerClient;

public class SpeakerUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton lightOnOff;
    private final SpeakerClient parent;

    public SpeakerUI(SpeakerClient speakerClient) {
        super(speakerClient);
        parent = speakerClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        lightOnOff = new JButton("light On/Off");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{lightOnOff});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lightOnOff) {
            parent.LightOn();
            if(lightOnOff.getText() == "Turn on"){
                lightOnOff.setText("Turn Off");
            
        }
        else{
            lightOnOff.setText("Turn On");
        }
            lightOnOff.setText("Turn on");
    }
}
}
