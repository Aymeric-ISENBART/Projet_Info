/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.Proj_v2.Projet.Interface;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import fr.insa.isenbart.Proj_v2.Projet.barre;
import fr.insa.isenbart.Proj_v2.Projet.noeud;
import fr.insa.isenbart.Proj_v2.Projet.noeud_simple;
import fr.insa.isenbart.Proj_v2.Projet.point;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author aymer
 */
public class Controleur 
{
    private MainPane main;
    private int etat;
    private boolean ver = false;
    private double[] pt1 = new double[2];
    private ArrayList<barre> listBSelect;
    private ArrayList<noeud> listNSelect;

    
    public Controleur(MainPane main)
    {
        this.main= main;
        this.listBSelect = new ArrayList<barre>();
        this.listNSelect = new ArrayList<noeud>();
    }
    
    
    /**
     * 10 : Selection d'élements
     * 
     * 20 : Création appui
     * 
     * 30 : Créations de points
     * 
     * 40 : Créaction de segment
     * 
     * 50 :
     * 
     * 60 : Verrouiller
     *  
     */
    public void changementEtat(int nvEtat)
    {
        switch(nvEtat)
        {
            case 10:
                this.main.getbAppui().setDisable(false);
                this.main.getbPoint().setDisable(false);
                this.main.getbSegment().setDisable(false);
                this.main.getCpCouleur().setDisable(false);
                
            break;
            
            case 30:
                this.main.getbSegment().setDisable(true);
                this.main.getbAppui().setDisable(true);
                this.main.getCpCouleur().setDisable(true);
            break;
            
            case 40:
                this.main.getbPoint().setDisable(true);
                this.main.getbAppui().setDisable(true);
                this.main.getCpCouleur().setDisable(true);
            break;
            
            case 60 :
                if(!ver)
                {
                    this.main.getbAppui().setDisable(true);
                    this.main.getbPoint().setDisable(true);
                    this.main.getbSegment().setDisable(true);
                    this.main.getbSelect().setDisable(true);
                    this.main.getCpCouleur().setDisable(true);
                    this.main.getbVerrouiller().setText("Déverrouiller");
                }
                else
                {
                    this.main.getbAppui().setDisable(false);
                    this.main.getbPoint().setDisable(false);
                    this.main.getbSegment().setDisable(false);
                    this.main.getbSelect().setDisable(false);
                    this.main.getCpCouleur().setDisable(false);
                    this.main.getbVerrouiller().setText("Verrouiller");
                }
                
                ver = !ver;
                
            break;
            
            
            
        }
        
        this.etat = nvEtat;
    }
    
    public void clicCanvas(MouseEvent t)
    {
        ArrayList<noeud> ensNd = this.main.gettModel().getEns_noeud();
        ArrayList<barre> ensBr = this.main.gettModel().getEns_barre();
        noeud_simple nvNd;
                
        switch(this.etat)
        {
            case 10:
                
            break;    
            
            case 30 :
               
                nvNd = new noeud_simple(ensNd.size(), new point(t.getX(), t.getY()));                
                System.out.println(nvNd);
                
                ensNd.add(nvNd);
                
                this.main.redrawAll();
            break;
            
            case 40:
                nvNd = new noeud_simple(ensNd.size(), new point(t.getX(), t.getY()));
                ensNd.add(nvNd);
                
                this.main.redrawAll();
                this.etat = 41;
            break;

            case 41:
                nvNd = new noeud_simple(ensNd.size(), new point(t.getX(), t.getY()));
                ensNd.add(nvNd);
                
                ensBr.add(new barre(ensBr.size(), ensNd.get(ensNd.size()-2), nvNd));
                
                this.main.redrawAll();
                
                this.etat = 40; // enlever cette ligne pour créer un polyligne
            break;
        }
    }
    
    
    
    public void ObjetProche(point pt)
    {
        double distN;
        double distB;
        noeud Nd;
        barre Br;
    
        if(this.main.gettModel().getEns_noeud().isEmpty())
        {
            distN = 999;
            Nd = null;
        }
        else
        {
            distN = this.main.gettModel().getEns_noeud().get(0).distPoint(pt);
            Nd = this.main.gettModel().getEns_noeud().get(0);

            for(int i=1; i<this.main.gettModel().getEns_noeud().size(); i++)
            {
                double cur = this.main.gettModel().getEns_noeud().get(i).distPoint(pt);
                if(cur < distN)
                {
                    distN = cur;
                    Nd = this.main.gettModel().getEns_noeud().get(i);
                }
            }
        }
        
        
        if(this.main.gettModel().getEns_barre().isEmpty())
        {
            distB = 999;
            Br = null;
        }
        else
        {
            distB = this.main.gettModel().getEns_barre().get(0).distPoint(pt);
            Br = this.main.gettModel().getEns_barre().get(0);

            for(int i=1; i<this.main.gettModel().getEns_barre().size(); i++)
            {
                double cur = this.main.gettModel().getEns_barre().get(i).distPoint(pt);
                if(cur < distB)
                {
                    distB = cur;
                    Br = this.main.gettModel().getEns_barre().get(i);
                }
            }
        }
        
        if((distN != 999)&&(distB!=999))
        {
            if(distN < distB)
            {
                NoeudProche(Nd);
            }
            else
            {
                BarreProche(Br);
            }
        }
        
    }  

    
    public noeud NoeudProche(noeud Nd)
    {
        return Nd;
    }
    
    public barre BarreProche(barre Br)
    {
        return Br;
    }
    
    
        
        
    
    
    
}
