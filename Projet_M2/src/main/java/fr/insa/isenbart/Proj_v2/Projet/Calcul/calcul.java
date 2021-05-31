package fr.insa.isenbart.Proj_v2.Projet.Calcul;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import fr.insa.isenbart.Proj_v2.Projet.appui_double;
import fr.insa.isenbart.Proj_v2.Projet.appui_simple;
import fr.insa.isenbart.Proj_v2.Projet.barre;
import fr.insa.isenbart.Proj_v2.Projet.noeud;
import fr.insa.isenbart.Proj_v2.Projet.noeud_appui;
import fr.insa.isenbart.Proj_v2.Projet.point;
import fr.insa.isenbart.Proj_v2.Projet.segment;
import java.util.*;

public class calcul 
{
    
    public static int nbInconnue(Treillis treil)
    {
        appui_simple apS = new appui_simple(0);
        appui_double apD = new appui_double(0);
        
        ArrayList<noeud> EnsNoeud = treil.getEns_noeud();
        ArrayList<barre> EnsBarre = treil.getEns_barre();
        
        int inc = EnsBarre.size();
        
        for(noeud n : EnsNoeud)
        {
            if(n.getClass() == apS.getClass())
            {
                System.out.println("Appui Simple");
                inc = inc + 1;
            }
            else if(n.getClass() == apD.getClass())
            {
                System.out.println("Appui Double");
                inc = inc + 2;
            }
            else // Noeud simple
            {
                
            }
        }
        
        return inc;
    }
    
    public static boolean isostaticite(Treillis treil)
    {
        if(nbInconnue(treil) == (treil.getEns_noeud().size()*2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static int degIsostaticite(Treillis treil)
    {
        if(isostaticite(treil))
        {
            return 0;
        }
        else
        {
            return (nbInconnue(treil) - treil.getEns_noeud().size()*2);
        }
    }
    
    
    public static double angle(barre b1)
    {
        double angle=0;
        noeud bn1=b1.getNd1();
        noeud bn2=b1.getNd2();

        droite d1= new droite(bn1,bn2);

        droite planX = new droite(0,d1.getordonnee());

        double a= d1.getcoeff();
        double b =planX.getcoeff();
        angle=Math.atan(Math.abs((a-b)/1+a*b));

        return angle;
    }
    
    public static double angle(segment s1)
    {
        double angle=0;
        point sp1=s1.getP1();
        point sp2=s1.getP2();

        droite d1= new droite(sp1,sp2);

        droite planX = new droite(0,d1.getordonnee());

        double a= d1.getcoeff();
        double b =planX.getcoeff();
        angle=Math.atan(Math.abs((a-b)/1+a*b)); // retourn un angle entre ]-pi/2 ; pi/2[

        return angle;
    }
    
    public static double angleAppuiSimple(noeud_appui app)
    {
        segment seg = app.getSeg_trlg_terrain();
        
        return angle(seg)+(Math.PI/2);
    }
}
