package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.juego.opcion.OpcionGroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.GroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GroupChoiceTest {
    @Test
    public void GroupChoiceAsignaPuntosAJugador() throws PreguntaError, RespuestaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Carlos");
        pregunta.agregarOpcion(grupos.get(0),"Marta");
        pregunta.agregarOpcion(grupos.get(0),"Paula");

        pregunta.agregarOpcion(grupos.get(1),"Uno");
        pregunta.agregarOpcion(grupos.get(1),"Dos");

        Jugador jugador = new Jugador("Florencia");
        ArrayList<OpcionGroupChoice> opciones = pregunta.obtenerOpciones();

        pregunta.iniciar(jugador);
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opciones.get(0));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opciones.get(1));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opciones.get(2));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opciones.get(3));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opciones.get(4));

        Respuesta respuesta = pregunta.confirmar();
        assertEquals(respuesta.obtenerPuntaje().getValor(),3);

    }

    @Test
    public void GroupChoicePuedeCrearseConMaximoDeSeisOpciones() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice();
        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");
        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();

        pregunta.agregarOpcion(grupos.get(0),"Carlos");
        pregunta.agregarOpcion(grupos.get(0),"Marta");
        pregunta.agregarOpcion(grupos.get(0),"Paula");
        pregunta.agregarOpcion(grupos.get(1),"Uno");
        pregunta.agregarOpcion(grupos.get(1),"Dos");
        pregunta.agregarOpcion(grupos.get(1),"Tres");

        assertThrows(PreguntaError.class, ()-> pregunta.agregarOpcion(grupos.get(1),"Frula"));
    }

    @Test
    public void GroupChoicePuedeCrearseIndicandoleLosGruposYOpciones() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice();
        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");
        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();

        pregunta.agregarOpcion(grupos.get(0),"Carlos");
        pregunta.agregarOpcion(grupos.get(0),"Marta");
        pregunta.agregarOpcion(grupos.get(0),"Paula");
        pregunta.agregarOpcion(grupos.get(1),"Uno");
        pregunta.agregarOpcion(grupos.get(1),"Dos");

        assertEquals(pregunta.obtenerOpciones().size(),5);

    }

    @Test
    public void GroupChoicePuedeCrearseConMaximoDosGrupos() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice();
        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");

        assertThrows(PreguntaError.class, ()-> pregunta.definirGrupo("Otro Grupo"));
    }

    @Test
    public void GroupChoicePuedeCrearseIndicandoleLosGrupos() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice();
        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");

        assertEquals(pregunta.obtenerGrupos().size(),2);

    }

    @Test
    public void GroupChoiceAsignaPuntosAJugadores() throws PreguntaError, RespuestaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Colores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Azul");
        pregunta.agregarOpcion(grupos.get(0),"Rojo");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        Jugador pedro = new Jugador("Pedro");
        ArrayList<OpcionGroupChoice> opcionesPedro = pregunta.obtenerOpciones();
        pregunta.iniciar(pedro);
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(0));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(1));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(2));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(3));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(4));
        Respuesta respuestaPedro = pregunta.confirmar();

        Jugador carlos = new Jugador("Carlos");
        ArrayList<OpcionGroupChoice> opcionesCarlos = pregunta.obtenerOpciones();
        pregunta.iniciar(carlos);
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesCarlos.get(0));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesCarlos.get(1));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesCarlos.get(2));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesCarlos.get(3));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesCarlos.get(4));
        Respuesta respuestaCarlos = pregunta.confirmar();

        assertEquals(respuestaPedro.obtenerPuntaje().getValor(),3);
        assertEquals(respuestaCarlos.obtenerPuntaje().getValor(),2);

    }

    @Test
    public void GroupChoiceSoloSePuedenSeleccionarComoMaximoSeisOpciones() throws PreguntaError, RespuestaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Colores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Azul");
        pregunta.agregarOpcion(grupos.get(0),"Rojo");
        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        Jugador jugador = new Jugador("Maxi");
        ArrayList<OpcionGroupChoice> opciones = pregunta.obtenerOpciones();
        pregunta.iniciar(jugador);
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opciones.get(0));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opciones.get(1));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opciones.get(2));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opciones.get(3));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opciones.get(4));

        assertThrows(RespuestaError.class, ()-> pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opciones.get(4)));
    }

    @Test
    public void GroupChoiceNoSePuedeConfirmarSinHaberEmpezadoLaPregunta() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Colores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Azul");
        pregunta.agregarOpcion(grupos.get(0),"Rojo");
        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        Jugador jugador = new Jugador("Maxi");
        ArrayList<OpcionGroupChoice> opciones = pregunta.obtenerOpciones();
       assertThrows(PreguntaError.class, pregunta::confirmar);

    }

    @Test
    public void GroupChoiceNoSePuedeSeleccionarUnaOpcionSinHaberIniciado() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Colores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Azul");
        pregunta.agregarOpcion(grupos.get(0),"Rojo");
        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        Jugador jugador = new Jugador("Maxi");
        ArrayList<OpcionGroupChoice> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class,()->pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opciones.get(3)));

    }

    @Test
    public void GroupChoiceNoSePuedeIniciarSinJugador() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Colores");
        pregunta.definirGrupo("Animales");
        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Azul");
        pregunta.agregarOpcion(grupos.get(0),"Rojo");
        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        assertThrows(PreguntaError.class,()->pregunta.iniciar(null));

    }

    @Test
    public void GroupChoiceNoSePuedeIniciarSiHayOtroJUgadorJugando() throws PreguntaError, RespuestaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Colores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Azul");
        pregunta.agregarOpcion(grupos.get(0),"Rojo");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        Jugador pedro = new Jugador("Pedro");
        ArrayList<OpcionGroupChoice> opcionesPedro = pregunta.obtenerOpciones();
        pregunta.iniciar(pedro);
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(0));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(1));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(2));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(3));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(4));

        Jugador carlos = new Jugador("Carlos");
        ArrayList<OpcionGroupChoice> opcionesCarlos = pregunta.obtenerOpciones();
        assertThrows(PreguntaError.class,()->pregunta.iniciar(carlos));
    }

    @Test
    public void GroupChoiceNoSeAsignanPuntosAJugadorQueRespondeTodasMal() throws PreguntaError, RespuestaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Flores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Margarita");
        pregunta.agregarOpcion(grupos.get(0),"Rosa");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        Jugador pedro = new Jugador("Pedro");
        ArrayList<OpcionGroupChoice> opcionesPedro = pregunta.obtenerOpciones();
        pregunta.iniciar(pedro);
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(0));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(1));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(2));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(3));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(4));

        Respuesta respuesta = pregunta.confirmar();

        assertEquals(0,respuesta.obtenerPuntaje().getValor());

    }

    @Test
    public void GroupChoiceAsignanTodosLosPuntosAJugadorQueRespondeTodasBien() throws PreguntaError, RespuestaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Flores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Margarita");
        pregunta.agregarOpcion(grupos.get(0),"Rosa");
        pregunta.agregarOpcion(grupos.get(0),"Jazmin");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        Jugador pedro = new Jugador("Pedro");
        ArrayList<OpcionGroupChoice> opcionesPedro = pregunta.obtenerOpciones();
        pregunta.iniciar(pedro);
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(0));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(1));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(0),opcionesPedro.get(2));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(3));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(4));
        pregunta.seleccionarOpcionEnGrupo(grupos.get(1),opcionesPedro.get(5));

        Respuesta respuesta = pregunta.confirmar();

        assertEquals(6,respuesta.obtenerPuntaje().getValor());

    }

    @Test
    public void GroupChoiceNoSePuedeIniciarConUnSoloGrupo() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice();

        pregunta.definirGrupo("Flores");
        Jugador jugador = new Jugador("Marcos");

        assertThrows(PreguntaError.class, ()->pregunta.iniciar(jugador));

    }
}
