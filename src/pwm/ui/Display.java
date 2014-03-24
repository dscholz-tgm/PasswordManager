package pwm.ui;

import pwm.ui.rendering.PWMColors;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import pwm.Assets;
import pwm.profilemodel.Category;
import pwm.profilemodel.RootEntry;
import pwm.ui.rendering.TreeRenderer;
import pwm.ui.rendering.TreeWrapper;

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
        
        RootEntry re = testTree();
        tree.setModel(new TreeWrapper(re));
        
        //Modifying
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setBackground(this.getBackground());
        tree.setCellRenderer(new TreeRenderer());
        centerPanel.setDividerLocation(160);
        
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

    private RootEntry testTree() {
        RootEntry re = new RootEntry();
        Category cat0 = new Category(re);
        Category cat1 = new Category(re);
        Category cat2 = new Category(re);
        Category cat3 = new Category(re);
        
        Category cat0_0 = new Category(cat0);
        Category cat0_1 = new Category(cat0);
        
        Category cat1_0 = new Category(cat1);
        Category cat1_1 = new Category(cat1);
        Category cat1_2 = new Category(cat1);
        
        Category cat1_1_1 = new Category(cat1_1);
        Category cat1_1_2 = new Category(cat1_1);
        return re;
    }

}
