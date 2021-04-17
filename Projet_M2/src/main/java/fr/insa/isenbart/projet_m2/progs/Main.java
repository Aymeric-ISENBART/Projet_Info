/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.projet_m2.progs;

/**
 *
 * @author aymer
 */
public class Main 
{
    public static void main(String[] args)
    {
        point p1 = new point(1,3);
        point p2 = new point(4,6);
        segment seg = new segment(p1, p2);
        noeud_simple nd = new noeud_simple(p1); 
        
        
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(seg);
        System.out.println(nd);
    }
    
}
