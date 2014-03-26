package pwm.ui;

import pwm.ui.rendering.PWMColors;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.TreePath;
import pwm.Assets;
import pwm.Controller;
import pwm.profilemodel.EntryContainer;
import pwm.profilemodel.RootEntry;
import pwm.ui.rendering.TreeRenderer;
import pwm.ui.rendering.TreeWrapper;

/**
 * The display of the manager
 *
 * @author Dominik
 * @version 0.4
 */
public class Display extends JFrame {

    private Assets assets;
    private Controller controller;
    private JTree tree;

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
        tree = new JTree();
        JScrollPane treePanel = new JScrollPane();
        PWMTable rightPanel = new PWMTable(assets);
        JPanel footer = new PWMFooter(assets);

        tree.setModel(null);
        
        //Modifying
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.setBackground(PWMColors.TREE_COLOR);
        tree.setCellRenderer(new TreeRenderer());
        tree.addTreeSelectionListener(new CategorySelectListener(rightPanel));
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

    public TreeWrapper setTree(RootEntry re) {
        TreeWrapper tr = new TreeWrapper(re);
        tree.setModel(tr);
        return tr;
    }

    public EntryContainer getSelectedContainer() {
        TreePath path = tree.getSelectionPath();
        Object lastOb = path == null ? null : path.getLastPathComponent();
        EntryContainer retour = lastOb != null && lastOb instanceof EntryContainer ? (EntryContainer) lastOb : (EntryContainer) tree.getModel().getRoot();
        return retour;
    }

    public void updateTree() {
        tree.expandPath(tree.getSelectionPath());
        tree.revalidate();
    }

}
