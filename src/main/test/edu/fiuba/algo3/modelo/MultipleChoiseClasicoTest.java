package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleChoiseClasicoTest {
    @Test
    public void creacionMultipleChoiseConTituloObtenerTituloLoDevuelve() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 5);

        assertEquals("¿Estamos en Algo 3?", pregunta.ObtenerTitulo());
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
        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        assertTrue(opciones.isEmpty());
    }

    @Test
    public void agregarOpcionGuardaLaOpcionEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcion("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("Si papa, Obiousli", unicaOpcion.getTitulo());
    }

    @Test
    public void agregarOpcionGuardaLaOpcionEnLaPreguntaConValorCero() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcion("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(0, unicaOpcion.getValor());
    }

    @Test
    public void agregarVariasOpcionesGuardaTodasLasOpcionesEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcion("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        assertEquals(1, opciones.size());

        Opcion unicaOpcion = opciones.get(0);

        assertEquals("Si papa, Obiousli", unicaOpcion.getTitulo());
    }

    @Test
    public void agregarOpcionCorrectaGuardaLaOpcionEnLaPreguntaConValorCero() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        Opcion unicaOpcion = opciones.get(0);

        assertEquals(1, unicaOpcion.getValor());
    }

    @Test
    public void agregarVariasOpcionesCorrectasGuardaTodasLasOpcionesEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcionCorrecta("No lo se Rick");
        pregunta.AgregarOpcionCorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void agregarVariasOpcionesCorrectasEIncorrectasGuardaTodasLasOpcionesEnLaPregunta() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcionCorrecta("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        assertEquals(3, opciones.size());
    }

    @Test
    public void iniciarSinJugadorLanzaPreguntaError() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);

        assertThrows(PreguntaError.class, () -> pregunta.Iniciar(null));
    }

    @Test
    public void iniciarConJugadorTeHabilitaASeleccionarPregunta() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        assertThrows(PreguntaError.class, ()-> pregunta.SeleccionarOpcion(opciones.get(0)));

        Jugador carlos = new Jugador("Carlos");
        pregunta.Iniciar(carlos);

        assertDoesNotThrow(()-> pregunta.SeleccionarOpcion(opciones.get(0)));
    }

    @Test
    public void iniciarDosVecesLanzaPreguntaError() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        Jugador maria = new Jugador("Maria");

        pregunta.Iniciar(carlos);

        assertThrows(PreguntaError.class, ()-> pregunta.Iniciar(maria));
    }

    @Test
    public void seleccionarOpcionConOpcionNoGuardadaEnLaPreguntaLanzaPreguntaError() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        pregunta.Iniciar(carlos);

        Opcion opcion = new Opcion("Opcion frula", 10000);

        assertThrows(PreguntaError.class, ()-> pregunta.SeleccionarOpcion(opcion));
    }

    @Test
    public void seleccionarOpcionConOpcionNulaLanzaPreguntaError() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        pregunta.Iniciar(carlos);

        assertThrows(PreguntaError.class, ()-> pregunta.SeleccionarOpcion(null));
    }

    @Test
    public void confirmarHabilitaPoderVolverAIniciarLaRespuesta() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");

        Jugador carlos = new Jugador("Carlos");
        Jugador maria = new Jugador("Maria");

        pregunta.Iniciar(carlos);
        pregunta.Confirmar();

        assertDoesNotThrow(()-> pregunta.Iniciar(maria));
    }

    @Test
    public void confirmarDevuelveUnaRespuestaConLasOpcionesSeleccionadas() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.Iniciar(carlos);

        pregunta.SeleccionarOpcion(opciones.get(0));

        Respuesta respuesta = pregunta.Confirmar();

        ArrayList<Opcion> opcionesRespuesta = respuesta.opcionesElegidas;

        assertEquals(1, opcionesRespuesta.size());

        Opcion opcionSeleccionada = opcionesRespuesta.get(0);

        assertEquals("Si papa, Obiousli", opcionSeleccionada.getTitulo());
    }

    @Test
    public void seleccionarVariasRespuestasSeInsertanEnLaRespuesta() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");
        pregunta.AgregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.AgregarOpcion("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.Iniciar(carlos);

        pregunta.SeleccionarOpcion(opciones.get(0));
        pregunta.SeleccionarOpcion(opciones.get(1));
        pregunta.SeleccionarOpcion(opciones.get(3));

        Respuesta respuesta = pregunta.Confirmar();
        ArrayList<Opcion> opcionesRespuesta = respuesta.opcionesElegidas;

        assertEquals(3, opcionesRespuesta.size());
        assertEquals("Si papa, Obiousli", opcionesRespuesta.get(0).getTitulo());
        assertEquals("No lo se Rick", opcionesRespuesta.get(1).getTitulo());
        assertEquals("No, Yo no estoy en Derecho, En Fiuba", opcionesRespuesta.get(2).getTitulo());
    }

    @Test
    public void puntajeConOpcionesNulasElPuntajeEsCero() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");
        pregunta.AgregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.AgregarOpcion("Que se Yo, ando por ahi");

        assertEquals(0, pregunta.PuntajeConOpciones(null));
    }

    @Test
    public void puntajeConOpcionesNoGuardadasEnLaPreguntaLanzaPreguntaError() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");
        pregunta.AgregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.AgregarOpcion("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = new ArrayList<>();
        opciones.add(new Opcion("Yo soy re frula hermano", 30000000));

        assertThrows(PreguntaError.class, ()-> pregunta.PuntajeConOpciones(opciones));
    }

    @Test
    public void puntajeSeleccionadoTodasLasOpcionesCorrectasPuntajeUno() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");
        pregunta.AgregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.AgregarOpcion("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.Iniciar(carlos);

        pregunta.SeleccionarOpcion(opciones.get(0));
        pregunta.SeleccionarOpcion(opciones.get(3));

        Respuesta respuesta = pregunta.Confirmar();

        Integer puntaje = pregunta.PuntajeConOpciones(respuesta.opcionesElegidas);

        assertEquals(1, puntaje);
    }

    @Test
    public void puntajeSeleccionandoTodasLasOpcionesCorrectasYUnaIncorrectaDevuelveCero() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");
        pregunta.AgregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.AgregarOpcion("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.Iniciar(carlos);

        pregunta.SeleccionarOpcion(opciones.get(0));
        pregunta.SeleccionarOpcion(opciones.get(3));
        pregunta.SeleccionarOpcion(opciones.get(2));

        Respuesta respuesta = pregunta.Confirmar();

        Integer puntaje = pregunta.PuntajeConOpciones(respuesta.opcionesElegidas);

        assertEquals(0, puntaje);
    }

    @Test
    public void puntajeSeleccionandoUnaSolaOpcionCorrectaDevueveCero() throws MultipleChoiseError, PreguntaError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);
        pregunta.AgregarOpcionCorrecta("Si papa, Obiousli");
        pregunta.AgregarOpcion("No lo se Rick");
        pregunta.AgregarOpcion("Mentira, yo estoy en Derecho");
        pregunta.AgregarOpcionCorrecta("No, Yo no estoy en Derecho, En Fiuba");
        pregunta.AgregarOpcion("Que se Yo, ando por ahi");

        ArrayList<Opcion> opciones = pregunta.ObtenerOpciones();

        Jugador carlos = new Jugador("Carlos");
        pregunta.Iniciar(carlos);

        pregunta.SeleccionarOpcion(opciones.get(0));

        Respuesta respuesta = pregunta.Confirmar();

        Integer puntaje = pregunta.PuntajeConOpciones(respuesta.opcionesElegidas);

        assertEquals(0, puntaje);
    }
}
