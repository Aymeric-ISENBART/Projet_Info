package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;


public class triangle_terrain 
{

    private int identificateur;
    private ArrayList<point> pts = new ArrayList<point>();
    private ArrayList<segment> segs = new ArrayList<segment>();
    

    
    public triangle_terrain(int id, point p1, point p2, point p3) 
    {
        this.identificateur = id;
        
        
        this.pts.add(p1);
        this.pts.add(p2);
        this.pts.add(p3);
        
        
        this.segs.add(new segment(getPts().get(0), getPts().get(1)));
        this.segs.add(new segment(getPts().get(0), getPts().get(2)));
        this.segs.add(new segment(getPts().get(1), getPts().get(2)));
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
}