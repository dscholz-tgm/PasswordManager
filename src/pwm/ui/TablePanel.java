package pwm.ui;

import pwm.ui.rendering.PWMColors;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import pwm.Assets;

/**
 * The table panel, which shows the different password entries
 * 
 * @author Dominik Scholz
 * @version 0.1
 */
public class TablePanel extends JPanel {

    private Assets assets;
    private BufferedImage background;
    
    public TablePanel(Assets assets) {
        super();
        this.assets = assets;
        
        this.setBackground(PWMColors.BRIGHTER_3);
        loadBackground(assets.getIcon());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        int a = Math.min(getWidth(), getHeight());
        g.drawImage(background, getWidth()/2-a/2, getHeight()/2-a/2, a, a, this);
    }

    private void loadBackground(Image background) {
        Color backgroundtrans = new Color(getBackground().getRed(), getBackground().getGreen(), getBackground().getBlue(), 200);
        this.background = new BufferedImage(background.getWidth(this), background.getHeight(this),  
        BufferedImage.TYPE_BYTE_GRAY);  
        Graphics g = this.background.getGraphics();  
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.background.getWidth(), this.background.getHeight());
        g.drawImage(background, 0, 0, null);
        g.setColor(backgroundtrans);
        g.fillRect(0, 0, this.background.getWidth(), this.background.getHeight());
        g.dispose();
    }
    
    
}
