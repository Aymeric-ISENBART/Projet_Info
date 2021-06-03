package fr.insa.isenbart.Proj_v2.Projet;

import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class noeud_simple extends noeud 
{

    private point Pt;
    private Color clr;
    
    
    public noeud_simple(int id, point pt)
    {
        super(id);
        this.Pt = pt; 
        this.clr = Color.BLACK;
    }
    
    public noeud_simple(int id, Color col, point pt)
    {
        super(id);
        this.Pt = pt;
        this.clr = col;
    }
    

    
    
    public point getPt() 
    {
        return Pt;
    }

    public void setPt(point Pt) 
    {
        this.Pt = Pt;
    }

    @Override
    public double getX() 
    {
        return this.Pt.getPx();
    }

    @Override
    public double getY() 
    {
        return this.Pt.getPy();

    }

    @Override
    public void dessine(GraphicsContext context) 
    {
        context.setFill(this.clr);
        context.fillOval(this.getX()-getRayonDessin(), this.getY()-this.getRayonDessin(), 2*this.getRayonDessin(), 2*this.getRayonDessin());
    }

    @Override
    public void dessineSelection(GraphicsContext context) 
    {
        context.setFill(this.clr);
        context.fillOval(this.getX()-(getRayonDessin()+2), this.getY()-(this.getRayonDessin()+2), 2*(this.getRayonDessin()+2), 2*(this.getRayonDessin()+2));
    }

    @Override
    public void changeCouleur(Color color) 
    {
        this.clr = color;
    }

    @Override
    public segment getSeg() 
    {
        return null;
    }

    @Override
    public triangle_terrain getTrlg() 
    {
        return null;
    }
}
