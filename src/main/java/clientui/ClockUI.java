package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.ClockClient;
import javax.swing.JTextField;

public class ClockUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton clock;
    private JButton reset;
    private JTextField manualTime;
    private final ClockClient parent;
    


    public ClockUI(ClockClient clockClient) {
        super(clockClient);
        parent = clockClient;
        init();
    }

    /*
    * Initialise JFrame componenets
    */
    @Override
    public void init() {
        super.init();
        clock = new JButton("Start");
        reset = new JButton("Reset");
        manualTime = new JTextField();
        manualTime.setText("This is a text");
        manualTime.setColumns(20);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{clock});
        add(new JButton[]{reset});
        add(manualTime);
    }

    /*
    *Perform appropriate action in parent class based on button clicked
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clock) {
            parent.checkTime();
        }
        else if (e.getSource() == reset) {
            parent.resetTime();
        }
    }
}
