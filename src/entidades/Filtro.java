/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author rau3
 */
public class Filtro {
    
    private int idFiltro;
    private String codFiltro;
    private String marca;
    private int stock;
    private boolean existencia;

    public Filtro() {
    }

    public Filtro(int idFiltro, String codFiltro, String marca, int stock, boolean existencia) {
        this.idFiltro = idFiltro;
        this.codFiltro = codFiltro;
        this.marca = marca;
        this.stock = stock;
        this.existencia = existencia;
    }

    public Filtro(String codFiltro, String marca, int stock, boolean existencia) {
        this.codFiltro = codFiltro;
        this.marca = marca;
        this.stock = stock;
        this.existencia = existencia;
    }

    public Filtro(String marca, int stock, boolean existencia) {
        this.marca = marca;
        this.stock = stock;
        this.existencia = existencia;
    }

    public int getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(int idFiltro) {
        this.idFiltro = idFiltro;
    }

    public String getCodFiltro() {
        return codFiltro;
    }

    public void setCodFiltro(String codFiltro) {
        this.codFiltro = codFiltro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean getExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }
    
    
    
}
