package com.guelar.listadosymenus.pojos;
public class Encapsulador {

    private int     idImagen;
    private String  titulo;
    private String  descripcion;
    private boolean seleccionado;

    public Encapsulador(int idImagen, String titulo, String descripcion, boolean seleccionado) {
        this.idImagen     = idImagen;
        this.titulo       = titulo;
        this.descripcion  = descripcion;
        this.seleccionado = seleccionado;
    }

    // ── Getters ──────────────────────────────────────
    public int     getIdImagen()    { return idImagen; }
    public String  getTitulo()      { return titulo; }
    public String  getDescripcion() { return descripcion; }
    public boolean isSeleccionado() { return seleccionado; }

    // ── Setter ──
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}