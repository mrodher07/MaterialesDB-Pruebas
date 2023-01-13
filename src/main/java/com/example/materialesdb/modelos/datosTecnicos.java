package com.example.materialesdb.modelos;

import javafx.beans.property.*;

public class datosTecnicos {

    private static final datosTecnicos instance = new datosTecnicos();
    private final IntegerProperty idMaterial;
    private final DoubleProperty peso;
    private final StringProperty color;
    private final StringProperty herramientaUso;
    private final StringProperty lugarUso;
    private final StringProperty recipiente;
    private final StringProperty composicion;


    public datosTecnicos(int idMaterial, Double peso, String color, String herramientaUso, String lugarUso, String recipiente, String composicion) {
        this.idMaterial = new SimpleIntegerProperty(idMaterial);
        this.peso = new SimpleDoubleProperty(peso);
        this.color = new SimpleStringProperty(color);
        this.herramientaUso = new SimpleStringProperty(herramientaUso);
        this.lugarUso = new SimpleStringProperty(lugarUso);
        this.recipiente = new SimpleStringProperty(recipiente);
        this.composicion = new SimpleStringProperty(composicion);
    }

    public datosTecnicos() {
        this.idMaterial = new SimpleIntegerProperty(0);
        this.peso = new SimpleDoubleProperty(0.00);
        this.color = new SimpleStringProperty("");
        this.herramientaUso = new SimpleStringProperty("");
        this.lugarUso = new SimpleStringProperty("");
        this.recipiente = new SimpleStringProperty("");
        this.composicion = new SimpleStringProperty("");
    }

    @Override
    public String toString() {
        return "IDMaterial: "+idMaterial+
                ", Peso: "+ peso +
                ", Color: "+ color +
                ", Herramienta de Uso: "+ herramientaUso +
                ", Lugar de Uso: "+ lugarUso +
                ", Recipiente: "+ recipiente +
                ", Composicion: "+ composicion +".";
    }

    public static datosTecnicos getInstance() {
        return instance;
    }

    public void setIdMaterial(int idMaterial){
        this.idMaterial.set(idMaterial);
    }
    public int getIdMaterial(){
        return idMaterial.get();
    }
    public IntegerProperty idMaterialProperty(){
        return idMaterial;
    }
    public void setPeso(String peso){
        this.peso.set(Double.parseDouble(peso));
    }
    public double getPeso(){
        return peso.get();
    }
    public DoubleProperty pesoProperty(){
        return peso;
    }
    public void setColor(String color){
        this.color.set(color);
    }
    public String getColor(){
        return color.get();
    }
    public StringProperty colorProperty(){
        return color;
    }
    public void setHerramientaUso(String herramientaUso){
        this.herramientaUso.set(herramientaUso);
    }
    public String getHerramientaUso(){
        return herramientaUso.get();
    }
    public StringProperty herramientaUsoProperty(){
        return herramientaUso;
    }

    public void setLugarUso(String lugarUso){
        this.lugarUso.set(lugarUso);
    }
    public String getLugarUso(){
        return lugarUso.get();
    }

    public StringProperty lugarUsoProperty(){
        return lugarUso;
    }
    public void setRecipiente(String recipiente){
        this.recipiente.set(recipiente);
    }
    public String getRecipiente(){
        return recipiente.get();
    }
    public StringProperty recipienteProperty(){
        return recipiente;
    }
    public void setComposicion(String composicion){
        this.composicion.set(composicion);
    }
    public String getComposicion(){
        return composicion.get();
    }
    public StringProperty composicionProperty(){
        return composicion;
    }
}