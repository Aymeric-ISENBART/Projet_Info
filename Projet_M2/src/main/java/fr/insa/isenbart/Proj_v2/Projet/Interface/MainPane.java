/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.Proj_v2.Projet.Interface;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import fr.insa.isenbart.Proj_v2.Projet.terrain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 *
 * @author aymer
 */
public class MainPane extends BorderPane 
{
    private DCanvas canvas;
    
    private Button bPoint;
    private Button bSegment;
    private Button bTrlgTerrain;
    private Button bAppui;
    private Button bSelect;
    private Button bVerrouiller;
    
    private ColorPicker cpCouleur;
    
    private Treillis trModel;
    private terrain teModel;
    
    private Controleur control;
    
    
    
    
    public MainPane()
    {
        this(new Treillis(), new terrain());
    }
    
    public MainPane(Treillis trModel, terrain teModel)
    {
        this.control = new Controleur(this);
        this.trModel = trModel;
        this.teModel = teModel;
        
        this.bPoint = new Button("Noeud");
        this.bPoint.setOnAction((t) -> 
        {
            this.control.changementEtat(30);
        });
        
        this.bAppui = new Button("Appui");
        this.bAppui.setOnAction((t) -> 
        {
            this.control.changementEtat(20);
        });
        
        
        this.bSegment = new Button("Barre");
        this.bSegment.setOnAction((t) -> 
        {
            this.control.changementEtat(40);
        });
        
        this.bTrlgTerrain = new Button("TrlgTer");
        this.bTrlgTerrain.setOnAction((t) -> 
        {
            this.control.changementEtat(50);
        });
        
        this.bSelect = new Button("Selection Noeud");
        this.bSelect.setOnAction((t) -> 
        {
            this.control.changementEtat(10);
        });
        
        
        
        this.cpCouleur = new ColorPicker();
        this.cpCouleur.setOnAction((t) -> 
        {
            this.control.changeCouleur(this.getCpCouleur().getValue());
        });
        
        this.bVerrouiller = new Button("Verrouiller");
        this.bVerrouiller.setOnAction((t) -> 
        {
            this.control.changementEtat(60);
        });
        
        HBox hbHaut = new HBox(this.getbPoint(), this.getbSegment(), this.getbTrlgTerrain(), this.getbAppui(), this.getbSelect(), this.cpCouleur, this.getbVerrouiller());
        this.setTop(hbHaut);
        
        this.canvas = new DCanvas(this);
        this.setCenter(canvas);
        this.canvas.setOnMouseClicked((t) -> 
        {
            this.control.clicCanvas(t);
        });
        
        this.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> 
        {
            if(key.getCode()==KeyCode.DELETE) 
            {
                this.control.supprimer();
            }
        });
        
        
        this.control.changementEtat(30);
        
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
    public Treillis gettrModel() {
        return trModel;
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

    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(DCanvas canvas) {
        this.canvas = canvas;
    }

    /**
     * @return the control
     */
    public Controleur getControl() {
        return control;
    }

    /**
     * @return the teModel
     */
    public terrain getTeModel() {
        return teModel;
    }

    /**
     * @return the bTrlgTerrain
     */
    public Button getbTrlgTerrain() {
        return bTrlgTerrain;
    }

    /**
     * @param bTrlgTerrain the bTrlgTerrain to set
     */
    public void setbTrlgTerrain(Button bTrlgTerrain) {
        this.bTrlgTerrain = bTrlgTerrain;
    }


    
    
    
    
}
