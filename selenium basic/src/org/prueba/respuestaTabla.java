package org.prueba;

import java.util.ArrayList;

public class respuestaTabla {
    public static void main(String[] args) {

    }
    boolean result = false;
    ArrayList<ArrayList<String>> contenido;
    boolean nuevoRegistro = false;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ArrayList<ArrayList<String>> getContenido() {
        return contenido;
    }

    public void setContenido(ArrayList<ArrayList<String>> contenido) {
        this.contenido = contenido;
    }

    public boolean isNuevoRegistro() {
        return nuevoRegistro;
    }

    public void setNuevoRegistro(boolean nuevoRegistro) {
        this.nuevoRegistro = nuevoRegistro;
    }
}
