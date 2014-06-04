package pwm;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import test.passwordgen.AlphanumericPasswordGenerator;

/**
 * quick and dirty
 * @author Samwise Gamgee
 */
public class CustomDialog extends JDialog {
    private JPanel myPanel;

    public CustomDialog(JFrame frame, boolean modal, String title, String button, final String length) {
        myPanel = new JPanel();
        getContentPane().add(myPanel);
        setLocationRelativeTo(frame);
        setVisible(true);

        JLabel jl = new JLabel(button);
        jl.setBounds(45, 1, 275, 50);
        myPanel.add(jl);

        JButton gen = new JButton("STRG+C");
        gen.setBounds(100, 120, 10, 10);

        final JSlider js = new JSlider(4, 128);
        js.setBounds(45, 45, 175, 20);
        myPanel.add(js);

        final JLabel value = new JLabel(length + js.getValue());
        value.setBounds(45, 65, 60, 20);

        myPanel.add(value);

        js.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                value.setText(length + js.getValue());
            }
        });

        gen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                StringSelection stringSelection = new StringSelection(new AlphanumericPasswordGenerator().generatePW(js.getValue(), "420".hashCode()));
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
            }
        });

        myPanel.add(gen);

        myPanel.setName(title);
        setSize(300, 125);
    }
}
