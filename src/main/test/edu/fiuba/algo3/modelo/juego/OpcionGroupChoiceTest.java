package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.preguntas.opcion.OpcionGroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OpcionGroupChoiceTest {

    @Test
    public void OpcionSeCreaIndicandoleGrupoPuntoYTitulo(){
        Grupo grupo = new Grupo("Saludos");
        Punto punto = new PuntoNegativo();
        OpcionGroupChoice opcion = new OpcionGroupChoice("Hola",punto,grupo);

        assertEquals(opcion.obtenerTitulo(),"Hola");
        assertEquals(opcion.obtenerPunto(),punto);
        assertEquals(opcion.obtenerGrupo(),grupo);
    }

}
