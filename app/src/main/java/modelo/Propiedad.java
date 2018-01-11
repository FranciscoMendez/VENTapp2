package modelo;

import java.io.Serializable;
import java.util.List;

public class Propiedad implements Serializable{

    private int codigo;
    private String direccion;
    private String urlVideo;
    private String descripcion;
    private String estado;
    private String tipo;
    private String costo;
    private Sector sector;

    // relacion bidireccional muchas propiedades pueden pertenecer a una persona

    private Persona persona;

    // relacion bidireccional muchas propiedades pueden pertenecer a una categoria

    private Categoria categoria;


    private double longuitud;


    private double latitud;

    private List<Imagen> imagenes;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(double longuitud) {
        this.longuitud = longuitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    @Override
    public String toString() {
        return "Propiedad{" +
                "codigo=" + codigo +
                ", direccion='" + direccion + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", tipo='" + tipo + '\'' +
                ", costo='" + costo + '\'' +
                ", sector=" + sector +
                ", persona=" + persona +
                ", categoria=" + categoria +
                ", longuitud=" + longuitud +
                ", latitud=" + latitud +
                ", imagenes=" + imagenes +
                '}';
    }
}
