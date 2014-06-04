package pwm;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.OK_OPTION;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

import javax.swing.JPasswordField;
import javax.swing.filechooser.FileFilter;

import pwm.profilemodel.Category;
import pwm.profilemodel.EntryContainer;
import pwm.profilemodel.PasswordEntry;
import pwm.profilemodel.ProfileEntry;
import pwm.profilemodel.RootEntry;
import pwm.profilemodel.passwordfields.EntryField;
import pwm.ui.Display;
import pwm.ui.PasswordEntryWindow;
import pwm.ui.rendering.ReloadableButton;
import pwm.ui.rendering.TreeWrapper;
import pwm.utils.PWMCharset;

/**
 * Handles the interactions
 *
 * @author Dominik Scholz, Samuel Schmidt
 * @version 0.2
 */
public class Controller {

    private Assets assets;
    private Display display;
    private Profile profile;
    private CryptFile cryptFile;
    private RootEntry root;
    private TreeWrapper tw;
    private JPasswordField pf;
    private String cryptFileName =  "c:\\simple\\test";
    
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

    private String inputDialog(String message, String initalSelectionValue) {
        return (String) JOptionPane.showInputDialog(display, assets.getLocalized(message), assets.getLocalized(message + ".title"), PLAIN_MESSAGE, null, null, initalSelectionValue);
    }

    private String inputDialog(String message, int messagetype) {
        return JOptionPane.showInputDialog(display, assets.getLocalized(message), assets.getLocalized(message + ".title"), messagetype);
    }

