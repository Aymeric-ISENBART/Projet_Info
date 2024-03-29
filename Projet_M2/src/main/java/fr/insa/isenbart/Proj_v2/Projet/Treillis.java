package fr.insa.isenbart.Proj_v2.Projet;


import java.io.PrintWriter;
import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 */
public class Treillis
{

    private ArrayList<barre> ens_barre = new ArrayList<barre>();
    private ArrayList<noeud> ens_noeud = new ArrayList<noeud>();
    private catalogue cat;
   
    
    public Treillis() 
    {
        this.ens_barre = new ArrayList<barre>();
        this.ens_noeud = new ArrayList<noeud>();
        //this.clr = Color.BLUE;                
    }
    
    public Treillis(ArrayList<noeud> ensNd, ArrayList<barre> ensBr) 
    {
        this.ens_barre = ensBr;
        this.ens_noeud = ensNd;
    }

    
    public void dessine(GraphicsContext context)
    {
        for(barre b : this.ens_barre)
        {
            b.dessine(context);
            //System.out.println("Dessine_Barre");
        }
        
        for(noeud n : this.ens_noeud)
        {
            n.dessine(context);
            //System.out.println("Dessine_Noeud");
        }
    }
    
    
    public static Treillis TreillisTest()
    {
        ArrayList<barre> ensBar = new ArrayList<barre>();
        ArrayList<noeud> ensNoeud = new ArrayList<noeud>();
        
        for(int i=0; i<6; i++)
        {
            ensNoeud.add(new noeud_simple(i, new point(Math.random()*200, Math.random()*200)));
        }
        
        ensBar.add(new barre(0, ensNoeud.get(1), ensNoeud.get(4)));
        ensBar.add(new barre(1, ensNoeud.get(3), ensNoeud.get(5)));
        ensBar.add(new barre(2, ensNoeud.get(0), ensNoeud.get(3)));
        ensBar.add(new barre(3, ensNoeud.get(3), ensNoeud.get(4)));
        ensBar.add(new barre(4, ensNoeud.get(2), ensNoeud.get(1)));

        
        Treillis treil = new Treillis(ensNoeud, ensBar);
        
        System.out.println(treil);
        
        return treil;
    }
    
    public static Treillis TreillisTest2()
    {
        ArrayList<barre> ensBar = new ArrayList<barre>();
        ArrayList<noeud> ensNoeud = new ArrayList<noeud>();
        
        noeud_simple nd1 = new noeud_simple(0, Color.BLUE, new point(10,15));
        noeud_simple nd2 = new noeud_simple(1, Color.BLUE, new point(100,105));
        noeud_simple nd3 = new noeud_simple(2, Color.BLUE, new point(90,25));
        noeud_simple nd4 = new noeud_simple(3, Color.BLUE, new point(175,35));
        noeud_simple nd5 = new noeud_simple(4, Color.BLUE, new point(18,228));
        noeud_simple nd6 = new noeud_simple(5, Color.BLUE, new point(14,160));
        
        ensNoeud.add(nd1);
        ensNoeud.add(nd2);
        ensNoeud.add(nd3);
        ensNoeud.add(nd4);
        ensNoeud.add(nd5);
        ensNoeud.add(nd6);

        
        barre b1 = new barre(0, nd1, nd2);
        barre b2 = new barre(0, nd2, nd3);
        barre b3 = new barre(0, nd3, nd4);
        barre b4 = new barre(0, nd4, nd5);
        barre b5 = new barre(0, nd1, nd6);
        
        ensBar.add(b1);
        ensBar.add(b2);
        ensBar.add(b3);
        ensBar.add(b4);
        ensBar.add(b5);
        
        Treillis treil = new Treillis(ensNoeud, ensBar);
        
        System.out.println(treil);
        
        return treil;
    }
    
    
    @Override
    public String toString()
    {
        String res = "";
        
        res = res + "\t Noeuds : [";
        for(noeud n : ens_noeud)
        {
            res = res + n.toString() + ", ";
        }
        res = res + "]\n"; 
        
        res = res + "\t Barres : [";
        for(barre b : ens_barre)
        {
            res = res + b.toString() + ", ";
        }
        res = res + "]\n\n"; 
        
        return res;
    }
    
