package com.ieszv.ad.archivodevinostoni.data;

import java.io.Serializable;


public class Vino implements Serializable {

    private long id;
    private String nombre;
    private String bodega;
    private String color;
    private String origen;
    private double graduacion;
    private int fecha;


    public Vino() {
        this(0,null,null,null,null,0.0,0);
    }

    public Vino(long id, String nombre, String bodega, String color, String origen, double graduacion, int fecha) {
        this.id = id;
        this.nombre = nombre;
        this.bodega = bodega;
        this.color = color;
        this.origen = origen;
        this.graduacion = graduacion;
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getBodega() {
        return bodega;
    }

    public String getColor() {
        return color;
    }

    public String getOrigen() {
        return origen;
    }

    public double getGraduacion() {
        return graduacion;
    }

    public int getFecha() {
        return fecha;
    }

    public long setId(long id) {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setGraduacion(double graduacion) {
        this.graduacion = graduacion;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }
}


