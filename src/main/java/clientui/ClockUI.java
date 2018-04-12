package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.ClockClient;

public class ClockUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton clock;
    private final ClockClient parent;

    public ClockUI(ClockClient clockClient) {
        super(clockClient);
        parent = clockClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        clock = new JButton("Clock");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{clock});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clock) {
            parent.checkTime();
        }
    }
}
