package pwm.ui.rendering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import pwm.profilemodel.Category;
import pwm.profilemodel.EntryContainer;
import pwm.profilemodel.RootEntry;

/**
 * Wrapper for the tree model
 * 
 * @author Dominik Scholz
 * @version 0.3
 */
public class TreeWrapper implements TreeModel {
    
    private RootEntry root;
    private TreeModelListener ml;
    
    public TreeWrapper(RootEntry root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((EntryContainer) parent).getContainerEntries().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((EntryContainer) parent).getContainerEntries().size();
    }

    @Override
    public boolean isLeaf(Object node) {
        if(node instanceof EntryContainer) {
            return ((EntryContainer) node).getContainerEntries().size() == 0;
        }
        return false;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((EntryContainer) parent).getContainerEntries().indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        ml = l;
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        ml = null;
    }

    public void nodeInserted(Category entry) {
        ml.treeNodesInserted(fireEvent(entry));
    }
    
    public void structureChanged(Category entry) {
        ml.treeStructureChanged(fireEvent(entry));
    }
    
    public void nodeChanged(Category entry) {
        ml.treeNodesChanged(fireEvent(entry));
    }
    
    public void nodeRemoved(Category entry) {
        ml.treeNodesRemoved(fireEvent(entry));
    }

    private TreeModelEvent fireEvent(Category entry) {
        List<EntryContainer> parent = new ArrayList<>();
        for(EntryContainer cone = entry.getParent(); cone instanceof Category; cone = ((Category) cone).getParent()) {
            parent.add(cone);
        }
        parent.add(root);
        Collections.reverse(parent);
        return new TreeModelEvent(this, parent.toArray());
    }
}
