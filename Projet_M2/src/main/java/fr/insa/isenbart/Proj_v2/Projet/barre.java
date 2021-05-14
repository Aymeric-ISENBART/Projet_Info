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
    }
    
    public barre(int id, noeud nd1, noeud nd2, type_b typB) 
    {
        this.identitficateur = id;
        this.nd1 = nd1;
        this.nd2 = nd2;
        this.type = typB;
    }

    public double distPoint(point pt)
    {
        double x1 = this.nd1.getX();
        double y1 = this.nd1.getY();
        double x2 = this.nd2.getX();
        double y2 = this.nd2.getY();
        double x3 = pt.getPx();
        double y3 = pt.getPy();
        
        double up = ((x3-x1)*(x2-x1) + (y3-y1)*(y2-y1)) / (Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
        
        if (up<0)
        {
            return this.nd1.distPoint(pt);
        }
        else if(up>1)
        {
            return this.nd2.distPoint(pt);
        }
        else
        {
            point p4 = new point(x1 + up*(x2-x1), y1 + up*(y2-y1));
            
            return p4.distPoint(pt);
        }
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
    
    
    public void dessine(GraphicsContext context)
    {
        context.setStroke(/*this.clr*/Color.BLUE);
        context.strokeLine(this.nd1.getX(), this.nd1.getY(), this.nd2.getX(), this.nd2.getY());
    }
    
    @Override
    public String toString()
    {
        return "[" + this.nd1.toString() + "," + this.nd2.toString() + "]";
    }


}