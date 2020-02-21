package peru.volcanes.volcanesper.m_model;
public class alertacenizas {
    String altura;
    String direccion_dispersion;
    String fecha;
    String fecha_detalle;
    String hora_detalle;
    String hora;
    String observaciones;
    String radio_dispersion_ceniza;
    String recomendaciones;
    String tipo_evento;
    String zonas_afectadas;
    String codigovolcan;
    String codigoalerta;

    public String getAltura() {
        return altura;
    }
    public void setAltura(String altura) {
        this.altura = altura;
    }
    public String getDireccion_dispersion() {
        return direccion_dispersion;
    }
    public void setDireccion_dispersion(String direccion_dispersion) {
        this.direccion_dispersion = direccion_dispersion;
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
    public String getFecha_detalle() {
        return fecha_detalle;
    }
    public void setFecha_detalle(String fecha_detalle) {
        this.fecha_detalle = fecha_detalle;
    }
    public String getHora_detalle() {
        return hora_detalle;
    }
    public void setHora_detalle(String hora_detalle) {
        this.hora_detalle = hora_detalle;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getRadio_dispersion_ceniza() {
        return radio_dispersion_ceniza;
    }
    public void setRadio_dispersion_ceniza(String radio_dispersion_ceniza) {
        this.radio_dispersion_ceniza = radio_dispersion_ceniza;
    }
    public String getRecomendaciones() {
        return recomendaciones;
    }
    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
    public String getTipo_evento() {
        return tipo_evento;
    }
    public void setTipo_evento(String tipo_evento) {
        this.tipo_evento = tipo_evento;
    }
    public String getZonas_afectadas() {
        return zonas_afectadas;
    }
    public void setZonas_afectadas(String zonas_afectadas) {
        this.zonas_afectadas = zonas_afectadas;
    }
    public String getCodigovolcan() {
        return codigovolcan;
    }
    public void setCodigovolcan(String codigovolcan) {
        this.codigovolcan = codigovolcan;
    }
    public String getCodigoalerta() {
        return codigoalerta;
    }
    public void setCodigoalerta(String codigoalerta) {
        this.codigoalerta = codigoalerta;
    }
    public alertacenizas(String altura, String direccion_dispersion, String fecha, String fecha_detalle, String hora_detalle, String hora, String observaciones, String radio_dispersion_ceniza, String recomendaciones, String tipo_evento, String zonas_afectadas, String codigovolcan, String codigoalerta) {
        this.altura = altura;
        this.direccion_dispersion = direccion_dispersion;
        this.fecha = fecha;
        this.fecha_detalle = fecha_detalle;
        this.hora_detalle = hora_detalle;
        this.hora = hora;
        this.observaciones = observaciones;
        this.radio_dispersion_ceniza = radio_dispersion_ceniza;
        this.recomendaciones = recomendaciones;
        this.tipo_evento = tipo_evento;
        this.zonas_afectadas = zonas_afectadas;
        this.codigovolcan = codigovolcan;
        this.codigoalerta = codigoalerta;
    }
    public alertacenizas() {
    }
}