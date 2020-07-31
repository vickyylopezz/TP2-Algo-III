package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.juego.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GroupChoiceTest {
    @Test
    public void GroupChoiceCreaGrupo() throws PreguntaError, RespuestaError {
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
}
