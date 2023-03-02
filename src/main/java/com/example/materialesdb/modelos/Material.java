package com.example.materialesdb.modelos;

import javafx.beans.property.*;

public class Material {

    private static final Material instance = new Material();
    private final IntegerProperty idMaterial;
    private final StringProperty nombreMaterial;
    private final StringProperty fabricante;
    private final StringProperty material;
    private final DoubleProperty precio;
    private final StringProperty indicadorPeligro;
    private final StringProperty fechaInicioVenta;
    private final StringProperty fechaFinVenta;


    /**
     *
     * Constructor pasandole parametros para el objeto de Material
     *
     * @param idMaterial
     * @param nombreMaterial
     * @param fabricante
     * @param material
     * @param precio
     * @param indicadorPeligro
     * @param fechaInicioVenta
     * @param fechaFinVenta
     */
    public Material(int idMaterial, String nombreMaterial, String fabricante, String material, double precio, String indicadorPeligro, String fechaInicioVenta, String fechaFinVenta) {
        this.idMaterial = new SimpleIntegerProperty(idMaterial);
        this.nombreMaterial = new SimpleStringProperty(nombreMaterial);
        this.fabricante = new SimpleStringProperty(fabricante);
        this.material = new SimpleStringProperty(material);
        this.precio = new SimpleDoubleProperty(precio);
        this.indicadorPeligro = new SimpleStringProperty(indicadorPeligro);
        this.fechaInicioVenta = new SimpleStringProperty(fechaInicioVenta);
        this.fechaFinVenta = new SimpleStringProperty(fechaFinVenta);
    }

    /**
     * Constructor sin pasarle parametros para el objeto de Material
     */
    public Material() {
        this.idMaterial = new SimpleIntegerProperty(0);
        this.nombreMaterial = new SimpleStringProperty("");
        this.fabricante = new SimpleStringProperty("");
        this.material = new SimpleStringProperty("");
        this.precio = new SimpleDoubleProperty(0.00);
        this.indicadorPeligro = new SimpleStringProperty("");
        this.fechaInicioVenta = new SimpleStringProperty("");
        this.fechaFinVenta = new SimpleStringProperty("");
    }

    /**
     * Devuelve un string con los valores del objeto Material.
     * @return
     */
    @Override
    public String toString() {
        return "IDMaterial: "+idMaterial+
                ", Nombre Material: "+nombreMaterial+
                ", Fabricante: "+fabricante+
                ", Material: "+material+
                ", Precio: "+precio+
                ", Indicador de Peligro: "+indicadorPeligro+
                ", fechaInicioVenta: "+fechaInicioVenta+
                ", fechaFinVenta: "+fechaFinVenta+".";
    }

    public static Material getInstance() {
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
    public void setNombreMaterial(String nombreMaterial){
        this.nombreMaterial.set(nombreMaterial);
    }
    public String getNombreMaterial(){
        return nombreMaterial.get();
    }
    public StringProperty nombreMaterialProperty(){
        return nombreMaterial;
    }
    public void setFabricante(String fabricante){
        this.fabricante.set(fabricante);
    }
    public String getFabricante(){
        return fabricante.get();
    }
    public StringProperty fabricanteProperty(){
        return fabricante;
    }
    public void setMaterial(String material){
        this.material.set(material);
    }
    public String getMaterial(){
        return material.get();
    }
    public StringProperty materialProperty(){
        return material;
    }
    public void setPrecio(Double precio){
        this.precio.set(precio);
    }
    public Double getPrecio(){
        return precio.get();
    }
    public DoubleProperty precioProperty(){
        return precio;
    }
    public void setIndicadorPeligro(String indicadorPeligro){
        this.indicadorPeligro.set(indicadorPeligro);
    }
    public String getIndicadorPeligro(){
        return indicadorPeligro.get();
    }

    public StringProperty indicadorPeligroProperty(){
        return indicadorPeligro;
    }
    public void setFechaInicioVenta(String fechaInicioVenta){
        this.fechaInicioVenta.set(fechaInicioVenta);
    }
    public String getFechaInicioVenta(){
        return fechaInicioVenta.get();
    }
    public StringProperty fechaInicioVentaProperty(){
        return fechaInicioVenta;
    }
    public void setFechaFinVenta(String fechaFinVenta){
        this.fechaFinVenta.set(fechaFinVenta);
    }
    public String getFechaFinVenta(){
        return fechaFinVenta.get();
    }
    public StringProperty fechaFinVentaProperty(){
        return fechaFinVenta;
    }
}