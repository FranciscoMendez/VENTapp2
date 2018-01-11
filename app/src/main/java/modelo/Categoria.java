package modelo;

import java.io.Serializable;
import java.util.List;

public class Categoria implements Serializable{

    private int codigo;
    private String alias;
    private String descripcion;
    private List<Propiedad> propiedades;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }


    @Override
    public String toString() {
        return "Categoria{" +
                "codigo=" + codigo +
                ", alias='" + alias + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", propiedades=" + propiedades +
                '}';
    }
}
