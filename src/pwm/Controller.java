package pwm;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import pwm.profilemodel.Category;
import pwm.profilemodel.EntryContainer;
import pwm.profilemodel.PasswordEntry;
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
    private Profile profile;
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
        JOptionPane.showMessageDialog(display, assets.getLocalized(message), assets.getLocalized(message + ".title"), messagetype);
    }
    
    private String inputDialog(String message) {
        return inputDialog(message, PLAIN_MESSAGE);
    }
    
    private String inputDialog(String message, int messagetype) {
        return JOptionPane.showInputDialog(display, assets.getLocalized(message),  assets.getLocalized(message + ".title"), messagetype);
    }
    
    private int confirmDialog(String message) {
        return confirmDialog(message, PLAIN_MESSAGE);
    }
    
    private int confirmDialog(String message, int messagetype) {
        String localizedMessage = assets.getLocalized(message);
        if(localizedMessage == null) localizedMessage = message;
        return JOptionPane.showConfirmDialog(display, assets.getLocalized(message), assets.getLocalized(message + ".title"), JOptionPane.OK_CANCEL_OPTION,messagetype);
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
                String masterkey = inputDialog("open.masterkey");
                if(masterkey != null && masterkey.length() > 0) {
                    try {
                        loadProfile(new Profile(masterkey,file));
                    } catch (PWMException ex) {
                        //couldn't read or decrypt
                        messageDialog("open.wrongkey",ERROR_MESSAGE);
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
        this.profile = profile;
        ReloadableButton.setAllEnabled(true);
        root = profile.getRootEntry();
        tw = display.setTree(root);
    }
    
    /**
     * Invoked when saving the profile
     */
    public void saveProfileAs() {
        JFileChooser chooser = new JFileChooser();
        
        //accepts the file
        if(chooser.showSaveDialog(display) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile(); // <--- our file 
            if(!file.getPath().endsWith(Profile.FILE_ENDING)) file = new File(file.getPath() + Profile.FILE_ENDING);
            profile.setFile(file);
            try {
                profile.encrypt();
            } catch (PWMException ex) {
                messageDialog(ex.getMessage(),ERROR_MESSAGE);
            }
        }
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
        String categoryName = inputDialog("category.create");
        if(categoryName != null && categoryName.length() > 0) {
            Category category = new Category(display.getSelectedContainer(),categoryName);
            tw.nodeInserted(category);
            tw.structureChanged(category);
            display.updateTree();
        }
    }
    
    /**
     * Invoked when editing a category
     */
    public void editCategory() {
        EntryContainer ec = display.getSelectedContainer();
        if(ec instanceof Category) {
            Category category = ((Category) ec);
            String newCategoryName = inputDialog("category.edit");
            if(newCategoryName != null && newCategoryName.length() > 0) {
                category.setName(newCategoryName);
                tw.nodeChanged(category);
                tw.structureChanged(category);
                display.updateTree();
            }
        }
    }
    
    /**
     * Invoked when removing a category
     */
    public void removeCategory() {
        EntryContainer ec = display.getSelectedContainer();
        if(ec instanceof Category) {
            Category category = ((Category) ec);
            int newCategoryName = confirmDialog("category.remove",WARNING_MESSAGE);
            if(newCategoryName == OK_OPTION) {
                category.getParent().getEntries().remove(category);
                tw.nodeRemoved(category);
                tw.structureChanged(category);
                display.updateTree();
            }
        }
    }
    
    /**
     * Invoked when creating a password
     */
    public void createPassword() {
        String passwordTitle = inputDialog("password.create.title");
        String passwordUsername = inputDialog("password.create.username");
        String passwordPassword = inputDialog("password.create.password");
        String passwordWebsite = inputDialog("password.create.webiste");
        
        if(passwordTitle == null || passwordUsername == null || passwordPassword == null || passwordWebsite == null) return;
        
        EntryContainer cont = display.getSelectedContainer();
        if(cont != root) new PasswordEntry(display.getSelectedContainer(),passwordTitle,passwordUsername,passwordPassword,passwordWebsite);
    }
    
    /**
     * Invoked when editing a password
     */
    public void editPassword() {
        //Not implemented jet
    }
    
    /**
     * Invoked when removing a password
     */
    public void removePassword() {
        //Not implemented jet
    }
    
}
