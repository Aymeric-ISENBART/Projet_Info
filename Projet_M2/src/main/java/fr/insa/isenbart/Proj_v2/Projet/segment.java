package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 */
public class segment {

    private point P1;
    private point P2; 
    
    public segment(point P1, point P2) 
    {
        this.P1 = P1;
        this.P2 = P2;
    }
    
    public double distPoint(point pt)
    {
        return PointNormal(pt).distPoint(pt);
    }
    
    public point PointNormal(point pt)
    {
        segment seg13 = new segment(this.getP1(), pt);
        
        double lg12 = this.longueur();
        double lg13 = seg13.longueur();
        
        double cosAngle = ((this.getP2().getPx()-this.getP1().getPx())*(pt.getPx()-this.getP1().getPx()) + (this.getP2().getPy()-this.getP1().getPy())*(pt.getPy()-this.getP1().getPy()))/(lg12 * lg13);
        
        double lg14 = lg13*cosAngle;
        double rapp = lg14/lg12;
        
        double p4x = this.getP1().getPx() + rapp*(this.getP2().getPx()-this.getP1().getPx());
        double p4y = this.getP1().getPy() + rapp*(this.getP2().getPy()-this.getP1().getPy());
        
        return new point(p4x, p4y);
    }
    
    public void dessineSelection(GraphicsContext context)
    {
        context.setStroke(Color.CORAL);
        context.strokeLine(this.P1.getPx(), this.P1.getPy(), this.P2.getPx(), this.P2.getPy());
        context.setLineWidth(2);
    }
    
    public String toString()
    {
        return "[" + this.getP1().toString() + "," + this.getP2().toString() + "]";
    }
    
    public double longueur()
    {
        return Math.sqrt(Math.pow(this.getP1().getPx()-this.getP2().getPx(),2) + Math.pow(this.getP1().getPy()-this.getP2().getPy(),2));
    }
    
    public double getMinX()
    {
        return Math.min(this.P1.getPx(), this.P2.getPx());
    }
    
    public double getMinY()
    {
        return Math.min(this.P1.getPy(), this.P2.getPy());
    }
    
    public double getMaxX()
    {
        return Math.max(this.P1.getPx(), this.P2.getPx());
    }
    
    public double getMaxY()
    {
        return Math.max(this.P1.getPy(), this.P2.getPy());
    }
    

    /**
     * @return the P1
     */
    public point getP1() {
        return P1;
    }

    /**
     * @return the P2
     */
    public point getP2() {
        return P2;
    }
    
    
}