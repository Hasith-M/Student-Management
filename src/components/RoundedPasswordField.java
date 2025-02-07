/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;

public class RoundedPasswordField extends JPasswordField {
    private int arcSize = 15;
    private Color borderColor = new Color(60, 60, 60);
    private Color focusBorderColor = new Color(30, 144, 255);
    private int borderSize = 2;
    private boolean isFocused = false;
    
    public RoundedPasswordField() {
        setOpaque(false);
        setBorder(new EmptyBorder(5, 10, 5, 10));
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                isFocused = true;
                repaint();
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                isFocused = false;
                repaint();
            }
        });
    }
    
    public void setArcSize(int size) {
        this.arcSize = size;
        repaint();
    }
    
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }
    
    public void setFocusBorderColor(Color color) {
        this.focusBorderColor = color;
        repaint();
    }
    
    public void setBorderSize(int size) {
        this.borderSize = size;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize));
        
        g2.setColor(isFocused ? focusBorderColor : borderColor);
        g2.setStroke(new java.awt.BasicStroke(borderSize));
        g2.draw(new RoundRectangle2D.Double(borderSize/2, borderSize/2, 
                getWidth() - borderSize - 1, getHeight() - borderSize - 1, 
                arcSize, arcSize));
        
        if (isFocused) {
            g2.setColor(new Color(focusBorderColor.getRed(), 
                                focusBorderColor.getGreen(), 
                                focusBorderColor.getBlue(), 50));
            g2.setStroke(new java.awt.BasicStroke(borderSize + 2));
            g2.draw(new RoundRectangle2D.Double(borderSize/2, borderSize/2, 
                    getWidth() - borderSize - 1, getHeight() - borderSize - 1, 
                    arcSize, arcSize));
        }
        
        g2.dispose();
        super.paintComponent(g);
    }
    
    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arcSize, arcSize);
        return shape.contains(x, y);
    }
    
    @Override
    public Insets getInsets() {
        int margin = borderSize + 5;
        return new Insets(margin, margin + 5, margin, margin + 5);
    }
}