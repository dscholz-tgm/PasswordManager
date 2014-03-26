package pwm.ui.rendering;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import pwm.profilemodel.Category;

/**
 * Wrapper for the table model
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class TableWrapper implements TableModel {
    
    private Category container;
    private TableModelListener listener;
    
    public TableWrapper() {
    }
    
    public void setContainer(Category category) {
        this.container = container;
    }

    @Override
    public int getRowCount() {
        return container.getEntries().size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listener = l;
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listener = null;
    }
    
    public void tablechanged() {
        listener.tableChanged(new TableModelEvent(this));
    }
    
}
