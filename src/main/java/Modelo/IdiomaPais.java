/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class IdiomaPais {
    private final String idioma;
    private final boolean esOficial;
    private final double porcentaje;

    public IdiomaPais(String idioma, boolean esOficial, double porcentaje) {
        this.idioma = idioma;
        this.esOficial = esOficial;
        this.porcentaje = porcentaje;
    }
// algo algo 
    public String getIdioma() {
        return idioma;
    }

    public boolean isEsOficial() {
        return esOficial;
    }

    public double getPorcentaje() {
        return porcentaje;
    }
}
