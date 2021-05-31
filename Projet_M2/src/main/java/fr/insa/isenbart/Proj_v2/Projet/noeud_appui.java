package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 */
public abstract class noeud_appui extends noeud 
{
 
    private triangle_terrain trgl_terrain;
    private segment seg_trlg_terrain;
    
    private double rayonDessin = 3;
    private Color clr;
    
    
    public noeud_appui(int id, triangle_terrain trlg, segment seg) 
    {
        super(id);
        this.seg_trlg_terrain = seg;
        this.trgl_terrain = trlg;
        this.clr = Color.ORANGERED;
    }
    
    public noeud_appui(int id) 
    {
        super(id);
        this.clr = Color.ORANGERED;
    }

    public double getX()
    {
        return this.getX();
    }
    
    public double getY()
    {
        return this.getY();
    }
    
    public abstract void dessine(GraphicsContext context);
    
    @Override
    public segment getSeg()
    {
        return this.getSeg_trlg_terrain();
    }
    
    @Override
    public triangle_terrain getTrlg()
    {
        return this.getTrgl_terrain();
    }
            
    
    
    
    
    public triangle_terrain getTrgl_terrain() 
    {
        return trgl_terrain;
    }

    public void setTrgl_terrain(triangle_terrain trgl_terrain) 
    {
        this.trgl_terrain = trgl_terrain;
    }

    
    public segment getSeg_trlg_terrain() 
    {
        return seg_trlg_terrain;
    }

    
    public void setSeg_trlg_terrain(segment seg_trlg_terrain) 
    {
        this.seg_trlg_terrain = seg_trlg_terrain;
    }

    /**
     * @return the rayonDessin
     */
    public double getRayonDessin() {
        return rayonDessin;
    }

    /**
     * @return the clr
     */
    public Color getClr() {
        return clr;
    }

}
