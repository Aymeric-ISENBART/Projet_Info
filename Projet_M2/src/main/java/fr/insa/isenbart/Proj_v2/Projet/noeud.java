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
        this.clr = Color.PINK;
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
        context.setFill(this.clr);
        context.fillOval(this.getX()-getRayonDessin(), this.getY()-getRayonDessin(), 2*getRayonDessin(), 2*getRayonDessin());
    }
    
    // Si souhaite une couleur particulière pour la sélection, remplacer this.clr par la couleur souhaitée
    public void dessineSelection(GraphicsContext context)
    {
        context.setFill(this.clr);
        context.fillOval(this.getX()-(getRayonDessin()+2), this.getY()-(getRayonDessin()+2), 2*(getRayonDessin()+2), 2*(getRayonDessin()+2));
    }
    
    public void changeCouleur(Color clr)
    {
        this.setColor(clr);
    }
    
    public point getPoint()
    {
        return new point(this.getX(), this.getY());
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

    /**
     * @return the rayonDessin
     */
    public double getRayonDessin() {
        return rayonDessin;
    }
    

}