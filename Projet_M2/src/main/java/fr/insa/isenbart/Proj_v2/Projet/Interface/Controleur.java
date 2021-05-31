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

/*
Fuck off
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
                case 60 :
                    if(!isVer())
                    {
                        this.getMain().getbAppui().setDisable(true);
                        this.getMain().getbPoint().setDisable(true);
                        this.getMain().getbSegment().setDisable(true);
                        this.getMain().getbSelect().setDisable(true);
                        //this.getMain().getCpCouleur().setDisable(true);
                        this.getMain().getbTrlgTerrain().setDisable(true);
                        this.getMain().getbVerrouiller().setText("Déverrouiller");
                    }
                    else
                    {
                        this.getMain().getbTrlgTerrain().setDisable(false);
                        this.getMain().getbAppui().setDisable(false);
                        this.getMain().getbPoint().setDisable(false);
                        this.getMain().getbSegment().setDisable(false);
                        this.getMain().getbSelect().setDisable(false);
                        //this.getMain().getCpCouleur().setDisable(false);
                        this.getMain().getbVerrouiller().setText("Verrouiller");
                    }

                    ver = !isVer();

                break;
                
                case 70 :
                    System.out.println("Sauvegarder");
                break;
                
                case 80:
                    System.out.println("Calculer");
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
            /**
             * 10 : Selection de noeud(s)
             * Lorsque la sélection de noeud est sélectionnée :
             * 
             * Les listes de sélection de barre et de Triangle de terrain sont vidées
             * 
             * Si aucun noeud n'est encore sélectionné, on place ce noeud dans la liste de sélection (de noeud)
             * Sinon :
             *      Si le bouton SHIFT est appuyé, on ajoute ce noeud à la liste de sélection (s'il n'y est pas déjà)
             *      Si le bouton CONTROL est appuyé :
             *          Si le noeud est déjà dans la liste de sélection, on le retire
             *          Si le noeud n'est pas déjà dans la liste de sélection, on l'ajoute
             *      Si le bouton ALT est appuyé, on vide la liste de sélection
             *      Sinon seul le noeud sélectionné est dans la liste de sélection
             * 
             * On redessine tout

             */
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
                            //System.out.println(this.getListNSelect());
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
                        
                        //System.out.println(this.getListNSelect());

                    }
                    else if(t.isAltDown())
                    {
                        this.listNSelect.clear();
                        //System.out.println(this.listNSelect);
                    }
                    
                    else
                    {
                        this.getListNSelect().clear();
                        this.getListNSelect().add(ndProche);

                        //System.out.println(this.getListNSelect());
                    }
                }
                
                this.main.redrawAll();
            break;
            
            /**
             * 11 : Selection de barre(s)
             * Lorsque la sélection de barre est sélectionnée :
             * 
             * Les listes de sélection de noeud et de Triangle-de-terrain sont vidées
             * 
             * Si aucune barre n'est encore sélectionnée, on place cette barre dans la liste de sélection (de barre)
             * Sinon :
             *      Si le bouton SHIFT est appuyé, on ajoute cette varre à la liste de sélection (si elle n'y est pas déjà)
             *      Si le bouton CONTROL est appuyé :
             *          Si la barre est déjà dans la liste de sélection, on la retire
             *          Si la barre n'est pas déjà dans la liste de sélection, on l'ajoute
             *      Si le bouton ALT est appuyé, on vide la liste de sélection
             *      Sinon seul la barre sélectionnée est dans la liste de sélection
             * 
             * On redessine tout
             */
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
                            //System.out.println(this.getListBSelect());
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
                        
                        //System.out.println(this.getListBSelect());

                    }
                    else if(t.isAltDown())
                    {
                        this.listBSelect.clear();
                        //System.out.println(this.listBSelect);
                    }
                    
                    else
                    {
                        this.getListBSelect().clear();
                        this.getListBSelect().add(BarreProche);

                        //System.out.println(this.getListBSelect());
                    }
                }
                
                this.main.redrawAll();

            break;
            
            /**
             * 12 : Selection de triangle(s)-de-terrain
             * Lorsque la sélection de triangle-de-terrain TDT est sélectionnée :
             * 
             * Les listes de sélection de noeud et de barre sont vidées
             * 
             * Si aucun TDT n'est encore sélectionnée, on place ce TDT dans la liste de sélection (de TDT)
             * Sinon :
             *      Si le bouton SHIFT est appuyé, on ajoute ce TDT à la liste de sélection (s'il n'y est pas déjà)
             *      Si le bouton CONTROL est appuyé :
             *          Si le TDT est déjà dans la liste de sélection, on le retire
             *          Si le TDT n'est pas déjà dans la liste de sélection, on l'ajoute
             *      Si le bouton ALT est appuyé, on vide la liste de sélection
             *      Sinon seul le TDT sélectionné est dans la liste de sélection
             * 
             * On redessine tout
             */
            case 12 :
                ptClic = new point(t.getX(), t.getY());
                this.Sselect = SegmentProche(ptClic);
                triangle_terrain terrProche = TriangleProche(this.getSselect());
                
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
            
            
            /**
             * 20 : Creation d'appui
             * 
             * Au clic, on recupère le segment le plus proche puis on projete sur ce segment le point cliqué
             * 
             * Lorsque l'appui est positionné, on redessine tout
             */
            case 20 :
                this.listBSelect.clear();
                this.listNSelect.clear();
                this.listTTSelect.clear();
                
                this.main.redrawAll();
                
                ptClic = new point(t.getX(), t.getY());
                
                
                this.Sselect = SegmentProche(ptClic);

                triangle_terrain tt = TriangleProche(this.getSselect());

                if(!(ptClic.getPx() < this.Sselect.getMinX()) && !(ptClic.getPx() > this.Sselect.getMaxX()))
                {
                    if(!(ptClic.getPy() < this.Sselect.getMinY()) && !(ptClic.getPy() > this.Sselect.getMaxY()))
                    {
                        point App = this.getSselect().PointNormal(ptClic);

                        appui_simple aS = new appui_simple(this.main.gettrModel().getEns_noeud().size(), tt, this.getSselect(), App);
                        this.main.gettrModel().getEns_noeud().add(aS);

                    }
                }
                this.Sselect = null;
                
                
                this.main.redrawAll();
            break;
            
            
            /**
             * 30 : création de noeud(s)
             * 
             * On crée un nouveau noeud simple où le point cliqué se trouve
             * 
             * On redessine tout
             */
            case 30 :
                if(this.newCol != null)
                {
                    nvNd = new noeud_simple(ensNd.size(), newCol, new point(t.getX(), t.getY()));                
                }
                else
                {
                    nvNd = new noeud_simple(ensNd.size(), new point(t.getX(), t.getY()));                
                }
                
                //System.out.println(nvNd);
                ensNd.add(nvNd);
                this.getMain().redrawAll();
                
            break;
            
            
            /**
             * 40-41-42 : création de barre(s)
             * On peut créer une barre : 
             *   - En créant deux nouveaux noeuds
             *   - En créant un nouveaux noeud et en en sélectionnant un déjà existant
             *   - En sélectionnant deux noeuds déjà existants
             * 
             * 40 : création/sélection du premier noeud/appui
             * 
             * Si la touche SHIFT est préssée, on sélectionne le noeud le plus proche pour servir de premier noeud de la barre
             *  > On passe à l'état 42
             * Sinon on crée un nouveau noeud simple où la zone de dessin a été cliquée
             *  > On passe à l'état 41
             * On redessine tout
             * 
             * 41 : création/sélection du second noeud/appui (le premier point a été créé)
             * 
             * Si la touche SHIFT est pressée, on sélectionne le noeud le plus proche pour servir de second point à la barre
             * Sinon on crée un nouveau noeud simple où la zone de dessin a été cliquée
             *  
             * > On repasse à l'état 40 et on redessine tout
             * 
             */
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
                this.etat = 40; 
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
            
            
            
            /**
             * 50-51-52 : Création de triangle de terrain (TDT)
             * On peut créer un nouveau TDT :
             *   - En créant trois nouveaux points
             *   - En créant deux nouveaux points et en sélectionnant un déjà existant
             *   - En créant un nouveaux point et en sélectionnant deux déjà existants
             *   - En sélectionnant trois points déjà existants
             *   
             * 50 : création/sélection du premier point
             * Si la touche SHIFT est pressée, on sélectionne le point le plus proche et on en fait le premier point du TDT
             * Sinon on crée un nouveau point
             *  > etat 51 et on redessine tout
             * 
             * 51 : création/sélection du deuxième point
             * Si la touche SHIFT est pressée, on sélectionne le point le plus proche et on en fait le second point du TDT
             * Sinon on crée un nouveau point
             *  > etat 52 et on redessine tout
             * 
             * 52 : création/sélection du troisième point
             * Si la touche SHIFT est pressée, on sélectionne le point le plus proche et on en fait le troisième point du TDT
             * Sinon on crée un nouveau point
             *  > etat 50 et on redessine tout
             * 
             */
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

                pts[0] = null;
                pts[1] = null;
                
                this.etat = 50;
                this.getMain().redrawAll();
            break;
        }           
    }
    
    /**
     * Methode TriangleProche :
     * Cette méthode permet de retourner le TDT contenant le segment sélectionné
     * 
     * Si la liste de TDT du terrain est vide, 
     *   @return null
     * Sinon on parcourt tous les triangles de terrain et on regarde si le segment est contenu dans un de ces TDT,
     *   @return TDT
     */
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
    
    /**
     * Methode SegmentProche :
     * Cette méthode permet de retourner le segment le plus proche du segment sélectionné
     * 
     * Si la liste de TDT du terrain est vide, 
     *   @return null
     * Sinon on parcourt tous les srgments des triangles de terrain et on regarde leur distance normale par rapport au clic, et retourne le segment le plus proche
     *   @return segment
     */
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
    
    /**
     * Methode BarreProche :
     * Cette méthode permet de retourner la barre la plus proche du point_clic
     * 
     * Si la liste de barre du treillis est vide, 
     *   @return null
     * Sinon on calcul la distance de chaque barre par rapport au point cliqué et on retourne la barre ayant la distance la plus faible
     *   @return barre
     */
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
    
    /**
     * Methode NoeudProche :
     * Cette méthode permet de retourner le noeud le plus proche du point cliqué
     * 
     * Si la liste de noeuds du treillis est vide, 
     *   @return null
     * Sinon on calcul la distance de chaque noeud par rapport au point cliqué, et on retourne le noeud le plus proche
     *   @return noeud
     */
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
    
    /**
     * Methode PointProche :
     * Cette méthode permet de retourner le point le plus proche du point clic
     * 
     * Si la liste de TDT du terrain est vide, 
     *   @return null
     * Sinon on parcourt tous les points des triangles de terrain et on calcul leur distance normale par rapport au clic.
     * On retourne le point le plus proche
     *   @return point
     */
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
        
        this.Sselect = null;

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

    /**
     * @return the Sselect
     */
    public segment getSselect() {
        return Sselect;
    }
}
