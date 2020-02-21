package peru.volcanes.volcanesper.m_model;
public class reportealertadecenizastwo {
    Integer orden;
    String volcan;
    String pueblos;
    String tipodevento;
    String direccion;
    String radio;
    String fecha;
    String hora;
    String horautc;
    String observaciones;
    String recomendaciones;
    String simulacro;
    public Integer getOrden() {
        return orden;
    }
    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    public String getVolcan() {
        return volcan;
    }
    public void setVolcan(String volcan) {
        this.volcan = volcan;
    }
    public String getPueblos() {
        return pueblos;
    }
    public void setPueblos(String pueblos) {
        this.pueblos = pueblos;
    }
    public String getTipodevento() {
        return tipodevento;
    }
    public void setTipodevento(String tipodevento) {
        this.tipodevento = tipodevento;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getRadio() {
        return radio;
    }
    public void setRadio(String radio) {
        this.radio = radio;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getHorautc() {
        return horautc;
    }
    public void setHorautc(String horautc) {
        this.horautc = horautc;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getRecomendaciones() {
        return recomendaciones;
    }
    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
    public String getSimulacro() {
        return simulacro;
    }
    public void setSimulacro(String simulacro) {
        this.simulacro = simulacro;
    }
    public reportealertadecenizastwo(Integer orden, String volcan, String pueblos, String tipodevento, String direccion, String radio, String fecha, String hora, String horautc, String observaciones, String recomendaciones, String simulacro) {
        this.orden = orden;
        this.volcan = volcan;
        this.pueblos = pueblos;
        this.tipodevento = tipodevento;
        this.direccion = direccion;
        this.radio = radio;
        this.fecha = fecha;
        this.hora = hora;
        this.horautc = horautc;
        this.observaciones = observaciones;
        this.recomendaciones = recomendaciones;
        this.simulacro = simulacro;
    }
    public reportealertadecenizastwo() {
    }
}