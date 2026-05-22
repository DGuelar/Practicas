package com.guelar.androidavanzado;

public class Lista_entrada {
    private String id;
    private int idImagen;
    private String textoEncima;
    private String textoDebajo;
    private int colorFondo;

    public Lista_entrada(String id, int idImagen, String textoEncima, String textoDebajo, int colorFondo) {
        this.id = id;
        this.idImagen = idImagen;
        this.textoEncima = textoEncima;
        this.textoDebajo = textoDebajo;
        this.colorFondo = colorFondo;
    }

    public String getId()          { return id; }
    public int getIdImagen()       { return idImagen; }
    public String getTextoEncima() { return textoEncima; }
    public String getTextoDebajo() { return textoDebajo; }
    public int getColorFondo()     { return colorFondo; }
}