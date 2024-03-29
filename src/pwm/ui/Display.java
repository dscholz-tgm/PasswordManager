package pwm.ui;

import pwm.ui.rendering.PWMColors;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.TreePath;
import pwm.Assets;
import pwm.Controller;
import pwm.profilemodel.EntryContainer;
import pwm.profilemodel.ProfileEntry;
import pwm.profilemodel.RootEntry;
import pwm.ui.rendering.TreeRenderer;
import pwm.ui.rendering.TreeWrapper;

/**
 * The display of the manager
 *
 * @author Dominik Scholz, Samuel Schmidt
 * @version 0.4
 */
public class Display extends JFrame {

    private Assets assets;
    private Controller controller;
    private JTree tree;
    private PWMMenuBar menuBar;
    private PWMTable rightPanel;

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
        menuBar = new PWMMenuBar(new MenuListener(controller), assets);
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
        rightPanel = new PWMTable(assets);
        JPanel footer = new PWMFooter(assets);

        tree.setModel(null);

        //Modifying
//        tree.setRootVisible(false);
//        tree.setShowsRootHandles(true);
        tree.setBackground(PWMColors.TREE_COLOR);
        tree.setCellRenderer(new TreeRenderer());
        tree.addTreeSelectionListener(new CategorySelectListener(getRightPanel()));
        treePanel.setViewportView(tree);
        treePanel.setMinimumSize(new Dimension(160, 160));
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

    public TreeWrapper setTree(RootEntry re) {
        TreeWrapper tr = new TreeWrapper(re);
        tree.setModel(tr);
        return tr;
    }

    /**
     * Returns the Selected Container(Category)
     * @return category
     */
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

    /**
     * Returns the Selected Profile Entry,
     * (the row in Password Entries)
     * @return the Profile Entry
     */
    public ProfileEntry getSelectedRow() {
        int index = rightPanel.getTable().getSelectedRow();
        EntryContainer kat = getSelectedContainer();
        return controller.getRoot().getEntries().get(index);
    }
    
    /**
     * Returns the Selected Profile Entry,
     * (the row in Password Entries)
     * @return the Profile Entry
     */
    public int getSelectedIndex() {
        return rightPanel.getTable().getSelectedRow()+1;
    }

    /**
     * Updates the Language of the Menubar
     */
    public void updateMenuBar() {
        JMenu[] jm = menuBar.getPointers();
        int cc;
        JMenuItem jmi;
        
        for (int i = 0; i < jm.length; i++) {
            jm[i].setText(assets.getLocalized(jm[i].getName()));
            cc = jm[i].getItemCount();
            for (int j = 0; j < cc; j++) {
                jmi = jm[i].getItem(j);
                if (jmi != null) {
                    jmi.setText(assets.getLocalized(jm[i].getName() + "." + jmi.getActionCommand()));
                }
            }
        }

    }
    
    /**
     * Updates the Language of the Table Headers
     */
    public void updateTableHeaders() {
        JTableHeader th = rightPanel.getTable().getTableHeader();
        TableColumnModel tcm = th.getColumnModel();
        if(tcm.getTotalColumnWidth() != 0){
            TableColumn tc = tcm.getColumn(0);
            tc.setHeaderValue(assets.getLocalized("entryfield.title"));
            tc = tcm.getColumn(1);
            tc.setHeaderValue(assets.getLocalized("entryfield.username"));
            tc = tcm.getColumn(2);
            tc.setHeaderValue(assets.getLocalized("entryfield.password"));
            tc = tcm.getColumn(3);
            tc.setHeaderValue(assets.getLocalized("entryfield.website"));
        }
    }

    /**
     * @return the rightPanel
     */
    public PWMTable getRightPanel() {
        return rightPanel;
    }

    private void updateLanguage() {
    }
}
