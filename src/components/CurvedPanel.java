/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

public class CurvedPanel extends JPanel {
    public static enum CurveType {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        ALL
    }
    
    private int curveSize = 30; // Size of the curve
    private CurveType curveType = CurveType.TOP;
    
    public CurvedPanel() {
        setOpaque(false);
    }
    
    public CurvedPanel(CurveType type) {
        this.curveType = type;
        setOpaque(false);
    }
    
    public void setCurveSize(int size) {
        this.curveSize = size;
        repaint();
    }
    
    public void setCurveType(CurveType type) {
        this.curveType = type;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Enable anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Set color
        g2.setColor(getBackground());
        
        // Create path for the curved shape
        Path2D.Double path = new Path2D.Double();
        
        int w = getWidth();
        int h = getHeight();
        
        switch (curveType) {
            case TOP -> {
                path.moveTo(0, curveSize);
                path.quadTo(w/2, 0, w, curveSize);
                path.lineTo(w, h);
                path.lineTo(0, h);
                path.closePath();
            }
            case BOTTOM -> {
                path.moveTo(0, 0);
                path.lineTo(w, 0);
                path.lineTo(w, h-curveSize);
                path.quadTo(w/2, h, 0, h-curveSize);
                path.closePath();
            }
            case LEFT -> {
                path.moveTo(curveSize, 0);
                path.lineTo(w, 0);
                path.lineTo(w, h);
                path.lineTo(curveSize, h);
                path.quadTo(0, h/2, curveSize, 0);
                path.closePath();
            }
            case RIGHT -> {
                path.moveTo(0, 0);
                path.lineTo(w-curveSize, 0);
                path.quadTo(w, h/2, w-curveSize, h);
                path.lineTo(0, h);
                path.closePath();
            }
            case ALL -> {
                path.moveTo(curveSize, 0);
                path.quadTo(w/2, 0, w-curveSize, 0);
                path.quadTo(w, 0, w, curveSize);
                path.quadTo(w, h/2, w, h-curveSize);
                path.quadTo(w, h, w-curveSize, h);
                path.quadTo(w/2, h, curveSize, h);
                path.quadTo(0, h, 0, h-curveSize);
                path.quadTo(0, h/2, 0, curveSize);
                path.quadTo(0, 0, curveSize, 0);
                path.closePath();
            }
        }
        
        g2.fill(path);
        g2.dispose();
    }
}