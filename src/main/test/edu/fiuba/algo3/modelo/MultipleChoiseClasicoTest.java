package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

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
    public void iniciarSinJugadorLanzaPreguntaError() throws MultipleChoiseError {
        MultipleChoiseClasico pregunta = new MultipleChoiseClasico("¿Estamos en Algo 3?", 3);

        assertThrows(PreguntaError.class, () -> pregunta.Iniciar(null));
    }
}
