 package peru.volcanes.volcanesper;
 public class movimientossismicos {
    String categoria;
    String epicentro;
    String fechautc;
    String horautc;
    String intenso;
    String lat;
    String lon;
    String magnitud;
    String profundidad;
    String referencia;
    String simulacro;
    String tiporeporte;
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getEpicentro() {
        return epicentro;
    }
    public void setEpicentro(String epicentro) {
        this.epicentro = epicentro;
    }
    public String getFechautc() {
        return fechautc;
    }

    public void setFechautc(String fechautc) {
        this.fechautc = fechautc;
    }

    public String getHorautc() {
        return horautc;
    }

    public void setHorautc(String horautc) {
        this.horautc = horautc;
    }

    public String getIntenso() {
        return intenso;
    }

    public void setIntenso(String intenso) {
        this.intenso = intenso;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    public String getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(String profundidad) {
        this.profundidad = profundidad;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getSimulacro() {
        return simulacro;
    }

    public void setSimulacro(String simulacro) {
        this.simulacro = simulacro;
    }

    public String getTiporeporte() {
        return tiporeporte;
    }

    public void setTiporeporte(String tiporeporte) {
        this.tiporeporte = tiporeporte;
    }


    public movimientossismicos(String categoria, String epicentro, String fechautc, String horautc, String intenso, String lat, String lon, String magnitud, String profundidad, String referencia, String simulacro, String tiporeporte) {
        this.categoria = categoria;
        this.epicentro = epicentro;
        this.fechautc = fechautc;
        this.horautc = horautc;
        this.intenso = intenso;
        this.lat = lat;
        this.lon = lon;
        this.magnitud = magnitud;
        this.profundidad = profundidad;
        this.referencia = referencia;
        this.simulacro = simulacro;
        this.tiporeporte = tiporeporte;
    }


    public movimientossismicos() {
    }
}