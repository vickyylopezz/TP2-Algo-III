package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleChoiseClasicoTest {
    @Test
    public void creacionMultipleChoiseConTituloObtenerTituloLoDevuelve() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 5);

        assertEquals("¿Estamos en Algo 3?", pregunta.obtenerTitulo());
    }

    @Test
    public void creacionMultipleChoiseConSegundosNegativosLanzaMultipleChoiseError() {
        assertThrows(MultipleChoiseError.class, () -> new MultipleChoiseClasico("¿Estamos en Algo 3?", -1));
    }

    @Test
    public void creacionMultipleChoiseConSegundosCeroNoLanzaMultipleChoiseError() {
        assertDoesNotThrow(() -> {
            new MultipleChoiseClasico("¿Estamos en Algo 3?", 0);
        });
    }

    @Test
    public void multipleChoiseClasicoImplementaInterfacePregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);

        assertTrue(pregunta instanceof Pregunta);
    }

    @Test
    public void obtenerOpcionesSinHaberAgregadoOpcionesDevuelveArrayVacio() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertTrue(opciones.isEmpty());
    }

    @Test
    public void agregarOpcionIncorrectaGuardaLaOpcionEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("Si papa, Obiousli", unicaOpcion.getTitulo());
    }

    @Test
    public void agregarOpcionIncorrectaGuardaLaOpcionEnLaPreguntaConValorCero() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(0, unicaOpcion.getValor());
    }

    @Test
    public void agregarVariasOpcionesGuardaTodasLasOpcionesEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionIncorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("Si papa, Obiousli", unicaOpcion.getTitulo());
    }

    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPreguntaConValorCero() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(1, unicaOpcion.getValor());
    }

    @Test
    public void agregarVariasOpcionesCorrectasGuardaTodasLasOpcionesEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionCorrecta("No lo se Rick");
        pregunta.agregarOpcionCorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarVariasOpcionesCorrectasEIncorrectasGuardaTodasLasOpcionesEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionCorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void iniciarSinJugadorLanzaPreguntaError() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        assertThrows(PreguntaError.class, () -> pregunta.iniciar(null));
    }

    @Test
    public void seleccionarSinIniciarLanzaPreguntaError() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class, () -> pregunta.seleccionarOpcion(opciones.get(0)));
    }

    @Test
    public void confirmarSinIniciarLanzaPreguntaError() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.obtenerOpciones();

        assertThrows(PreguntaError.class, pregunta::confirmar);
    }

    @Test
    public void iniciarConJugadorTeHabilitaASeleccionarPregunta() throws MultipleChoiseError, PreguntaError {
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

    @Test
    public void iniciarDosVecesLanzaPreguntaError() throws MultipleChoiseError, PreguntaError {
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
    public void seleccionarOpcionConOpcionNoGuardadaEnLaPreguntaLanzaPreguntaError() throws MultipleChoiseError, PreguntaError {
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
    public void seleccionarOpcionConOpcionNulaLanzaPreguntaError() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        pregunta.iniciar(carlos);

        assertThrows(PreguntaError.class, ()-> pregunta.seleccionarOpcion(null));
    }

    @Test
    public void confirmarHabilitaPoderVolverAIniciarLaRespuesta() throws MultipleChoiseError, PreguntaError {
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
    public void confirmarDevuelveUnaRespuestaConLasOpcionesSeleccionadas() throws MultipleChoiseError, PreguntaError {
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

    @Test
    public void seleccionarVariasRespuestasSeInsertanEnLaRespuesta() throws MultipleChoiseError, PreguntaError {
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

    @Test
    public void puntajeConOpcionesNulasElPuntajeEsCero() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.agregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.agregarOpcionIncorrecta("No lo se Rick");
        pregunta.agregarOpcionIncorrecta("Mentira, yo estoy en Derecho");
        pregunta.agregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.agregarOpcionIncorrecta("Que se Yo, ando por ahi");

        assertEquals(0, pregunta.puntajeConOpciones(null));
    }

    @Test
    public void puntajeConOpcionesNoGuardadasEnLaPreguntaLanzaPreguntaError() throws MultipleChoiseError {
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
    public void puntajeSeleccionadoTodasLasOpcionesCorrectasPuntajeUno() throws MultipleChoiseError, PreguntaError {
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
    public void puntajeSeleccionandoTodasLasOpcionesCorrectasYUnaIncorrectaDevuelveCero() throws MultipleChoiseError, PreguntaError {
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
    public void puntajeSeleccionandoUnaSolaOpcionCorrectaDevueveCero() throws MultipleChoiseError, PreguntaError {
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
}
