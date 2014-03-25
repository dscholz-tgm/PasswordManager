package pwm.ui.rendering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

/**
 * Class for rendering the Table
 *
 * @author Dominik Scholz
 * @version 0.1
 */
public class TableRenderer implements TableCellRenderer {
    
    private final Font font = new Font(Font.SANS_SERIF,Font.PLAIN,12);
    private final Color fontColor = PWMColors.TEXT;
    private final Color transparent = new Color(0,0,0,0);
    private final Color selectColor = new Color(0,70,140,40);
    private final Color rowColor1 = new Color(250,250,250,140);
    private final Color rowColor2 = new Color(220,220,220,140);
    private final LayoutManager layout = new BorderLayout();
    private final Border border = new EmptyBorder(1,2,1,8);

    public TableRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String text = (String) value;
        JPanel comp = new JPanel(layout);
        comp.setBorder(border);
        JLabel lab = new JLabel(text);
        lab.setForeground(fontColor);
        comp.add(lab, BorderLayout.WEST);
        comp.setBackground(isSelected ? selectColor : row % 2 == 0 ? rowColor1 : rowColor2);
        return comp;
    }
}
