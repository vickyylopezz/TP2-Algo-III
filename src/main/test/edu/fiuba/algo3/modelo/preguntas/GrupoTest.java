package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrupoTest {
    @Test
    public void GrupoSeCreaConTitulo(){
        Grupo grupo = new Grupo("Animales");

        assertEquals(grupo.obtenerTitulo(),"Animales");
    }

    @Test
    public void OpcioneSeAgreganAlGrupoCorrectamente(){
        Grupo grupo = new Grupo("Saludos");
        Opcion opcion1 = new Opcion("hola",new PuntoPositivo(), new Grupo("Saludo"));
        Opcion opcion2 = new Opcion("chau",new PuntoNegativo(), new Grupo("Saludar"));

        grupo.agregarOpcion(opcion1);
        grupo.agregarOpcion(opcion2);
        ArrayList<Opcion> opciones = grupo.obtenerOpciones();
        assertEquals(opciones.get(0),opcion1);
        assertEquals(opciones.get(1),opcion2);
    }
}
