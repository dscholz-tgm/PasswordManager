package pwm.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import pwm.Assets;

/**
 * Listens to the menu bar items
 * @author Dominik Scholz
 * @version 0.1
 */
public class MenuListener implements ActionListener {
    
    private static Assets assets = Assets.get();
    
    public static void menuAction(String action) {
        System.out.println(action);
        switch(action) {
            case "about": 
//                new AbouCtDisplay(assets.getLocalized("about.title"), 550, 300, MenuListener.class.getResource("/data/html/about.html").toString());
                break;
            case "close":
                if(JOptionPane.showConfirmDialog(null, assets.getLocalized("close.dialog"), assets.getLocalized("close.title"), JOptionPane.YES_NO_OPTION) == 0) System.exit(0);
                break;
            case "new":
//                NewGameDisplay.get().show(Slidepuzzle.get().display, true, assets.getLocalized("newgame"),760,640);
                break;
            case "help":
//                new HelpDisplay("Spielhilfe", 640, 640, MenuListener.class.getResource("/data/html/help_" + assets.getSetting("lang") + ".html").toString());
                break;
            case "highscore":
//                Slidepuzzle.get().highscore.show();
                break;
            default:
                //silence and friendly :3
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuAction(e.getActionCommand());
    }
    
}
