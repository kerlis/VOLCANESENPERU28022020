package peru.volcanes.volcanesper.m_model;
public class localidades {
    String depatamento;
    Double latitud;
    String localidad;
    Double longitud;
    String provincia;
    public String getDepatamento() {
        return depatamento;
    }
    public void setDepatamento(String depatamento) {
        this.depatamento = depatamento;
    }
    public Double getLatitud() {
        return latitud;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    public Double getLongitud() {
        return longitud;
    }
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public localidades(String depatamento, Double latitud, String localidad, Double longitud, String provincia) {
        this.depatamento = depatamento;
        this.latitud = latitud;
        this.localidad = localidad;
        this.longitud = longitud;
        this.provincia = provincia;
    }
    public localidades() {
    }
}