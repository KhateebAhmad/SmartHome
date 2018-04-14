package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.NightLightClient;

public class NightLightUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    public JButton on;
    private final NightLightClient parent;

    public NightLightUI(NightLightClient nightLightClient) {
        super(nightLightClient);
        parent = nightLightClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        on = new JButton("Turn On");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
    }

    /*
    *Alternate text of button to turn on/off and call the 'on' methos in paren class
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == on) {
            parent.on();
            if(on.getText() == "Turn On"){
                on.setText("Turn Off");
            }
            else{
                on.setText("Turn On");
            }
            on.repaint();
        }
    }
}
