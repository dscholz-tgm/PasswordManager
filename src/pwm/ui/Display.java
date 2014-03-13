package pwm.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import pwm.Assets;

/**
 * The display of the manager
 * @author Dominik
 * @version 0.1
 */
public class Display extends JFrame {
    
    private Assets assets;
    
    public Display(int width, int height) {
        super(Assets.get().getLocalized("windowname"));
        assets = Assets.get();
        
        //centered window
        this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-width/2,
                Toolkit.getDefaultToolkit().getScreenSize().height/2-height/2, 
                width, height);
        
        //close whole application when closing
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Initializing
        JMenuBar menuBar = new PWMMenuBar(assets);
        JToolBar toolBar = new PWMToolBar(assets);
        
        //Local Strings
        updateLanguage();
        
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        
        //Adding
        this.setJMenuBar(menuBar);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        
        //Make magic and put the thing out there
        this.setVisible(true);
        
        this.assets = assets;
    }
    
     private void updateLanguage() {
    }

}
