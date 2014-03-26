package pwm;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import pwm.profilemodel.Category;
import pwm.profilemodel.RootEntry;
import pwm.ui.Display;
import pwm.ui.rendering.ReloadableButton;
import pwm.ui.rendering.TreeWrapper;

/**
 * Handles the interactions
 * 
 * @author Dominik Scholz
 * @version 0.2
 */
public class Controller {

    private Assets assets;
    private Display display;
    private RootEntry root;
    private TreeWrapper tw;
    
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
        if(masterkey != null && masterkey.length() > 0) loadProfile(new Profile(masterkey));
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
                        loadProfile(new Profile(masterkey,file));
                    } catch (PWMException ex) {
                        //couldn't read or decrypt
                        messageDialog(ex.getMessage(),ERROR_MESSAGE);
                    }
                }
            } else messageDialog("open.wrongfile",ERROR_MESSAGE);
        }
    }
    
    
    /**
     * Invoked when successfully created a profile
     * 
     * @param profile the profile which was created
     */
    private void loadProfile(Profile profile) {
        ReloadableButton.setAllEnabled(true);
        root = profile.getRootEntry();
        tw = display.setTree(root);
    }

    /**
     * Invoked when closing the application
     */
    public void close() {
        if(JOptionPane.showConfirmDialog(null, assets.getLocalized("close.dialog"), assets.getLocalized("close.title"), JOptionPane.YES_NO_OPTION) == 0) System.exit(0);
    }
    
    
    //////////
    // Edit
    //////////
    
    /**
     * Invoked when creating a category
     */
    public void createCategory() {
        String categoryName = inputDialog("create.category");
        if(categoryName != null && categoryName.length() > 0) {
            Category category = new Category(display.getSelectedContainer(),categoryName);
            tw.nodeInserted(category);
            tw.structureChanged(category);
            display.updateTree();
        }
    }
    
}
