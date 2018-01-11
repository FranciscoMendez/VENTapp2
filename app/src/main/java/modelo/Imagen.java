package modelo;

import java.io.Serializable;
import java.util.Arrays;

public class Imagen implements Serializable{

    private int codigoImagen;
    private String nombreImagen;
    private String descripcionImagen;
    private byte[] img;
    private Propiedad propiedad;

    public int getCodigoImagen() {
        return codigoImagen;
    }

    public void setCodigoImagen(int codigoImagen) {
        this.codigoImagen = codigoImagen;
    }

    public String getDescripcionImagen() {
        return descripcionImagen;
    }

    public void setDescripcionImagen(String descripcionImagen) {
        this.descripcionImagen = descripcionImagen;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "codigoImagen=" + codigoImagen +
                ", nombreImagen='" + nombreImagen + '\'' +
                ", descripcionImagen='" + descripcionImagen + '\'' +
                ", img=" + Arrays.toString(img) +
                ", propiedad=" + propiedad +
                '}';
    }
}
