package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultipleChoiseClasicoTest {
    //
    // --------------------------- Test unitarios ---------------------------
    //

    // Test implementacion
    @Test
    public void multipleChoiseClasicoImplementaInterfacePregunta() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);

        assertTrue(pregunta instanceof Pregunta);
    }

    // Test constructor
    @Test
    public void creacionMultipleChoiseConSegundosNegativosLanzaMultipleChoiseError() {
        assertThrows(PreguntaError.class, () -> new MultipleChoiseClasico("¿Estamos en Algo 3?", -1));
    }

    @Test
    public void creacionMultipleChoiseConSegundosCeroNoLanzaMultipleChoiseError() {
        assertDoesNotThrow(() -> {
            new MultipleChoiseClasico("¿Estamos en Algo 3?", 0);
        });
    }

    @Test
    public void creacionMultipleChoiseConSegundosMayoresACeroNoLanzaMultipleChoiseError() {
        assertDoesNotThrow(() -> {
            new MultipleChoiseClasico("¿Estamos en Algo 3?", 5);
        });
    }

    // Test obtenerTitulo
    @Test
    public void creacionMultipleChoiseConTituloObtenerTituloLoDevuelve() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 5);

        assertEquals("¿Estamos en Algo 3?", pregunta.obtenerTitulo());
    }

    // Test obtenerSegundos
    @Test
    public void creacionMultipleChoiseConSegunosObtenerSegunosLoDevuelve() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 5);

        assertEquals(5, pregunta.obtenerSegundos());
    }

    // Test obtenerOpciones
    @Test
    public void obtenerOpcionesSinHaberAgregadoOpcionesDevuelveArrayVacio() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertTrue(opciones.isEmpty());
    }

    @Test
    public void obtenerOpcionesLasDevuelveTodasLasOpcionesAgregadas() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(5, opciones.size());
    }

    @Test
    public void obtenerOpcionesLasDevuelveEnElOrdenDeGuardado() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals("Si papa, Obiousli", opciones.get(0).getTitulo());
        assertEquals("No lo se Rick", opciones.get(1).getTitulo());
        assertEquals("Mentira, yo estoy en Derecho", opciones.get(2).getTitulo());
        assertEquals("No, Yo no estoy en Derecho, En Fiuba", opciones.get(3).getTitulo());
        assertEquals("Que se Yo, ando por ahi", opciones.get(4).getTitulo());
    }

    // Test agregarOpcionIncorrecta
    @Test
    public void agregarOpcionIncorrectaGuardaLaOpcionEnLaPregunta() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("Si papa, Obiousli", unicaOpcion.getTitulo());
    }

    @Test
    public void agregarOpcionIncorrectaGuardaLaOpcionEnLaPreguntaConValorCero() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(0, unicaOpcion.getValor());
    }

    @Test
    public void agregarVariasOpcionesIncorrectasGuardaTodasLasOpcionesEnLaPregunta() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesIncorrectasLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("Si papa, Obiousli", unicaOpcion.getTitulo());
    }

    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPreguntaConValorUno() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(1, unicaOpcion.getValor());
    }

    @Test
    public void agregarVariasOpcionesCorrectasGuardaTodasLasOpcionesEnLaPregunta() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionCorrecta("No lo se Rick");
        pregunta.agregarOpcionCorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesCorrectasLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionCorrecta("Mentira, yo no estoy en Derecho");
        pregunta.agregarOpcionIncorrecta("Puede ser no, estoy totalmente seguro");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(4, opciones.size());
    }

    @Test
    public void agregarMasDeCincoOpcionesSextaOpcionCorrectaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionCorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionIncorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionCorrecta("Que se Yo, ando por ahi");

        assertThrows(PreguntaError.class, ()->pregunta.agregarOpcionCorrecta("Esta es re frulaaaaa"));
    }

    @Test
    public void agregarMasDeCincoOpcionesSextaOpcionIncorrectaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(new Jugador("Maria")));
    }

    @Test
    public void iniciarConUnaOpcionLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(new Jugador("Maria")));
    }

    @Test
    public void iniciarSinJugadorLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(null));
    }

    @Test
    public void iniciarDosVecesLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class, () -> pregunta.seleccionarOpcion(opciones.get(0)));
    }

    @Test
    public void seleccionarOpcionConOpcionNoGuardadaEnLaPreguntaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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

        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", segundos);
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

        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", segundos);
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
    public void seleccionarVariasRespuestasSeInsertanEnLaRespuesta() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
        ArrayList<Opcion> opcionesRespuesta = respuesta.opcionesElegidas;

        assertEquals(3, opcionesRespuesta.size());
        assertEquals("Si papa, Obiousli", opcionesRespuesta.get(0).getTitulo());
        assertEquals("No lo se Rick", opcionesRespuesta.get(1).getTitulo());
        assertEquals("No, Yo no estoy en Derecho, En Fiuba", opcionesRespuesta.get(2).getTitulo());
    }

    // Test confirmar
    @Test
    public void confirmarSinIniciarLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class, pregunta::confirmar);
    }

    @Test
    public void confirmarHabilitaPoderVolverAIniciarLaRespuesta() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
    public void confirmarDevuelveUnaRespuestaConLasOpcionesSeleccionadas() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        pregunta.seleccionarOpcion(opciones.get(0));

        Respuesta respuesta = pregunta.confirmar();

        ArrayList<Opcion> opcionesRespuesta = respuesta.opcionesElegidas;

        assertEquals(1, opcionesRespuesta.size());

        Opcion opcionSeleccionada = opcionesRespuesta.get(0);

        assertEquals("Si papa, Obiousli", opcionSeleccionada.getTitulo());
    }

    // Test puntajeConOpciones
    @Test
    public void puntajeConOpcionesNulasElPuntajeEsCero() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        assertEquals(0, pregunta.puntajeConOpciones(null));
    }

    @Test
    public void puntajeConOpcionesNoGuardadasEnLaPreguntaLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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
    public void puntajeSeleccionandoTodasLasOpcionesCorrectasPuntajeUno() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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

        Integer puntaje = pregunta.puntajeConOpciones(respuesta.opcionesElegidas);

        assertEquals(1, puntaje);
    }

    @Test
    public void puntajeSeleccionandoTodasLasOpcionesCorrectasYUnaIncorrectaDevuelveCero() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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

        Integer puntaje = pregunta.puntajeConOpciones(respuesta.opcionesElegidas);

        assertEquals(0, puntaje);
    }

    @Test
    public void puntajeSeleccionandoUnaSolaOpcionCorrectaDevueveCero() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
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

        Integer puntaje = pregunta.puntajeConOpciones(respuesta.opcionesElegidas);

        assertEquals(0, puntaje);
    }

    /*
    // Test puntajeConRespuestas
    @Test
    public void puntajeConRespuestasPasandoleNullLanzaPreguntaError() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        assertThrows(PreguntaError.class, ()-> pregunta.puntajeConRespuestas(null));
    }

    @Test
    public void puntajeConRespuestasPasandoleArrayVacioDevuelveArrayVacio() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        ArrayList<Integer> puntajes = pregunta.puntajeConRespuestas(respuestas);

        assertTrue(puntajes.isEmpty());
    }

    @Test
    public void puntajeConRespuestasPasandoleArrayConUnaRespuestaDevuelveElPuntajeCorrecto() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        // Juega un jugador para obtener la respuesta,
        // en este caso es correcta por lo que el puntaje
        // debe ser 1.
        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();
        pregunta.seleccionarOpcion(opciones.get(0));
        Respuesta respuesta = pregunta.confirmar();

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        respuestas.add(respuesta);

        ArrayList<Integer> puntajes = pregunta.puntajeConRespuestas(respuestas);

        assertEquals(1, puntajes.size());
        assertEquals(1, puntajes.get(0));
    }

    @Test
    public void puntajeConRespuestasPasandoleArrayConVariasRespuestasDevuelveElPuntajeCorrectoOrdenado() throws PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Respuesta> respuestas = new ArrayList<>();
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        // 3 jugadores uno con cada respuesta seleccionada.
        // tiene que devolver un array [1, 0, 0]
        for (int i = 0; i < 3; i++) {
            pregunta.iniciar(new Jugador("Jugador " + i));

            pregunta.seleccionarOpcion(opciones.get(i));

            Respuesta respuesta = pregunta.confirmar();
            respuestas.add(respuesta);
        }

        ArrayList<Integer> puntajes = pregunta.puntajeConRespuestas(respuestas);

        assertEquals(3, puntajes.size());

        assertEquals(1, puntajes.get(0));
        assertEquals(0, puntajes.get(1));
        assertEquals(0, puntajes.get(2));
    }
    */
}
