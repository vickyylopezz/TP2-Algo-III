package edu.fiuba.algo3.vista;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CargadorResources {
    public static Image obtenerImagen(String Path) {
        FileInputStream stream = null;
        try { stream = new FileInputStream(Resources.LogoPrincipalRuta()); }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        if (stream == null) return null;
        return new Image(stream);
    }
}
