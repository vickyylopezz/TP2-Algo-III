package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.juego.opcion.OpcionGroupChoice;
import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.util.punto.Punto;
import edu.fiuba.algo3.modelo.util.punto.PuntoNegativo;
import edu.fiuba.algo3.modelo.util.punto.PuntoNulo;
import edu.fiuba.algo3.modelo.util.punto.PuntoPositivo;
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

    @Test
    public void OpcionSeAgregaGrupoIgualYdebenCoincidir(){
        Grupo grupo = new Grupo("Mascotas");
        OpcionGroupChoice opcion = new OpcionGroupChoice("Hola",new PuntoNulo(),grupo);

        opcion.agregarGrupo(grupo);

        assertTrue(opcion.grupoCoincide());
    }

    @Test
    public void OpcionCloneClonaLaOpcion(){
        Grupo grupo = new Grupo("Mascotas");
        Punto punto = new PuntoPositivo();
        OpcionGroupChoice opcion = new OpcionGroupChoice("Hola",punto,grupo);

        OpcionGroupChoice copia = opcion.clone();

        assertEquals(copia.obtenerTitulo(),"Hola");
        assertEquals(copia.obtenerPunto(),punto);
        assertEquals(copia.obtenerGrupo(),grupo);
        assertNotEquals(opcion,copia);
    }
}
