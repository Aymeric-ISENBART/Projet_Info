package fr.insa.isenbart.projet_m2.progs;

import java.util.*;

/**
 * 
 */
public class segment 
{

    private point P1;
    private point P2; 
    
    public segment(point P1, point P2) 
    {
        this.P1 = P1;
        this.P2 = P2;
    }
    
    public String toString()
    {
        return "(" + this.P1.toString() + " ; " + this.P2.toString() + ")";
    }
    
    public double longueur()
    {
        return Math.sqrt((this.P1.getPx()-this.P2.getPx())*(this.P1.getPx()-this.P2.getPx()) + (this.P1.getPy()-this.P2.getPy())*(this.P1.getPy()-this.P2.getPy()));
    }



}