/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.Proj_v2.Projet.Interface;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import fr.insa.isenbart.Proj_v2.Projet.appui_simple;
import fr.insa.isenbart.Proj_v2.Projet.barre;
import fr.insa.isenbart.Proj_v2.Projet.noeud;
import fr.insa.isenbart.Proj_v2.Projet.noeud_appui;
import fr.insa.isenbart.Proj_v2.Projet.noeud_simple;
import fr.insa.isenbart.Proj_v2.Projet.point;
import fr.insa.isenbart.Proj_v2.Projet.segment;
import fr.insa.isenbart.Proj_v2.Projet.triangle_terrain;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
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
    private point[] pts = new point[2];
    
    private ArrayList<barre> listBSelect;
    private ArrayList<noeud> listNSelect;
    private ArrayList<triangle_terrain> listTTSelect;
    private segment Sselect;
    
    private Color newCol;

    
    public Controleur(MainPane main)
    {
        this.main= main;
        this.listBSelect = new ArrayList<barre>();
        this.listTTSelect = new ArrayList<triangle_terrain>();
        this.listNSelect = new ArrayList<noeud>();
    }
    
    
    
    public void changementEtat(int nvEtat)
    {
        if((nvEtat != 10) && (nvEtat != 11) && (nvEtat != 12))
        {
            switch(nvEtat)
            {
                case 30:

                break;

                case 40:

                break;

                case 60 :
                    if(!isVer())
                    {
                        this.getMain().getbAppui().setDisable(true);
                        this.getMain().getbPoint().setDisable(true);
                        this.getMain().getbSegment().setDisable(true);
                        this.getMain().getbSelect().setDisable(true);
                        this.getMain().getCpCouleur().setDisable(true);
                        this.getMain().getbVerrouiller().setText("Déverrouiller");
                    }
                    else
                    {
                        this.getMain().getbAppui().setDisable(false);
                        this.getMain().getbPoint().setDisable(false);
                        this.getMain().getbSegment().setDisable(false);
                        this.getMain().getbSelect().setDisable(false);
                        this.getMain().getCpCouleur().setDisable(false);
                        this.getMain().getbVerrouiller().setText("Verrouiller");
                    }

                    ver = !isVer();

                break;
            }

            this.etat = nvEtat;
        }
        else
        {
            switch(this.etat)
            {
                case 10 :
                    this.etat = 11;
                    this.main.getbSelect().setText("Selection Barre");
                break;
                
                case 11 :
                    this.etat = 12;
                    this.main.getbSelect().setText("Selection Triangle terr");

                break;
                
                case 12 :
                    this.etat = 10;
                    this.main.getbSelect().setText("Selection Noeud");
                break;
                
                default :
                    this.etat = 10;
                    this.main.getbSelect().setText("Selection Noeud");
                break;
            }
        }
    }
    
    /**
     * 10 : Selection de noeud(s)
     * 11 : Selection de barre(s)
     * 12 : Sélection de triangle(s) de terrain
     * 
     * 20 : Création d'appuis
     *      > 20 : Selectionne un segment d'un triangle de terrain (> etat = 21)
     *      > 21 : Crée l'appui si possible
     * 
     * 30 : Créations de points
     * 
     * 40 : Créaction de la barre
     *      > 40 : 
     *          => Si rien n'est pressé, alors crée le premier noeud et l'affiche (> etat = 41)
     *          => Si la touche Shift est pressée, alors selectionne le noeud le plus proche (> etat = 42)
     *     
     *      > 41 : creation du second point et du segment : affichage de l'ensemble (etat = 40)
     *      > 42 : selection du second point et cfreation de la barre (etat = 40)
     * 
     * 50 : création de triangles de terrain
     *      > 50 : création du premier point (etat = 51)
     *      > 51 : creation du second point (etat = 52)
     *      > 52 : creation du troisieme point et affichage du triangle (etat = 50)
     * 
     * 60 : Verrouiller
     *  
     */
    
    public void clicCanvas(MouseEvent t)
    {
        ArrayList<noeud> ensNd = this.getMain().gettrModel().getEns_noeud();
        ArrayList<barre> ensBr = this.getMain().gettrModel().getEns_barre();
        ArrayList<triangle_terrain> ensTrTerr = this.getMain().getTeModel().getEns_triangle_terrain();
        noeud_simple nvNd;
        point ptClic;
        
        switch(this.getEtat())
        {
            case 10:
                ptClic = new point(t.getX(), t.getY());
                noeud ndProche = NoeudProche(ptClic);
                
                this.listBSelect.clear();
                this.listTTSelect.clear();
                
                if(this.getListNSelect().size() == 0)
                {
                    this.getListNSelect().add(ndProche);
                }
                
                if(ndProche != null)
                {
                    if(t.isShiftDown())
                    {
                        if(!this.listNSelect.contains(ndProche))
                        {
                            this.getListNSelect().add(ndProche);
                            System.out.println(this.getListNSelect());
                        }
                        
                    }
                    else if(t.isControlDown())
                    {
                        if(this.getListNSelect().contains(ndProche))
                        {
                            this.getListNSelect().remove(ndProche);
                        }
                        else 
                        {
                            this.getListNSelect().add(ndProche);
                        }
                        
                        System.out.println(this.getListNSelect());

                    }
                    else if(t.isAltDown())
                    {
                        this.listNSelect.clear();
                        System.out.println(this.listNSelect);
                    }
                    
                    else
                    {
                        this.getListNSelect().clear();
                        this.getListNSelect().add(ndProche);

                        System.out.println(this.getListNSelect());
                    }
                }
                
                this.main.redrawAll();
            break;
            
            case 11:
                ptClic = new point(t.getX(), t.getY());
                barre BarreProche = BarreProche(ptClic);
                
                this.listNSelect.clear();
                this.listTTSelect.clear();
                
                if(this.getListBSelect().size() == 0)
                {
                    this.getListBSelect().add(BarreProche);
                }
                
                if(BarreProche != null)
                {
                    if(t.isShiftDown())
                    {
                        if(!this.listBSelect.contains(BarreProche))
                        {
                            this.getListBSelect().add(BarreProche);
                            System.out.println(this.getListBSelect());
                        }
                        
                    }
                    else if(t.isControlDown())
                    {
                        if(this.getListBSelect().contains(BarreProche))
                        {
                            this.getListBSelect().remove(BarreProche);
                        }
                        else 
                        {
                            this.getListBSelect().add(BarreProche);
                        }
                        
                        System.out.println(this.getListBSelect());

                    }
                    else if(t.isAltDown())
                    {
                        this.listBSelect.clear();
                        System.out.println(this.listBSelect);
                    }
                    
                    else
                    {
                        this.getListBSelect().clear();
                        this.getListBSelect().add(BarreProche);

                        System.out.println(this.getListBSelect());
                    }
                }
                
                this.main.redrawAll();

            break;
            
            case 12 :
                ptClic = new point(t.getX(), t.getY());
                segment segProche = SegmentProche(ptClic);
                triangle_terrain terrProche = TriangleProche(segProche);
                
                this.listBSelect.clear();
                this.listNSelect.clear();
                
                if(this.listTTSelect.isEmpty())
                {
                    this.listTTSelect.add(terrProche);
                }
                else
                {
                    if(t.isShiftDown())
                    {
                        if(!this.listTTSelect.contains(terrProche))
                        {
                            this.listTTSelect.add(terrProche);
                        }
                    }
                    
                    else if(t.isControlDown())
                    {
                        if(!this.listTTSelect.contains(terrProche))
                        {
                            this.listTTSelect.add(terrProche);
                        }          
                        else
                        {
                            this.listTTSelect.remove(terrProche);
                        }
                    }
                    
                    else if(t.isAltDown())
                    {
                        this.listTTSelect.clear();
                    }
                    else
                    {
                        this.listTTSelect.clear();
                        this.listTTSelect.add(terrProche);
                    }
                }
                
                this.main.redrawAll();
            break;
            
            
            
            case 20 :
                this.listBSelect.clear();
                this.listNSelect.clear();
                
                this.main.redrawAll();
                
                ptClic = new point(t.getX(), t.getY());
                
                this.Sselect = SegmentProche(ptClic);
                
                this.main.redrawAll();
                this.etat = 21;
            break;
            
            case 21 :
                ptClic = new point(t.getX(), t.getY());
                triangle_terrain tt = TriangleProche(this.Sselect);
                
                if(!(ptClic.getPx() < this.Sselect.getMinX()) && !(ptClic.getPx() > this.Sselect.getMaxX()))
                {
                    if(!(ptClic.getPy() < this.Sselect.getMinY()) && !(ptClic.getPy() > this.Sselect.getMaxY()))
                    {
                        point App = this.Sselect.PointNormal(ptClic);
                        
                        appui_simple aS = new appui_simple(this.main.gettrModel().getEns_noeud().size(), tt, this.Sselect, App);
                        this.main.gettrModel().getEns_noeud().add(aS);

                        this.etat = 20;
                        
                        this.listTTSelect.clear();

                    }
                }
                this.main.redrawAll();
            break;
            
            
            case 30 :
                if(this.newCol != null)
                {
                    nvNd = new noeud_simple(ensNd.size(), newCol, new point(t.getX(), t.getY()));                
                }
                else
                {
                    nvNd = new noeud_simple(ensNd.size(), new point(t.getX(), t.getY()));                
                }
                
                System.out.println(nvNd);
                ensNd.add(nvNd);
                this.getMain().redrawAll();
                
            break;
            
            case 40:
                this.listNSelect.clear();

                if(t.isShiftDown())
                {
                    noeud NdProche = NoeudProche(new point(t.getX(), t.getY()));

                    this.listNSelect.add(NdProche);
                    
                    this.etat = 42;
                }
                else
                {
                    nvNd = new noeud_simple(ensNd.size(), new point(t.getX(), t.getY()));
                    ensNd.add(nvNd);

                    this.etat = 41;
                }
                
                this.getMain().redrawAll();
            break;

            case 41:
                if(t.isShiftDown())
                {
                    ndProche = NoeudProche(new point(t.getX(), t.getY()));
                    this.listNSelect.add(ndProche);

                    barre Seg = new barre(this.main.gettrModel().getEns_noeud().size(), ensNd.get(ensNd.size()-1), ndProche);
                    this.main.gettrModel().getEns_barre().add(Seg);
                }
                else
                {
                    nvNd = new noeud_simple(ensNd.size(), new point(t.getX(), t.getY()));
                    ensNd.add(nvNd);

                    ensBr.add(new barre(ensBr.size(), ensNd.get(ensNd.size()-2), ensNd.get(ensNd.size()-1)));
                }
                
                this.getMain().redrawAll();
                this.etat = 40; // enlever cette ligne pour créer un polyligne
            break;
            
            case 42:
                if(t.isShiftDown())
                {
                    ndProche = NoeudProche(new point(t.getX(), t.getY()));
                    this.listNSelect.add(ndProche);

                    barre Seg = new barre(this.main.gettrModel().getEns_noeud().size(), this.listNSelect.get(0), ndProche);
                    this.main.gettrModel().getEns_barre().add(Seg);
}
                else
                {
                    nvNd = new noeud_simple(ensNd.size(), new point(t.getX(), t.getY()));
                    ensNd.add(nvNd);

                    ensBr.add(new barre(ensBr.size(), this.listNSelect.get(0), ensNd.get(ensNd.size()-1)));
                }
                
                this.main.redrawAll();
                this.etat = 40;
                
            break;
            
            
            case 50:   
                
                if(t.isShiftDown())
                {
                    point ptProche = PointProche(new point(t.getX(), t.getY()));
                    pts[0] = ptProche;
                }
                else
                {
                     pts[0] = new point(t.getX(), t.getY());
                }
                
                
                this.getMain().redrawAll();
                this.etat = 51;
            break;
            
            case 51 :
                if(t.isShiftDown())
                {
                    point ptProche = PointProche(new point(t.getX(), t.getY()));
                    pts[1] = ptProche;
                }
                else
                {
                     pts[1] = new point(t.getX(), t.getY());
                }
                                
                this.getMain().redrawAll();
                this.etat = 52;
            break;
            
            case 52 :
                point nvP;
                if(t.isShiftDown())
                {
                    nvP = PointProche(new point(t.getX(), t.getY()));
                }
                else
                {
                     nvP = new point(t.getX(), t.getY());
                }
                
                ensTrTerr.add(new triangle_terrain(ensTrTerr.size(), getPts()[0], getPts()[1],nvP));
                //System.out.println(ensTrTerr.get(ensTrTerr.size()-1));

                pts[0] = null;
                pts[1] = null;
                
                this.etat = 50;
                this.getMain().redrawAll();
            break;
        }
    }
    
    
    public triangle_terrain TriangleProche(segment seg)
    {
        if(this.main.getTeModel().getEns_triangle_terrain().isEmpty())
        {
            return null;
        }
        else
        {
            triangle_terrain trlgProche = null;
            for(triangle_terrain tt : this.main.getTeModel().getEns_triangle_terrain())
            {
                if(tt.getSegs().contains(seg))
                {
                    trlgProche = tt;
                }
            }
            
            return trlgProche;
        }
    }
    
    public segment SegmentProche(point pt)
    {
        double distS;
        segment Seg;
        
        if(this.main.getTeModel().getEns_triangle_terrain().isEmpty())
        {
            return null;
        }
        else
        {
            double cur;
            
            Seg = this.main.getTeModel().getEns_triangle_terrain().get(0).getSegs().get(0);
            distS = Seg.distPoint(pt);
            
            for(triangle_terrain tt : this.main.getTeModel().getEns_triangle_terrain())
            {
                for(int i=0; i < tt.getSegs().size(); i++)
                {
                    cur = tt.getSegs().get(i).distPoint(pt);
                    
                    if(cur < distS)
                    {
                        distS = cur;
                        Seg = tt.getSegs().get(i);
                    }
                }
            }
        }
        
        return Seg;
    }
    
    public barre BarreProche(point pt)
    {
        double distB;
        barre b;
        
        System.out.println("Ensemble de barre :" + this.main.gettrModel().getEns_barre());
        
        
        if(this.main.gettrModel().getEns_barre().isEmpty())
        {
            return null;
        }
        else
        {
            b = this.getMain().gettrModel().getEns_barre().get(0);
            distB = b.distPoint(pt);
            
            
            for(int i=0; i<this.getMain().gettrModel().getEns_barre().size(); i++)
            {
                double cur = this.getMain().gettrModel().getEns_barre().get(i).distPoint(pt);
                
                if(cur < distB)
                {
                    distB = cur;
                    b = this.getMain().gettrModel().getEns_barre().get(i);
                }
            }
            
            return b;
        }
    }
    
    
    public noeud NoeudProche(point pt)
    {
        double distN;
        noeud Nd;
    
        if(this.getMain().gettrModel().getEns_noeud().isEmpty())
        {
            return null;
        }
        else
        {
            Nd = this.getMain().gettrModel().getEns_noeud().get(0);
            distN = Nd.distPoint(pt);

            for(int i=1; i<this.getMain().gettrModel().getEns_noeud().size(); i++)
            {
                double cur = this.getMain().gettrModel().getEns_noeud().get(i).distPoint(pt);
                if(cur < distN)
                {
                    distN = cur;
                    Nd = this.getMain().gettrModel().getEns_noeud().get(i);
                }
            }
            return Nd;
        }
    }
    
    public point PointProche(point pt)
    {
        double distPt;
        point Point;
    
        if(this.getMain().getTeModel().getEns_triangle_terrain().isEmpty())
        {
            return null;
        }
        else
        {
            
            distPt = this.getMain().getTeModel().getEns_triangle_terrain().get(0).getPts().get(0).distPoint(pt);
            Point = this.getMain().getTeModel().getEns_triangle_terrain().get(0).getPts().get(0);

            for(triangle_terrain tt : this.getMain().getTeModel().getEns_triangle_terrain())
            {
                for(int i=0; i < tt.getPts().size(); i++)
                {
                    double cur = tt.getPts().get(i).distPoint(pt);
                    
                    if(cur < distPt)
                    {
                        distPt = cur;
                        Point = tt.getPts().get(i);
                    }
                }
            }
            
            return Point;
        }
    }
    
    public void changeCouleur(Color clr)
    {
        switch(etat)
        {
            case 10 :
                if(this.listNSelect.size() != 0)
                {
                    for(noeud n : this.listNSelect)
                    {
                        n.changeCouleur(clr);
                    }
                }
                if(this.listBSelect.size() != 0)
                {
                    for(barre b : this.listBSelect)
                    {
                        b.changeCouleur(clr);
                    }
                }
            break;
            
            case 30:
                this.newCol = clr;
            break;
        }
    }
    
    public void supprimer()
    {
        if(this.listNSelect.size() != 0)
        {
            for(noeud n : this.listNSelect)
            {
                this.main.gettrModel().getEns_noeud().remove(n);
            }
            this.listNSelect.clear();
        }

        if(this.listBSelect.size() != 0)
        {
            for(barre b : this.listBSelect)
            {
                this.main.gettrModel().getEns_barre().remove(b);
            }
            this.listBSelect.clear();
        }
        
        if(this.listTTSelect.size() != 0)
        {
            for(triangle_terrain tt : this.listTTSelect)
            {
                this.main.getTeModel().getEns_triangle_terrain().remove(tt);
            }
            this.listTTSelect.clear();
        }

        this.main.redrawAll();
    }
    
    

    /**
     * @return the main
     */
    public MainPane getMain() {
        return main;
    }

    /**
     * @return the etat
     */
    public int getEtat() {
        return etat;
    }

    /**
     * @return the ver
     */
    public boolean isVer() {
        return ver;
    }

    /**
     * @return the pt1
     */
    public double[] getPt1() {
        return pt1;
    }

    /**
     * @return the listBSelect
     */
    public ArrayList<barre> getListBSelect() {
        return listBSelect;
    }

    /**
     * @return the listNSelect
     */
    public ArrayList<noeud> getListNSelect() {
        return listNSelect;
    }

    /**
     * @return the pts
     */
    public point[] getPts() {
        return pts;
    }

    /**
     * @return the ListSSelect
     */
    public ArrayList<triangle_terrain> getListTTSelect() 
    {
        return listTTSelect;
    }
}
