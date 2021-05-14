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
    }
    
    public noeud_simple(int id, Color clr, point pt)
    {
        super(id, clr);
        this.Pt = pt;
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
}