package pwm.ui.rendering;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import pwm.profilemodel.EntryContainer;
import pwm.profilemodel.PasswordEntry;
import pwm.profilemodel.ProfileEntry;
import pwm.profilemodel.RootEntry;

/**
 * Wrapper for a tree model
 * @author Dominik
 */
public class TreeWrapper implements TreeModel {
    
    private RootEntry root;
    
    public TreeWrapper(RootEntry root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((EntryContainer) parent).getEntries().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((EntryContainer) parent).getEntries().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        if(node instanceof EntryContainer) {
            for(ProfileEntry entry : ((EntryContainer) node).getEntries()) {
                if(entry instanceof EntryContainer) return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((EntryContainer) parent).getEntries().indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }
    
}
