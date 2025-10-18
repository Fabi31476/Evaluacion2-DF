/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Pais;
import java.util.ArrayList;

public class Controlador {
    private final ArrayList<Pais> listaPaises;
   

    public Controlador() {
        listaPaises = new ArrayList<>();
    }

    public void agregarPais(Pais pais) {
        listaPaises.add(pais);
    }

    public void eliminarPais(int codigo) {
        listaPaises.removeIf(p -> p.getCodigo() == codigo);
    }

    public ArrayList<Pais> listarPaises() {
        return listaPaises;
    }

    public Pais buscarPaisPorNombre(String nombre) {
        for (Pais p : listaPaises) {
            if (p.getNombrePais().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Pais> filtrarPorContinente(String continente) {
        ArrayList<Pais> filtrados = new ArrayList<>();
        for (Pais p : listaPaises) {
            if (p.getContinente().equalsIgnoreCase(continente)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public ArrayList<Pais> topPaisesPoblados(int top) {
        listaPaises.sort((p1, p2) -> Integer.compare(p2.getPoblacion(), p1.getPoblacion()));
        int cantidad = Math.min(top, listaPaises.size());
        return new ArrayList<>(listaPaises.subList(0, cantidad));
    }
}
