package pwm;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pwm.ui.Display;

/**
 * Starts the Password Manager
 * @author Dominik Scholz
 * @version 0.2
 */
public class PWMStarter {
    
    public static void main(String[] args) {
        
        //checking java version
        String version = System.getProperty("java.version");
        double versionNumber = Double.parseDouble(version.substring(0,version.indexOf(".", version.indexOf(".")+1)));
        if(versionNumber < 1.7) {
            System.out.println("Unsupportet java version, please download 1.7 or newer!");
            System.exit(-1);
        }
        
        //setting the LookAndFeel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        
        //loading assets
        Assets assets = Assets.get();
        
        //creating controller
        Controller controller = new Controller(assets);
        
        //starting display
        Display display = new Display(controller,assets,800,600);
        
        //register display
        controller.register(display);
    }

}
