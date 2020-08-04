package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.juego.Pregunta;
import edu.fiuba.algo3.modelo.preguntas.multipleChoice.MultipleChoiceClasico;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class MultipleChoiceClasicoTest {
    //
    // --------------------------- Test unitarios ---------------------------
    //

    // Test implementacion
    @Test
    public void multipleChoiceClasicoImplementaInterfacePregunta() {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        assertTrue(pregunta instanceof Pregunta);
    }

    // Test obtenerTitulo
    @Test
    public void creacionMultipleChoiceConTituloObtenerTituloLoDevuelve() {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        assertEquals("TITULO PREGUNTA", pregunta.obtenerTitulo());
    }

    // Test obtenerOpciones
    @Test
    public void obtenerOpcionesSinHaberAgregadoOpcionesDevuelveArrayVacio() {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertTrue(opciones.isEmpty());
    }

    @Test
    public void obtenerOpcionesDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(5, opciones.size());
    }

    @Test
    public void obtenerOpcionesLasDevuelveEnElOrdenGuardado() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");
        pregunta.agregarOpcionCorrecta("OPCCOR4");
        pregunta.agregarOpcionIncorrecta("OPCINC5");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("OPCCOR1", opciones.get(0).obtenerTitulo());
        assertEquals("OPCINC2", opciones.get(1).obtenerTitulo());
        assertEquals("OPCINC3", opciones.get(2).obtenerTitulo());
        assertEquals("OPCCOR4", opciones.get(3).obtenerTitulo());
        assertEquals("OPCINC5", opciones.get(4).obtenerTitulo());
    }

    // Test agregarOpcionIncorrecta
    @Test
    public void agregarOpcionIncorrectaGuardaLaOpcionEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionIncorrecta("OPCION INCORRECTA");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("OPCION INCORRECTA", unicaOpcion.obtenerTitulo());
    }

    @Test
    public void agregarOpcionIncorrectaGuardaLaOpcionEnLaPreguntaConValorCero() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionIncorrecta("OPCION INCORRECTA");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(0, unicaOpcion.obtenerPunto().obtenerValor());
    }

    @Test
    public void agregarVariasOpcionesIncorrectasGuardaTodasLasOpcionesEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionIncorrecta("OPCINC1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesIncorrectasLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionIncorrecta("OPCINC1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");
        pregunta.agregarOpcionIncorrecta("OPCINC4");
        pregunta.agregarOpcionIncorrecta("OPCINC5");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionIncorrecta("NO ENTRA"));
    }

    // Test agregarOpcionCorrecta
    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("OPCCOR1", unicaOpcion.obtenerTitulo());
    }

    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPreguntaConValorUno() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(1, unicaOpcion.obtenerPunto().obtenerValor());
    }

    @Test
    public void agregarVariasOpcionesCorrectasGuardaTodasLasOpcionesEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionCorrecta("OPCCOR2");
        pregunta.agregarOpcionCorrecta("OPCCOR3");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesCorrectasLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionCorrecta("OPCCOR2");
        pregunta.agregarOpcionCorrecta("OPCCOR3");
        pregunta.agregarOpcionCorrecta("OPCCOR4");
        pregunta.agregarOpcionCorrecta("OPCCOR5");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionCorrecta("NO ENTRA"));
    }

    // Test agregarOpcionesCorrecta y agregarOpcionesIncorrecta
    @Test
    public void agregarVariasOpcionesCorrectasEIncorrectasGuardaTodasLasOpcionesEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");
        pregunta.agregarOpcionCorrecta("OPCCOR4");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(4, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesSextaOpcionCorrectaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");
        pregunta.agregarOpcionCorrecta("OPCCOR4");
        pregunta.agregarOpcionIncorrecta("OPCINC5");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionCorrecta("NO ENTRA"));
    }

    @Test
    public void agregarMasDeCincoOpcionesSextaOpcionIncorrectaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");
        pregunta.agregarOpcionCorrecta("OPCCOR4");
        pregunta.agregarOpcionIncorrecta("OPCINC5");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionIncorrecta("NO ENTRA"));
    }

    // Test puntajeConOpciones
    @Test
    public void puntajeSeleccionandoTodasLasOpcionesCorrectasPuntajeUno() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");
        pregunta.agregarOpcionCorrecta("OPCCOR4");
        pregunta.agregarOpcionIncorrecta("OPCINC5");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        ArrayList<Opcion> seleccionadas = new ArrayList<>();

        seleccionadas.add(opciones.get(0)); //OPCCOR1
        seleccionadas.add(opciones.get(3)); //OPCCOR4

        Punto puntaje = pregunta.puntajeConOpciones(seleccionadas);

        assertEquals(1, puntaje.obtenerValor());
    }

    @Test
    public void puntajeSeleccionandoTodasLasOpcionesCorrectasYUnaIncorrectaDevuelveCero() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");
        pregunta.agregarOpcionCorrecta("OPCCOR4");
        pregunta.agregarOpcionIncorrecta("OPCINC5");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        ArrayList<Opcion> seleccionadas = new ArrayList<>();

        seleccionadas.add(opciones.get(0));
        seleccionadas.add(opciones.get(3));
        seleccionadas.add(opciones.get(2));

        Punto puntaje = pregunta.puntajeConOpciones(seleccionadas);

        assertEquals(0, puntaje.obtenerValor());
    }

    @Test
    public void puntajeSeleccionandoUnaSolaOpcionCorrectaDevueveCero() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("TITULO PREGUNTA");

        pregunta.agregarOpcionCorrecta("OPCCOR1");
        pregunta.agregarOpcionIncorrecta("OPCINC2");
        pregunta.agregarOpcionIncorrecta("OPCINC3");
        pregunta.agregarOpcionCorrecta("OPCCOR4");
        pregunta.agregarOpcionIncorrecta("OPCINC5");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        ArrayList<Opcion> seleccionadas = new ArrayList<>();

        seleccionadas.add(opciones.get(0));

        Punto puntaje = pregunta.puntajeConOpciones(seleccionadas);

        assertEquals(0, puntaje.obtenerValor());
    }

    /*
    // Test constructor
    @Test
    public void creacionMultipleChoiceConSegundosNegativosLanzaMultipleChoiceError() {
        assertThrows(PreguntaError.class, () -> new MultipleChoiceClasico("¿Estamos en Algo 3?", -1));
    }

    @Test
    public void creacionMultipleChoiceConSegundosCeroNoLanzaMultipleChoiceError() {
        assertDoesNotThrow(() -> {
            new MultipleChoiceClasico("¿Estamos en Algo 3?", 0);
        });
    }

    @Test
    public void creacionMultipleChoiceConSegundosMayoresACeroNoLanzaMultipleChoiceError() {
        assertDoesNotThrow(() -> {
            new MultipleChoiceClasico("¿Estamos en Algo 3?", 5);
        });
    }

    // Test obtenerSegundos
    @Test
    public void creacionMultipleChoiceConSegundosObtenerSegundosLoDevuelve() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 5);

        assertEquals(5, pregunta.obtenerSegundos());
    }
    */
}
