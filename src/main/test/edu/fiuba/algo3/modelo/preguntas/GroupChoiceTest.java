package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.punto.PuntoError;
import edu.fiuba.algo3.modelo.preguntas.opcion.Grupo;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupChoiceTest {

    @Test
    public void GroupChoiceAsignaPuntosAJugador() throws PreguntaError, PuntoError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Carlos");
        pregunta.agregarOpcion(grupos.get(0),"Marta");
        pregunta.agregarOpcion(grupos.get(0),"Paula");

        pregunta.agregarOpcion(grupos.get(1),"Uno");
        pregunta.agregarOpcion(grupos.get(1),"Dos");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        opcionesElegidas.add(opciones.get(1));
        opcionesElegidas.add(opciones.get(2));
        opcionesElegidas.add(opciones.get(4));
        opcionesElegidas.add(opciones.get(6));
        opcionesElegidas.add(opciones.get(9));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidas).obtenerValor(),3);

    }

    @Test
    public void GroupChoicePuedeCrearseConMaximoDeSeisOpciones() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");
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
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");
        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");
        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();

        pregunta.agregarOpcion(grupos.get(0),"Carlos");
        pregunta.agregarOpcion(grupos.get(0),"Marta");
        pregunta.agregarOpcion(grupos.get(0),"Paula");
        pregunta.agregarOpcion(grupos.get(1),"Uno");
        pregunta.agregarOpcion(grupos.get(1),"Dos");

        assertEquals(pregunta.obtenerOpciones().size(),10);

    }

    @Test
    public void GroupChoicePuedeCrearseConMaximoDosGrupos() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");
        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");

        assertThrows(PreguntaError.class, ()-> pregunta.definirGrupo("Otro Grupo"));
    }

    @Test
    public void GroupChoicePuedeCrearseIndicandoleLosGrupos() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");
        pregunta.definirGrupo("Nombres");
        pregunta.definirGrupo("Numeros");

        assertEquals(pregunta.obtenerGrupos().size(),2);

    }

    @Test
    public void GroupChoiceAsignaPuntosAJugadores() throws PreguntaError, PuntoError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        pregunta.definirGrupo("Colores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Azul");
        pregunta.agregarOpcion(grupos.get(0),"Rojo");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesJugador1 = new ArrayList<>();
        opcionesJugador1.add(opciones.get(0));
        opcionesJugador1.add(opciones.get(2));
        opcionesJugador1.add(opciones.get(5));
        opcionesJugador1.add(opciones.get(6));
        opcionesJugador1.add(opciones.get(9));

        ArrayList<Opcion> opcionesJugador2 = new ArrayList<>();
        opcionesJugador2.add(opciones.get(1));
        opcionesJugador2.add(opciones.get(3));
        opcionesJugador2.add(opciones.get(4));
        opcionesJugador2.add(opciones.get(7));
        opcionesJugador2.add(opciones.get(8));

        assertEquals(pregunta.puntajeConOpciones(opcionesJugador1).obtenerValor(),3);
        assertEquals(pregunta.puntajeConOpciones(opcionesJugador2).obtenerValor(),2);

    }

    @Test
    public void GroupChoiceNoSeAsignanPuntosAJugadorQueRespondeTodasMal() throws PreguntaError, PuntoError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        pregunta.definirGrupo("Flores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Margarita");
        pregunta.agregarOpcion(grupos.get(0),"Rosa");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        opcionesElegidas.add(opciones.get(1));
        opcionesElegidas.add(opciones.get(3));
        opcionesElegidas.add(opciones.get(5));
        opcionesElegidas.add(opciones.get(7));
        opcionesElegidas.add(opciones.get(9));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidas).obtenerValor(),0);

    }

    @Test
    public void GroupChoiceAsignanTodosLosPuntosAJugadorQueRespondeTodasBien() throws PreguntaError, PuntoError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        pregunta.definirGrupo("Flores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Margarita");
        pregunta.agregarOpcion(grupos.get(0),"Rosa");
        pregunta.agregarOpcion(grupos.get(0),"Jazmin");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesElegidas = new ArrayList<>();
        opcionesElegidas.add(opciones.get(0));
        opcionesElegidas.add(opciones.get(2));
        opcionesElegidas.add(opciones.get(4));
        opcionesElegidas.add(opciones.get(6));
        opcionesElegidas.add(opciones.get(8));

        assertEquals(pregunta.puntajeConOpciones(opcionesElegidas).obtenerValor(),5);

    }

    @Test
    public void GroupChoiceSinPenalidadDevuelveFalseEnPenalidad(){
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        assertFalse(pregunta.conPenalidad());
    }

    @Test
    public void GroupChoiceOpcionesCorrectasSonLasAgregadas() throws PreguntaError, PuntoError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        pregunta.definirGrupo("Flores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();
        pregunta.agregarOpcion(grupos.get(0),"Margarita");
        pregunta.agregarOpcion(grupos.get(0),"Rosa");
        pregunta.agregarOpcion(grupos.get(0),"Jazmin");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> opcionesCorrectas = new ArrayList<>();
        opcionesCorrectas.add(opciones.get(0));
        opcionesCorrectas.add(opciones.get(2));
        opcionesCorrectas.add(opciones.get(4));
        opcionesCorrectas.add(opciones.get(6));
        opcionesCorrectas.add(opciones.get(8));
        opcionesCorrectas.add(opciones.get(10));

        assertTrue(pregunta.opcionesCorrectas(opcionesCorrectas));

    }

    @Test
    public void opcionesSeleccionablesSinSeleccionarNingunaDevuelveTodasLasOpciones() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        pregunta.definirGrupo("Flores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();

        pregunta.agregarOpcion(grupos.get(0),"Margarita");
        pregunta.agregarOpcion(grupos.get(0),"Rosa");
        pregunta.agregarOpcion(grupos.get(0),"Jazmin");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(opciones, pregunta.opcionesSeleccionables(new ArrayList<>()));
    }

    @Test
    public void opcionesSeleccionablesSeleccionandoUnaDevuelveTodasLasOpcionesMenosDos() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        pregunta.definirGrupo("Flores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();

        pregunta.agregarOpcion(grupos.get(0),"Margarita");
        pregunta.agregarOpcion(grupos.get(0),"Rosa");
        pregunta.agregarOpcion(grupos.get(0),"Jazmin");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> seleccionadas = new ArrayList<>();
        seleccionadas.add(opciones.get(0));

        ArrayList<Opcion> opcionesValidas = pregunta.opcionesSeleccionables(seleccionadas);

        // 10 = (6 opciones - 1 seleccionada) * 2 grupos
        assertEquals(10, opcionesValidas.size());
        assertFalse(opcionesValidas.contains(opciones.get(0)));
        assertFalse(opcionesValidas.contains(opciones.get(1)));
    }

    @Test
    public void opcionesSeleccionablesSeleccionandoTodasDevuelveArrayVacio() throws PreguntaError {
        GroupChoice pregunta = new GroupChoice("Seleecione la opcion correcta de cada grupo");

        pregunta.definirGrupo("Flores");
        pregunta.definirGrupo("Animales");

        ArrayList<Grupo> grupos = pregunta.obtenerGrupos();

        pregunta.agregarOpcion(grupos.get(0),"Margarita");
        pregunta.agregarOpcion(grupos.get(0),"Rosa");
        pregunta.agregarOpcion(grupos.get(0),"Jazmin");

        pregunta.agregarOpcion(grupos.get(1),"Perro");
        pregunta.agregarOpcion(grupos.get(1),"Gato");
        pregunta.agregarOpcion(grupos.get(1),"Conejo");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        ArrayList<Opcion> seleccionadas = new ArrayList<>();
        seleccionadas.add(opciones.get(0));
        seleccionadas.add(opciones.get(3));
        seleccionadas.add(opciones.get(4));
        seleccionadas.add(opciones.get(6));
        seleccionadas.add(opciones.get(9));
        seleccionadas.add(opciones.get(10));

        ArrayList<Opcion> opcionesValidas = pregunta.opcionesSeleccionables(seleccionadas);

        assertEquals(0, opcionesValidas.size());
    }

}
