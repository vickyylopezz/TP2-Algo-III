package edu.fiuba.algo3;

import edu.fiuba.algo3.eventos.BotonOpcionEventHandler;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.vistas.RegistroView;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {
    private Stage stage;

    public static void main(String[] args) throws IOException, URISyntaxException {
        new Kahoot().leerArchivo();
        launch(args);
    }

    @Override
    public void start(Stage stage){
        this.stage = stage;

        Button empezar = new Button("Empezar");
        empezar.setMinSize(220, 70);
        empezar.setOnAction(e -> {
            // muestro la pantalla de usuarios
            Scene registro = pantallaDeRegistro();
            stage.setScene(registro);
        });
        StackPane layout = new StackPane(empezar);
        layout.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(layout,800,600));
        stage.show();
    }

    public Scene pantallaDeRegistro(){
        Text indicadorJugador1 = new Text("Jugador 1");
        indicadorJugador1.setFont(new Font(15));
        Text indicadorJugador2 = new Text("Jugador 2");
        indicadorJugador2.setFont(new Font(15));
        HBox indicadorJugadores = new HBox(200, indicadorJugador1, indicadorJugador2);
        indicadorJugadores.setAlignment(Pos.CENTER);

        // CAMPOS DE TEXTO
        TextField campo1 = new TextField();
        TextField campo2 = new TextField();
        HBox camposJugadores = new HBox(100, campo1, campo2);
        camposJugadores.setAlignment(Pos.CENTER);

        //BOTON
        Button inicio = new Button("Iniciar partida");
        inicio.setMinSize(220,70);
        inicio.setOnAction(e -> {
            /* ESCENA 2: Jugadores, Pregunta y Opciones */
            Jugador jugador1 = new Jugador(campo1.getText());
            Jugador jugador2 = new Jugador(campo2.getText());
            ArrayList<Jugador> jugadores = new ArrayList<>();
            jugadores.add(jugador1); jugadores.add(jugador2);

            try {
                MultipleChoiceParcial pregunta = obtenerPregunta();
                Scene escena = crearEscenaJuegoCon(pregunta, jugadores);
                cambiarEscena(escena, this.stage);
            } catch (PreguntaError error) {
                System.out.println(error);
                System.exit(1);
            }
        });

        // CONDICION DEL BOTON -----------
        BooleanBinding campo1Lleno = Bindings.createBooleanBinding(() -> {
            if (campo1.getText() == null || campo1.getText().trim().isEmpty()) {
                return false;
            }
            return true;
        }, campo1.textProperty());

        BooleanBinding campo2Lleno = Bindings.createBooleanBinding(() -> {
            if (campo2.getText() == null || campo2.getText().trim().isEmpty()) {
                return false;
            }
            return true;
        }, campo2.textProperty());

        inicio.disableProperty().bind(campo1Lleno.not().or(campo2Lleno.not()));
        // -------------------------------

        // LAYOUT GENERAL, ESCENA, STAGE
        //inicio.setShape(new Circle(100));
        VBox contenidoIniciar = new VBox(75, indicadorJugadores, camposJugadores, inicio);
        contenidoIniciar.setAlignment(Pos.CENTER);
        contenidoIniciar.setStyle("-fx-background-color: #addcff");

        return new Scene(contenidoIniciar, 800, 600);
    }

    public MultipleChoiceParcial obtenerPregunta() throws PreguntaError {
        MultipleChoiceParcial pregunta = new MultipleChoiceParcial("¿Cuál es la opción correcta?", 10);
        pregunta.agregarOpcionCorrecta("Esta si");
        pregunta.agregarOpcionIncorrecta("Esta no");
        pregunta.agregarOpcionIncorrecta("Casi, pero no");
        pregunta.agregarOpcionIncorrecta("Frío");

        return pregunta;
    }

    public Scene crearEscenaJuegoCon(MultipleChoiceParcial pregunta, ArrayList<Jugador> jugadores) {
        Text consigna = new Text(pregunta.titulo());
        Text nombre1 = new Text("Jugador 1: " + jugadores.get(0).nombre());
        Text nombre2 = new Text("Jugador 2: " + jugadores.get(1).nombre());

        Button opcion1 = new Button(pregunta.obtenerOpciones().get(0).getTitulo());
        opcion1.setOnAction(new BotonOpcionEventHandler(pregunta.obtenerOpciones().get(0)));
        Button opcion2 = new Button(pregunta.obtenerOpciones().get(1).getTitulo());
        opcion2.setOnAction(new BotonOpcionEventHandler(pregunta.obtenerOpciones().get(1)));
        Button opcion3 = new Button(pregunta.obtenerOpciones().get(2).getTitulo());
        opcion3.setOnAction(new BotonOpcionEventHandler(pregunta.obtenerOpciones().get(2)));
        Button opcion4 = new Button(pregunta.obtenerOpciones().get(3).getTitulo());
        opcion4.setOnAction(new BotonOpcionEventHandler(pregunta.obtenerOpciones().get(3)));

        GridPane grillaOpciones = new GridPane();
        grillaOpciones.setMinSize(600,600);
        grillaOpciones.addRow(0, nombre1, nombre2);
        grillaOpciones.addRow(1, consigna);
        grillaOpciones.addRow(2, opcion1, opcion2);
        grillaOpciones.addRow(3, opcion3, opcion4);
        grillaOpciones.setVgap(20);
        grillaOpciones.setHgap(50);
        grillaOpciones.setAlignment(Pos.CENTER);
        grillaOpciones.setStyle("-fx-background-color: #f4a460");

        return (new Scene(grillaOpciones, 800,600));
    }



    public void cambiarEscena(Scene scene, Stage stage){
        stage.setScene(scene);
    }

}