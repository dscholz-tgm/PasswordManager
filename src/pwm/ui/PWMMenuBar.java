package pwm.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import pwm.Assets;

/**
 * The menu bar
 * @author Dominik Scholz
 * @version 0.1
 */
public class PWMMenuBar extends JMenuBar {
    
    private Assets assets;
    private JMenu pointer;
    private final String identifier = "menubar";
    private ActionListener menuListener;

    public PWMMenuBar(Assets assets) {
        super();
        this.assets = assets;
        
        menuListener = new MenuListener();
        
        createMenu("profile");
            createItem("new", KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
            createItem("open", KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
            separator();
            createItem("save", KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
            createItem("saveas");
            separator();
            createItem("changemasterkey");
            createItem("changeencryptfile");
            
            separator();
            createItem("close", KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
            
        createMenu("edit");
            createItem("createcategory");
            createItem("editcategory");
            createItem("deletecategory");
            separator();
            createItem("createentry");
            createItem("editentry");
            createItem("deleteentry");
            separator();
            createItem("find", KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
        
        createMenu("view");
            createItem("changelanguage");
            separator();
            createItem("expandhierarchy");
            createItem("collapsehierarchy");
        
        createMenu("tools");
            createItem("importpwlist");
            createItem("exportpwlist");
            separator();
            createItem("passwordgen");
            separator();
            createItem("options");
            
        createMenu("help");
            createItem("help", KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));
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
        menu.setForeground(PWMColors.TEXT);
        this.add(menu);
        pointer = menu;
        return menu;
    }
    
    public JMenuItem createItem(String identifier) {
        String fullIdentifier = pointer.getName() + "." + identifier;
        JMenuItem item = ReloadableButton.register(fullIdentifier, new JMenuItem());
        item.setActionCommand(identifier);
        item.addActionListener(menuListener);
        pointer.add(item);
        return item;
    }
    
    public JMenuItem createItem(String identifier, KeyStroke ks) {
        JMenuItem item = createItem(identifier);
        item.setAccelerator(ks);
        return item;
    }
    
    private void separator() {
        pointer.add(new JSeparator());
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

}
