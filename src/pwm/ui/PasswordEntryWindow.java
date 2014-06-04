package pwm.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pwm.Assets;
import pwm.ui.rendering.PWMColors;

/**
 * 
 * @author Brunner Helmuth
 * @version Jun 4, 2014
 */
public class PasswordEntryWindow extends JFrame {

	private static PasswordEntryWindow instance;

	private Assets assets= Assets.get();

	private int width= 500, height= 350;

	private JPanel superpanel, center;
	private Container co;
	private GridBagConstraints c;

	private JTextField title, username, url;
	private JPasswordField passwordone, passwordtwo;
	private JButton save, exit;

	//1. title, username, url, passwordone, passwordtwo
	private JLabel[] labels= {
			new JLabel(assets.getLocalized("passwordentrywindow.title")),
			new JLabel(assets.getLocalized("passwordentrywindow.username")),
			new JLabel(assets.getLocalized("passwordentrywindow.url")),
			new JLabel(assets.getLocalized("passwordentrywindow.passwordone")),
			new JLabel(assets.getLocalized("passwordentrywindow.passwordtwo")),
	};


	private PasswordEntryWindow() {
		super();

		//centered window
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2,
				width, height);

		//close whole application when closing
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//background
		this.setBackground(PWMColors.MAIN_COLOR);
		this.getContentPane().setBackground(PWMColors.MAIN_COLOR);

		superpanel= new JPanel();
		center= new JPanel();

		this.init();

	}

	public static PasswordEntryWindow get() {
		if(instance!=null)
			instance.dispose();
		instance= new PasswordEntryWindow();
		instance.setVisible(true);

		return instance;
	}

	/**
	 * Loads all components.
	 */
	public void init() {
		superpanel.setLayout(new BorderLayout());

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

		JPanel[] panels= new JPanel[5];

		for(int i=0;i<5;i++) {
			panels[i]= new JPanel();
			panels[i].setLayout(new BorderLayout());
			panels[i].add(labels[i], BorderLayout.WEST);
		}

		title= new JTextField();
		title.setColumns(30);
		panels[0].add(title, BorderLayout.EAST);
		center.add(panels[0]);

		username= new JTextField();
		username.setColumns(30);
		panels[1].add(username, BorderLayout.EAST);
		center.add(panels[1]);

		url= new JTextField();
		url.setColumns(30);
		panels[2].add(url, BorderLayout.EAST);
		center.add(panels[2]);		

		passwordone= new JPasswordField();
		passwordone.setColumns(30);
		panels[3].add(passwordone, BorderLayout.EAST);
		center.add(panels[3]);		

		passwordtwo= new JPasswordField();
		passwordtwo.setColumns(30);
		panels[4].add(passwordtwo, BorderLayout.EAST);
		center.add(panels[4]);		

		this.save= new JButton("save");
		this.exit= new JButton("exit");

		c= new GridBagConstraints();

		//TODO Fix this not really good
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 2.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.LAST_LINE_END; //bottom of space
		c.insets = new Insets(0,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 0;   //2 columns wide
		c.gridy = 0;       //third row

		co= new Container();
		co.setLayout(new GridBagLayout());

		co.add(this.save, c);
		co.add(this.exit, c);

		this.superpanel.add(center);
		super.add(this.superpanel);
		super.add(co, BorderLayout.SOUTH);
	}

}
