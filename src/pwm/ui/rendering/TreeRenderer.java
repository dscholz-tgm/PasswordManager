package pwm.ui.rendering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeCellRenderer;
import pwm.Assets;
import pwm.profilemodel.EntryContainer;

/**
 * Class for rendering the JTree
 *
 * @author Dominik Scholz
 * @version 0.2
 */
public class TreeRenderer implements TreeCellRenderer {

    private final Font font = new Font(Font.SANS_SERIF,Font.PLAIN,12);
    private final Color fontColor = PWMColors.BLUE;
    private final Color selectColor = PWMColors.TREE_COLOR.brighter();
    private final LayoutManager layout = new BorderLayout();
    private final Border border = new EmptyBorder(1,2,1,8);
    private final Icon leaficon;
    private final Icon noleaficon;

    public TreeRenderer() {
        leaficon = new ImageIcon(Assets.get().getLeafIcon());
        noleaficon = new ImageIcon(Assets.get().getNoLeafIcon());
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        EntryContainer container = (EntryContainer) value;
        JPanel comp = new JPanel(layout);
        comp.setBorder(border);
        JLabel lab = new JLabel(container.getName());
        lab.setFont(font);
        lab.setIcon(leaf ? leaficon : noleaficon);
        lab.setForeground(fontColor);
        comp.add(lab, BorderLayout.EAST);
        comp.setBackground(selected ? selectColor : tree.getBackground());
        return comp;
    }

}
