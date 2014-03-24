package pwm.ui;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import pwm.Assets;

/**
 * Represents a Reloadable Lable (text)
 * It also provides the facility for renaming every
 * ReloadableLable, because it stores every added one
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class ReloadableLabel {
    
    private static List<ReloadableLabel> lableList = new LinkedList<>();
    private String identifier;
    private JLabel label;
    
    public ReloadableLabel(String identifier, JLabel label) {
        this.identifier = identifier;
        this.label = label;
        updateLanguage();
        lableList.add(this);
    }
    
    public JLabel getLabel() {
        return label;
    }
    
    public void updateLanguage() {
        label.setText(Assets.get().getLocalized(identifier,identifier));
    }
    
    public String lastIdentifier() {
        return identifier.substring(identifier.lastIndexOf(".")+1,identifier.length());
    }
    
    public static JLabel register(String identifier) {
        JLabel label = new JLabel();
        new ReloadableLabel(identifier,label);
        return label;
    }
    
    public static void updateAllLanguage() {
        for(ReloadableLabel label : lableList) label.updateLanguage();
    }
}
