package pwm.ui;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import pwm.profilemodel.EntryContainer;

/**
 * Listener for the selection of a category in the tree menu
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class CategorySelectListener implements TreeSelectionListener {

    private PWMTable table;
    
    public CategorySelectListener(PWMTable table) {
        this.table = table;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        JTree tree = (JTree) e.getSource();
        Object ob = tree.getLastSelectedPathComponent();
        if(ob != null && ob instanceof EntryContainer) table.updateModel((EntryContainer) ob);
    }
    
}
