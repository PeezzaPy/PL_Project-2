import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class OvalButton extends JButton {
     private static final int ARC_WIDTH = 20; // Adjust the arc width to change the roundness of the button

    public OvalButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFont(new Font("Monetserrat",Font.BOLD,22));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw the rounded rectangle shape
        Shape roundedRect = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, ARC_WIDTH, ARC_WIDTH);
        g2.setColor(getBackground());
        g2.fill(roundedRect);

        g2.setColor(getForeground());
        g2.setFont(getFont());

        // Draw the button text at the center
        FontMetrics fm = g2.getFontMetrics();
        Rectangle textRect = fm.getStringBounds(getText(), g2).getBounds();
        int x = (width - textRect.width) / 2;
        int y = (height - textRect.height) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);

        g2.dispose();
}
   @Override
    protected void paintBorder(Graphics g) {
        // Remove the default border painting
    }
}
