package pwm.ui;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pwm.Assets;
import pwm.ui.rendering.PWMColors;

/**
 * The tool bar
 * @author Dominik Scholz
 * @version 0.1
 */
public class PWMFooter extends JPanel {
    
    private Assets assets;

    public PWMFooter(Assets assets) {
        super(new FlowLayout(FlowLayout.LEFT));
        this.assets = assets;
        
        JLabel copyright = new JLabel("Copyright 2014   |   Adrian Bergler, Dominik Scholz, Helmuth Brunner, Samuel Schmidt");
        
        this.add(copyright);
        this.setBackground(PWMColors.FRAME_COLOR);
    }

}
