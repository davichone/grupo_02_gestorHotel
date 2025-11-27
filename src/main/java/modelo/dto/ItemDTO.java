/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dto;

/**
 *
 * @author drola
 */
public class ItemDTO {

    private String Org, familia, subFamilia, estado;
    private String cod;
    private String item;
    private String existencia;
    private String fechaEntrada;
    
    public ItemDTO(){}

    public ItemDTO(String Org, String familia, String subFamilia, String estado, String cod, String item, String existencia, String fechaEntrada) {
        this.Org = Org;
        this.familia = familia;
        this.subFamilia = subFamilia;
        this.estado = estado;
        this.cod = cod;
        this.item = item;
        this.existencia = existencia;
        this.fechaEntrada = fechaEntrada;
    }

    public String getOrg() {
        return Org;
    }

    public void setOrg(String Org) {
        this.Org = Org;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(String subFamilia) {
        this.subFamilia = subFamilia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

}
