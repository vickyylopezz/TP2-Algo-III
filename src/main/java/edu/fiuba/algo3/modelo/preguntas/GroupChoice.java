package edu.fiuba.algo3.modelo.preguntas;

import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeGruposError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.opcion.Grupo;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;
import edu.fiuba.algo3.modelo.util.punto.Punto;

import java.util.ArrayList;

public class GroupChoice extends Pregunta {

    private final ArrayList<Grupo> grupos;

    public GroupChoice(String titulo) {
        super(titulo, "Group Choice", new SinPenalidad(new CalculadorPuntajeParcial()));

        this.grupos = new ArrayList<>();
    }

    public ArrayList<Grupo> obtenerGrupos() { return new ArrayList<>(this.grupos); }

    public void definirGrupo(String titulo) throws PreguntaError {
        if(this.grupos.size() == 2){
            throw new CantidadMaximaDeGruposError();
        }
        Grupo grupo = new Grupo(titulo);
        this.grupos.add(grupo);
    }

    public void agregarOpcion(Grupo grupoCorrecto, String titulo) throws PreguntaError{
        if(opciones.size() == 12) {
            throw new CantidadMaximaDeOpcionesError();
        }

        Punto puntaje;
        for (Grupo grupo: this.grupos) {
            if (grupo == grupoCorrecto) { puntaje = this.estado.puntajeCorrecto(); }
            else { puntaje = this.estado.puntajeIncorrecto(); }

            Opcion opcion = new Opcion(titulo, puntaje, grupo);
            grupo.agregarOpcion(opcion);
            this.opciones.add(opcion);
        }
    }

    @Override
    public ArrayList<Opcion> opcionesSeleccionables(ArrayList<Opcion> seleccionadas) {
        ArrayList<Opcion> seleccionables = this.obtenerOpciones();
        for (Opcion opcion: seleccionadas) {
            seleccionables.remove(opcion);
            seleccionables.remove(this.buscarOpcionGrupoOpuesto(opcion));
        }
        return seleccionables;
    }

    private Opcion buscarOpcionGrupoOpuesto(Opcion opcion) {
        Grupo grupoOpuesto = this.grupos.get(0);
        if (opcion.agrupacion() == grupoOpuesto) grupoOpuesto = this.grupos.get(1);

        for (Opcion opcionBuscada: grupoOpuesto.obtenerOpciones()) {
            if (opcionBuscada.obtenerTitulo().equals(opcion.obtenerTitulo())) {
                return opcionBuscada;
            }
        }

        return null;
    }
}