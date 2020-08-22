package edu.fiuba.algo3.kahoot;

import edu.fiuba.algo3.eventos.kahoot.KahootCambioModoEventHandler;
import edu.fiuba.algo3.eventos.kahoot.KahootReiniciarEventHandler;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.componentes.ContenedorSonido;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

public class Kahoot {

    private final Stage stage;
    private StackPane raiz;
    private ArrayList<Pregunta> preguntas;
    private ArrayList<Jugador> jugadores;
    private MediaPlayer reproductor;

    public Kahoot(Stage stage) {
        this.stage = stage;

        this.reproductor = new MediaPlayer(
                new Media(new File(Resources.MusicaKahootRuta()).toURI().toString())
        );
        this.reproductor.setOnEndOfMedia(() -> reproductor.seek(Duration.ZERO));
        this.reproductor.play();

        this.raiz = this.crearEscena();
        this.preguntas = new ArrayList<>();
        this.jugadores = new ArrayList<>();
    }

    public void iniciar() {
        KahootModo modoPreparacion = new KahootPreparacion(
                this.stage, this.raiz, this.preguntas, this.jugadores
        );
        KahootJuego modoJuego = new KahootJuego(
                this.stage, this.raiz, this.preguntas, this.jugadores, this.reproductor
        );
        KahootModo modoRespuestas = new KahootResultados(
                this.stage, this.raiz, this.preguntas, this.jugadores
        );

        modoPreparacion.cuandoFinaliceEjecutar(new KahootCambioModoEventHandler(modoJuego));
        modoJuego.cuandoFinaliceEjecutar(new KahootCambioModoEventHandler(modoRespuestas));
        modoRespuestas.cuandoFinaliceEjecutar(e -> {
            this.reproductor = modoJuego.getReproductor();
            new KahootReiniciarEventHandler(this).handle(null);
        });

        modoPreparacion.iniciar();
        this.stage.show();
    }

    private StackPane crearEscena() {
        ContenedorSonido reproductorVista = new ContenedorSonido(this.reproductor);
        reproductorVista.setAlignment(Pos.BOTTOM_RIGHT);
        reproductorVista.setPickOnBounds(false);

        StackPane raiz = new StackPane();

        this.stage.setTitle("Kahoot");
        this.stage.setMinWidth(1280);
        this.stage.setMinHeight(720);
        this.stage.setScene(new Scene(new StackPane(raiz, reproductorVista)));
        this.stage.setMaximized(true);

        return raiz;
    }

    public void reiniciar() {
        this.preguntas = new ArrayList<>();
        this.jugadores = new ArrayList<>();

        this.iniciar();
    }
}
