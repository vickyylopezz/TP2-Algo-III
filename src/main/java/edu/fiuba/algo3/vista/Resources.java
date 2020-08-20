package edu.fiuba.algo3.vista;

public class Resources {
    static public String Ruta = "src/main/resources/";

    static public String Logos = "logos/";
    static public String LogoPrincipal = "logo.png";

    public static String LogoPrincipalRuta() { return Ruta + Logos + LogoPrincipal; }

    static public String Iconos = "iconos/";
    static public String IconoFlechaDerecha = "adelante.png";
    static public String IconoFlechaIzquierda = "atras.png";
    static public String IconoJugadorBlanco = "jugador.png";
    static public String musicaKahoot = "Kahoot.wav";
    static public String IconoMute = "mute.png";
    static public String IconoSonido = "sonido.png";
    static public String FondoLadrillo = "fondo_ladrillo.png";

    public static String IconoFlechaDerechaRuta() { return Ruta + Iconos + IconoFlechaDerecha; }
    public static String IconoFlechaIzquierdaRuta() { return Ruta + Iconos + IconoFlechaIzquierda; }
    public static String IconoJugadorBlancoRuta() { return Ruta + Iconos + IconoJugadorBlanco; }
    public static String MusicaKahootRuta(){return Ruta + musicaKahoot;}
    public static String IconoMuteRuta(){return Ruta + Iconos + IconoMute;}
    public static String IconoSonidoRuta(){return Ruta + Iconos + IconoSonido;}

    public static String DataPreguntas = "Preguntas.json";
    public static String RutaDataPreguntas() { return Ruta + DataPreguntas; }

    public static String FondoPrincipalRuta() { return Ruta + Iconos + FondoLadrillo; }
}
