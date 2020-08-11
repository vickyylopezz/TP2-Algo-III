package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class OpcionTest {

    // contructor
    @Test
    public void crearOpcionConTituloYPuntoEsValido(){
        assertDoesNotThrow(() -> {new Opcion("Titulo", mock(Punto.class));});
    }

    @Test
    public void crearOpcionConTituloPuntoYGrupoEsValido(){
        assertDoesNotThrow(() -> {new Opcion("Titulo", mock(Punto.class), mock(Grupo.class));});
    }

    // obtenerTitulo
    @Test
    public void ObtenerTituloDevuelveElTituloDeLaOpcion(){
        Opcion opcion = new Opcion("Una opcion", new PuntoPositivo());
        assertEquals("Una opcion",opcion.obtenerTitulo());
    }

    // obtenerPunto
    @Test
    public void ObtenerPuntoDevuelveElPuntoDeLaOpcion(){
        Punto puntoPositivo = new PuntoPositivo();

        Opcion opcion = new Opcion("Otra opcion", puntoPositivo);

        assertEquals(puntoPositivo ,opcion.obtenerPunto());
    }

    // agrupacion
    @Test
    public void agrupacionTeDevuelveDeQueGrupoEsLaOpcion() {
        Grupo grupo = mock(Grupo.class);
        Opcion opcion = new Opcion("titulo", mock(Punto.class), grupo);

        assertEquals(grupo, opcion.agrupacion());
    }

    @Test
    public void agrupacionConstructorSinGrupoDevuelveNull() {
        Opcion opcion = new Opcion("titulo", mock(Punto.class));

        assertNull(opcion.agrupacion());
    }
}
