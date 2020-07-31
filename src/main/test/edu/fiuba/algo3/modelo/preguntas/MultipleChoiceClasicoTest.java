package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.PreguntaError;
import edu.fiuba.algo3.modelo.excepciones.RespuestaError;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.Opcion;
import edu.fiuba.algo3.modelo.juego.Respuesta;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultipleChoiceClasicoTest {
    //
    // --------------------------- Test unitarios ---------------------------
    //

    // Test implementacion
    @Test
    public void multipleChoiceClasicoImplementaInterfacePregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);

        assertTrue(pregunta instanceof Pregunta);
    }

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

    // Test obtenerTitulo
    @Test
    public void creacionMultipleChoiceConTituloObtenerTituloLoDevuelve() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 5);

        assertEquals("¿Estamos en Algo 3?", pregunta.obtenerTitulo());
    }

    // Test obtenerSegundos
    @Test
    public void creacionMultipleChoiceConSegundosObtenerSegundosLoDevuelve() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 5);

        assertEquals(5, pregunta.obtenerSegundos());
    }

    // Test obtenerOpciones
    @Test
    public void obtenerOpcionesSinHaberAgregadoOpcionesDevuelveArrayVacio() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertTrue(opciones.isEmpty());
    }

    @Test
    public void obtenerOpcionesDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
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
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("Si papa, Obiousli", opciones.get(0).obtenerTitulo());
        assertEquals("No lo se Rick", opciones.get(1).obtenerTitulo());
        assertEquals("Mentira, yo estoy en Derecho", opciones.get(2).obtenerTitulo());
        assertEquals("No, Yo no estoy en Derecho, En Fiuba", opciones.get(3).obtenerTitulo());
        assertEquals("Que se Yo, ando por ahi", opciones.get(4).obtenerTitulo());
    }

    // Test agregarOpcionIncorrecta
    @Test
    public void agregarOpcionIncorrectaGuardaLaOpcionEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("Si papa, Obiousli", unicaOpcion.obtenerTitulo());
    }

    @Test
    public void agregarOpcionIncorrectaGuardaLaOpcionEnLaPreguntaConValorCero() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(0, unicaOpcion.obtenerPunto());
    }

    @Test
    public void agregarVariasOpcionesIncorrectasGuardaTodasLasOpcionesEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesIncorrectasLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionIncorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionIncorrecta("Esta es re frulaaaaa"));
    }

    // Test agregarOpcionCorrecta
    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("Si papa, Obiousli", unicaOpcion.obtenerTitulo());
    }

    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPreguntaConValorUno() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(1, unicaOpcion.obtenerPunto());
    }

    @Test
    public void agregarVariasOpcionesCorrectasGuardaTodasLasOpcionesEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionCorrecta("No lo se Rick");
        pregunta.agregarOpcionCorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesCorrectasLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionCorrecta("No lo se Rick");
        pregunta.agregarOpcionCorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionCorrecta("Que se Yo, ando por ahi");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionCorrecta("Esta es re frulaaaaa"));
    }

    // Test agregarOpcionesCorrecta y agregarOpcionesIncorrecta
    @Test
    public void agregarVariasOpcionesCorrectasEIncorrectasGuardaTodasLasOpcionesEnLaPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionCorrecta("Mentira, yo no estoy en Derecho");
        pregunta.agregarOpcionIncorrecta("Puede ser no, estoy totalmente seguro");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(4, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesSextaOpcionCorrectaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionCorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionIncorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionCorrecta("Que se Yo, ando por ahi");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionCorrecta("Esta es re frulaaaaa"));
    }

    @Test
    public void agregarMasDeCincoOpcionesSextaOpcionIncorrectaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionCorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionIncorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionCorrecta("Que se Yo, ando por ahi");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionIncorrecta("Esta es re frulaaaaa"));
    }

    // Test iniciar
    @Test
    public void iniciarSinOpcionesLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(new Jugador("Maria")));
    }

    @Test
    public void iniciarConUnaOpcionLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(new Jugador("Maria")));
    }

    @Test
    public void iniciarSinJugadorLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(null));
    }

    @Test
    public void iniciarDosVecesLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        Jugador maria = new Jugador("Maria");

        pregunta.iniciar(carlos);

        assertThrows(PreguntaError.class, ()-> pregunta.iniciar(maria));
    }

    @Test
    public void iniciarConJugadorTeHabilitaASeleccionarPregunta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class, ()-> pregunta.seleccionarOpcion(opciones.get(0)));

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        assertDoesNotThrow(()-> pregunta.seleccionarOpcion(opciones.get(0)));
    }

    // Test seleccionar
    @Test
    public void seleccionarSinIniciarLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class, () -> pregunta.seleccionarOpcion(opciones.get(0)));
    }

    @Test
    public void seleccionarOpcionConOpcionNoGuardadaEnLaPreguntaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        Opcion opcion = new Opcion("Opcion frula", 10000);

        assertThrows(PreguntaError.class, ()-> pregunta.seleccionarOpcion(opcion));
    }

    @Test
    public void seleccionarOpcionConOpcionNulaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        assertThrows(PreguntaError.class, ()-> pregunta.seleccionarOpcion(null));
    }

    //@Test //activar solamente cuando se requiera probar el tiempo de respuesta
    public void seleccionarConElMismoTiempoDeRespuestaLanzaPreguntaError() throws PreguntaError {
        int segundos = 1; //

        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", segundos);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        // Simulo que pasan x segundos para seleccionar la opcion
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {
            fail();
        }

        assertThrows(PreguntaError.class, ()-> pregunta.seleccionarOpcion(opciones.get(0)));
    }

    //@Test //activar solamente cuando se requiera probar el tiempo de respuesta
    public void seleccionarPasandoElTiempoDeRespuestaLanzaPreguntaError() throws PreguntaError {
        int segundos = 1; //

        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", segundos);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        // Simulo que pasan x segundos para seleccionar la opcion
        try {
            Thread.sleep((long) ((segundos + 0.5) * 1000));
        } catch (Exception e) {
            fail();
        }

        assertThrows(PreguntaError.class, ()-> pregunta.seleccionarOpcion(opciones.get(0)));
    }

    @Test
    public void seleccionarVariasRespuestasSeInsertanEnLaRespuesta() throws PreguntaError, RespuestaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        pregunta.seleccionarOpcion(opciones.get(0));
        pregunta.seleccionarOpcion(opciones.get(1));
        pregunta.seleccionarOpcion(opciones.get(3));

        Respuesta respuesta = pregunta.confirmar();
        ArrayList<Opcion> opcionesRespuesta = respuesta.obtenerOpcionesElegidas();

        assertEquals(3, opcionesRespuesta.size());
        assertEquals("Si papa, Obiousli", opcionesRespuesta.get(0).obtenerTitulo());
        assertEquals("No lo se Rick", opcionesRespuesta.get(1).obtenerTitulo());
        assertEquals("No, Yo no estoy en Derecho, En Fiuba", opcionesRespuesta.get(2).obtenerTitulo());
    }

    // Test confirmar
    @Test
    public void confirmarSinIniciarLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class, pregunta::confirmar);
    }

    @Test
    public void confirmarHabilitaPoderVolverAIniciarLaRespuesta() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        Jugador maria = new Jugador("Maria");

        pregunta.iniciar(carlos);
        pregunta.confirmar();

        assertDoesNotThrow(()-> pregunta.iniciar(maria));
    }

    @Test
    public void confirmarDevuelveUnaRespuestaConLasOpcionesSeleccionadas() throws PreguntaError, RespuestaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        pregunta.seleccionarOpcion(opciones.get(0));

        Respuesta respuesta = pregunta.confirmar();

        ArrayList<Opcion> opcionesRespuesta = respuesta.obtenerOpcionesElegidas();

        assertEquals(1, opcionesRespuesta.size());

        Opcion opcionSeleccionada = opcionesRespuesta.get(0);

        assertEquals("Si papa, Obiousli", opcionSeleccionada.obtenerTitulo());
    }

    // Test puntajeConOpciones
    @Test
    public void puntajeConOpcionesNulasElPuntajeEsCero() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        assertEquals(0, pregunta.puntajeConOpciones(null));
    }

    @Test
    public void puntajeConOpcionesNoGuardadasEnLaPreguntaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = new ArrayList<>();
        opciones.add(new Opcion("Yo soy re frula hermano", 30000000));

        assertThrows(PreguntaError.class, ()-> pregunta.puntajeConOpciones(opciones));
    }

    @Test
    public void puntajeSeleccionandoTodasLasOpcionesCorrectasPuntajeUno() throws PreguntaError, RespuestaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        pregunta.seleccionarOpcion(opciones.get(0));
        pregunta.seleccionarOpcion(opciones.get(3));

        Respuesta respuesta = pregunta.confirmar();

        Integer puntaje = respuesta.obtenerPuntaje();

        assertEquals(1, puntaje);
    }

    @Test
    public void puntajeSeleccionandoTodasLasOpcionesCorrectasYUnaIncorrectaDevuelveCero() throws PreguntaError, RespuestaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        pregunta.seleccionarOpcion(opciones.get(0));
        pregunta.seleccionarOpcion(opciones.get(3));
        pregunta.seleccionarOpcion(opciones.get(2));

        Respuesta respuesta = pregunta.confirmar();

        Integer puntaje = respuesta.obtenerPuntaje();

        assertEquals(0, puntaje);
    }

    @Test
    public void puntajeSeleccionandoUnaSolaOpcionCorrectaDevueveCero() throws PreguntaError, RespuestaError {
        MultipleChoiceClasico pregunta = new MultipleChoiceClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        pregunta.seleccionarOpcion(opciones.get(0));

        Respuesta respuesta = pregunta.confirmar();

        Integer puntaje = respuesta.obtenerPuntaje();

        assertEquals(0, puntaje);
    }
}
