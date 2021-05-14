/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.Proj_v2.Projet.Interface;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 *
 * @author aymer
 */
public class MainPane extends BorderPane 
{
    private DCanvas canvas;
    
    private Button bPoint;
    private Button bSegment;
    private Button bAppui;
    private Button bSelect;
    private Button bVerrouiller;
    
    private ColorPicker cpCouleur;
    
    private Treillis tModel;
    
    private Controleur control;
    
    
    
    
    public MainPane()
    {
        this(new Treillis());
    }
    
    public MainPane(Treillis tModel)
    {
        this.control = new Controleur(this);
        this.tModel = tModel;
        
        this.bPoint = new Button("Noeud");
        this.bPoint.setOnAction((t) -> 
        {
            this.control.changementEtat(30);
        });
        
        this.bAppui = new Button("Appui");
        this.bAppui.setOnAction((t) -> 
        {
            System.out.println("Mode Appui");
        });
        
        
        this.bSegment = new Button("Segment");
        this.bSegment.setOnAction((t) -> 
        {
            System.out.println("Mode Segment");
            this.control.changementEtat(40);
        });
        
        this.bSelect = new Button("Select");
        this.bSelect.setOnAction((t) -> 
        {
            System.out.println("Mode Select");
            this.control.changementEtat(10);
        });
        
        this.cpCouleur = new ColorPicker();
        this.cpCouleur.setOnAction((t) -> 
        {
            System.out.println("Couleur");
        });
        
        this.bVerrouiller = new Button("Verrouiller");
        this.bVerrouiller.setOnAction((t) -> 
        {
            System.out.println("Verrouiller");
            this.control.changementEtat(60);
        });
        
        HBox hbHaut = new HBox(this.getbPoint(), this.getbSegment(), this.getbAppui(), this.getbSelect(), this.cpCouleur, this.getbVerrouiller());
        this.setTop(hbHaut);
        
        this.canvas = new DCanvas(this);
        this.setCenter(canvas);
        this.canvas.setOnMouseClicked((t) -> 
        {
            this.control.clicCanvas(t);
        });
        
        this.control.changementEtat(10);
        
        this.redrawAll();
    }
    
    public void redrawAll()
    {
        this.getCanvas().redrawAll();
    }

    /**
     * @return the canvas
     */
    public DCanvas getCanvas() {
        return canvas;
    }

    /**
     * @return the bPoint
     */
    public Button getbPoint() {
        return bPoint;
    }

    /**
     * @return the bSegment
     */
    public Button getbSegment() {
        return bSegment;
    }

    /**
     * @return the bAppui
     */
    public Button getbAppui() {
        return bAppui;
    }

    /**
     * @return the bSelect
     */
    public Button getbSelect() {
        return bSelect;
    }

    /**
     * @return the tModel
     */
    public Treillis gettModel() {
        return tModel;
    }

    /**
     * @return the cpCouleur
     */
    public ColorPicker getCpCouleur() {
        return cpCouleur;
    }

    /**
     * @return the bVerrouiller
     */
    public Button getbVerrouiller() {
        return bVerrouiller;
    }
    
    
    
    
}
