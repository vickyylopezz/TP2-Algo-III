package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.IniciarJuegoControlador;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import edu.fiuba.algo3.vista.componentes.JugadorVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaDerechaVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaIzquierdaVista;
import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaVista;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class App extends Application {
    private Stage escenario;
    private Juego juego;
    private Jugador[] jugadores = new Jugador[2];
    private Media musica;
    private MediaPlayer reproductor;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, PreguntaError {
        this.escenario = stage;
        this.escenario.setTitle("Kahoot");
        this.escenario.setScene(this.iniciarJuegoEscena());
        this.comenzarMusica();
        this.escenario.show();

        //Escena crearJuego = new CrearJuegoVista();
        //Escena cargadorPreguntas = new CargarPreguntasVista();
        //Escena iniciarJuego = new IniciarJuegoVista();
        //Escena registrarJugadores = new RegistrarJugadoresVista();
        //Escena puntosobtenidos = new PuntosObtenidosVista();
        //Escena ganador = new GanadorVista();

        //Jugada jugada = new Jugada(new VerdaderoFalsoConPenalidad("Estamos en el año 2021"), new Jugador("Carlos"));
        //Escena previaPregunta = new PreviaPreguntaVista(jugada);

        //stage.setScene(crearJuego.obtenerEscena());
        //stage.setScene(cargadorPreguntas.obtenerEscena());
        //stage.setScene(iniciarJuego.obtenerEscena());
        //stage.setScene(registrarJugadores.obtenerEscena());
        //stage.setScene(puntosobtenidos.obtenerEscena());
        //stage.setScene(previaPregunta.obtenerEscena());
        //stage.setScene(ganador.obtenerEscena());
        //stage.show();

        //CargarPreguntaControlador preguntasControlador = new CargarPreguntaControlador(stage);
        //preguntasControlador.mostrarVista();

        //IniciarJuegoControlador iniciarJuegoControlador = new IniciarJuegoControlador(stage);
        //iniciarJuegoControlador.mostrarVista();
        //comenzarMusica();

    }

    private Scene iniciarJuegoEscena() {
        //Botones

        BotonEtiquetaIzquierdaVista botonCargarPreguntas = new BotonEtiquetaIzquierdaVista("CARGAR PREGUNTAS");
        //botonCargarPreguntas.setOnAction((event) -> this.cargarPreguntasEscena());

        BotonCuadradoVista botonIniciar = new BotonCuadradoVista("INICIAR");
        botonIniciar.setOnAction((event) -> this.escenario.setScene(resgistrarJugadoresEscena()));

        //Contenedor
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista(null, botonCargarPreguntas));
        contenedorPrincipal.setCenter(botonIniciar);

        return new Scene(contenedorPrincipal,800,600);
    }

    private Scene resgistrarJugadoresEscena() {
        //XBox
        HBox contenedor = new HBox();
        contenedor.setAlignment(Pos.TOP_CENTER);
        JugadorVista jugadorVista1 = new JugadorVista("JUGADOR 1");
        JugadorVista jugadorVista2 = new JugadorVista("JUGADOR 2");
        contenedor.getChildren().addAll(jugadorVista1.obtenerNodo(), jugadorVista2.obtenerNodo());

        //Botones
        BotonEtiquetaDerechaVista botonInicio = new BotonEtiquetaDerechaVista("INICIO");
        botonInicio.setOnAction((event) -> this.escenario.setScene(iniciarJuegoEscena()));

        BotonEtiquetaIzquierdaVista botonConfirmar = new BotonEtiquetaIzquierdaVista("CONFIRMAR");
        botonConfirmar.setOnAction((event) -> this.confirmarJugador(jugadorVista1,jugadorVista2));

        //Contenedor
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista(botonInicio,botonConfirmar));
        contenedorPrincipal.setCenter(contenedor);

        return new Scene(contenedorPrincipal, 800, 600);

    }

    private void confirmarJugador(JugadorVista jugadorVista1, JugadorVista jugadorVista2){
        if (jugadorVista1.esInvalido()){
            jugadorVista1.validarJugadorVista();
        } else {
            jugadorVista1.confirmarJugadorVista();
        }

        if (jugadorVista2.esInvalido()){
            jugadorVista2.validarJugadorVista();
        } else {
            jugadorVista2.confirmarJugadorVista();
        }

        if(!jugadorVista1.esInvalido() && !jugadorVista2.esInvalido()){
            this.escenario.setScene(juegoEscena());
        }
    }


    private Scene juegoEscena() {
        //Botones
        BotonEtiquetaDerechaVista botonJugadores = new BotonEtiquetaDerechaVista("JUGADORES");
        //set on action

        BotonCuadradoVista botonIniciar = new BotonCuadradoVista("Iniciar Juego");

        //Contenedor
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista( botonJugadores,null));
        contenedorPrincipal.setCenter(botonIniciar);

        return new Scene(contenedorPrincipal, 800, 600);
    }

    public void comenzarMusica(){
        this.musica = new Media(new File("src/main/resources/Kahoot.wav").toURI().toString());
        this.reproductor = new MediaPlayer(musica);

        this.reproductor.setOnEndOfMedia(new Runnable() {
            public void run() {
                reproductor.seek(Duration.ZERO);
            }
        });
        this.reproductor.play();
    }



}