package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;

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
    
    public String toString()
    {
        return "[" + this.getP1().toString() + "," + this.getP2().toString() + "]";
    }
    
    public double longueur()
    {
        return Math.sqrt(Math.pow(this.getP1().getPx()-this.getP2().getPx(),2) + Math.pow(this.getP1().getPy()-this.getP2().getPy(),2));
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