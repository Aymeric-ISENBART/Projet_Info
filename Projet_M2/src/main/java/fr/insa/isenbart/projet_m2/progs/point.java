package fr.insa.isenbart.projet_m2.progs;

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
        return "(" + this.px + " ; " + this.py + ")"; 
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
        this.px = py;
    }
    
    
    
    public double minX()
    {
        return this.px;
    }
    
    public double maxX()
    {
        return this.px;
    }
    
    public double minY()
    {
        return this.py;
    }
    
    public double maxY()
    {
        return this.py;
    }
}