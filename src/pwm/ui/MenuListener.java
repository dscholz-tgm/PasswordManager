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
            case "save": controller.saveProfile(); break;
            case "saveas": controller.saveProfileAs(); break;
            case "close": controller.close(); break;
            case "changemasterkey": controller.changeMasterkey(); break;
            
            //edit
            case "createcategory": controller.createCategory(); break;
            case "editcategory": controller.editCategory(); break;
            case "deletecategory": controller.removeCategory(); break;
            case "createentry": controller.createPassword("create"); break;
            case "editentry": controller.editPassword(); break;
            case "deleteentry": controller.removePassword(); break;

            //view
            case "changelanguage": controller.changeLanguage(); break;
            
            
            //help
            case "help": controller.help(); break;
            case "update": controller.checkUpdate(); break;
            case "website": controller.webPage(); break;
            case "donate": controller.donate(); break;
            case "about": controller.about(); break;
            
            default: System.out.println(action);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuAction(e.getActionCommand());
    }

}
