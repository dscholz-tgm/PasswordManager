package pwm;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import pwm.ui.Display;

/**
 * Handles the interactions
 * 
 * @author Dominik Scholz
 * @version 0.2
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
    
    private void messageDialog(String message) {
        messageDialog(message, PLAIN_MESSAGE);
    }
    
    private void messageDialog(String message, int messagetype) {
        String localizedMessage = assets.getLocalized(message);
        if(localizedMessage == null) localizedMessage = message;
        JOptionPane.showMessageDialog(display, localizedMessage, message + ".title", messagetype);
    }
    
    private String inputDialog(String message) {
        return inputDialog(message, PLAIN_MESSAGE);
    }
    
    private String inputDialog(String message, int messagetype) {
        String localizedMessage = assets.getLocalized(message);
        if(localizedMessage == null) localizedMessage = message;
        return JOptionPane.showInputDialog(display, localizedMessage, message + ".title", messagetype);
    }

    //////////
    // Profile
    //////////
    
    /**
     * Invoked when creating a new profile
     */
    public void newProfile() {
        String masterkey = inputDialog("new.masterkey");
        if(masterkey != null && masterkey.length() > 0) new Profile(masterkey);
    }
    
    /**
     * Invoked when opening a profile
     */
    public void openProfile() {
        JFileChooser chooser = new JFileChooser();
        
        //accepts the file
        if(chooser.showOpenDialog(display) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile(); // <--- our file 
            //has right ending
            if(file.getPath().endsWith(Profile.FILE_ENDING)) {
                //get key
                String masterkey = inputDialog("new.masterkey");
                if(masterkey != null && masterkey.length() > 0) {
                    try {
                        new Profile(masterkey,file);
                    } catch (PWMException ex) {
                        //couldn't read or decrypt
                        messageDialog(ex.getMessage(),ERROR_MESSAGE);
                    }
                }
            } else messageDialog("open.wrongfile",ERROR_MESSAGE);
        }
    }

    /**
     * Invoked when closing the application
     */
    public void close() {
        if(JOptionPane.showConfirmDialog(null, assets.getLocalized("close.dialog"), assets.getLocalized("close.title"), JOptionPane.YES_NO_OPTION) == 0) System.exit(0);
    }
}
