package edu.fiuba.algo3.vista;

public class Resources {
    static public String Ruta = "src/main/resources/";

    static public String Logos = "logos/";
    static public String LogoPrincipal = "logo.png";

    public static String LogoPrincipalRuta() { return Ruta + Logos + LogoPrincipal; }

    static public String Iconos = "iconos/";
    static public String IconoFlechaDerecha = "adelante.png";
    static public String IconoFlechaIzquierda = "atras.png";
    static public String IconoJugadorNegro = "icono jugador.png";
    static public String IconoJugadorBlanco = "jugador.png";
    static public String musicaKahoot = "Kahoot.wav";

    public static String IconoFlechaDerechaRuta() { return Ruta + Iconos + IconoFlechaDerecha; }
    public static String IconoFlechaIzquierdaRuta() { return Ruta + Iconos + IconoFlechaIzquierda; }
    public static String IconoJugadorNegroRuta() { return Ruta + Iconos + IconoJugadorNegro; }
    public static String IconoJugadorBlancoRuta() { return Ruta + Iconos + IconoJugadorBlanco; }
    public static String MusicaKahootRuta(){return Ruta + musicaKahoot;};
}
