package pwm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pwm.Controller;

/**
 * Listens to the menu bar items
 *
 * @author Dominik Scholz
 * @version 0.2
 */
public class MenuListener implements ActionListener {

    private Controller controller;

    public MenuListener(Controller controller) {
        this.controller = controller;
    }

    public void menuAction(String action) {
        System.out.println(action);
        switch (action) {
            //profile
            case "new": controller.newProfile(); break;
            case "open": controller.openProfile();  break;
            case "close": controller.close(); break;

            default:
            //silence and friendly :3
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuAction(e.getActionCommand());
    }

}
