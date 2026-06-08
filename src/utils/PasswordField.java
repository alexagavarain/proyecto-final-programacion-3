package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

public class PasswordField extends JPasswordField {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int arcWidth = 15;
    private int arcHeight = 15;

    public PasswordField() {
        setMaximumSize(new Dimension(300, 28));
        setAlignmentX(CENTER_ALIGNMENT);
        
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setCaretColor(Color.BLACK);

        setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D.Double shape = new RoundRectangle2D.Double(
            0, 0, width - 1, height - 1, arcWidth, arcHeight
        );

        g2.setColor(getBackground());
        g2.fill(shape);
        g2.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D.Double shape = new RoundRectangle2D.Double(
            0, 0, width - 1, height - 1, arcWidth, arcHeight
        );

        g2.setColor(AppColors.iceGrey);
        g2.setStroke(new BasicStroke(1.0f));

        g2.draw(shape);
        g2.dispose();
    }
}