package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.LightClient;

public class LightUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    public JButton on;
    private final LightClient parent;

    public LightUI(LightClient lightClient) {
        super(lightClient);
        parent = lightClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        on = new JButton("Turn On");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
//        off = new JButton("Off");
//        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
//        add(new JButton[]{off});
    }

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
