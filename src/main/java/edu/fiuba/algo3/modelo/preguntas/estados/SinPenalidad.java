package edu.fiuba.algo3.modelo.preguntas.estados;

public class SinPenalidad implements Penalidad{
    @Override
    public Boolean conPenalidad() {
        return false;
    }
}
