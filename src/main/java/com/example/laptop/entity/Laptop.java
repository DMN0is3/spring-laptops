package com.example.laptop.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "Laptops")
@ApiModel("Clase Laptop")
public class Laptop {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Clave Registro AUTO")
    private Long id;
    private String marca;
    private String modelo;
    private Integer pulgadas;
    private String procesador;
    private Integer ram;
    private Integer hdd;
    private Double peso;
    @ApiModelProperty("Precio en €")
    private Double precio;
    @ApiModelProperty("Compra disponible Online")
    private Boolean disponible;

    // Constructores
    public Laptop() { }

    public Laptop(Long id, String marca, String modelo, Integer pulgadas, String procesador, Integer ram, Integer hdd, Double peso, Double precio, Boolean disponible) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.pulgadas = pulgadas;
        this.procesador = procesador;
        this.ram = ram;
        this.hdd = hdd;
        this.peso = peso;
        this.precio = precio;
        this.disponible = disponible;
    }

    // Getter&Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(Integer pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getHdd() {
        return hdd;
    }

    public void setHdd(Integer hdd) { this.hdd = hdd; }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getDisponible() { return disponible; }

    public void setDisponible(Boolean disponible) { this.disponible = disponible; }

    // toString()
    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", pulgadas=" + pulgadas +
                ", procesador='" + procesador + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", peso=" + peso +
                ", precio=" + precio +
                '}';
    }
}
