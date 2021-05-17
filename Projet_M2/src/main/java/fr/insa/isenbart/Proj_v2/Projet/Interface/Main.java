/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.isenbart.Proj_v2.Projet.Interface;

import fr.insa.isenbart.Proj_v2.Projet.Treillis;
import fr.insa.isenbart.Proj_v2.Projet.terrain;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author aymer
 */
public class Main extends Application 
{

    @Override
    public void start(Stage stage) 
    {
        //Scene sc = new Scene(new MainPane(Treillis.TreillisTest(), terrain.terrainTest()), 800,600);
        Scene sc = new Scene(new MainPane(new Treillis(), new terrain()), 800,600);
        stage.setScene(sc);
          stage.show();
    }

    public static void main(String[] args) 
    {
        launch();
    }

}
