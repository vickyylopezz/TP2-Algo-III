package edu.fiuba.algo3.modelo;

public class VerdaderoFalsoClasico {
    public String titulo;

    public VerdaderoFalsoClasico(String titulo) {
        this.titulo = titulo;
    }

    /*

    private ArrayList<String> respuesta;
    private ArrayList<String> opciones;
    private String pregunta;

    public VerdaderoFalso(String pregunta){
        this.respuesta = new ArrayList<String>();
        this.opciones = new ArrayList<String>();
        this.pregunta = pregunta;
    }

    public void agregarOpcionCorrecta(String opcion) {
        this.respuesta.add(opcion);
        this.agregarOpcion(opcion);
    }

    public void agregarOpcion(String opcion) {
        opciones.add(opcion);
    }

    public ArrayList<Integer> obtenerPuntaje(ArrayList<String> respuestas) {
        ArrayList<Integer> puntajes = new ArrayList<Integer>();
        for(int i = 0; i < respuestas.size(); i++){
            if (this.respuesta.contains(respuestas.get(i))){
                puntajes.add(1);
            } else {
                puntajes.add(0);
            }
        }
        return puntajes;
    }

    */
}
