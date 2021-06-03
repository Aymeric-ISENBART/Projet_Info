/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.Proj_v2.Projet.Interface;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import fr.insa.isenbart.Proj_v2.Projet.barre;
import fr.insa.isenbart.Proj_v2.Projet.noeud;
import fr.insa.isenbart.Proj_v2.Projet.point;
import fr.insa.isenbart.Proj_v2.Projet.segment;
import fr.insa.isenbart.Proj_v2.Projet.terrain;
import fr.insa.isenbart.Proj_v2.Projet.triangle_terrain;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author aymer
 */

public class DCanvas extends Pane
{
    private Canvas canvas;
    private MainPane main;
    
    public DCanvas(MainPane main)
    {
        this.main = main;
        
        this.canvas = new Canvas(this.getWidth(), this.getHeight());
        this.getChildren().add(this.canvas);
        
        this.canvas.heightProperty().bind(this.heightProperty());
        this.canvas.heightProperty().addListener((o) -> 
        {
            this.redrawAll();
        });
             
        this.canvas.widthProperty().bind(this.widthProperty());
        this.canvas.widthProperty().addListener((o) -> 
        {
            this.redrawAll();
        });
        
        this.redrawAll();
    }
    
    public void redrawAll()
    {   
        GraphicsContext context = this.canvas.getGraphicsContext2D();
        
        Treillis trModel = this.main.gettrModel();
        terrain teModel = this.main.getTeModel();
        
        // Gère la couleur de fond
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        teModel.dessine(context);
        trModel.dessine(context);
        

        // Dessine la sélection de noeud        
        ArrayList<noeud> SelectNd = this.main.getControl().getListNSelect();
        if(!SelectNd.isEmpty())
        {
            for(noeud n : SelectNd)
            {
                n.dessineSelection(context);
            }
        } 
        
        // Dessine la sélection de barre        
        ArrayList<barre> SelectBarre = this.main.getControl().getListBSelect();
        if(!SelectBarre.isEmpty())
        {
            for(barre b : SelectBarre)
            {
                b.dessineSelection(context);
            }
        }
        
        // Dessine la sélection de TDT        
        ArrayList<triangle_terrain> SelectTrlgTerr = this.main.getControl().getListTTSelect();
        if(!SelectTrlgTerr.isEmpty())
        {
            for(triangle_terrain tt : SelectTrlgTerr)
            {
                tt.dessineSelection(context);
            }
        }
        
        
        final double rayonDessin = 5;
        
        // Dessine le premier point pour créer le triangle de terrain
        point p1 = this.main.getControl().getPts()[0];
        if(p1 != null)
        {
            context.setFill(Color.YELLOWGREEN);
            context.fillOval(p1.getPx()-rayonDessin, p1.getPy()-rayonDessin, 2*rayonDessin, 2*rayonDessin);
        }
        
        // Dessine le second point pour créer le triangle de terrain
        point p2 = this.main.getControl().getPts()[1];
        if(p2 != null)
        {
            context.setFill(Color.YELLOWGREEN);
            context.fillOval(p2.getPx()-rayonDessin, p2.getPy()-rayonDessin, 2*rayonDessin, 2*rayonDessin);
        }
        
        /*segment segAppui = this.main.getControl().getSselect();
        if(segAppui != null)
        {
            segAppui.dessineSelection(context, Color.BLUE);
        }*/
        
    }
}
