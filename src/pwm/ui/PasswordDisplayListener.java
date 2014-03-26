package pwm.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import pwm.Assets;
import pwm.profilemodel.passwordfields.PasswordField;

/**
 * Displays the password
 *
 * @author Dominik Scholz
 * @version 0.1
 */
public class PasswordDisplayListener implements MouseListener {
    
    private static final int HARDCODED_PASSWORDINT_REMOVE = 2;
    
    /**
     * Dirty don't let it be
     * @param e 
     */
    private void click(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JTable target = (JTable)e.getSource();
            Object value = target.getModel().getValueAt(target.getSelectedRow(), HARDCODED_PASSWORDINT_REMOVE);
            if(value instanceof PasswordField) {
                JOptionPane.showMessageDialog(target.getParent(), Assets.get().getLocalized("password.showplain") + ((PasswordField) value).getValue(),
                        Assets.get().getLocalized("password.showplain.title"), JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        click(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