    private String maskedConfirmDialog(String message) {
        pf = new JPasswordField();
        int check = JOptionPane.showConfirmDialog(display, pf, assets.getLocalized(message + ".title"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (check == JOptionPane.OK_OPTION) {
            String password = new String(pf.getPassword());
            if (password != null && password.length() > 0) {
                return password;
            }
        }
        return "";
    }

    private int confirmDialog(String message) {
        return confirmDialog(message, PLAIN_MESSAGE);
    }

    private int confirmDialog(String message, int messagetype) {
        String localizedMessage = assets.getLocalized(message);
        if (localizedMessage == null) {
            localizedMessage = message;
        }
        return JOptionPane.showConfirmDialog(display, assets.getLocalized(message), assets.getLocalized(message + ".title"), JOptionPane.OK_CANCEL_OPTION, messagetype);
    }

    //////////
    // Profile
    //////////
    /**
     * Invoked when creating a new profile
     */
    public void newProfile() {
        String masterkey = maskedConfirmDialog("new.masterkey");
        loadProfile(new Profile(masterkey));
    }

    /**
     * Invoked when opening a profile
     */
    public void openProfile() {
        JFileChooser chooser = new JFileChooser();
        FileFilter filter1 = new ExtensionFileFilter(".pwmp", new String[]{"pwmp"});
        chooser.setFileFilter(filter1);

        //accepts the file
        if (chooser.showOpenDialog(display) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile(); // <--- our file 
            //has right ending
            if (file.getPath().endsWith(Profile.FILE_ENDING)) {
                //get key
                String masterkey = maskedConfirmDialog("open.masterkey");
                if (masterkey != null && masterkey.length() > 0) {
                    try {
                        loadProfile(new Profile(masterkey, file));
                    } catch (PWMException ex) {
                        //couldn't read or decrypt
                        messageDialog("open.wrongkey", ERROR_MESSAGE);
                    }
                }
            } else {
                messageDialog("open.wrongfile", ERROR_MESSAGE);
            }
        }
    }

    /**
     * Invoked when succesfully creating a profile
     * @param profile
     * the profile which was created
     */
    private void loadProfile(Profile profile) {
        this.profile = profile;
        ReloadableButton.setAllEnabled(true);
        root = profile.getRootEntry();
        tw = display.setTree(getRoot());
    }

    /**
     * Invoked when saving the profile (Save)
     */
    public void saveProfile() {
        if (profile.getFile() == null) {
            saveProfileAs();    //Calls saveAs if location unknown
        } else {
            try {
                profile.encrypt();
            } catch (PWMException ex) {
                messageDialog(ex.getMessage(), ERROR_MESSAGE);
            }
        }
    }

    /**
     * Invoked when saving the profile (Save as...)
     */
    public void saveProfileAs() {
        JFileChooser chooser = new JFileChooser();

        //accepts the file
        if (chooser.showSaveDialog(display) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile(); // <--- our file 
            if (!file.getPath().endsWith(Profile.FILE_ENDING)) {
                file = new File(file.getPath() + Profile.FILE_ENDING);
            }
            profile.setFile(file);
            try {
                profile.encrypt();
            } catch (PWMException ex) {
                messageDialog(ex.getMessage(), ERROR_MESSAGE);
            }
        }
    }

    /**
     * Invoked when changing the masterkey
     */
    public void changeMasterkey() {
        String newMasterKey = inputDialog("masterkey.change", JOptionPane.QUESTION_MESSAGE);
        if (newMasterKey == null || newMasterKey.equals("")) {
            return;
        } else {
            profile.setKey(newMasterKey.getBytes(PWMCharset.get()));
        }
    }

    /**
     * Invoked when closing the application
     */
    public void close() {
        if (JOptionPane.showConfirmDialog(null, assets.getLocalized("close.dialog"), assets.getLocalized("close.title"), JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }

    //////////
    // Cryptfile
    //////////
    /**
     * Invoked when creating a CryptFile
     */
    public void createCryptFile(){
        cryptFileName = profile.getFile().getParent()+"\\cryptfile";
        cryptFile = new CryptFile(cryptFileName, 128);
        try {
            cryptFile.encrypt();
        } catch (PWMException ex) {
            Logger.getLogger(CryptFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //////////
    // Edit
    //////////
    /**
     * Invoked when creating a category
     */
    public void createCategory() {
        String categoryName = inputDialog("category.create");
        if (categoryName != null && categoryName.length() > 0) {
            Category category = new Category(display.getSelectedContainer(), categoryName);
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
        if (ec instanceof Category) {
            Category category = ((Category) ec);
            String newCategoryName = inputDialog("category.edit");
            if (newCategoryName != null && newCategoryName.length() > 0) {
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
        if (ec instanceof Category) {
            Category category = ((Category) ec);
            int newCategoryName = confirmDialog("category.remove", WARNING_MESSAGE);
            if (newCategoryName == OK_OPTION) {
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
    public void createPassword(String mode) {
  
    	PasswordEntryWindow.get();
    	
        EntryContainer cont = display.getSelectedContainer();
        if (cont != getRoot()) {
//            new PasswordEntry(display.getSelectedContainer(), passwordTitle, passwordUsername, passwordPassword, passwordWebsite);
        }
    }

    /**
     * Invoked when editing a password
     */
    public void editPassword() {
//        EntryContainer ec = display.getSelectedContainer();
//        int index = display.getSelectedIndex();
//         if (ec instanceof Category) {
//            Category category = ((Category) ec);
//            int newEntryName = confirmDialog("entry.edit", WARNING_MESSAGE);
//            if (newEntryName == OK_OPTION) {
//                String passwordTitle = inputDialog("password.edit.title");
//                String passwordUsername = inputDialog("password.edit.username");
//                String passwordPassword = maskedConfirmDialog("password.edit.password");
//                String passwordWebsite = inputDialog("password.edit.website");
//                if (passwordTitle == null || passwordUsername == null || passwordPassword == null || passwordWebsite == null) {
//                    return;
//                }
//                PasswordEntry pe = (PasswordEntry) category.getEntries().get(index);
//                List<EntryField> ef = pe.getEntryfields();
//                ef.get
//            }
//         }
//        createPassword("edit");
//        EntryContainer ec = display.getSelectedContainer();
//        ProfileEntry pe = display.getSelectedRow();
//        if (ec instanceof Category) {
//            Category category = ((Category) ec);
//            category.removeEntry(pe);
//        }
    }

    /**
     * Invoked when removing a password
     */
    public void removePassword() {
        EntryContainer ec = display.getSelectedContainer();
        ProfileEntry pe = display.getSelectedRow();

        if (ec instanceof Category) {
            Category category = ((Category) ec);
            int newEntryName = confirmDialog("entry.remove", WARNING_MESSAGE);
            if (newEntryName == OK_OPTION) {
                category.removeEntry(pe);
            }
        }
    }

    /**
     * Invoked when changing the language
     */
    public void changeLanguage() {
        Object[] selectionValues = {"English", "German(Deutsch)"};
        String initialSelection = assets.getSetting("lang").equals("en") ? "English" : "German(Deutsch)";
        String language;
        Object selection = JOptionPane.showInputDialog(null, null,
                assets.getLocalized("menubar.view.changelanguage"), JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        if (selection != null) {
            selection = (String) selection;
            language = selection.equals("English") ? "en" : "de";
            assets.setSetting("lang", language);
            display.updateMenuBar();
            display.updateTableHeaders();
        }
    }

    //////////
    // Help
    //////////
    
    /**
     * Invoked by the Hilfe-MenuItem: Opens the help-Page
     */
    public void help(){
        
        String url = "http://lmgtfy.com/?q=Password+Manager+Help";
        
        try {
            openUrl(url);
        } catch (IOException e) {
            
        } catch (URISyntaxException e) {
            
        }
    }
    
    public void checkUpdate(){
    
    }
    
    public void webPage(){
        
    }
    
    public void donate(){
        
    }
    
    public void about(){
        
    }
    
    //////////
    // Utility-Methods
    //////////
    
    /**
     * Returns the root Entry
     * @return the root
     */
    public RootEntry getRoot() {
        return root;
    }
    
    /**
     * Opens the given URL in the Default-Browser
     * @param url the URL
     * @throws IOException
     * @throws URISyntaxException
     */
    public void openUrl(String url) throws IOException, URISyntaxException {
        if(java.awt.Desktop.isDesktopSupported() ) {
              java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

              if(desktop.isSupported(java.awt.Desktop.Action.BROWSE) ) {
                java.net.URI uri = new java.net.URI(url);
                    desktop.browse(uri);
              }
            } 
      }
}
