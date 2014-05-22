package pwm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pwm.Controller;

/**
 * Listens to the menu bar items
 *
 * @author Dominik Scholz
 * @version 0.3
 */
public class MenuListener implements ActionListener {

    private Controller controller;

    public MenuListener(Controller controller) {
        this.controller = controller;
    }

    public void menuAction(String action) {
        switch (action) {
            //profile
            case "new": controller.newProfile(); break;
            case "open": controller.openProfile();  break;
            case "saveas": controller.saveProfileAs(); break;
            case "close": controller.close(); break;
                
            //edit
            case "createcategory": controller.createCategory(); break;
            case "editcategory": controller.editCategory(); break;
            case "deletecategory": controller.removeCategory(); break;
            case "createentry": controller.createPassword("create"); break;
            case "editentry": controller.editPassword(); break;
            case "deleteentry": controller.removePassword(); break;

            //view
            case "changelanguage": controller.changeLanguage(); break;
                
            default:
            System.err.println(action);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuAction(e.getActionCommand());
    }

}
