package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;

public class CustomSpinner extends JSpinner {

    private static final long serialVersionUID = 1L;
    private int arcWidth = 15;
    private int arcHeight = 15;

    public CustomSpinner() {
        super();
        styles();
    }

    public CustomSpinner(SpinnerModel model) {
        super(model);
        styles();
    }

    private void styles() {
        setMaximumSize(new Dimension(300, 28));
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);

        setUI(new CustomSpinnerUI());
        setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 5));
        
        updateEditorStyles();
    }

    @Override
    public void setEditor(JComponent editor) {
        super.setEditor(editor);
        updateEditorStyles();
    }

    private void updateEditorStyles() {
        JComponent editor = getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor defaultEditor = (JSpinner.DefaultEditor) editor;
            defaultEditor.getTextField().setOpaque(false);
            defaultEditor.getTextField().setEditable(true);
            defaultEditor.getTextField().setForeground(Color.BLACK);
            defaultEditor.getTextField().setCaretColor(Color.BLACK);
            defaultEditor.getTextField().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        }
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

        if (!isEnabled()) {
            g2.setColor(AppColors.iceGrey);
        } else {
            g2.setColor(getBackground());
        }

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

    private class CustomSpinnerUI extends com.formdev.flatlaf.ui.FlatSpinnerUI {
        
        @Override
        protected Component createNextButton() {
            Component c = super.createNextButton();
            if (c instanceof JButton) {
                customizeButton((JButton) c, SwingConstants.NORTH);
            }
            return c;
        }

        @Override
        protected Component createPreviousButton() {
            Component c = super.createPreviousButton();
            if (c instanceof JButton) {
                customizeButton((JButton) c, SwingConstants.SOUTH);
            }
            return c;
        }

        private void customizeButton(JButton button, int direction) {
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            
            button = new JButton() {
                private static final long serialVersionUID = 1L;
                @Override
                public void paint(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    
                    if (this.isEnabled()) {
                        g2.setColor(AppColors.subtitle);
                    } else {
                        g2.setColor(AppColors.iceGrey);
                    }
                    
                    int[] xPoints = {getWidth() / 2 - 4, getWidth() / 2 + 4, getWidth() / 2};
                    int[] yPoints;
                    
                    if (direction == SwingConstants.NORTH) {
                        yPoints = new int[]{getHeight() / 2 + 2, getHeight() / 2 + 2, getHeight() / 2 - 2};
                    } else {
                        yPoints = new int[]{getHeight() / 2 - 2, getHeight() / 2 - 2, getHeight() / 2 + 2};
                    }
                    
                    g2.fillPolygon(xPoints, yPoints, 3);
                    g2.dispose();
                }
            };
            
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
        }
    }
}