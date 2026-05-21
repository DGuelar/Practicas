package com.guelar.listadosymenus.pojos;
public class Planeta {

    private String nombre;
    private String descripcion;
    private int idImagen;
    private boolean esFavorito;
    private String urlInfo;

    public Planeta(String nombre, String descripcion, int idImagen,
                   boolean esFavorito, String urlInfo) {
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.idImagen    = idImagen;
        this.esFavorito  = esFavorito;
        this.urlInfo     = urlInfo;
    }

    // ── Getters ──────────────────────────────────────────────
    public String getNombre()      { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int    getIdImagen()    { return idImagen; }
    public boolean isEsFavorito()  { return esFavorito; }
    public String getUrlInfo()     { return urlInfo; }
    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }
}