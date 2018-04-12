package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.BedClient;
import client.LightClient;

public class LightUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton Light;
    private final LightClient parent;

    public LightUI(LightClient bedClient) {
        super(bedClient);
        parent = bedClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        Light = new JButton("Light ");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{Light});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Light) {
            parent.warm();
        }
    }
}
