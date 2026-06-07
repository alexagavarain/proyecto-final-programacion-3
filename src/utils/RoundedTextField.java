package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class RoundedTextField extends JTextField {

    private int arcWidth = 15;
    private int arcHeight = 15;
    private Color borderColor = AppColors.iceGrey;
    private Color focusColor = Color.RED;
    private boolean isFocused = false;

    public RoundedTextField(int columns) {
        super(columns);
        initComponent();
    }

    public RoundedTextField(String text) {
        super(text);
        initComponent();
    }

    public RoundedTextField() {
        super();
        initComponent();
    }

    private void initComponent() {
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setCaretColor(Color.BLACK);
        
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isFocused = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                isFocused = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D.Double inputShape = new RoundRectangle2D.Double(
            0, 0, width - 1, height - 1, arcWidth, arcHeight
        );

        g2.setColor(getBackground());
        g2.fill(inputShape);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D.Double borderShape = new RoundRectangle2D.Double(
            0, 0, width - 1, height - 1, arcWidth, arcHeight
        );

        if (isFocused) {
            g2.setColor(focusColor);
            g2.setStroke(new BasicStroke(1.5f));
        } else {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.0f));
        }

        g2.draw(borderShape);
        g2.dispose();
    }

    public void setRoundness(int radius) {
        this.arcWidth = radius;
        this.arcHeight = radius;
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setFocusColor(Color color) {
        this.focusColor = color;
        repaint();
    }
}