package peru.volcanes.volcanesper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class Datosnotificacion extends AppCompatActivity {
    TextView datos;
    String dato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_notificacion);
        datos = (TextView) findViewById(R.id.datos);
        Intent i=this.getIntent();
        dato = i.getExtras().getString("NOTIFICACIONDATA");
        datos.setText(dato);


      //  Bundle b = getIntent().getExtras();// add these lines of code to get data from notification
      //  String someData = b.getString("title");


        Toast.makeText(Datosnotificacion.this,"Volcán:  " + dato, Toast.LENGTH_LONG).show();

    }





}