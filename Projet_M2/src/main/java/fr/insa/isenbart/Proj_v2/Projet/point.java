package fr.insa.isenbart.Proj_v2.Projet;

import java.util.*;

/**
 * 
 */
public class point {

    private double px;
    private double py;
    
    
    public point(double px, double py) 
    {
        this.px = px;
        this.py = py;
    }
    
    public String toString()
    {
        return "(" + this.getPx() + " ; " + this.getPy() + ")"; 
    }
    
    public double getPx()
    {
        return this.px;
    }
    
    public double getPy()
    {
        return this.py;
    }
     
    public void setPx(double px)
    {
        this.px = px;
    }
    
    public void setPy(double py)
    {
        this.setPx(py);
    }
    
    public double distPoint(point pt)
    {
        return Math.sqrt(Math.pow(this.px-pt.getPx(), 2) + Math.pow(this.py-pt.getPy(), 2));
    }
    
    
    
    
    public double minX()
    {
        return this.getPx();
    }
    
    public double maxX()
    {
        return this.getPx();
    }
    
    public double minY()
    {
        return this.getPy();
    }
    
    public double maxY()
    {
        return this.getPy();
    }
}