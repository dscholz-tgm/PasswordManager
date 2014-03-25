package pwm;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pwm.ui.Display;

/**
 * Handles the interactions
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class Controller {

    private Assets assets;
    private Display display;
    
    public Controller(Assets assets) {
        this.assets = assets;
    }
    
    public void register(Display display) {
        this.display = display;
    }
    
    //////////
    // Window Helper
    //////////
    
    private void messageDialog(String message, int messagetype) {
        String localizedMessage = assets.getLocalized(message);
        if(localizedMessage == null) localizedMessage = message;
        JOptionPane.showMessageDialog(display, message, "", messagetype);
    }

    //////////
    // Profile
    //////////
    
    /**
     * Invoked when creating a new profile
     */
    public void newProfile() {
         JOptionPane.showMessageDialog(null, "new game");
    }
    
    /**
     * Invoked when opening a profile
     */
    public void openProfile() {
        JFileChooser chooser = new JFileChooser();
        if(chooser.showOpenDialog(display) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile(); // <--- our file 
            if(file.getPath().endsWith(Profile.FILE_ENDING)) {
                new Profile(file);
            } else {
                messageDialog("open.wrongfilename",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Invoked when closing the application
     */
    public void close() {
        if(JOptionPane.showConfirmDialog(null, assets.getLocalized("close.dialog"), assets.getLocalized("close.title"), JOptionPane.YES_NO_OPTION) == 0) System.exit(0);
    }
}
