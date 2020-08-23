package edu.fiuba.algo3;

import edu.fiuba.algo3.kahoot.Kahoot;
import edu.fiuba.algo3.vista.CargadorResources;
import edu.fiuba.algo3.vista.Resources;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage)  {
        stage.getIcons().add(CargadorResources.obtenerImagen(Resources.IconoKahootRuta()));
        Kahoot app = new Kahoot(stage);
        app.iniciar();
    }

    public static void main(String[] args) {
        launch();
    }
}