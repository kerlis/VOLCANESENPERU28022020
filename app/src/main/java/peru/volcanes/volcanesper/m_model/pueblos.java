package peru.volcanes.volcanesper.m_model;
public class pueblos {
    String latitud;
    String longitud;
    String pueblo;
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
    public String getPueblo() {
        return pueblo;
    }
    public void setPueblo(String pueblo) {
        this.pueblo = pueblo;
    }
    public pueblos(String latitud, String longitud, String pueblo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.pueblo = pueblo;
    }
    public pueblos() {
    }
}