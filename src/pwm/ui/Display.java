package pwm.ui;

import pwm.ui.rendering.PWMColors;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import pwm.Assets;
import pwm.Controller;
import pwm.profilemodel.Category;
import pwm.profilemodel.RootEntry;
import pwm.ui.rendering.TreeRenderer;
import pwm.ui.rendering.TreeWrapper;

/**
 * The display of the manager
 *
 * @author Dominik
 * @version 0.3
 */
public class Display extends JFrame {

    private Assets assets;
    private Controller controller;

    public Display(Controller controller, Assets assets, int width, int height) {
        super(assets.getLocalized("windowname"));
        this.assets = assets;
        this.controller = controller;

        //centered window
        this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2,
                width, height);

        //close whole application when closing
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //background
        this.setBackground(PWMColors.MAIN_COLOR);
        this.getContentPane().setBackground(PWMColors.MAIN_COLOR);

        //Initializing
        JMenuBar menuBar = new PWMMenuBar(new MenuListener(controller), assets);
        JToolBar toolBar = new PWMToolBar(assets);
        JSplitPane centerPanel = new JSplitPane();
        for (Component comp : centerPanel.getComponents()) {
            comp.setBackground(PWMColors.FRAME_COLOR);
        }
        SplitPaneUI ui = centerPanel.getUI();
        if (ui instanceof BasicSplitPaneUI) {
            ((BasicSplitPaneUI) ui).getDivider().setBorder(null);
        }
        JTree tree = new JTree();
        JScrollPane treePanel = new JScrollPane();
        JPanel rightPanel = new TablePanel(assets);
        JPanel footer = new PWMFooter(assets);

        RootEntry re = testTree();
        tree.setModel(new TreeWrapper(re));

        //Modifying
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setBackground(PWMColors.TREE_COLOR);
        tree.setCellRenderer(new TreeRenderer());
        treePanel.setViewportView(tree);
        treePanel.setMinimumSize(new Dimension(160,160));
        centerPanel.setDividerLocation(160);
        centerPanel.setDividerSize(0);

        //Local Strings
        updateLanguage();

        //Adding
        centerPanel.add(treePanel, JSplitPane.LEFT);
        centerPanel.add(rightPanel, JSplitPane.RIGHT);

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
