package edu.fiuba.algo3;

import edu.fiuba.algo3.eventos.BajarPreguntaEventHandler;
import edu.fiuba.algo3.eventos.BorrarPreguntaEventHanlder;
import edu.fiuba.algo3.eventos.SubirPreguntaEventHandler;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.juego.Jugada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.verdaderoFalso.VerdaderoFalsoConPenalidad;
import edu.fiuba.algo3.vista.Resources;
import edu.fiuba.algo3.vista.Tema;
import edu.fiuba.algo3.vista.componentes.JugadorVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonCuadradoVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaDerechaVista;
import edu.fiuba.algo3.vista.componentes.botones.BotonEtiquetaIzquierdaVista;
import edu.fiuba.algo3.vista.componentes.cabeceras.CabeceraKahootVista;
import edu.fiuba.algo3.vista.componentes.preguntas.PreguntaBarraVista;
import edu.fiuba.algo3.vista.escenas.juego.PreviaPreguntaVista;
import edu.fiuba.algo3.vista.escenas.juego.PuntosObtenidosVista;
import edu.fiuba.algo3.vista.componentes.textos.MiniTexto;
import edu.fiuba.algo3.vista.escenas.postjuego.GanadorVista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.geometry.Pos.*;
import static javafx.geometry.Pos.CENTER;

public class App extends Application {
    private Stage escenario;
    private Juego juego;
    private Jugador[] jugadores = new Jugador[2];
    private ArrayList<Pregunta> preguntas = new ArrayList<>();
    private Media musica;
    private MediaPlayer reproductor;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.escenario = stage;
        this.escenario.setTitle("Kahoot");
        this.escenario.setScene(this.iniciarJuegoEscena());
        this.comenzarMusica();
        this.escenario.show();

        //Escena crearJuego = new CrearJuegoVista();
        //Escena cargadorPreguntas = new CargarPreguntasVista();
        //Escena iniciarJuego = new IniciarJuegoVista();
        //Escena registrarJugadores = new RegistrarJugadoresVista();
        //PuntosObtenidosVista puntosobtenidos = new PuntosObtenidosVista();
        //PreviaPreguntaVista previaPregunta = new PreviaPreguntaVista(jugada);
        //Escena ganador = new GanadorVista();

        //Jugada jugada = new Jugada(new VerdaderoFalsoConPenalidad("Estamos en el año 2021"), new Jugador("Carlos"));
        //this.escenario.setScene(this.previaPreguntaEscena(jugada));
        //this.escenario.setScene(this.resultadosEscena());
        //this.escenario.show();

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
        botonCargarPreguntas.setOnAction((event) -> this.escenario.setScene(this.seleccionarPreguntasEscena()));

        BotonCuadradoVista botonIniciar = new BotonCuadradoVista("INICIAR");
        botonIniciar.setOnAction((event) -> this.escenario.setScene(resgistrarJugadoresEscena()));

