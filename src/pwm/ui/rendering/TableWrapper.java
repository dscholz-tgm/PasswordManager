package pwm.ui.rendering;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import pwm.profilemodel.EntryContainer;

/**
 * Wrapper for the table model
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class TableWrapper implements TableModel {
    
    private EntryContainer container;
    
    public TableWrapper() {
    }
    
    public void setContainer(EntryContainer category) {
        this.container = container;
    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
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
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
    
}
