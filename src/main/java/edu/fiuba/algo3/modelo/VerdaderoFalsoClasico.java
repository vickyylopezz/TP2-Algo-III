package edu.fiuba.algo3.modelo;

public class VerdaderoFalsoClasico {
    
    private ArrayList<Opcion> opciones;
    private String pregunta;

    public VerdaderoFalsoClasico(String pregunta){
        this.opciones = new ArrayList<Opcion>();
        this.pregunta = pregunta;
    }

    public void agregarOpcionCorrecta(String opcionTitulo) {
        Opcion opcion = new Opcion(opcionTitulo, 1);
        this.opciones.add(opcion);
    }

    public void agregarOpcion(String opcionTitulo) {
        Opcion opcion = new Opcion(opcionTitulo, 0);
        this.opciones.add(opcion);
    }

    public ArrayList<Integer> obtenerPuntaje(ArrayList<Opcion> respuestas) {
        ArrayList<Integer> puntajes = new ArrayList<Integer>();
        for(int i = 0; i < respuestas.size(); i++){
            puntajes.add(respuestas.get(i).getValor());
        }
        return puntajes;
    }

    public ArrayList<Opcion> obtenerOpciones() {
        return this.opciones;
    }
}
