/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

public class Pais {
    private final int codigo;
    private final String nombrePais;
    private final String continente;
    private final int poblacion;
    private final ArrayList<IdiomaPais> idiomas;

    public Pais(int codigo, String nombrePais, String continente, int poblacion) {
        this.codigo = codigo;
        this.nombrePais = nombrePais;
        this.continente = continente;
        this.poblacion = poblacion;
        this.idiomas = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public String getContinente() {
        return continente;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public ArrayList<IdiomaPais> getIdiomas() {
        return idiomas;
    }

    public void agregarIdioma(IdiomaPais idioma) {
        idiomas.add(idioma);
    }
}
