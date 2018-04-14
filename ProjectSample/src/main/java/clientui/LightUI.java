package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.BedClient;
import client.LightClient;

public class LightUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton on;
    private final LightClient parent;

    public LightUI(LightClient lightClient) {
        super(lightClient);
        parent = lightClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        on = new JButton("Turn on");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == on) {
            parent.LightOn();
            if(on.getText() == "Turn on"){
                on.setText("Turn Off");
            
        }
        else{
            on.setText("Turn On");
        }
            on.setText("Turn on");
    }
}
}
