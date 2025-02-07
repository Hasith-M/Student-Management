/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AnimatedLoginButton extends JButton {
    private Timer animationTimer;
    private float progress = 0;
    private boolean isAnimating = false;
    private String originalText;
    private Color backgroundColor = new Color(0, 120, 212);
    private Color foregroundColor = Color.WHITE;
    private int roundness = 10;
    private int dotSize = 4;
    private float dotSpacing = 15;
    
    public AnimatedLoginButton() {
        this("Login");
    }
    
    public AnimatedLoginButton(String text) {
        super(text);
        originalText = text;
        setupButton();
        setupAnimation();
    }
    
    private void setupButton() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(foregroundColor);
        
        // Mouse hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backgroundColor = backgroundColor.darker();
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                backgroundColor = backgroundColor.brighter();
                repaint();
            }
        });
    }
    
    private void setupAnimation() {
        animationTimer = new Timer(16, new ActionListener() { // ~60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAnimating) {
                    progress += 0.05f;
                    if (progress >= 1) {
                        progress = 0;
                    }
                    repaint();
                }
            }
        });
    }
    
    public void startAnimation() {
        if (!isAnimating) {
            isAnimating = true;
            setText("");
            animationTimer.start();
            setEnabled(false);
        }
    }
    
    public void stopAnimation() {
        if (isAnimating) {
            isAnimating = false;
            setText(originalText);
            animationTimer.stop();
            progress = 0;
            setEnabled(true);
            repaint();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw background
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), roundness, roundness);
        
        if (isAnimating) {
            // Draw loading animation
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            
            for (int i = 0; i < 3; i++) {
                float alpha = Math.max(0, 1 - Math.abs(progress - i / 3f) * 2);
                g2.setColor(new Color(foregroundColor.getRed()/255f, 
                                    foregroundColor.getGreen()/255f, 
                                    foregroundColor.getBlue()/255f, 
                                    alpha));
                                    
                float x = centerX + (i - 1) * dotSpacing;
                g2.fillOval((int)x - dotSize/2, centerY - dotSize/2, dotSize, dotSize);
            }
        } else {
            // Draw text
            FontMetrics fm = g2.getFontMetrics();
            g2.setColor(foregroundColor);
            String text = getText();
            int textX = (getWidth() - fm.stringWidth(text)) / 2;
            int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(text, textX, textY);
        }
        
        g2.dispose();
    }
    
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        return new Dimension(Math.max(100, size.width), Math.max(35, size.height));
    }
    
    // Customization methods
    public void setButtonBackground(Color color) {
        this.backgroundColor = color;
        repaint();
    }
    
    public void setButtonForeground(Color color) {
        this.foregroundColor = color;
        repaint();
    }
    
    public void setRoundness(int roundness) {
        this.roundness = roundness;
        repaint();
    }
    
    public void setDotSize(int size) {
        this.dotSize = size;
        repaint();
    }
    
    public void setDotSpacing(float spacing) {
        this.dotSpacing = spacing;
        repaint();
    }
}