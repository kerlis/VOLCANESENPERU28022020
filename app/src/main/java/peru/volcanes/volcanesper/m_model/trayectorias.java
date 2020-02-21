package peru.volcanes.volcanesper.m_model;
import java.lang.reflect.Array;
public class trayectorias {
    Array transicion;
    String fin;
    String inicio;
    String tiempo;
    String volcan;
    String codigo;
    String estadovolcan;
    public String getFin() {
        return fin;
    }
    public void setFin(String fin) {
        this.fin = fin;
    }
    public String getInicio() {
        return inicio;
    }
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }
    public String getTiempo() {
        return tiempo;
    }
    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
    public Array getTransicion() {
        return transicion;
    }
    public void setTransicion(Array transicion) {
        this.transicion = transicion;
    }
    public String getVolcan() {
        return volcan;
    }
    public void setVolcan(String volcan) {
        this.volcan = volcan;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getEstadovolcan() {
        return estadovolcan;
    }
    public void setEstadovolcan(String estadovolcan) {
        this.estadovolcan = estadovolcan;
    }
    public trayectorias(String codigo, String estadovolcan, String fin, String inicio, String tiempo, Array transicion, String volcan) {
        this.codigo = codigo;
        this.estadovolcan = estadovolcan;
        this.fin = fin;
        this.inicio = inicio;
        this.tiempo = tiempo;
        this.transicion = transicion;
        this.volcan = volcan;
    }
    public trayectorias() {
    }
}