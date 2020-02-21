package peru.volcanes.volcanesper.m_model;
public class reporteactividad {


    String tiponotificacion;
    String codigovolcan;
    String fecha;
    String hora;
    String horautc;
    String pdfurl;
    String simulacro;
    String nombrereporte;
    String coloralerta;
    String analisis;
    String conclusiones;



/*
    analisis:
            "16-31 de diciembre de 2018"
    codigovolcan:x
            "1493157379002"
    coloralerta:
            "verde"
    conclusiones:
            "El volcán Ubinas mantiene niveles bajos de acti..."
    fecha:x
            "2/01/2019"
    hora:x
            "15:40"
    horautc:x
            "20:40"
    nombrereporte:
            "2019-24"
    pdfurl:x
            "https://ovs.igp.gob.pe/sites/ovs.igp.gob.pe/fil..."
    simulacro:x
            "no"
    tiponotificacion:
            "n04"



            - volcan: $scope.reporte.volcan,
            - fecha: $scope.reporte.fecha,
            - hora: $scope.reporte.hora,
            -  horautc: $scope.reporte.horautc,
            -  reporteactividad: $scope.reporte.reporteactividad,
            -  simulacro: $scope.reporte.simulacro,

            - tiponotificacion: $scope.reporte.tiponotificacion = "n02",


    coloralerta: $scope.reporte.coloralerta,
    analisis: $scope.reporte.analisis,
    conclusiones: $scope.reporte.conclusiones
*/

    public String getCodigovolcan() {
        return codigovolcan;
    }

    public void setCodigovolcan(String codigovolcan) {
        this.codigovolcan = codigovolcan;
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

    public String getPdfurl() {
        return pdfurl;
    }

    public void setPdfurl(String pdfurl) {
        this.pdfurl = pdfurl;
    }

    public String getSimulacro() {
        return simulacro;
    }

    public void setSimulacro(String simulacro) {
        this.simulacro = simulacro;
    }

    public String getTiponotificacion() {
        return tiponotificacion;
    }

    public void setTiponotificacion(String tiponotificacion) {
        this.tiponotificacion = tiponotificacion;
    }

    public String getNombrereporte() {
        return nombrereporte;
    }

    public void setNombrereporte(String nombrereporte) {
        this.nombrereporte = nombrereporte;
    }

    public String getColoralerta() {
        return coloralerta;
    }

    public void setColoralerta(String coloralerta) {
        this.coloralerta = coloralerta;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }


    public reporteactividad(String codigovolcan, String fecha, String hora, String horautc, String pdfurl, String simulacro, String tiponotificacion, String nombrereporte, String coloralerta, String analisis, String conclusiones) {
        this.codigovolcan = codigovolcan;
        this.fecha = fecha;
        this.hora = hora;
        this.horautc = horautc;
        this.pdfurl = pdfurl;
        this.simulacro = simulacro;
        this.tiponotificacion = tiponotificacion;
        this.nombrereporte = nombrereporte;
        this.coloralerta = coloralerta;
        this.analisis = analisis;
        this.conclusiones = conclusiones;
    }

    public reporteactividad() {
    }
}
