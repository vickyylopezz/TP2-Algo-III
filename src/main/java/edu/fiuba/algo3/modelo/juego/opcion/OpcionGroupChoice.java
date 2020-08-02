package edu.fiuba.algo3.modelo.juego.opcion;

import edu.fiuba.algo3.modelo.preguntas.groupChoice.Grupo;
import edu.fiuba.algo3.modelo.util.punto.Punto;


public class OpcionGroupChoice extends Opcion {
    protected Grupo grupoPertenece;
    protected Grupo grupoElegido;

    public OpcionGroupChoice(String titulo, Punto punto, Grupo grupo){
        this.titulo = titulo;
        this.punto = punto;
        this.grupoPertenece = grupo;
    }

    public void agregarGrupo(Grupo grupo){
        this.grupoElegido = grupo;
    }

    public boolean grupoCoincide(){
        return this.grupoPertenece.equals(this.grupoElegido);
    }

    public Grupo obtenerGrupo(){return this.grupoPertenece;}

    public OpcionGroupChoice clone(){
        return new OpcionGroupChoice(this.titulo,this.punto,this.grupoPertenece);
    }
}
