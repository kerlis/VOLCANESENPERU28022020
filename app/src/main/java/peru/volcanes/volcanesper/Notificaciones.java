package peru.volcanes.volcanesper;
import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class Notificaciones {


    private String id;
    private String mensaje;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public Notificaciones(String id, String mensaje) {
        this.id = id;
        this.mensaje = mensaje;
    }

    public Notificaciones() {
    }

}