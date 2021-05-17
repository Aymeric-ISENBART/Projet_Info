package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 
 */
public class barre 
{
    private int identitficateur;
    private noeud nd1;
    private noeud nd2;
    private type_b type;
    private Color clr;

    /**
     * Default constructor
     */
    public barre(int id, noeud nd1, noeud nd2) 
    {
        this.identitficateur = id;
        this.nd1 = nd1;
        this.nd2 = nd2;
        this.clr = Color.BLUE;
    }
    
    public barre(int id, noeud nd1, noeud nd2, type_b typB) 
    {
        this.identitficateur = id;
        this.nd1 = nd1;
        this.nd2 = nd2;
        this.type = typB;
        this.clr = Color.BLUE;
    }

    public double distPoint(point pt)
    {
        double x1 = this.nd1.getX();
        double y1 = this.nd1.getY();
        double x2 = this.nd2.getX();
        double y2 = this.nd2.getY();
        double x3 = pt.getPx();
        double y3 = pt.getPy();
        
        segment seg12 = new segment(this.nd1.getPoint(), this.nd2.getPoint());
        segment seg13 = new segment(this.nd1.getPoint(), pt);
        
        double lg12 = seg12.longueur();
        double lg13 = seg13.longueur();
        
        double up = ((x3-x1)*(x2-x1) + (y3-y1)*(y2-y1)) / (lg12 * lg13);
        
        if((up == 1) || (up == -1))
        {
            return 0;
        }
        else if(up == 0)
        {
            return this.nd1.distPoint(pt);
        }
        else
        {
            point p4 = new point(this.nd1.getX()*up, this.nd1.getX()*Math.tan(Math.acos(up)));
            
            return p4.distPoint(pt);
        }
    }
    
    public void dessineSelection(GraphicsContext context)
    {
        this.getNd1().dessineSelection(context);
        this.getNd2().dessineSelection(context);
        
        context.setStroke(Color.RED);
        context.strokeLine(this.nd1.getX(), this.nd1.getY(), this.nd2.getX(), this.nd2.getY());
        context.setLineWidth(2);
    }
    
    public void dessine(GraphicsContext context)
    {
        context.setStroke(this.clr);
        context.strokeLine(this.nd1.getX(), this.nd1.getY(), this.nd2.getX(), this.nd2.getY());
        context.setLineWidth(1);

    }
    
    public void changeCouleur(Color clr)
    {
        this.setClr(clr);
    }
    
    
    public int getIdentitficateur() 
    {
        return identitficateur;
    }

   
    public void setIdentitficateur(int identitficateur) 
    {
        this.identitficateur = identitficateur;
    }

    
    public noeud getNd1() 
    {
        return nd1;
    }

    
    public void setNd1(noeud nd1) 
    {
        this.nd1 = nd1;
    }

   
    public noeud getNd2() 
    {
        return nd2;
    }

    
    public void setNd2(noeud nd2) 
    {
        this.nd2 = nd2;
    }

    
    public type_b getType() 
    {
        return type;
    }

    
    public void setType(type_b type) 
    {
        this.type = type;
    }
    
    
    
    
    @Override
    public String toString()
    {
        return "[" + this.nd1.toString() + "," + this.nd2.toString() + "]";
    }

    /**
     * @param clr the clr to set
     */
    public void setClr(Color clr) {
        this.clr = clr;
    }


}