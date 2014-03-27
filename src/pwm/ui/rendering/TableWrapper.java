package pwm.ui.rendering;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import pwm.Assets;
import pwm.profilemodel.Category;
import pwm.profilemodel.PasswordEntry;
import pwm.profilemodel.ProfileEntry;

/**
 * Wrapper for the table model
 * 
 * @author Dominik Scholz
 * @version 0.3
 */
public class TableWrapper implements TableModel {
    
    private Category category;
    private TableModelListener listener;
    
    public TableWrapper(Category category) {
        this.category = category;
    }
    
    @Override
    public int getRowCount() {
        return category.getEntries().size();
    }

    @Override
    public int getColumnCount() {
        return PasswordEntry.tableFields.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return Assets.get().getLocalized(PasswordEntry.tableFields[columnIndex].getName());
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return PasswordEntry.tableFields[columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProfileEntry entry = category.getEntries().get(rowIndex);
        if(entry instanceof PasswordEntry) return ((PasswordEntry) entry).getEntryfields().get(columnIndex);
        if(entry instanceof Category) return columnIndex == 0 ? "Category: " + ((Category) entry).getName() :"";
        return "";
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
