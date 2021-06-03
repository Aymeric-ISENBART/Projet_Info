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
    private double rayonDessin = 5; 
    //private point Pt;
    
    public noeud(int id) 
    {
        this.identificateur=id;
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
    public abstract segment getSeg();
    public abstract triangle_terrain getTrlg();
    
    
    public abstract void dessine(GraphicsContext context);
    public abstract void dessineSelection(GraphicsContext context);
    public abstract void changeCouleur(Color color);
    

    
    public point getPoint()
    {
        return new point(this.getX(), this.getY());
    }
    
    /**
     * @return the clr
     */
    
    public double distPoint(point pt)
    {
        return pt.distPoint(new point(this.getX(), this.getY()));
    }

    /**
     * @param clr the clr to set
     */

    /**
     * @return the rayonDessin
     */
    public double getRayonDessin() {
        return rayonDessin;
    }

    /**
     * @param identificateur the identificateur to set
     */
    public void setIdentificateur(int identificateur) {
        this.identificateur = identificateur;
    }
    

}
