package peru.volcanes.volcanesper.m_model;
public class reporte {
    String fecha;
    String nombrereporte;
    String pdfurl;
    String codigovolcan;
    Integer orden;
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getNombrereporte() {
        return nombrereporte;
    }
    public void setNombrereporte(String nombrereporte) {
        this.nombrereporte = nombrereporte;
    }
    public String getPdfurl() {
        return pdfurl;
    }
    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }
    public String getCodigovolcan() {
        return codigovolcan;
    }
    public void setCodigovolcan(String codigovolcan) {
        this.codigovolcan = codigovolcan;
    }
    public Integer getOrden() {
        return orden;
    }
    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    public reporte(String fecha, String nombrereporte, String pdfurl, String codigovolcan, Integer orden) {
        this.fecha = fecha;
        this.nombrereporte = nombrereporte;
        this.pdfurl = pdfurl;
        this.codigovolcan = codigovolcan;
        this.orden = orden;
    }
    public reporte() {
    }
}