/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.Proj_v2.Projet.Interface;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import fr.insa.isenbart.Proj_v2.Projet.barre;
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
        
        Treillis tModel = this.main.gettModel();
        
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        tModel.dessine(context);
    }
}
