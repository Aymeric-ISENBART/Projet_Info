package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class triangle_terrain
{

    private int identificateur;
    
    private ArrayList<point> pts = new ArrayList<point>();
    private ArrayList<segment> segs = new ArrayList<segment>();
    
    private Color clr;
    private double rayonDessin = 3;


    public triangle_terrain(int id, point p1, point p2, point p3) 
    {
        this.identificateur = id;
        this.clr = Color.GREENYELLOW;
        
        
        this.pts.add(p1);
        this.pts.add(p2);
        this.pts.add(p3);
        
        
        this.segs.add(new segment(getPts().get(0), getPts().get(1)));
        this.segs.add(new segment(getPts().get(0), getPts().get(2)));
        this.segs.add(new segment(getPts().get(1), getPts().get(2)));
    }
    
    public static triangle_terrain triangleTerrainTest(int id)
    {
        
        ArrayList<point> ListPt = new ArrayList<point>();
        
        for(int i=0; i<3; i++)
        {
            ListPt.add(new point((300+Math.random()*200), (300 + Math.random()*200)));
        }
        
        triangle_terrain trlgTer = new triangle_terrain(id,ListPt.get(0), ListPt.get(1), ListPt.get(2));
        return trlgTer;
    }
    
    public String toString()
    {
        String res = "Triangle terrain n°" + this.getIdentificateur() + "\n\tPoints : ";
        for(point p : this.getPts())
        {
            res = res + p.toString();
        }
        res = res + "\n\tSegments : ";
        for(segment s : this.getSegs())
        {
            res = res + s.toString();
        }
        
        return res;
    }
    
    public void dessine(GraphicsContext context)
    {
        for(point p : this.getPts())
        {
            context.setFill(this.getClr());
            context.fillOval(p.getPx()-getRayonDessin(), p.getPy()-getRayonDessin(), 2*getRayonDessin(), 2*getRayonDessin());
        }
        
        for(segment s : this.getSegs())
        {
            context.setStroke(this.getClr());
            context.strokeLine(s.getP1().getPx(), s.getP1().getPy(), s.getP2().getPx(), s.getP2().getPy());
        }
    }

   
    public int getIdentificateur() 
    {
        return identificateur;
    }

    public void setIdentificateur(int identificateur) 
    {
        this.identificateur = identificateur;
    }

    
    public ArrayList<point> getPts() 
    {
        return pts;
    }

    
    public void setPts(ArrayList<point> pts) 
    {
        this.pts = pts;
        
        ArrayList<segment> segments = new ArrayList<segment>();
        segments.add(new segment(getPts().get(0), getPts().get(1)));
        segments.add(new segment(getPts().get(0), getPts().get(2)));
        segments.add(new segment(getPts().get(1), getPts().get(2)));
        
        this.segs = segments;
    }

    
    public ArrayList<segment> getSegs() 
    {
        return segs;
    }
    
    public void setSegs(ArrayList<segment> segs) 
    {
        this.segs = segs;
    }

    /**
     * @return the clr
     */
    public Color getClr() 
    {
        return clr;
    }

    /**
     * @return the rayonDessin
     */
    public double getRayonDessin() {
        return rayonDessin;
    }
}