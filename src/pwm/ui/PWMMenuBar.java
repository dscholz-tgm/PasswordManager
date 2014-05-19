package pwm.ui;

import pwm.ui.rendering.ReloadableButton;
import pwm.ui.rendering.PWMColors;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import pwm.Assets;

/**
 * The menu bar
 * 
 * @author Dominik Scholz
 * @version 0.2
 */
public final class PWMMenuBar extends JMenuBar {
    
    private final Assets assets;
    private JMenu[] pointer = new JMenu[5];
    private int jMenuCounter;
    private final String identifier = "menubar";
    private final ActionListener menuListener;

    public PWMMenuBar(ActionListener menuListener, Assets assets) {
        super();
        this.menuListener = menuListener;
        this.assets = assets;
        this.jMenuCounter = 0;
        
        pointer[0] = createMenu("profile");
            createItem("new", true, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
            createItem("open", true, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
            separator();
            createItem("save", false, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
            createItem("saveas", false);
            separator();
            createItem("changemasterkey", false);
            createItem("changeencryptfile", false);
            separator();
            createItem("close", true, KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        ++jMenuCounter;    
        
        pointer[1] = createMenu("edit");
            createItem("createcategory", false);
            createItem("editcategory", false);
            createItem("deletecategory", false);
            separator();
            createItem("createentry", false);
            createItem("editentry", false);
            createItem("deleteentry", false);
            separator();
            createItem("find", false, KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
        ++jMenuCounter; 
            
        pointer[2] = createMenu("view");
            createItem("changelanguage");
            separator();
            createItem("expandhierarchy", false);
            createItem("collapsehierarchy", false);
        ++jMenuCounter; 
            
        pointer[3] = createMenu("tools");
            createItem("importpwlist", false);
            createItem("exportpwlist", false);
            separator();
            createItem("passwordgen");
            separator();
            createItem("options");
        ++jMenuCounter;   
            
        pointer[4] = createMenu("help");
            createItem("help", true, KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
            createItem("update");
            separator();
            createItem("website");
            createItem("donate");
            separator();
            createItem("about");
    }
    
    public JMenu createMenu(String identifier) {
        String fullIdentifier = this.identifier + "." + identifier;
        String name = assets.getLocalized(fullIdentifier);
        JMenu menu = new JMenu(name != null && name.length() > 0 ? name : fullIdentifier);
        menu.setName(fullIdentifier);
        menu.setForeground(PWMColors.TEXT_2);
        this.add(menu);
        pointer[jMenuCounter] = menu;
        return menu;
    }
    
    public JMenuItem createItem(String identifier) {
        return createItem(identifier, true);
    }
    
    public JMenuItem createItem(String identifier, boolean enabled) {
        String fullIdentifier = getPointer(jMenuCounter).getName() + "." + identifier;
        JMenuItem item = ReloadableButton.register(fullIdentifier, new JMenuItem(), enabled);
        item.setActionCommand(identifier);
        item.addActionListener(menuListener);
        getPointer(jMenuCounter).add(item);
        return item;
    }
    
    public JMenuItem createItem(String identifier, boolean enabled, KeyStroke ks) {
        JMenuItem item = createItem(identifier, enabled);
        item.setAccelerator(ks);
        return item;
    }
    
    private void separator() {
        getPointer(jMenuCounter).addSeparator();
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(PWMColors.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(0,0,0,140));
        g.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(new GradientPaint(0,0,new Color(255,255,255,0),0,getHeight()/2,new Color(255,255,255,20)));
        g.fillRect(0, 0, getWidth(), getHeight()/2);
    }

    /**
     * @return the pointer at specified index
     */
    public JMenu getPointer(int index) {
        return pointer[index];
    }
    
    /**
     * @return the pointer
     */
    public JMenu[] getPointers() {
        return pointer;
    }
}
