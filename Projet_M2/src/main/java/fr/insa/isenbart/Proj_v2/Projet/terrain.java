package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 */
public class terrain
{
    private ArrayList<triangle_terrain> ens_triangle_terrain;
   
    public terrain() 
    {
        this.ens_triangle_terrain = new ArrayList<triangle_terrain>();
    }
    
    public static terrain terrainTest()
    {
        terrain Ter = new terrain();
        
        for(int i=0; i<3; i++)
        {
            Ter.getEns_triangle_terrain().add(triangle_terrain.triangleTerrainTest(i));
        }
        return Ter;
    }
    
    public void dessine(GraphicsContext context)
    {
        for(triangle_terrain tt: this.getEns_triangle_terrain())
        {
            tt.dessine(context);
        }
    }
    
    /**
     * @return the ens_triangle_terrain
     */
    public ArrayList<triangle_terrain> getEns_triangle_terrain() {
        return ens_triangle_terrain;
    }

    /**
     * @param ens_triangle_terrain the ens_triangle_terrain to set
     */
    public void setEns_triangle_terrain(ArrayList<triangle_terrain> ens_triangle_terrain) {
        this.ens_triangle_terrain = ens_triangle_terrain;
    }

    
            
    
}