        //Contenedor
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista(null, botonCargarPreguntas));
        contenedorPrincipal.setCenter(botonIniciar);

        return new Scene(contenedorPrincipal,800,600);
    }

    private Scene seleccionarPreguntasEscena() {
        //XBox
        StackPane contenedor = new StackPane();

        VBox caja = new VBox();
        String estilo = "-fx-border-radius: 2;";
        estilo += "-fx-background-radius: 2;";
        estilo += "-fx-border-width: 1;";
        estilo += "-fx-border-color: #999;";
        estilo += "-fx-background-color: white;";
        caja.setStyle(estilo);

        //Cuerpo caja
        VBox listadoPreguntas = new VBox();
        listadoPreguntas.setAlignment(Pos.TOP_CENTER);

        this.actualizarPreguntas(listadoPreguntas);

        ScrollPane cuerpoCaja = new ScrollPane();
        cuerpoCaja.setContent(listadoPreguntas);
        cuerpoCaja.setStyle("-fx-background-color: white;-fx-background: white");
        cuerpoCaja.setFitToHeight(true);
        cuerpoCaja.setFitToWidth(true);
        cuerpoCaja.setPrefHeight(400);

        //Cabecera Caja
        HBox cabecera = new HBox();

        cabecera.setStyle("-fx-border-width: 0 0 1 0;-fx-border-color: #999");
        cabecera.setPadding(new Insets(0, 10, 0, 10));
        cabecera.setPrefHeight(60);
        cabecera.setAlignment(Pos.CENTER_LEFT);

        Label tituloIzuiquirda = new Label("Agrege las Preguntas");
        tituloIzuiquirda.setStyle("-fx-font-weight: bold;-fx-font-size: 20;-fx-text-fill: #999;");

        Pane separador = new Pane();
        HBox.setHgrow(separador, Priority.ALWAYS);

        BotonCuadradoVista botonCargarJson = new BotonCuadradoVista("Cargar Json");
        botonCargarJson.setOnAction((evento) -> {
            FileChooser seleccionadorArchivos = new FileChooser();
            seleccionadorArchivos.getExtensionFilters().add(new FileChooser.ExtensionFilter("Json", "*.json"));
            File archivo = seleccionadorArchivos.showOpenDialog(this.escenario);

            Lector lector = new Lector();
            try { lector.extraerPreguntas(archivo); }
            catch (IOException | PreguntaError e) { e.printStackTrace(); }

            this.preguntas.addAll(lector.obtenerPreguntas());
            this.actualizarPreguntas(listadoPreguntas);
        });

        cabecera.getChildren().addAll(tituloIzuiquirda, separador, botonCargarJson);

        caja.getChildren().addAll(cabecera, cuerpoCaja);

        StackPane.setMargin(caja, new Insets(20, 20, 20, 20));
        contenedor.getChildren().add(caja);

        //Botones
        BotonEtiquetaDerechaVista botonInicio = new BotonEtiquetaDerechaVista("INICIO");
        botonInicio.setOnAction((event) -> this.escenario.setScene(iniciarJuegoEscena()));

        BotonEtiquetaIzquierdaVista botonConfirmar = new BotonEtiquetaIzquierdaVista("CONFIRMAR");
        botonConfirmar.setOnAction((event) -> this.escenario.setScene(resgistrarJugadoresEscena()));

        //Contenedor
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista(botonInicio, botonConfirmar));
        contenedorPrincipal.setCenter(contenedor);

        return new Scene(contenedorPrincipal, 800, 600);
    }

    private void actualizarPreguntas(VBox listadoPreguntas) {
        listadoPreguntas.getChildren().clear();

        for (Pregunta pregunta: this.preguntas) {
            HBox item = new HBox();
            String estilo = "-fx-border-width: 0 0 1 0";
            item.setStyle(estilo);
            item.setPadding(new Insets(10, 10, 10, 10));
            item.setSpacing(5);

            BotonCuadradoVista botonSubir = new BotonCuadradoVista("subir");
            SubirPreguntaEventHandler eventoSubir = new SubirPreguntaEventHandler(listadoPreguntas.getChildren(), item);
            botonSubir.setOnAction(eventoSubir);

            BotonCuadradoVista botonBajar = new BotonCuadradoVista("bajar");
            BajarPreguntaEventHandler eventoBajar = new BajarPreguntaEventHandler(listadoPreguntas.getChildren(), item);
            botonBajar.setOnAction(eventoBajar);

            PreguntaBarraVista barraPregunta = new PreguntaBarraVista(pregunta);
            HBox.setHgrow(barraPregunta, Priority.NEVER);

            Pane separador = new Pane();
            HBox.setHgrow(separador, Priority.ALWAYS);

            BotonCuadradoVista botonBorrar = new BotonCuadradoVista("borrar");
            BorrarPreguntaEventHanlder eventoBorrar = new BorrarPreguntaEventHanlder(listadoPreguntas.getChildren(), item);
            botonBorrar.setOnAction(eventoBorrar);

            item.getChildren().addAll(
                    botonSubir,
                    botonBajar,
                    barraPregunta,
                    separador,
                    botonBorrar
            );
            listadoPreguntas.getChildren().add(item);
        }
        if (this.preguntas.size() == 0) {
            MiniTexto texto = new MiniTexto("No hay preguntas seleccionadas");
            texto.setPadding(new Insets(10, 10, 10, 10));
            listadoPreguntas.getChildren().add(texto);
        }
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
        //set on action

        //Contenedor
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista( botonJugadores,null));
        contenedorPrincipal.setCenter(botonIniciar);

        return new Scene(contenedorPrincipal, 800, 600);
    }

    public Scene previaPreguntaEscena(Jugada jugada){
        PreviaPreguntaVista previaPregunta = new PreviaPreguntaVista(jugada);

        Label jLabelTurno = new Label("Turno de " + jugada.obtenerJugador().nombre());
        jLabelTurno.setStyle("-fx-text-fill: #9A31E1; -fx-font-size: 18; -fx-background-color: white; -fx-border-color: #9A31E1");
        jLabelTurno.setPrefHeight(70);
        jLabelTurno.setPrefWidth(200);

        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista(jLabelTurno, null));
        contenedorPrincipal.setCenter(previaPregunta.obtenerNodo());

        return new Scene(contenedorPrincipal, 800, 600);
    }

    public Scene resultadosEscena(){
        PuntosObtenidosVista puntosObtenidos = new PuntosObtenidosVista();

        BotonEtiquetaIzquierdaVista botonContinuar = new BotonEtiquetaIzquierdaVista("Continuar");
        /*
        if (juego.existePartida()) {
            //botonContinuar.setOnAction((event) -> this.escenario.setScene(this.previaPreguntaEscena(jugada)));
        } else {
            botonContinuar.setOnAction((event) -> this.escenario.setScene(this.ganadorEscena()));
        }
        */
        botonContinuar.setOnAction((event) -> this.escenario.setScene(this.ganadorEscena()));

        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista(null, botonContinuar));
        contenedorPrincipal.setCenter(puntosObtenidos.obtenerNodo());

        return new Scene(contenedorPrincipal, 800, 600);
    }

    public Scene ganadorEscena(){
        //Botones
        BotonEtiquetaDerechaVista botonResultados = new BotonEtiquetaDerechaVista("Resultados");
        botonResultados.setOnAction((event) -> this.escenario.setScene(resultadosEscena()));

        //TituloGanador
        Label tituloGanador = new Label("G A N A D O R");
        tituloGanador.setStyle("-fx-text-fill: #9463EB; -fx-font-size: 50; -fx-font-weight: bold");
        tituloGanador.setAlignment(TOP_CENTER);

        //JugadorGanador
        GanadorVista ganador = new GanadorVista();

        //XBox
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(tituloGanador,ganador.obtenerNodo());
        vbox.setAlignment(CENTER);

        //Contenedor
        BorderPane contenedorPrincipal = new BorderPane();
        contenedorPrincipal.setTop(new CabeceraKahootVista( botonResultados,null));
        contenedorPrincipal.setCenter(vbox);

        return new Scene(contenedorPrincipal, 800, 600);

    }

    public void comenzarMusica(){
        this.musica = new Media(new File(Resources.MusicaKahootRuta()).toURI().toString());
        this.reproductor = new MediaPlayer(musica);

        this.reproductor.setOnEndOfMedia(new Runnable() {
            public void run() {
                reproductor.seek(Duration.ZERO);
            }
        });
        this.reproductor.play();
    }
}