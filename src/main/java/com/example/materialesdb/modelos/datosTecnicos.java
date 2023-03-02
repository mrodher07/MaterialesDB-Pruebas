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

    /**
     *
     * Constructor pasandole parametros para el objeto de datosTecnicos
     *
     * @param idMaterial
     * @param peso
     * @param color
     * @param herramientaUso
     * @param lugarUso
     * @param recipiente
     * @param composicion
     */
    public datosTecnicos(int idMaterial, Double peso, String color, String herramientaUso, String lugarUso, String recipiente, String composicion) {
        this.idMaterial = new SimpleIntegerProperty(idMaterial);
        this.peso = new SimpleDoubleProperty(peso);
        this.color = new SimpleStringProperty(color);
        this.herramientaUso = new SimpleStringProperty(herramientaUso);
        this.lugarUso = new SimpleStringProperty(lugarUso);
        this.recipiente = new SimpleStringProperty(recipiente);
        this.composicion = new SimpleStringProperty(composicion);
    }

    /**
     * Constructor sin pasarle parametros para el objeto de datosTecnicos
     */
    public datosTecnicos() {
        this.idMaterial = new SimpleIntegerProperty(0);
        this.peso = new SimpleDoubleProperty(0.00);
        this.color = new SimpleStringProperty("");
        this.herramientaUso = new SimpleStringProperty("");
        this.lugarUso = new SimpleStringProperty("");
        this.recipiente = new SimpleStringProperty("");
        this.composicion = new SimpleStringProperty("");
    }

    /**
     * Funcion toString que muestra todos los datos pertenecientes al objeto datosTecnicos
     * @return
     */
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

    /**
     *
     * @return
     */
    public static datosTecnicos getInstance() {
        return instance;
    }

    /**
     *
     * @param idMaterial
     */
    public void setIdMaterial(int idMaterial){
        this.idMaterial.set(idMaterial);
    }

    /**
     *
     * @return
     */
    public int getIdMaterial(){
        return idMaterial.get();
    }

    /**
     *
     * @return
     */
    public IntegerProperty idMaterialProperty(){
        return idMaterial;
    }

    /**
     *
     * @param peso
     */
    public void setPeso(String peso){
        this.peso.set(Double.parseDouble(peso));
    }

    /**
     *
     * @return
     */
    public double getPeso(){
        return peso.get();
    }

    /**
     *
     * @return
     */
    public DoubleProperty pesoProperty(){
        return peso;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color){
        this.color.set(color);
    }

    /**
     *
     * @return
     */
    public String getColor(){
        return color.get();
    }

    /**
     *
     * @return
     */
    public StringProperty colorProperty(){
        return color;
    }

    /**
     *
     * @param herramientaUso
     */
    public void setHerramientaUso(String herramientaUso){
        this.herramientaUso.set(herramientaUso);
    }

    /**
     *
     * @return
     */
    public String getHerramientaUso(){
        return herramientaUso.get();
    }

    /**
     *
     * @return
     */
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