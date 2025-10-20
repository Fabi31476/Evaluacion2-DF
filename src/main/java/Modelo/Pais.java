/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Pais {
    private String codigo;
    private String nombre;
    private String continente;
    private int poblacion;
    private double pib;
    private String formaGobierno;
    private String capital;

    public Pais(String codigo, String nombre, String continente, int poblacion, double pib, String formaGobierno, String capital) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.continente = continente;
        this.poblacion = poblacion;
        this.pib = pib;
        this.formaGobierno = formaGobierno;
        this.capital = capital;
    }

    // Constructor simplificado (el que estabas usando)
    public Pais(String codigo, String nombre, String continente, int poblacion, String capital) {
        this(codigo, nombre, continente, poblacion, 0, "Desconocida", capital);
    }

    // Getters y setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getContinente() { return continente; }
    public int getPoblacion() { return poblacion; }
    public double getPib() { return pib; }
    public String getFormaGobierno() { return formaGobierno; }
    public String getCapital() { return capital; }

    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setContinente(String continente) { this.continente = continente; }
    public void setPoblacion(int poblacion) { this.poblacion = poblacion; }
    public void setPib(double pib) { this.pib = pib; }
    public void setFormaGobierno(String formaGobierno) { this.formaGobierno = formaGobierno; }
    public void setCapital(String capital) { this.capital = capital; }
}
