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
    static public String musicaKahoot = "sonidos/Kahoot.wav";
    static public String musicaPreguntaJ1 = "sonidos/pregunta1.wav";
    static public String musicaPreguntaJ2 = "sonidos/pregunta2.wav";
    static public String musicaRespuesta = "sonidos/gong.wav";
    static public String IconoMute = "mute.png";
    static public String IconoSonido = "sonido.png";
    static public String IconoKahoot = "icono aplicacion.png";
    static public String FondoPrincipal = "fondo.png";

    public static String IconoFlechaDerechaRuta() { return Ruta + Iconos + IconoFlechaDerecha; }
    public static String IconoFlechaIzquierdaRuta() { return Ruta + Iconos + IconoFlechaIzquierda; }
    public static String IconoJugadorBlancoRuta() { return Ruta + Iconos + IconoJugadorBlanco; }
    public static String MusicaKahootRuta(){return Ruta + musicaKahoot;}
    public static String MusicaPreguntaJ1Ruta(){return Ruta + musicaPreguntaJ1;}
    public static String MusicaPreguntaJ2Ruta(){return Ruta + musicaPreguntaJ2;}
    public static String MusicaRespuestaRuta(){return Ruta + musicaRespuesta;}
    public static String IconoMuteRuta(){return Ruta + Iconos + IconoMute;}
    public static String IconoSonidoRuta(){return Ruta + Iconos + IconoSonido;}
    public static String IconoKahootRuta(){return Ruta + Iconos + IconoKahoot;}

    public static String DataPreguntas = "Preguntas.json";
    public static String RutaDataPreguntas() { return Ruta + DataPreguntas; }

    public static String FondoPrincipalRuta() { return "file:" + Ruta + FondoPrincipal; }
}
