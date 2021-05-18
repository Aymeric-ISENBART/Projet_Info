package fr.insa.isenbart.Proj_v2.Projet;

import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class appui_encastré extends noeud_appui
{

    private point appui;
    
    public appui_encastré(int id, triangle_terrain trlg, segment seg, point pt)
    {
        super(id,trlg,seg);
        this.appui=pt;
    }

    
    // Pas de deplacement, pas de rotation

    
    
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

    @Override
    public void dessine(GraphicsContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}