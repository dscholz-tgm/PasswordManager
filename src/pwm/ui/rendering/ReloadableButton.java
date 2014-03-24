package pwm.ui.rendering;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import pwm.Assets;

/**
 * Represents a Reloadable Button (image, text and enable)
 * It also provides the facility for renaming every
 * ReloadableButton, because it stores every added one
 * 
 * @author Dominik Scholz
 * @version 0.2
 */
public class ReloadableButton {
    
    private static List<ReloadableButton> buttonList = new LinkedList<>();
    private String identifier;
    private AbstractButton button;
    private boolean defaultEnabled;
    
    public ReloadableButton(String identifier, AbstractButton button) {
        this(identifier, button, true);
    }
    
    public ReloadableButton(String identifier, AbstractButton button, boolean enabled) {
        this.identifier = identifier;
        this.button = button;
        this.defaultEnabled = enabled;
        button.setEnabled(enabled);
        updateLanguage();
        updateIcon();
        buttonList.add(this);
    }
    
    public AbstractButton getButton() {
        return button;
    }
    
    public void updateLanguage() {
        button.setText(Assets.get().getLocalized(identifier,identifier));
    }
    
    public void updateIcon() {
        URL url = getClass().getResource(Assets.DATA_PATH + "icons/" + lastIdentifier() + ".png");
        if(url != null) button.setIcon(new ImageIcon(url));
    }
    
    private void setEnabled(boolean enabled) {
        button.setEnabled(enabled);
    }
    
    private void resetEnabled() {
        button.setEnabled(defaultEnabled);
    }
    
    public String lastIdentifier() {
        return identifier.substring(identifier.lastIndexOf(".")+1,identifier.length());
    }
    
    public static JMenuItem registerMenuItem(String identifier) {
        JMenuItem button = new JMenuItem();
        new ReloadableButton(identifier,button);
        return button;
    }
    
    public static JButton registerButton(String identifier) {
        JButton button = new JButton();
        new ReloadableButton(identifier,button);
        return button;
    }
    
    public static <T extends AbstractButton> T register(String identifier,T button, boolean enabled) {
        new ReloadableButton(identifier,button,enabled);
        return button;
    }
    
    public static void updateAllLanguage() {
        for(ReloadableButton button : buttonList) button.updateLanguage();
    }
    
    public static void updateAllIcon() {
        for(ReloadableButton button : buttonList) button.updateIcon();
    }
    
    public static void setAllEnabled(boolean enabled) {
        for(ReloadableButton button : buttonList) button.setEnabled(true);
    }
    
    public static void resetAllEnabled() {
        for(ReloadableButton button : buttonList) button.resetEnabled();
    }


}
