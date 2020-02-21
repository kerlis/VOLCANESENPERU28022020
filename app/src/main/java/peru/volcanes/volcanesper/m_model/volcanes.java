package peru.volcanes.volcanesper.m_model;
import java.io.Serializable;
public class volcanes implements Serializable {
    private static final long serialVersionUID = 8799656478674716638L;
    private String fechaactualizacion;
    private String horaactualizacion;
    private String actividadreciente;
    private String altura;
    private String codigo;
    public String alertavolcanica;
    private String diametrocrater;
    private String estado;
    private String glaciares;
    private String imagen;
    private String latitud;
    private String longitud;
    private String monitoreo;
    private String nombre;
    private String tipicaerupcion;
    private String tipo;
    private String camaraurl;
    private String sismograma;
    private String resena;
    private String region;
    private Integer orden;
    private String ultimaerupcion;
    private String mapasismico;
    private String proyeccionsenamhiurl;
    private String riesgo;
    public String getProyeccionsenamhiurl() {
        return proyeccionsenamhiurl;
    }
    public void setProyeccionsenamhiurl(String proyeccionsenamhiurl) {
        this.proyeccionsenamhiurl = proyeccionsenamhiurl;
    }
    public String getMapasismico() {
        return mapasismico;
    }
    public void setMapasismico(String mapasismico) {
        this.mapasismico = mapasismico;
    }
    public String getActividadreciente() {
        return actividadreciente;
    }
    public void setActividadreciente(String actividadreciente) {
        this.actividadreciente = actividadreciente;
    }
    public String getAlertavolcanica() {
        return alertavolcanica;
    }
    public void setAlertavolcanica(String alertavolcanica) {
        this.alertavolcanica = alertavolcanica;
    }
    public String getAltura() {
        return altura;
    }
    public void setAltura(String altura) {
        this.altura = altura;
    }
    public String getCamaraurl() {
        return camaraurl;
    }
    public void setCamaraurl(String camaraurl) {
        this.camaraurl = camaraurl;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getDiametrocrater() {
        return diametrocrater;
    }
    public void setDiametrocrater(String diametrocrater) {
        this.diametrocrater = diametrocrater;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getFechaactualizacion() {
        return fechaactualizacion;
    }
    public void setFechaactualizacion(String fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }
    public String getGlaciares() {
        return glaciares;
    }
    public void setGlaciares(String glaciares) {
        this.glaciares = glaciares;
    }
    public String getHoraactualizacion() {
        return horaactualizacion;
    }
    public void setHoraactualizacion(String horaactualizacion) {
        this.horaactualizacion = horaactualizacion;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getMonitoreo() {
        return monitoreo;
    }
    public void setMonitoreo(String monitoreo) {
        this.monitoreo = monitoreo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getOrden() {
        return orden;
    }
    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getResena() {
        return resena;
    }
    public void setResena(String resena) {
        this.resena = resena;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public String getSismograma() {
        return sismograma;
    }
    public void setSismograma(String sismograma) {
        this.sismograma = sismograma;
    }
    public String getTipicaerupcion() {
        return tipicaerupcion;
    }
    public void setTipicaerupcion(String tipicaerupcion) {
        this.tipicaerupcion = tipicaerupcion;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getUltimaerupcion() {
        return ultimaerupcion;
    }
    public void setUltimaerupcion(String ultimaerupcion) {
        this.ultimaerupcion = ultimaerupcion;
    }
    public String getRiesgo() {
        return riesgo;
    }
    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }
    public volcanes(String fechaactualizacion, String horaactualizacion, String actividadreciente, String altura, String codigo, String alertavolcanica, String diametrocrater, String estado, String glaciares, String imagen, String latitud, String longitud, String monitoreo, String nombre, String tipicaerupcion, String tipo, String camaraurl, String sismograma, String resena, String region, Integer orden, String ultimaerupcion, String mapasismico, String proyeccionsenamhiurl, String riesgo) {
        this.fechaactualizacion = fechaactualizacion;
        this.horaactualizacion = horaactualizacion;
        this.actividadreciente = actividadreciente;
        this.altura = altura;
        this.codigo = codigo;
        this.alertavolcanica = alertavolcanica;
        this.diametrocrater = diametrocrater;
        this.estado = estado;
        this.glaciares = glaciares;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longitud = longitud;
        this.monitoreo = monitoreo;
        this.nombre = nombre;
        this.tipicaerupcion = tipicaerupcion;
        this.tipo = tipo;
        this.camaraurl = camaraurl;
        this.sismograma = sismograma;
        this.resena = resena;
        this.region = region;
        this.orden = orden;
        this.ultimaerupcion = ultimaerupcion;
        this.mapasismico = mapasismico;
        this.proyeccionsenamhiurl = proyeccionsenamhiurl;
        this.riesgo = riesgo;
    }
    public volcanes() {
    }
}