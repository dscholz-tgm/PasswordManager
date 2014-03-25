package pwm.ui;

import java.awt.BorderLayout;
import pwm.ui.rendering.PWMColors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import pwm.Assets;
import pwm.ui.rendering.TableRenderer;

/**
 * The table panel, which shows the different password entries
 *
 * @author Dominik Scholz
 * @version 0.2
 */
public class TablePanel extends JPanel {

    private Assets assets;
    private BufferedImage background;
    private BufferedImage buffer;
    private JTable table;

    public TablePanel(Assets assets) {
        super(new BorderLayout());
        this.assets = assets;

        this.setBackground(PWMColors.BODY_COLOR);

        String[] columnNames = {"Title","Username","Password","Website"};

        Object[][] data = {
            {"Google", "mailmanXx@gmail.com", "admin", "google.com"},
            {"elearning", "mmuster", "Password", "elearning.tgm.ac.at"},
            {"Reddit", "TheManWithTheSafe", "12848963478128408", "reddit.com"},
            {"Imgur", "BananaLama", "o.O.o.O", "imgur.com"},
            {"Twitter", "Tweetbot", "birdTWeTx", "twitter.com"},
        };

        table = new JTable(data, columnNames);
        table.setOpaque(true);
        table.setBackground(new Color(0,0,0,0));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0,0));
        table.setDefaultRenderer(Object.class, new TableRenderer());
        table.setDragEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(table) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                super.getParent().repaint();
            }};
        scrollPane.setOpaque(false);
        table.setFillsViewportHeight(true);
        this.add(scrollPane);

        loadBackground(assets.getIcon());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        if(buffer == null || width != buffer.getWidth() || height != buffer.getHeight()) {
            int a = Math.min(width, height);
            buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = buffer.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.setColor(this.getBackground());
            g2.fillRect(0, 0, width, height);
            g2.drawImage(background, width/2 - a/2, height/2 - a/2, a, a, this);
            g2.dispose();
        }
        g.drawImage(buffer, 0, 0, buffer.getWidth(), buffer.getHeight(), this);
        
    }

    private void loadBackground(Image background) {
        this.background = new BufferedImage(background.getWidth(this), background.getHeight(this), BufferedImage.TYPE_INT_RGB);
        Graphics g = this.background.getGraphics();
        Color backgroundtrans = new Color(getBackground().getRed(), getBackground().getGreen(), getBackground().getBlue(), 220);
        g.setColor(PWMColors.BODY_COLOR);
        g.fillRect(0, 0, this.background.getWidth(), this.background.getHeight());
        g.drawImage(background, 0, 0, null);
        g.setColor(backgroundtrans);
        g.fillRect(0, 0, this.background.getWidth(), this.background.getHeight());
        g.dispose();
    }

}
