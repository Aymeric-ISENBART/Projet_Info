package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;
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

    
    
    public point getAppui() 
    {
        return appui;
    }

    
    
    public void setAppui(point appui) 
    {
        this.appui = appui;
    }

    @Override
    public void changeCouleur(Color clr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}