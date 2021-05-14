package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;


public class appui_double extends noeud_appui 
{
    
    private point appui;
    
    public appui_double(int id, triangle_terrain trlg, segment seg, point pt )
    {
        super(id,trlg,seg);
        this.appui=pt;
    }
    
    

    // Bloqué en translation => X,Y fixe

    
    public point getAppui() 
    {
        return appui;
    }

    
    
    public void setAppui(point appui) 
    {
        this.appui = appui;
    }

}