package fr.insa.isenbart.projet_m2.progs;

import java.util.*;


public class noeud_simple extends noeud 
{
    private point noeud;
    
    public noeud_simple(point pt)
    {
        this.noeud = pt;
    }
    
    public String toString()
    {
        return "Noeud simple : " + this.noeud.toString();
    }

}