package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpcionTest {
    @Test
    public void GetTituloDevuelveElTituloDeLaOpcion(){
        Opcion opcion = new Opcion("Una opcion",10);

        assertEquals("Una opcion",opcion.getTitulo());
    }

    @Test
    public void GetValoroDevuelveElValorDeLaOpcion(){
        Opcion opcion = new Opcion("Otra opcion",10);

        assertEquals(10,opcion.getValor());
    }
}
