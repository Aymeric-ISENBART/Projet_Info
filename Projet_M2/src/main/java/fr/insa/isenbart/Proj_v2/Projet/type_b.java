package fr.insa.isenbart.Proj_v2.Projet;


import java.util.*;


public class type_b 
{

    private int identificateur;
    private double cout_m;
    private double lg_min;
    private double lg_max;
    private double res_max_t;
    private double res_max_comp;

    
    /**
     * Default constructor
     */
    public type_b(int id, double cout, double lmin, double lmax, double rmComp, double rmTens) 
    {
        this.identificateur = id;
        this.cout_m = cout;
        this.lg_max = lmax;
        this.lg_min = lmin;
        this.res_max_comp = rmComp;
        this.res_max_t = rmTens;
    }

    /**
     * @return the identificateur
     */
    public int getIdentificateur() 
    {
        return identificateur;
    }

    /**
     * @param identificateur the identificateur to set
     */
    public void setIdentificateur(int identificateur) 
    {
        this.identificateur = identificateur;
    }

    /**
     * @return the cout_m
     */
    public double getCout_m() 
    {
        return cout_m;
    }

    /**
     * @param cout_m the cout_m to set
     */
    public void setCout_m(double cout_m) 
    {
        this.cout_m = cout_m;
    }

    
    public double getLg_min() 
    {
        return lg_min;
    }

    /**
     * @param lg_min the lg_min to set
     */
    public void setLg_min(double lg_min) 
    {
        this.lg_min = lg_min;
    }

    /**
     * @return the lg_max
     */
    public double getLg_max() 
    {
        return lg_max;
    }

    /**
     * @param lg_max the lg_max to set
     */
    public void setLg_max(double lg_max) 
    {
        this.lg_max = lg_max;
    }

    /**
     * @return the res_max_t
     */
    public double getRes_max_t() 
    {
        return res_max_t;
    }

    /**
     * @param res_max_t the res_max_t to set
     */
    public void setRes_max_t(double res_max_t) 
    {
        this.res_max_t = res_max_t;
    }

    /**
     * @return the res_max_comp
     */
    public double getRes_max_comp() 
    {
        return res_max_comp;
    }

    /**
     * @param res_max_comp the res_max_comp to set
     */
    public void setRes_max_comp(double res_max_comp) 
    {
        this.res_max_comp = res_max_comp;
    }
    
    

}