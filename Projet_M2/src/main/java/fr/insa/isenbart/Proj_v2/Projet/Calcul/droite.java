package fr.insa.isenbart.Proj_v2.Projet.Calcul;

import fr.insa.isenbart.Proj_v2.Projet.noeud;
import fr.insa.isenbart.Proj_v2.Projet.point;


public class droite 
{

 //attribut
 private double coeff; //coefficient directeur
 private double ordonnee;

 //contructeur
 public droite(double c, double o){
  this.coeff=c;
  this.ordonnee=o;
 }

 public droite(noeud a, noeud b)
 {

  double x1=a.getX();
  double y1=a.getY();
  double x2=b.getX();
  double y2=b.getY();
  if(x1==x2)
  {
   this.coeff=0;
  }
  else{
  this.coeff=(y2-y1)/(x2-x1);
   } //equation de droite y=ax+b donc y1=coeffx1+b d'ou b=y1-coeffx1
  this.ordonnee=y1-this.coeff*x1;
  
  
 }
 
 public droite(point a, point b)
 {

  double x1=a.getPx();
  double y1=a.getPy();
  double x2=b.getPx();
  double y2=b.getPy();
  if(x1==x2)
  {
   this.coeff=0;
  }
  else{
  this.coeff=(y2-y1)/(x2-x1);
   } //equation de droite y=ax+b donc y1=coeffx1+b d'ou b=y1-coeffx1
  this.ordonnee=y1-this.coeff*x1;
  
  
 }

 

 //methode get
 public double getcoeff(){
  return this.coeff;
}

public double getordonnee(){
 return this.ordonnee;
}
 
//methode toString

public String toString(){
 String res= "";
 res="y="+String.valueOf(this.getcoeff())+"*x+"+String.valueOf(this.getordonnee());
return res;
}

 
}
