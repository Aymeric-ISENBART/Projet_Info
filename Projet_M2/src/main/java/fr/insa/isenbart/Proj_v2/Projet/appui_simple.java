package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 */
public class appui_simple extends noeud_appui 
{

    // Déplacement tngentielle au terrain , pas normal
    
    private point appui;
    
    public appui_simple(int id, triangle_terrain trlg, segment seg, point pt )
    {
        super(id,trlg,seg);
        this.appui=pt;
    }
    
    public appui_simple(int id)
    {
        super(id);
    }

    
    
    public point getAppui() 
    {
        return appui;
    }

    
    
    public void setAppui(point appui) 
    {
        this.appui = appui;
    }

    @Override
    public void changeCouleur(Color clr) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void dessine(GraphicsContext context)
    {
        context.setFill(this.getClr());
        context.fillOval(this.getX()-this.getRayonDessin(), this.getY()-this.getRayonDessin(), 2*this.getRayonDessin(), 2*this.getRayonDessin());
        
    }
    
    @Override
    public double getX()
    {
        return this.appui.getPx();
    }
    
    @Override
    public double getY()
    {
        return this.appui.getPy();
    }

    @Override
    public void dessineSelection(GraphicsContext context) 
    {
        context.setFill(this.getClr());
        context.fillOval(this.getX()-(this.getRayonDessin()+2), this.getY()-(this.getRayonDessin()+2), 2*(this.getRayonDessin()+2), 2*(this.getRayonDessin()+2));
    }

}