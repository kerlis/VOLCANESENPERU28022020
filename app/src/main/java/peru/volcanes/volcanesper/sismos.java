package peru.volcanes.volcanesper;
public class sismos {
    String categoria;
    String epicentro;
    String fecha;
    String hora;
    String intenso;
    String latitud;
    String longitud;
    String magnitud;
    String profundidad;
    String referencia;
    String simulacro;
    String tiporeporte;
    String codigovolcan;
    String codigosismo;
    String distancia;
    String latitudvolcan;
    String longitudvolcan;

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

    public String getIntenso() {
        return intenso;
    }

    public void setIntenso(String intenso) {
        this.intenso = intenso;
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


    public String getCodigovolcan() {
        return codigovolcan;
    }

    public void setCodigovolcan(String codigovolcan) {
        this.codigovolcan = codigovolcan;
    }

    public String getCodigosismo() {
        return codigosismo;
    }

    public void setCodigosismo(String codigosismo) {
        this.codigosismo = codigosismo;
    }


    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }


    public String getLatitudvolcan() {
        return latitudvolcan;
    }

    public void setLatitudvolcan(String latitudvolcan) {
        this.latitudvolcan = latitudvolcan;
    }

    public String getLongitudvolcan() {
        return longitudvolcan;
    }

    public void setLongitudvolcan(String longitudvolcan) {
        this.longitudvolcan = longitudvolcan;
    }

    public sismos(String categoria, String codigosismo, String codigovolcan, String distancia, String epicentro, String fecha, String hora, String intenso, String latitud, String latitudvolcan, String longitud, String longitudvolcan, String magnitud, String profundidad, String referencia, String simulacro, String tiporeporte) {
        this.categoria = categoria;
        this.codigosismo = codigosismo;
        this.codigovolcan = codigovolcan;
        this.distancia = distancia;
        this.epicentro = epicentro;
        this.fecha = fecha;
        this.hora = hora;
        this.intenso = intenso;
        this.latitud = latitud;
        this.latitudvolcan = latitudvolcan;
        this.longitud = longitud;
        this.longitudvolcan = longitudvolcan;
        this.magnitud = magnitud;
        this.profundidad = profundidad;
        this.referencia = referencia;
        this.simulacro = simulacro;
        this.tiporeporte = tiporeporte;
    }

    public sismos() {
    }
}