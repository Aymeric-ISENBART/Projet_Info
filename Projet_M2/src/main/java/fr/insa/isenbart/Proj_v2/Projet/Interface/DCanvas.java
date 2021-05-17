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
        
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        trModel.dessine(context);
        teModel.dessine(context);
                
        ArrayList<noeud> SelectNd = this.main.getControl().getListNSelect();
        if(!SelectNd.isEmpty())
        {
            for(noeud n : SelectNd)
            {
                n.dessineSelection(context);
            }
        } 
        
        ArrayList<barre> SelectBarre = this.main.getControl().getListBSelect();
        if(!SelectBarre.isEmpty())
        {
            for(barre b : SelectBarre)
            {
                b.dessineSelection(context);
            }
        }
        
        final double rayonDessin = 3;
        
        point p1 = this.main.getControl().getPts()[0];
        if(p1 != null)
        {
            context.setFill(Color.YELLOWGREEN);
            context.fillOval(p1.getPx()-rayonDessin, p1.getPy()-rayonDessin, 2*rayonDessin, 2*rayonDessin);
        }
        
        point p2 = this.main.getControl().getPts()[1];
        if(p2 != null)
        {
            context.setFill(Color.YELLOWGREEN);
            context.fillOval(p2.getPx()-rayonDessin, p2.getPy()-rayonDessin, 2*rayonDessin, 2*rayonDessin);
        }
    }
}
