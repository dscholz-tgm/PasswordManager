package pwm.ui.rendering;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import pwm.Assets;
import pwm.Controller;
import pwm.ui.PasswordEntryWindow;

/**
 * Listener for the Button in PasswordEntryWindow
 * @author Brunner Helmuth
 * @version Jun 4, 2014
 */
public class WindowButtonListener implements ActionListener {

	private PasswordEntryWindow pew;
	private Assets assets= Assets.get();
	private Controller c;

	public WindowButtonListener(PasswordEntryWindow pew, Controller c) {
		this.pew= pew;
		this.c= c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b= (JButton) e.getSource();

		if(b.getText().equals(assets.getLocalized("passwordentrywindow.buttonsave"))) {

			if(this.pew.getPasswordone().getText().equals(this.pew.getPasswordtwo().getText())) {
				c.createPassword(this.pew.getFieldTitle().getText(), this.pew.getUsername().getText(), this.pew.getPasswordone().getText(), this.pew.getUrl().getText());
				this.pew.dispose();
				
			}else
				JOptionPane.showMessageDialog(this.pew, assets.getLocalized("passwordentrywindow.errormessage"));
		}

		System.out.println(assets.getLocalized("passwordentrywindow.buttonsave"));
		if(b.getText().equals(assets.getLocalized("passwordentrywindow.buttonexit"))) {
			this.pew.dispose();
		}		
	}
}
