package peru.volcanes.volcanesper;

public class Utilidades {

    public static final String TABLA_NOTIFICACIONES="notificaciones";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_MENSAJE="mensaje";

    public static final String  CREAR_TABLA_NOTIFICACIONES = "CREATE TABLE" +
            " "+TABLA_NOTIFICACIONES+" ("+CAMPO_ID+" " +
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_MENSAJE+ " TEXT)";

}
