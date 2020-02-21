package peru.volcanes.volcanesper.m_model;
public class dispersion {
    String horasreporte;
    Integer orden;
    String enlaceurl;
    public String getEnlaceurl() {
        return enlaceurl;
    }
    public void setEnlaceurl(String enlaceurl) {
        this.enlaceurl = enlaceurl;
    }
    public Integer getOrden() {
        return orden;
    }
    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    public String getHorasreporte() {
        return horasreporte;
    }
    public void setHorasreporte(String horasreporte) {
        this.horasreporte = horasreporte;
    }
    public dispersion(String enlaceurl, Integer orden, String horasreporte) {
        this.enlaceurl = enlaceurl;
        this.orden = orden;
        this.horasreporte = horasreporte;
    }
    public dispersion() {
    }
}