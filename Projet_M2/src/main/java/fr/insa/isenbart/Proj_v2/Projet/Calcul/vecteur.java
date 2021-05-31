package fr.insa.isenbart.Proj_v2.Projet.Calcul;

import fr.insa.isenbart.Proj_v2.Projet.noeud;




public class vecteur 
{

 //attribut
 private noeud n1; //coefficient directeur
 private noeud n2;
 private double abscisse;
 private double ordonnee;
 private double norme;

 //contructeur
 public vecteur(noeud n1, noeud n2 ){
  this.n1=n1;
  this.n2=n2;
  this.abscisse=n2.getX()-n1.getX();
  this.ordonnee=n2.getY()-n1.getY();
  this.norme = Math.sqrt((this.abscisse)*(this.abscisse)+(this.ordonnee)*(this.ordonnee));
 }

 public vecteur(double x , double y ){
 
  this.abscisse=x;
  this.ordonnee=y;
  this.norme = Math.sqrt((this.abscisse)*(this.abscisse)+(this.ordonnee)*(this.ordonnee));
 }

 //methode get
 public noeud getn1(){
  return this.n1;
}

public noeud getn2(){
 return this.n2;
}


public double getnorme()
{
 return this.norme;
}

public double getabscisse()
{
 return this.abscisse;
}

public double getordonnee()
{
 return this.ordonnee;
}


}
