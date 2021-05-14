package fr.insa.isenbart.Proj_v2.Projet;

import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 */
public abstract class noeud 
{

    private int identificateur;
    private double rayonDessin = 3;
    private Color clr;
    //private point Pt;
    
    public noeud(int id) 
    {
        this.identificateur=id;
    }
    
    public noeud(int id, Color clr) 
    {
        this.identificateur=id;
        this.clr=clr;
    }
    
    public int getId()
    {
        return this.identificateur;
    }
    
    @Override
    public String toString()
    {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
    
    public abstract double getX();
    public abstract double getY();

    
    
    public void dessine(GraphicsContext context)
    {
        context.setFill(/*this.clr*/ Color.RED);
        context.fillOval(this.getX()-rayonDessin, this.getY()-rayonDessin, 2*rayonDessin, 2*rayonDessin);
    }

    /**
     * @return the clr
     */
    public Color getColor() 
    {
        return clr;
    }
    
    public double distPoint(point pt)
    {
        return pt.distPoint(new point(this.getX(), this.getY()));
    }

    /**
     * @param clr the clr to set
     */
    public void setColor(Color clr) 
    {
        this.clr = clr;
    }
    

}