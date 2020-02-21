package peru.volcanes.volcanesper.m_model;
public class erupciones {
    String fuente;
    String observaciones;
    String year;

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public erupciones(String year, String observaciones, String fuente) {
        this.year = year;
        this.observaciones = observaciones;
        this.fuente = fuente;
    }

    public erupciones() {
    }

}