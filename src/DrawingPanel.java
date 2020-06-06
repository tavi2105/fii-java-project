import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final Player player;
    static int W, H;
    BufferedImage image;
    public static Graphics2D graphics;
    public DrawingPanel(Player player) {
        this.player = player;
        W = player.getTableDimension();
        H = player.getTableDimension();
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        Color color = new Color(0,0,0);
        drawLine(H/3,0,H/3,H,color);
        drawLine((H/3)*2,0,(H/3)*2,H,color);
        drawLine(0,W/3,W,W/3,color);
        drawLine(0,(W/3)*2,W,(W/3)*2,color);
    }
    public void drawLine(int x1, int y1, int x2, int y2, Color color){
        Line2D lin = new Line2D.Float(x1, y1, x2, y2);
        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(10));
        graphics.draw(lin);
    }
    public void drawX(int x1, int y1){
        Line2D lin = new Line2D.Float(x1-55, y1-55, x1+55, y1+55);
        Line2D lin2 = new Line2D.Float(x1-55, y1+55, x1+55, y1-55);
        graphics.setColor(new Color(142,67,0));
        graphics.setStroke(new BasicStroke(20));
        graphics.draw(lin);
        graphics.draw(lin2);
    }
    public void drawO(int x1, int y1){
        Ellipse2D.Double eli = new Ellipse2D.Double(x1-55,y1-55,110,110);
        graphics.setColor(new Color(5,76,124));
        graphics.setStroke(new BasicStroke(20));
        graphics.draw(eli);
    }

    @Override
    public void update(Graphics g) { }

    @Override
    protected void paintComponent(Graphics g) { // same
        g.drawImage(image, 0, 0, this);
    }

}
