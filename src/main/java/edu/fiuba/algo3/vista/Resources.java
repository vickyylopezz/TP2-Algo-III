package edu.fiuba.algo3.vista;

public class Resources {
    static public String Ruta = "src/main/resources/";

    static public String Logos = "logos/";
    static public String LogoPrincipal = "logos/logo.png";

    public static String LogoPrincipalRuta() { return Ruta + Logos + LogoPrincipal; }

    static public String Iconos = "iconos/";
    static public String IconoFlechaDerecha = "iconos/adelante.png";
    static public String IconoFlechaIzquierda = "iconos/atras.png";
    static public String IconoJugadorNegro = "iconos/icono jugador.png";
    static public String IconoJugadorBlanco = "iconos/jugador.png";

    public static String IconoFlechaDerechaRuta() { return Ruta + Iconos + IconoFlechaDerecha; }
    public static String IconoFlechaIzquierdaRuta() { return Ruta + Iconos + IconoFlechaIzquierda; }
    public static String IconoJugadorNegroRuta() { return Ruta + Iconos + IconoJugadorNegro; }
    public static String IconoJugadorBlancoRuta() { return Ruta + Iconos + IconoJugadorBlanco; }
}
