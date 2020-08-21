package edu.fiuba.algo3.modelo.preguntas.groupChoice;

import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeGruposError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.CantidadMaximaDeOpcionesError;
import edu.fiuba.algo3.modelo.excepciones.preguntas.PreguntaError;
import edu.fiuba.algo3.modelo.juego.*;
import edu.fiuba.algo3.modelo.preguntas.calculadorPuntaje.CalculadorPuntajeParcial;
import edu.fiuba.algo3.modelo.preguntas.opcion.Opcion;
import edu.fiuba.algo3.modelo.preguntas.estados.SinPenalidad;

import java.util.ArrayList;

public class GroupChoice extends Pregunta {

    private final ArrayList<Grupo> grupos;

    public GroupChoice(String titulo) {
        super(titulo, "Group Choice", new SinPenalidad(new CalculadorPuntajeParcial()));

        this.grupos = new ArrayList<>();
    }

    public ArrayList<Grupo> obtenerGrupos() { return this.grupos; }

    public void definirGrupo(String titulo) throws PreguntaError {
        if(this.grupos.size() == 2){
            throw new CantidadMaximaDeGruposError();
        }
        Grupo grupo = new Grupo(titulo);
        this.grupos.add(grupo);
    }

    public void agregarOpcion(Grupo grupo, String titulo) throws PreguntaError{
        if(opciones.size() == 12){
            throw new CantidadMaximaDeOpcionesError();
        }
        if(grupo.equals(grupos.get(0))){
            Opcion opcionIncorrecta = new Opcion(titulo,this.estado.puntajeIncorrecto(),grupos.get(1));
            Opcion opcionCorrecta = new Opcion(titulo,this.estado.puntajeCorrecto(),grupo);
            grupo.agregarOpcion(opcionCorrecta);
            grupos.get(1).agregarOpcion(opcionIncorrecta);
            opciones.add(opcionCorrecta);
            opciones.add(opcionIncorrecta);
        }else{
            Opcion opcionIncorrecta = new Opcion(titulo,this.estado.puntajeIncorrecto(),grupos.get(0));
            Opcion opcionCorrecta = new Opcion(titulo,this.estado.puntajeCorrecto(),grupo);
            grupo.agregarOpcion(opcionCorrecta);
            grupos.get(0).agregarOpcion(opcionIncorrecta);
            opciones.add(opcionCorrecta);
            opciones.add(opcionIncorrecta);
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