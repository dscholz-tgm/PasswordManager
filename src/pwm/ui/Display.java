package pwm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import pwm.Assets;

/**
 * The display of the manager
 * 
 * @author Dominik
 * @version 0.2
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
        
        //background
        this.setBackground(PWMColors.BRIGHTER_3);
        this.getContentPane().setBackground(PWMColors.BRIGHTER_3);
        
        //Initializing
        JMenuBar menuBar = new PWMMenuBar(assets);
        JToolBar toolBar = new PWMToolBar(assets);
        JSplitPane centerPanel = new JSplitPane();
        JTree tree = new JTree();
        JPanel rightPanel = new TablePanel(assets);
        JPanel footer = new PWMFooter(assets);
        
        //Modifying
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setBackground(this.getBackground());
        centerPanel.setDividerLocation(128);
        
        //Local Strings
        updateLanguage();
        
        //Adding
        centerPanel.add(new JScrollPane(tree), JSplitPane.LEFT);
        centerPanel.add(new JScrollPane(rightPanel), JSplitPane.RIGHT);
        
        this.setJMenuBar(menuBar);
        this.add(toolBar, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(footer, BorderLayout.SOUTH);
        
        //Make magic and put the thing out there
        this.setVisible(true);
        
        this.assets = assets;
    }
    
    private void updateLanguage() {
    }

}
