/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.Proj_v2.Projet.Calcul;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import fr.insa.isenbart.Proj_v2.Projet.Calcul.Matrice;
import fr.insa.isenbart.Proj_v2.Projet.Interface.Controleur;
import fr.insa.isenbart.Proj_v2.Projet.appui_double;
import fr.insa.isenbart.Proj_v2.Projet.appui_simple;
import fr.insa.isenbart.Proj_v2.Projet.barre;
import fr.insa.isenbart.Proj_v2.Projet.noeud;
import java.util.ArrayList;


/**
 *
 * @author aymer
 */
public class Creation_Matrice 
{
    private Treillis trModel;
    private appui_simple AS;
    private appui_double AD;
    
    public Creation_Matrice(Treillis treil)
    {
        this.trModel = treil;
    }
    
    public Matrice matTreil()
    {
        this.trModel.reIdentification();
        
        int nbL = this.trModel.getEns_noeud().size()*2;
        Matrice mat = new Matrice(nbL, nbL);
        
        double[][] matCoeff = new double[nbL][nbL];
        int indexL=0;
        int indexC=0;
        
        for(noeud n : this.trModel.getEns_noeud())
        {
            if((n.getClass()!=AS.getClass()) || (n.getClass()!=AD.getClass()))
            {
                for(barre b : this.trModel.getEns_barre())
                {
                    if((b.getNd1() == n) || (b.getNd2()==n))
                    {
                        matCoeff[n.getId()][b.getIdentitficateur()] = Math.cos(calcul.angle(b));
                        matCoeff[n.getId()+1][b.getIdentitficateur()] = Math.cos(calcul.angle(b));
                    }
                }
                indexL = indexL+2;
            }
        }
        
        indexC = this.trModel.getEns_barre().size();
        
        for(noeud n : this.trModel.getEns_noeud())
        {
            if(n.getClass() == AS.getClass())
            {
                matCoeff[indexL][indexC] = Math.cos(calcul.angleAppuiSimple(n));
                matCoeff[indexL][indexC] = Math.sin(calcul.angleAppuiSimple(n));
                
                for(barre b : this.trModel.getEns_barre())
                {
                    if((b.getNd1() == n) || (b.getNd2()==n))
                    {
                        matCoeff[indexL][b.getIdentitficateur()] = Math.cos(calcul.angle(b));
                        matCoeff[indexL+1][b.getIdentitficateur()] = Math.sin(calcul.angle(b));
                    }
                }
                indexL = indexL+2;
            }
            else if(n.getClass()==AD.getClass())
            {
                
            }
        }
        
    }
    
    public static void main(String[] args)
    {
        Treillis treil = Treillis.TreillisTest();
        Creation_Matrice cMatTreil = new Creation_Matrice(treil);
    }
    
}
