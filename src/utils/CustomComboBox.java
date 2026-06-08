package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class CustomComboBox<T> extends JComboBox<T> {

    private static final long serialVersionUID = 1L;
    private int arcWidth = 15;
    private int arcHeight = 15;

    public CustomComboBox() {
        setMaximumSize(new Dimension(300, 32));
        setAlignmentX(CENTER_ALIGNMENT);
        
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        
        setUI(new CustomComboBoxUI());
        setRenderer(new CustomComboRenderer());
        
        setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 5));
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

    private class CustomComboBoxUI extends BasicComboBoxUI {
        
        @Override
        protected JButton createArrowButton() {
            JButton button = new JButton() {
                private static final long serialVersionUID = 1L;
                @Override
                public void paint(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(AppColors.subtitle);
                    
                    int[] xPoints = {getWidth() / 2 - 5, getWidth() / 2 + 5, getWidth() / 2};
                    int[] yPoints = {getHeight() / 2 - 2, getHeight() / 2 - 2, getHeight() / 2 + 3};
                    g2.fillPolygon(xPoints, yPoints, 3);
                    g2.dispose();
                }
            };
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            return button;
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup popup = new BasicComboPopup(comboBox) {
                private static final long serialVersionUID = 1L;
                @Override
                protected void configurePopup() {
                    super.configurePopup();
                    setBorder(BorderFactory.createLineBorder(AppColors.subtleAccent, 1));
                    setBackground(Color.WHITE);
                }
            };
            return popup;
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        }

        @Override
        public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
            ListCellRenderer<Object> renderer = comboBox.getRenderer();
            Component c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
            c.setFont(comboBox.getFont());
            
            if (comboBox.isEnabled()) {
                c.setForeground(comboBox.getForeground());
                c.setBackground(comboBox.getBackground());
            } else {
                c.setForeground(AppColors.subtitle);
                c.setBackground(AppColors.iceGrey);
            }

            boolean shouldValidate = false;
            if (c instanceof JPanel) {
                shouldValidate = true;
            }

            int x = bounds.x;
            int y = bounds.y;
            int w = bounds.width;
            int h = bounds.height;
            
            if (padding != null) {
                x += padding.left;
                y += padding.top;
                w -= (padding.left + padding.right);
                h -= (padding.top + padding.bottom);
            }

            currentValuePane.paintComponent(g, c, comboBox, x, y, w, h, shouldValidate);
        }
    }

    private class CustomComboRenderer implements ListCellRenderer<T> {
        @SuppressWarnings("rawtypes")
        private final ListCellRenderer defaultRenderer = new JComboBox<T>().getRenderer();

        @SuppressWarnings("unchecked")
        @Override
        public Component getListCellRendererComponent(JList<? extends T> list, T value, int index, boolean isSelected, boolean cellHasFocus) {
            
            if (index == -1) {
                JLabel label = new JLabel();
                label.setFont(CreateFont.DEFAULT.deriveFont(13f));
                label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
                label.setOpaque(false);
                
                if (value != null) {
                    label.setText(value.toString());
                }
                
                if (!isEnabled()) {
                    label.setForeground(AppColors.subtitle);
                } else {
                    label.setForeground(Color.BLACK);
                }
                return label;
            }
            
            JComponent c = (JComponent) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            c.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            c.setFont(CreateFont.DEFAULT.deriveFont(13f));
            c.setOpaque(true);
            
            if (isSelected) {
                c.setBackground(AppColors.subtitle);
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(Color.WHITE);
                c.setForeground(Color.BLACK);
            }
            return c;
        }
    }
}