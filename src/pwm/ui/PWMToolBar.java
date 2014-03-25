package pwm.ui;

import javax.swing.JToolBar;
import pwm.Assets;
import pwm.ui.rendering.PWMColors;

/**
 * The tool bar
 * @author Dominik Scholz
 * @version 0.1
 */
public class PWMToolBar extends JToolBar {
    
    private Assets assets;

    public PWMToolBar(Assets assets) {
        this.assets = assets;
        this.setBackground(PWMColors.FRAME_COLOR);
    }

}