    public void reIdentification() // Reindexe tous les éléments des listes
    {
        for(barre b: this.getEns_barre())
        {
            b.setIdentitficateur(this.getEns_barre().indexOf(b));
        }
        for(noeud n : this.getEns_noeud())
        {
            n.setIdentificateur(this.getEns_noeud().indexOf(n));
        }
    }
    
    public void enregistrerTreillis(PrintWriter fw)
    {
        this.reIdentification();
        noeud_simple ns = new noeud_simple(0, new point(0, 0));

        fw.println("Barres : ");
        System.out.println("Barres : ");
        if(!this.ens_barre.isEmpty())
        {
            for(barre b : this.ens_barre)
            {
                System.out.println("\t" + b.getIdentitficateur() + ";" + b.getNd1() + ";" + b.getNd2() + ";" + b.getType());
                fw.println("\t" + b.getIdentitficateur() + ";" + b.getNd1() + ";" + b.getNd2() + ";" + b.getType()); 
            }
        }
                
        System.out.println("");
        System.out.println("");
        fw.println("");
        fw.println(""); 
                
        System.out.println("Noeuds : ");
        fw.println("Noeuds : ");
        System.out.println("\tNoeuds simples :");
        fw.println("\tNoeuds simples :");
         
        ArrayList<noeud> Appui = this.getAppui();
        
        if(!this.ens_noeud.isEmpty())
        {
            for(noeud n : this.ens_noeud)
            {
                if(n.getClass() == ns.getClass())
                {
                    System.out.println("\t\t" + n.getId()+ ";" + n);
                    fw.println("\t\t" + n.getId()+ ";" + n);
                }            
            }
        }
        
        System.out.println("");
        System.out.println("");
        fw.println("");
        fw.println("");
        
        System.out.println("\tAppuis");
        fw.println("\tAppuis");
        
        if(!Appui.isEmpty())
        {
            for(noeud n : Appui)
            {
                System.out.println("\t\t" + n.getId()+ ";" + n + "\n\t\tTriangle_de_terrain : " + n.getTrlg() + "\n\t\tSegment : " + n.getSeg());
                fw.println("\t\t" + n.getId()+ ";" + n + "\n\t\tTriangle_de_terrain : " + n.getTrlg() + "\n\t\tSegment : " + n.getSeg());

            }
        }
        
        
        System.out.println("Catalogue :");
        fw.println("Catalogue :");
        if(!(this.cat == null) && !this.cat.getCatalogue().isEmpty())
        {
            for(type_b tB : this.cat.getCatalogue())
            {
                System.out.println("\t\t" + tB);
                fw.println("\t\t" + tB);
            }
        }
        
        System.out.println("");
        System.out.println("");
        fw.println("");
        fw.println("");
    }
    
    

    public ArrayList<barre> getEns_barre() {
        return ens_barre;
    }

    
    public void setEns_barre(ArrayList<barre> ens_barre) {
        this.ens_barre = ens_barre;
    }

    public ArrayList<noeud> getEns_noeud() {
        return ens_noeud;
    }


    public void setEns_noeud(ArrayList<noeud> ens_noeud) {
        this.ens_noeud = ens_noeud;
    }
    
    public ArrayList<noeud> getAppui()
    {
        ArrayList<noeud> res = new ArrayList<noeud>();
        
        for(noeud n : this.ens_noeud)
        {
            if(n.getSeg() != null)
            {
                res.add(n);
            }
        }
        
        return res;
    }

    /**
     * @return the cat
     */
    public catalogue getCat() {
        return cat;
    }

    /**
     * @param cat the cat to set
     */
    public void setCat(catalogue cat) {
        this.cat = cat;
    }
}
