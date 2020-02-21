package peru.volcanes.volcanesper;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import peru.volcanes.volcanesper.m_model.Ultimanotificacionobj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Periodwork extends Worker {

    String Codigovolcan_reporteactividad;
    String Fecha_reporteactividad;
    String Hora_reporteactividad;
    String Horautc_reporteactividad;
    String Pdfurl_reporteactividad;
    String Simulacro_reporteactividad;
    String Tiponotificacion_reporteactividad;
    String Nombrereporte_reporteactividad;
    String Coloralerta_reporteactividad;
    String Analisis_reporteactividad;
    String Conclusiones_reporteactividad;

    String Hora_lahar;
    String Observaciones_lahar;
    String Simulacro_lahar;
    String Tipodevento_lahar;
    String Tiponotificacion_lahar;
    String Volcan_lahar;
    String Horautc_lahar;
    String Fecha_lahar;

    String Tiponotificacion_cenizas;
    String Volcan_cenizas;
    String Pueblos_cenizas;
    String Tipodevento_cenizas;
    String Direccion_cenizas;
    String Radio_cenizas;
    String Fecha_cenizas;
    String Hora_cenizas;
    String Horautc_cenizas;
    String Recomendaciones_cenizas;
    String Observaciones_cenizas;
    String Simulacro_cenizas;

    static final int READ_BLOCK_SIZE = 100;


    String TAG_REGISTER = null;
    String valorsonido = null;
    String valornotificacion = null;
    String valortipo = null;
    String valorringtone  = null;
    String sonido_notificacion = null;
    Integer valorentero   = 0;
    String valorramdomesound = null;


    String ultimanotificacionval = null;


    String not_lahar, notcenizas, notreporte1, notreporte2;


    //String valorramdomesound = null;

    private static final String TAG = "Periodwork";

    public Periodwork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Conexionsql conn = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
        SQLiteDatabase db = conn.getReadableDatabase();
        ArrayList<String> array_list = new ArrayList<String>();
        Cursor res = db.rawQuery( "SELECT * FROM "+Utilidades.TABLA_NOTIFICACIONES + " ORDER BY id DESC LIMIT 1", null );
        res.moveToFirst();

        String aqui =  res.getString(1);

        while(res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("mensaje")));
            res.moveToNext();
        }

        String valor3  = aqui;
        String valorextraido  = valor3.split("&")[0];
        String volcan = valor3.split("&")[1];
        String tipodevento   = valor3.split("&")[2];
        String fecha = valor3.split("&")[3];
        String hora = valor3.split("&")[4];
        String observaciones = valor3.split("&")[5];
        String simulacro = valor3.split("&")[6];
        String horautc  = valor3.split("&")[7];


        String file_ultimanotificacion = getApplicationContext().getFilesDir() + "/"+"ultima_notificacion";
        try {
            FileInputStream fileultimanotificacion = new FileInputStream(new File(file_ultimanotificacion));
            InputStreamReader InputRead_vibrar = new InputStreamReader(fileultimanotificacion);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead_vibrar.read(inputBuffer);
            ultimanotificacionval = String.copyValueOf(inputBuffer, 0, charRead);
            InputRead_vibrar.close();
            Log.d(TAG, "VIBRATION: " + valorsonido);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("ULTIMA NOTIFICACION: ", ultimanotificacionval);


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ConnectivityManager cmanager = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            info = Objects.requireNonNull(cmanager).getActiveNetworkInfo();
        }
        if (info != null && info.isConnected()) {
            ultimanotificacion(aqui, valorextraido, ultimanotificacionval);
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                Log.d("CONEXION","EXISTE CONECTIVIDAD");
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            }
        } else {
            Log.d("CONEXION","NO EXISTE CONECTIVIDAD");
        }

        return Result.retry();
    }

    private void ultimanotificacion(String datoslast, String tipo, String valormemoria) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance("https://volcanesperu-d84cf.firebaseio.com/").getReference("actividadvolcanica");
        mDatabase.keepSynced(true);
        mDatabase.child("ultimosreportesdeactividades").orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Ultimanotificacionobj sreporte = dataSnapshot.getValue(Ultimanotificacionobj.class);
                guardar_pref(sreporte, datoslast, tipo, valormemoria);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Ultimanotificacionobj sreporte = dataSnapshot.getValue(Ultimanotificacionobj.class);
                guardar_pref(sreporte, datoslast, tipo, valormemoria);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Ultimanotificacionobj sreporte = dataSnapshot.getValue(Ultimanotificacionobj.class);
                guardar_pref(sreporte, datoslast, tipo, valormemoria);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Ultimanotificacionobj sreporte = dataSnapshot.getValue(Ultimanotificacionobj.class);
                guardar_pref(sreporte, datoslast, tipo, valormemoria);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void guardar_pref(Ultimanotificacionobj sreporte, String datos, String tipo, String valormemoria) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("Codigovolcan_reporteactividad", sreporte.getCodigovolcan_reporteactividad());
        editor.putString("Fecha_reporteactividad", sreporte.getFecha_reporteactividad());
        editor.putString("Hora_reporteactividad", sreporte.getHora_reporteactividad());
        editor.putString("Horautc_reporteactividad", sreporte.getHorautc_reporteactividad());
        editor.putString("Pdfurl_reporteactividad", sreporte.getPdfurl_reporteactividad());
        editor.putString("Simulacro_reporteactividad", sreporte.getSimulacro_reporteactividad());
        editor.putString("Tiponotificacion_reporteactividad", sreporte.getTiponotificacion_reporteactividad());
        editor.putString("Nombrereporte_reporteactividad", sreporte.getNombrereporte_reporteactividad());
        editor.putString("Coloralerta_reporteactividad", sreporte.getColoralerta_reporteactividad());
        editor.putString("Analisis_reporteactividad", sreporte.getAnalisis_reporteactividad());
        editor.putString("Conclusiones_reporteactividad", sreporte.getConclusiones_reporteactividad());

        editor.putString("Hora_lahar", sreporte.getHora_lahar());
        editor.putString("Observaciones_lahar", sreporte.getObservaciones_lahar());
        editor.putString("Simulacro_lahar", sreporte.getSimulacro_lahar());
        editor.putString("Tipodevento_lahar", sreporte.getTipodevento_lahar());
        editor.putString("Tiponotificacion_lahar", sreporte.getTiponotificacion_lahar());
        editor.putString("Volcan_lahar", sreporte.getVolcan_lahar());
        editor.putString("Horautc_lahar", sreporte.getHorautc_lahar());
        editor.putString("Fecha_lahar", sreporte.getFecha_lahar());

        editor.putString("Tiponotificacion_cenizas", sreporte.getTiponotificacion_cenizas());
        editor.putString("Volcan_cenizas", sreporte.getVolcan_cenizas());
        editor.putString("Pueblos_cenizas", sreporte.getPueblos_cenizas());
        editor.putString("Tipodevento_cenizas", sreporte.getTipodevento_cenizas());
        editor.putString("Direccion_cenizas", sreporte.getDireccion_cenizas());
        editor.putString("Radio_cenizas", sreporte.getRadio_cenizas());
        editor.putString("Fecha_cenizas", sreporte.getFecha_cenizas());
        editor.putString("Hora_cenizas", sreporte.getHora_cenizas());
        editor.putString("Horautc_cenizas", sreporte.getHorautc_cenizas());
        editor.putString("Recomendaciones_cenizas", sreporte.getRecomendaciones_cenizas());
        editor.putString("Observaciones_cenizas", sreporte.getObservaciones_cenizas());
        editor.putString("Simulacro_cenizas", sreporte.getSimulacro_cenizas());

        editor.putString("Codigovolcan", sreporte.getCodigovolcan());
        editor.apply();
        mostrar_ult_sismo(datos, tipo, valormemoria);
    }

    private void mostrar_ult_sismo(String datoslastdos, String tipo, String valormemoria) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        String ultimodato_1 = prefs.getString("Tiponotificacion_reporteactividad","");
        String ultimodato_2 = prefs.getString("Tiponotificacion_lahar","");
        String ultimodato_3 = prefs.getString("Tiponotificacion_cenizas","");

         Codigovolcan_reporteactividad  = prefs.getString("Codigovolcan_reporteactividad","");
         Fecha_reporteactividad = prefs.getString("Fecha_reporteactividad","");
         Hora_reporteactividad = prefs.getString("Hora_reporteactividad","");
         Horautc_reporteactividad = prefs.getString("Horautc_reporteactividad","");
         Pdfurl_reporteactividad = prefs.getString("Pdfurl_reporteactividad","");
         Simulacro_reporteactividad = prefs.getString("Simulacro_reporteactividad","");
         Tiponotificacion_reporteactividad = prefs.getString("Tiponotificacion_reporteactividad","");
         Nombrereporte_reporteactividad = prefs.getString("Nombrereporte_reporteactividad","");
         Coloralerta_reporteactividad = prefs.getString("Coloralerta_reporteactividad","");
         Analisis_reporteactividad = prefs.getString("Analisis_reporteactividad","");
         Conclusiones_reporteactividad = prefs.getString("Conclusiones_reporteactividad","");

         Hora_lahar = prefs.getString("Hora_lahar","");
         Observaciones_lahar = prefs.getString("Observaciones_lahar","");
         Simulacro_lahar = prefs.getString("Simulacro_lahar","");
         Tipodevento_lahar = prefs.getString("Tipodevento_lahar","");
         Tiponotificacion_lahar = prefs.getString("Tiponotificacion_lahar","");
         Volcan_lahar = prefs.getString("Volcan_lahar","");
         Horautc_lahar = prefs.getString("Horautc_lahar","");
         Fecha_lahar = prefs.getString("Fecha_lahar","");

         Tiponotificacion_cenizas = prefs.getString("Tiponotificacion_cenizas","");
         Volcan_cenizas = prefs.getString("Volcan_cenizas","");
         Pueblos_cenizas = prefs.getString("Pueblos_cenizas","");
         Tipodevento_cenizas = prefs.getString("Tipodevento_cenizas","");
         Direccion_cenizas = prefs.getString("Direccion_cenizas","");
         Radio_cenizas = prefs.getString("Radio_cenizas","");
         Fecha_cenizas = prefs.getString("Fecha_cenizas","");
         Hora_cenizas = prefs.getString("Hora_cenizas","");
         Horautc_cenizas = prefs.getString("Horautc_cenizas","");
         Recomendaciones_cenizas = prefs.getString("Recomendaciones_cenizas","");
         Observaciones_cenizas = prefs.getString("Observaciones_cenizas","");
         Simulacro_cenizas = prefs.getString("Simulacro_cenizas","");

        if (Tiponotificacion_lahar.equals("n01")) {
            String tiponotificacion2  = datoslastdos.split("&")[0];
            String volcan = datoslastdos.split("&")[1];
            String tipodevento  = datoslastdos.split("&")[2];
            String fecha  = datoslastdos.split("&")[3];
            String hora = datoslastdos.split("&")[4];
            String horautc  = datoslastdos.split("&")[5];
            String observaciones = datoslastdos.split("&")[6];
            String simulacro  = datoslastdos.split("&")[7];


            String tiponotificacion2_m  = valormemoria.split("&")[0];
            String volcan_m = valormemoria.split("&")[1];
            String tipodevento_m  = valormemoria.split("&")[2];
            String fecha_m  = valormemoria.split("&")[3];
            String hora_m = valormemoria.split("&")[4];
            String horautc_m  = valormemoria.split("&")[5];
            String observaciones_m = valormemoria.split("&")[6];
            String simulacro_m  = valormemoria.split("&")[7];



            if(tiponotificacion2.equals("n01") && volcan.equals(Volcan_lahar) && fecha.equals(Fecha_lahar) && hora.equals(Hora_lahar) && tiponotificacion2_m.equals("n01") && volcan_m.equals(Volcan_lahar) && fecha_m.equals(Fecha_lahar) && hora_m.equals(Hora_lahar)){
                Log.d("ENVIO LAHAR ? : ","NO");
            }

            else{
                enviar_notificacion(Tiponotificacion_lahar+"&"+Volcan_lahar+"&"+Tipodevento_lahar+"&"+Fecha_lahar+"&"+Hora_lahar+"&"+Horautc_lahar+"&"+Observaciones_lahar+"&"+Simulacro_lahar, "n01");

                not_lahar  =  Tiponotificacion_lahar+"&"+Volcan_lahar+"&"+Tipodevento_lahar+"&"+Fecha_lahar+"&"+Hora_lahar+"&"+Horautc_lahar+"&"+Observaciones_lahar+"&"+Simulacro_lahar;

                Conexionsql conn = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
                SQLiteDatabase db = conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                //values.put(Utilidades.CAMPO_ID,  );
                values.put(Utilidades.CAMPO_MENSAJE, Tiponotificacion_lahar+"&"+Volcan_lahar+"&"+Tipodevento_lahar+"&"+Fecha_lahar+"&"+Hora_lahar+"&"+Horautc_lahar+"&"+Observaciones_lahar+"&"+Simulacro_lahar);
                Long idresultante = db.insert(Utilidades.TABLA_NOTIFICACIONES, Utilidades.CAMPO_ID, values);
                Log.d("el resultante: ","Resultante:" + idresultante + ""   );
                Log.d("ENVIO LAHAR ?: ","YES");




                try {
                    FileOutputStream fileOutputStream_tipo = getApplicationContext().openFileOutput("ultima_notificacion", Context.MODE_PRIVATE);
                    fileOutputStream_tipo.write(not_lahar.getBytes());
                    fileOutputStream_tipo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

        }


        else if (Tiponotificacion_reporteactividad.equals("n02")) {

            String tiponotificacion2  = datoslastdos.split("&")[0];
            String volcan = datoslastdos.split("&")[1];
            String fecha = datoslastdos.split("&")[2];
            String hora = datoslastdos.split("&")[3];
            String simulacro = datoslastdos.split("&")[4];
            String horautc = datoslastdos.split("&")[5];
            String reportepdf = datoslastdos.split("&")[6];
            String coloralerta = datoslastdos.split("&")[7];
            String analisis = datoslastdos.split("&")[8];
            String conclusiones = datoslastdos.split("&")[9];

            String tiponotificacion2_m  = valormemoria.split("&")[0];
            String volcan_m = valormemoria.split("&")[1];
            String fecha_m = valormemoria.split("&")[2];
            String hora_m = valormemoria.split("&")[3];
            String simulacro_m = valormemoria.split("&")[4];
            String horautc_m = valormemoria.split("&")[5];
            String reportepdf_m = valormemoria.split("&")[6];
            String coloralerta_m = valormemoria.split("&")[7];
            String analisis_m = valormemoria.split("&")[8];
            String conclusiones_m = valormemoria.split("&")[9];


            if(tiponotificacion2.equals("n02") && volcan.equals(Codigovolcan_reporteactividad) && fecha.equals(Fecha_reporteactividad) && hora.equals(Hora_reporteactividad) && tiponotificacion2_m.equals("n02") && volcan_m.equals(Codigovolcan_reporteactividad) && fecha_m.equals(Fecha_reporteactividad) && hora_m.equals(Hora_reporteactividad)){
                Log.d("ENVIO EXTRAORDINARIO?: ","NO");
            }
            else{

                enviar_notificacion(Tiponotificacion_reporteactividad+"&"+Codigovolcan_reporteactividad+"&"+Fecha_reporteactividad+"&"+Hora_reporteactividad+"&"+Simulacro_reporteactividad+"&"+Horautc_reporteactividad+"&"+Pdfurl_reporteactividad+"&"+Coloralerta_reporteactividad+"&"+Analisis_reporteactividad+"&"+Conclusiones_reporteactividad, "n02");


                notreporte2 = Tiponotificacion_reporteactividad+"&"+Codigovolcan_reporteactividad+"&"+Fecha_reporteactividad+"&"+Hora_reporteactividad+"&"+Simulacro_reporteactividad+"&"+Horautc_reporteactividad+"&"+Pdfurl_reporteactividad+"&"+Coloralerta_reporteactividad+"&"+Analisis_reporteactividad+"&"+Conclusiones_reporteactividad;

                Conexionsql conn = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
                SQLiteDatabase db = conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                //values.put(Utilidades.CAMPO_ID,  );
                values.put(Utilidades.CAMPO_MENSAJE, Tiponotificacion_reporteactividad+"&"+Codigovolcan_reporteactividad+"&"+Fecha_reporteactividad+"&"+Hora_reporteactividad+"&"+Simulacro_reporteactividad+"&"+Horautc_reporteactividad+"&"+Pdfurl_reporteactividad+"&"+Coloralerta_reporteactividad+"&"+Analisis_reporteactividad+"&"+Conclusiones_reporteactividad);
                Long idresultante = db.insert(Utilidades.TABLA_NOTIFICACIONES, Utilidades.CAMPO_ID, values);
                Log.d("el resultante: ","Resultante:" + idresultante + ""   );
                Log.d("ENVIO EXTRAORDINARIO?: ","YES");


                try {
                    FileOutputStream fileOutputStream_tipo = getApplicationContext().openFileOutput("ultima_notificacion", Context.MODE_PRIVATE);
                    fileOutputStream_tipo.write(notreporte2.getBytes());
                    fileOutputStream_tipo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        }


        else if (Tiponotificacion_cenizas.equals("n03")) {

            String tiponotificacion2 = datoslastdos.split("&")[0];
            String volcan  = datoslastdos.split("&")[1];
            String pueblos  = datoslastdos.split("&")[2];
            String tipodevento  = datoslastdos.split("&")[3];
            String direccion  = datoslastdos.split("&")[4];
            String radio = datoslastdos.split("&")[5];
            String fecha  = datoslastdos.split("&")[6];
            String hora  = datoslastdos.split("&")[7];
            String horautc  = datoslastdos.split("&")[8];
            String recomendaciones  = datoslastdos.split("&")[9];
            String observaciones  = datoslastdos.split("&")[10];
            String simulacro  = datoslastdos.split("&")[11];


            String tiponotificacion2_m = valormemoria.split("&")[0];
            String volcan_m  = valormemoria.split("&")[1];
            String pueblos_m  = valormemoria.split("&")[2];
            String tipodevento_m  = valormemoria.split("&")[3];
            String direccion_m  = valormemoria.split("&")[4];
            String radio_m = valormemoria.split("&")[5];
            String fecha_m  = valormemoria.split("&")[6];
            String hora_m  = valormemoria.split("&")[7];
            String horautc_m  = valormemoria.split("&")[8];
            String recomendaciones_m  = valormemoria.split("&")[9];
            String observaciones_m  = valormemoria.split("&")[10];
            String simulacro_m  = valormemoria.split("&")[11];



            if(tiponotificacion2.equals("n03") && volcan.equals(Volcan_cenizas) && fecha.equals(Fecha_cenizas) && hora.equals(Hora_cenizas) && tiponotificacion2_m.equals("n03") && volcan_m.equals(Volcan_cenizas) && fecha_m.equals(Fecha_cenizas) && hora_m.equals(Hora_cenizas)){
                Log.d("ENVIO CENIZAS? : ","NO");
            }
            else{
                enviar_notificacion(Tiponotificacion_cenizas+"&"+Volcan_cenizas+"&"+Pueblos_cenizas+"&"+Tipodevento_cenizas+"&"+Direccion_cenizas+"&"+Radio_cenizas+"&"+Fecha_cenizas+"&"+Hora_cenizas+"&"+Horautc_cenizas+"&"+Recomendaciones_cenizas+"&"+Observaciones_cenizas+"&"+Simulacro_cenizas, "n03");

                notcenizas = Tiponotificacion_cenizas+"&"+Volcan_cenizas+"&"+Pueblos_cenizas+"&"+Tipodevento_cenizas+"&"+Direccion_cenizas+"&"+Radio_cenizas+"&"+Fecha_cenizas+"&"+Hora_cenizas+"&"+Horautc_cenizas+"&"+Recomendaciones_cenizas+"&"+Observaciones_cenizas+"&"+Simulacro_cenizas;

                Conexionsql conn = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
                SQLiteDatabase db = conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                //values.put(Utilidades.CAMPO_ID,  );
                values.put(Utilidades.CAMPO_MENSAJE, Tiponotificacion_cenizas+"&"+Volcan_cenizas+"&"+Pueblos_cenizas+"&"+Tipodevento_cenizas+"&"+Direccion_cenizas+"&"+Radio_cenizas+"&"+Fecha_cenizas+"&"+Hora_cenizas+"&"+Horautc_cenizas+"&"+Recomendaciones_cenizas+"&"+Observaciones_cenizas+"&"+Simulacro_cenizas);
                Long idresultante = db.insert(Utilidades.TABLA_NOTIFICACIONES, Utilidades.CAMPO_ID, values);
                Log.d("el resultante: ","Resultante:" + idresultante + ""   );
                Log.d("ENVIO CENIZAS?: ","YES");


                try {
                    FileOutputStream fileOutputStream_tipo = getApplicationContext().openFileOutput("ultima_notificacion", Context.MODE_PRIVATE);
                    fileOutputStream_tipo.write(notcenizas.getBytes());
                    fileOutputStream_tipo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


        else if (Tiponotificacion_reporteactividad.equals("n04")) {

            String tiponotificacion2  = datoslastdos.split("&")[0];
            String volcan  = datoslastdos.split("&")[1];
            String  fecha  = datoslastdos.split("&")[2];
            String  hora  = datoslastdos.split("&")[3];
            String  simulacro  = datoslastdos.split("&")[4];
            String  horautc = datoslastdos.split("&")[5];
            String  reportepdf  = datoslastdos.split("&")[6];
            String  coloralerta = datoslastdos.split("&")[7];
            String  analisis  = datoslastdos.split("&")[8];
            String conclusiones = datoslastdos.split("&")[9];


            String tiponotificacion2_m  = valormemoria.split("&")[0];
            String volcan_m  = valormemoria.split("&")[1];
            String  fecha_m  = valormemoria.split("&")[2];
            String  hora_m  = valormemoria.split("&")[3];
            String  simulacro_m  = valormemoria.split("&")[4];
            String  horautc_m = valormemoria.split("&")[5];
            String  reportepdf_m  = valormemoria.split("&")[6];
            //String  coloralerta_m = valormemoria.split("&")[7];
            //String  analisis_m  = valormemoria.split("&")[8];
            //String conclusiones_m = valormemoria.split("&")[9];


            if(tiponotificacion2.equals("n04") && volcan.equals(Codigovolcan_reporteactividad) && fecha.equals(Fecha_reporteactividad) && hora.equals(Hora_reporteactividad) && tiponotificacion2_m.equals("n04") && volcan_m.equals(Codigovolcan_reporteactividad) && fecha_m.equals(Fecha_reporteactividad) && hora_m.equals(Hora_reporteactividad)){
                Log.d("ENVIO  ORDINARIO? : ","NO");
            }
            else{

                enviar_notificacion(Tiponotificacion_reporteactividad+"&"+Codigovolcan_reporteactividad+"&"+Fecha_reporteactividad+"&"+Hora_reporteactividad+"&"+Simulacro_reporteactividad+"&"+Horautc_reporteactividad+"&"+Pdfurl_reporteactividad+"&"+Coloralerta_reporteactividad+"&"+Analisis_reporteactividad+"&"+Conclusiones_reporteactividad, "n04");

                notreporte1 = Tiponotificacion_reporteactividad+"&"+Codigovolcan_reporteactividad+"&"+Fecha_reporteactividad+"&"+Hora_reporteactividad+"&"+Simulacro_reporteactividad+"&"+Horautc_reporteactividad+"&"+Pdfurl_reporteactividad+"&"+Coloralerta_reporteactividad+"&"+Analisis_reporteactividad+"&"+Conclusiones_reporteactividad;

                Conexionsql conn = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
                SQLiteDatabase db = conn.getWritableDatabase();
                ContentValues values = new ContentValues();
                //values.put(Utilidades.CAMPO_ID,  );
                values.put(Utilidades.CAMPO_MENSAJE, Tiponotificacion_reporteactividad+"&"+Codigovolcan_reporteactividad+"&"+Fecha_reporteactividad+"&"+Hora_reporteactividad+"&"+Simulacro_reporteactividad+"&"+Horautc_reporteactividad+"&"+Pdfurl_reporteactividad+"&"+Coloralerta_reporteactividad+"&"+Analisis_reporteactividad+"&"+Conclusiones_reporteactividad);
                Long idresultante = db.insert(Utilidades.TABLA_NOTIFICACIONES, Utilidades.CAMPO_ID, values);
                Log.d("el resultante: ","Resultante:" + idresultante + ""   );
                Log.d("ENVIO ORDINARIO?: ","YES");


                try {
                    FileOutputStream fileOutputStream_tipo = getApplicationContext().openFileOutput("ultima_notificacion", Context.MODE_PRIVATE);
                    fileOutputStream_tipo.write(notreporte1.getBytes());
                    fileOutputStream_tipo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }





    private void enviar_notificacion(String datos, String tipo){

        String file_vibrar = getApplicationContext().getFilesDir() + "/"+"vibrar_file";
        try {
            FileInputStream filevibrar = new FileInputStream(new File(file_vibrar));
            InputStreamReader InputRead_vibrar = new InputStreamReader(filevibrar);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead_vibrar.read(inputBuffer);
              valorsonido = String.copyValueOf(inputBuffer, 0, charRead);
            InputRead_vibrar.close();
            Log.d(TAG, "VIBRATION: " + valorsonido);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String file_notificar = getApplicationContext().getFilesDir() + "/"+"notificar_file";
        try {
            FileInputStream filenotificar = new FileInputStream(new File(file_notificar));
            InputStreamReader InputRead_notificar = new InputStreamReader(filenotificar);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead_notificar.read(inputBuffer);
            valornotificacion = String.copyValueOf(inputBuffer, 0, charRead);
            InputRead_notificar.close();
            Log.d(TAG, "NOTIFICAR: " + valornotificacion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String file_tipo = getApplicationContext().getFilesDir() + "/"+"tipo_file";
        try {
            FileInputStream filetipo = new FileInputStream(new File(file_tipo));
            InputStreamReader InputRead_tipo = new InputStreamReader(filetipo);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead_tipo.read(inputBuffer);
              valortipo = String.copyValueOf(inputBuffer, 0, charRead);
            InputRead_tipo.close();
            Log.d(TAG, "TIPO: " + valortipo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String file_ringtone = getApplicationContext().getFilesDir() + "/"+"ring_tone_file";
        try {
            FileInputStream fileringtone = new FileInputStream(new File(file_ringtone));
            InputStreamReader InputRead_ring = new InputStreamReader(fileringtone);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead_ring.read(inputBuffer);
            valorringtone = String.copyValueOf(inputBuffer, 0, charRead);
            InputRead_ring.close();
            Log.d(TAG, "RINGTONE: " + valorringtone);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String file_ramdom = getApplicationContext().getFilesDir() + "/"+"ramdom_number_file";
        try {
            FileInputStream fileramdom = new FileInputStream(new File(file_ramdom));
            InputStreamReader InputRead_ramdom = new InputStreamReader(fileramdom);
            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            int charRead;
            charRead = InputRead_ramdom.read(inputBuffer);
            valorramdomesound = String.copyValueOf(inputBuffer, 0, charRead);
            InputRead_ramdom.close();
            Log.d(TAG, "RAMDOM: " + valorramdomesound);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(tipo.equals("n01")){
            enviarnotificaciondatosalertalahar(datos);
        }
        else if(tipo.equals("n02")){
            enviarnotificaciondatosreporteextraordinario(datos);

        }
        else if(tipo.equals("n03")){
            sendNotificationalertadecenizas(datos);

        }
        else if(tipo.equals("n04")){
            enviarnotificaciondatosreporteordinario(datos);
        }
        else{
            enviarnotificaciondatossismos(datos);
        }

    }


    public void  enviarnotificaciondatosalertalahar(String remoteMessage) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String tiponotificacion2  = remoteMessage.split("&")[0];
            String volcan = remoteMessage.split("&")[1];
            String tipodevento = remoteMessage.split("&")[2];
            String fecha = remoteMessage.split("&")[3];
            String hora = remoteMessage.split("&")[4];
            String horautc = remoteMessage.split("&")[5];
            String observaciones = remoteMessage.split("&")[6];
            String simulacro = remoteMessage.split("&")[7];
            String asubstring = fecha.substring(0, 10);
            String nobrevolcan_r2;

            if (volcan.equals("1493157379002")) {
                nobrevolcan_r2 = "Volcán Ubinas";
            } else if (volcan.equals("1493157381161")) {
                nobrevolcan_r2 = "Volcán Sabancaya";
            } else if (volcan.equals("1506454510537")) {
                nobrevolcan_r2 = "Volcán Sara Sara";
            } else if (volcan.equals("1506455245814")) {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato";
            } else if (volcan.equals("1506455248101")) {
                nobrevolcan_r2 = "Volcán Andahua";
            } else if (volcan.equals("1506455249661")) {
                nobrevolcan_r2 = "Volcán Coropuna";
            } else if (volcan.equals("1506455251429")) {
                nobrevolcan_r2 = "Volcán Huambo";
            } else if (volcan.equals("1506455253382")) {
                nobrevolcan_r2 = "Volcán Chachani";
            } else if (volcan.equals("1506455254838")) {
                nobrevolcan_r2 = "Volcán Tutupaca";
            } else if (volcan.equals("1506455256229")) {
                nobrevolcan_r2 = "Volcán Huaynaputina";
            } else if (volcan.equals("1506455257749")) {
                nobrevolcan_r2 = "Volcán Ticsani";
            } else if (volcan.equals("1506455257753")) {
                nobrevolcan_r2 = "Volcán Casiri";
            } else if (volcan.equals("1506455257755")) {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni";
            } else if (volcan.equals("1506455257757")) {
                nobrevolcan_r2 = "Volcán Quimsachata";
            } else if (volcan.equals("1506455259126")) {
                nobrevolcan_r2 = "Volcán Yucamane";
            } else if (volcan.equals("1506455259128")) {
                nobrevolcan_r2 = "Volcán Misti";
            } else {
                nobrevolcan_r2 = "Volcán";
            }

            Intent intent = new Intent(getApplicationContext(), Alertadatoslahar.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            if (valorsonido.length() > 4) {

                if(valorentero <= 6) {
                    String channelId = "canal_sonido_alarmas_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else if(valorentero <= 7){
                    String  channelId = "canal_sonido_notificacion_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else{
                    String file_ramdom = getApplicationContext().getFilesDir() + "/"+"ramdom_number_file";
                    try {
                        FileInputStream fileramdom = new FileInputStream(new File(file_ramdom));
                        InputStreamReader InputRead_ramdom = new InputStreamReader(fileramdom);
                        char[] inputBuffer = new char[READ_BLOCK_SIZE];
                        int charRead;
                        charRead = InputRead_ramdom.read(inputBuffer);
                        valorramdomesound= String.copyValueOf(inputBuffer, 0, charRead);
                        InputRead_ramdom.close();
                        Log.d(TAG, "RAMDOM: " + valorramdomesound);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");

                    String channelId = "canal_sonido_personalizado_id"+valorramdomesound;
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                    Log.v(TAG_REGISTER, " Valor pi :$channelId");

                }

            }
            else{
                String channelId =  "canal_sin_sonido_con_vibracion_id";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("Alerta de Lahar")
                        .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                        .setAutoCancel(true)
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }

        else {
            String tiponotificacion2  = remoteMessage.split("&")[0];
            String volcan = remoteMessage.split("&")[1];
            String tipodevento = remoteMessage.split("&")[2];
            String fecha = remoteMessage.split("&")[3];
            String hora = remoteMessage.split("&")[4];
            String horautc = remoteMessage.split("&")[5];
            String observaciones = remoteMessage.split("&")[6];
            String simulacro = remoteMessage.split("&")[7];

            String asubstring = fecha.substring(0, 10);
            String nobrevolcan_r2;
            if (volcan.equals("1493157379002")) {
                nobrevolcan_r2 = "Volcán Ubinas";
            } else if (volcan.equals("1493157381161")) {
                nobrevolcan_r2 = "Volcán Sabancaya";
            } else if (volcan.equals("1506454510537")) {
                nobrevolcan_r2 = "Volcán Sara Sara";
            } else if (volcan.equals("1506455245814")) {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato";
            } else if (volcan.equals("1506455248101")) {
                nobrevolcan_r2 = "Volcán Andahua";
            } else if (volcan.equals("1506455249661")) {
                nobrevolcan_r2 = "Volcán Coropuna";
            } else if (volcan.equals("1506455251429")) {
                nobrevolcan_r2 = "Volcán Huambo";
            } else if (volcan.equals("1506455253382")) {
                nobrevolcan_r2 = "Volcán Chachani";
            } else if (volcan.equals("1506455254838")) {
                nobrevolcan_r2 = "Volcán Tutupaca";
            } else if (volcan.equals("1506455256229")) {
                nobrevolcan_r2 = "Volcán Huaynaputina";
            } else if (volcan.equals("1506455257749")) {
                nobrevolcan_r2 = "Volcán Ticsani";
            } else if (volcan.equals("1506455257753")) {
                nobrevolcan_r2 = "Volcán Casiri";
            } else if (volcan.equals("1506455257755")) {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni";
            } else if (volcan.equals("1506455257757")) {
                nobrevolcan_r2 = "Volcán Quimsachata";
            } else if (volcan.equals("1506455259126")) {
                nobrevolcan_r2 = "Volcán Yucamane";
            } else if (volcan.equals("1506455259128")) {
                nobrevolcan_r2 = "Volcán Misti";
            } else {
                nobrevolcan_r2 = "Volcán";
            }

            Log.d("VOLCANEX22:",volcan);

            Intent intent = new Intent(getApplicationContext(), Alertadatoslahar.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            if (valorsonido.length() > 4) {
                if (valorentero == 6) {
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.alarmasonido);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else if(valorentero == 7){
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.beep2);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else{
                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
            }

            else{
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Alerta de Lahar")
                        .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }
    }



    public void sendNotificationalertadecenizas(String remoteMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String tiponotificacion2 = remoteMessage.split("&")[0];
            String volcan = remoteMessage.split("&")[1];
            String pueblos = remoteMessage.split("&")[2];
            String tipodevento = remoteMessage.split("&")[3];
            String direccion = remoteMessage.split("&")[4];
            String radio = remoteMessage.split("&")[5];
            String fecha = remoteMessage.split("&")[6];
            String hora = remoteMessage.split("&")[7];

            String horautc = remoteMessage.split("&")[8];

            String recomendaciones = remoteMessage.split("&")[9];
            String observaciones = remoteMessage.split("&")[10];
            String simulacro = remoteMessage.split("&")[11];
            //  val asubstring = fecha.substring(0, 10)

            String asubstring = fecha;

            String nobrevolcan_r2;

            if (volcan.equals("1493157379002")) {
                nobrevolcan_r2 = "Volcán Ubinas";
            } else if (volcan.equals("1493157381161")) {
                nobrevolcan_r2 = "Volcán Sabancaya";
            } else if (volcan.equals("1506454510537")) {
                nobrevolcan_r2 = "Volcán Sara Sara";
            } else if (volcan.equals("1506455245814")) {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato";
            } else if (volcan.equals("1506455248101")) {
                nobrevolcan_r2 = "Volcán Andahua";
            } else if (volcan.equals("1506455249661")) {
                nobrevolcan_r2 = "Volcán Coropuna";
            } else if (volcan.equals("1506455251429")) {
                nobrevolcan_r2 = "Volcán Huambo";
            } else if (volcan.equals("1506455253382")) {
                nobrevolcan_r2 = "Volcán Chachani";
            } else if (volcan.equals("1506455254838")) {
                nobrevolcan_r2 = "Volcán Tutupaca";
            } else if (volcan.equals("1506455256229")) {
                nobrevolcan_r2 = "Volcán Huaynaputina";
            } else if (volcan.equals("1506455257749")) {
                nobrevolcan_r2 = "Volcán Ticsani";
            } else if (volcan.equals("1506455257753")) {
                nobrevolcan_r2 = "Volcán Casiri";
            } else if (volcan.equals("1506455257755")) {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni";
            } else if (volcan.equals("1506455257757")) {
                nobrevolcan_r2 = "Volcán Quimsachata";
            } else if (volcan.equals("1506455259126")) {
                nobrevolcan_r2 = "Volcán Yucamane";
            } else if (volcan.equals("1506455259128")) {
                nobrevolcan_r2 = "Volcán Misti";
            } else {
                nobrevolcan_r2 = "Volcán";
            }

            Intent intent = new Intent(getApplicationContext(), Alertando.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);


            if (valorsonido.length() > 4) {

                if(valorentero <= 6) {
                    String channelId = "canal_sonido_alarmas_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else if(valorentero <= 7){
                    String channelId = "canal_sonido_notificacion_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else{

                    String file_ramdom = getApplicationContext().getFilesDir() + "/"+"ramdom_number_file";
                    try {
                        FileInputStream fileramdom = new FileInputStream(new File(file_ramdom));
                        InputStreamReader InputRead_ramdom = new InputStreamReader(fileramdom);
                        char[] inputBuffer = new char[READ_BLOCK_SIZE];
                        int charRead;
                        charRead = InputRead_ramdom.read(inputBuffer);
                        valorramdomesound= String.copyValueOf(inputBuffer, 0, charRead);
                        InputRead_ramdom.close();
                        Log.d(TAG, "RAMDOM: " + valorramdomesound);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String channelId = "canal_sonido_personalizado_id"+valorramdomesound;
                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);

                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
            }
            else{
                String channelId = "canal_sin_sonido_con_vibracion_id";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("Alerta de Dispersión de Cenizas")
                        .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                        .setAutoCancel(true)
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }

        else {
            String tiponotificacion2  = remoteMessage.split("&")[0];
            String volcan = remoteMessage.split("&")[1];
            String pueblos = remoteMessage.split("&")[2];
            String tipodevento = remoteMessage.split("&")[3];
            String direccion = remoteMessage.split("&")[4];
            String radio = remoteMessage.split("&")[5];
            String fecha = remoteMessage.split("&")[6];
            String hora = remoteMessage.split("&")[7];
            String horautc = remoteMessage.split("&")[8];
            String recomendaciones = remoteMessage.split("&")[9];
            String observaciones = remoteMessage.split("&")[10];
            String simulacro = remoteMessage.split("&")[11];

            //  val asubstring = fecha.substring(0, 10)

            String asubstring = fecha;

            Log.d("VOLCANEX22:",volcan);


            String nobrevolcan_r2;
            if (volcan.equals("1493157379002")) {
                nobrevolcan_r2 = "Volcán Ubinas";
            } else if (volcan.equals("1493157381161")) {
                nobrevolcan_r2 = "Volcán Sabancaya";
            } else if (volcan.equals("1506454510537")) {
                nobrevolcan_r2 = "Volcán Sara Sara";
            } else if (volcan.equals("1506455245814")) {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato";
            } else if (volcan.equals("1506455248101")) {
                nobrevolcan_r2 = "Volcán Andahua";
            } else if (volcan.equals("1506455249661")) {
                nobrevolcan_r2 = "Volcán Coropuna";
            } else if (volcan.equals("1506455251429")) {
                nobrevolcan_r2 = "Volcán Huambo";
            } else if (volcan.equals("1506455253382")) {
                nobrevolcan_r2 = "Volcán Chachani";
            } else if (volcan.equals("1506455254838")) {
                nobrevolcan_r2 = "Volcán Tutupaca";
            } else if (volcan.equals("1506455256229")) {
                nobrevolcan_r2 = "Volcán Huaynaputina";
            } else if (volcan.equals("1506455257749")) {
                nobrevolcan_r2 = "Volcán Ticsani";
            } else if (volcan.equals("1506455257753")) {
                nobrevolcan_r2 = "Volcán Casiri";
            } else if (volcan.equals("1506455257755")) {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni";
            } else if (volcan.equals("1506455257757")) {
                nobrevolcan_r2 = "Volcán Quimsachata";
            } else if (volcan.equals("1506455259126")) {
                nobrevolcan_r2 = "Volcán Yucamane";
            } else if (volcan.equals("1506455259128")) {
                nobrevolcan_r2 = "Volcán Misti";
            } else {
                nobrevolcan_r2 = "Volcán";
            }

            Intent intent = new Intent(getApplicationContext(), Alertando.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            if (valorsonido.length() > 4) {
                if(valorentero <= 6) {
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.alarmasonido);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else if(valorentero <= 7){
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.beep2);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else{
                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
            }
            else{
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Alerta de Dispersión de Cenizas")
                        .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }
    }

    public void   enviarnotificaciondatosreporteextraordinario(String remoteMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String tiponotificacion2 = remoteMessage.split("&")[0];
            String volcan = remoteMessage.split("&")[1];
            String fecha = remoteMessage.split("&")[2];
            String hora = remoteMessage.split("&")[3];
            String simulacro = remoteMessage.split("&")[4];
            String horautc = remoteMessage.split("&")[5];
            String reportepdf = remoteMessage.split("&")[6];
            String coloralerta = remoteMessage.split("&")[7];
            String analisis = remoteMessage.split("&")[8];
            String conclusiones = remoteMessage.split("&")[9];
            // val asubstring = fecha
            String asubstring = fecha.substring(0, 10);
            String nobrevolcan_r2;

            if (volcan.equals("1493157379002")) {
                nobrevolcan_r2 = "Volcán Ubinas";
            } else if (volcan.equals("1493157381161")) {
                nobrevolcan_r2 = "Volcán Sabancaya";
            } else if (volcan.equals("1506454510537")) {
                nobrevolcan_r2 = "Volcán Sara Sara";
            } else if (volcan.equals("1506455245814")) {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato";
            } else if (volcan.equals("1506455248101")) {
                nobrevolcan_r2 = "Volcán Andahua";
            } else if (volcan.equals("1506455249661")) {
                nobrevolcan_r2 = "Volcán Coropuna";
            } else if (volcan.equals("1506455251429")) {
                nobrevolcan_r2 = "Volcán Huambo";
            } else if (volcan.equals("1506455253382")) {
                nobrevolcan_r2 = "Volcán Chachani";
            } else if (volcan.equals("1506455254838")) {
                nobrevolcan_r2 = "Volcán Tutupaca";
            } else if (volcan.equals("1506455256229")) {
                nobrevolcan_r2 = "Volcán Huaynaputina";
            } else if (volcan.equals("1506455257749")) {
                nobrevolcan_r2 = "Volcán Ticsani";
            } else if (volcan.equals("1506455257753")) {
                nobrevolcan_r2 = "Volcán Casiri";
            } else if (volcan.equals("1506455257755")) {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni";
            } else if (volcan.equals("1506455257757")) {
                nobrevolcan_r2 = "Volcán Quimsachata";
            } else if (volcan.equals("1506455259126")) {
                nobrevolcan_r2 = "Volcán Yucamane";
            } else if (volcan.equals("1506455259128")) {
                nobrevolcan_r2 = "Volcán Misti";
            } else {
                nobrevolcan_r2 = "Volcán";
            }

            Intent intent = new Intent(getApplicationContext(), Alertareporteactividad.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            if (valorsonido.length() > 4) {
                if(valorentero <= 6) {
                    String channelId = "canal_sonido_alarmas_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else if(valorentero <= 7) {
                    String channelId = "canal_sonido_notificacion_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else{
                    String file_ramdom = getApplicationContext().getFilesDir() + "/"+"ramdom_number_file";
                    try {
                        FileInputStream fileramdom = new FileInputStream(new File(file_ramdom));
                        InputStreamReader InputRead_ramdom = new InputStreamReader(fileramdom);
                        char[] inputBuffer = new char[READ_BLOCK_SIZE];
                        int charRead;
                        charRead = InputRead_ramdom.read(inputBuffer);
                        valorramdomesound= String.copyValueOf(inputBuffer, 0, charRead);
                        InputRead_ramdom.close();
                        Log.d(TAG, "RAMDOM: " + valorramdomesound);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);

                    String channelId = "canal_sonido_personalizado_id"+valorramdomesound;
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)

                    //val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    //val notificationManager = NotificationManagerCompat.from(applicationContext)
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
            }
                else {
                String channelId = "canal_sin_sonido_con_vibracion_id";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("Reporte Extraordinario")
                        .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                        .setAutoCancel(true)
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }

        else {
            String tiponotificacion2 = remoteMessage.split("&")[0];
            String volcan = remoteMessage.split("&")[1];
            String fecha = remoteMessage.split("&")[2];
            String hora = remoteMessage.split("&")[3];
            String simulacro = remoteMessage.split("&")[4];
            String horautc = remoteMessage.split("&")[5];
            String reportepdf = remoteMessage.split("&")[6];
            String coloralerta = remoteMessage.split("&")[7];
            String analisis = remoteMessage.split("&")[8];
            String conclusiones = remoteMessage.split("&")[9];

            String asubstring = fecha.substring(0, 10);


            Log.d("VOLCANEX22:",volcan);

            String nobrevolcan_r2;
            if (volcan.equals("1493157379002")) {
                nobrevolcan_r2 = "Volcán Ubinas";
            } else if (volcan.equals("1493157381161")) {
                nobrevolcan_r2 = "Volcán Sabancaya";
            } else if (volcan.equals("1506454510537")) {
                nobrevolcan_r2 = "Volcán Sara Sara";
            } else if (volcan.equals("1506455245814")) {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato";
            } else if (volcan.equals("1506455248101")) {
                nobrevolcan_r2 = "Volcán Andahua";
            } else if (volcan.equals("1506455249661")) {
                nobrevolcan_r2 = "Volcán Coropuna";
            } else if (volcan.equals("1506455251429")) {
                nobrevolcan_r2 = "Volcán Huambo";
            } else if (volcan.equals("1506455253382")) {
                nobrevolcan_r2 = "Volcán Chachani";
            } else if (volcan.equals("1506455254838")) {
                nobrevolcan_r2 = "Volcán Tutupaca";
            } else if (volcan.equals("1506455256229")) {
                nobrevolcan_r2 = "Volcán Huaynaputina";
            } else if (volcan.equals("1506455257749")) {
                nobrevolcan_r2 = "Volcán Ticsani";
            } else if (volcan.equals("1506455257753")) {
                nobrevolcan_r2 = "Volcán Casiri";
            } else if (volcan.equals("1506455257755")) {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni";
            } else if (volcan.equals("1506455257757")) {
                nobrevolcan_r2 = "Volcán Quimsachata";
            } else if (volcan.equals("1506455259126")) {
                nobrevolcan_r2 = "Volcán Yucamane";
            } else if (volcan.equals("1506455259128")) {
                nobrevolcan_r2 = "Volcán Misti";
            } else {
                nobrevolcan_r2 = "Volcán";
            }

            Intent intent = new Intent(getApplicationContext(), Alertareporteactividad.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            if (valorsonido.length() > 4) {
                if(valorentero <= 6) {
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.alarmasonido);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else if(valorentero <= 7) {
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.beep2);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
                else{

                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
            }
            else{
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Reporte Extraordinario")
                        .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }
    }


    public void   enviarnotificaciondatosreporteordinario(String remoteMessage) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String tiponotificacion2  = remoteMessage.split("&")[0];
            String volcan = remoteMessage.split("&")[1];
            String fecha = remoteMessage.split("&")[2];
            String hora = remoteMessage.split("&")[3];
            String simulacro = remoteMessage.split("&")[4];
            String horautc = remoteMessage.split("&")[5];
            String reportepdf = remoteMessage.split("&")[6];
            String coloralerta = remoteMessage.split("&")[7];
            String analisis = remoteMessage.split("&")[8];
            String conclusiones = remoteMessage.split("&")[9];

            Log.d("VOLCANEX22:",volcan);


            // val fecha_subs = fecha.substring(0, 10)

            String asubstring = fecha.substring(0, 10);
            String nobrevolcan_r2;

            if (volcan.equals("1493157379002")) {
                nobrevolcan_r2 = "Volcán Ubinas";
            } else if (volcan.equals("1493157381161")) {
                nobrevolcan_r2 = "Volcán Sabancaya";
            } else if (volcan.equals("1506454510537")) {
                nobrevolcan_r2 = "Volcán Sara Sara";
            } else if (volcan.equals("1506455245814")) {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato";
            } else if (volcan.equals("1506455248101")) {
                nobrevolcan_r2 = "Volcán Andahua";
            } else if (volcan.equals("1506455249661")) {
                nobrevolcan_r2 = "Volcán Coropuna";
            } else if (volcan.equals("1506455251429")) {
                nobrevolcan_r2 = "Volcán Huambo";
            } else if (volcan.equals("1506455253382")) {
                nobrevolcan_r2 = "Volcán Chachani";
            } else if (volcan.equals("1506455254838")) {
                nobrevolcan_r2 = "Volcán Tutupaca";
            } else if (volcan.equals("1506455256229")) {
                nobrevolcan_r2 = "Volcán Huaynaputina";
            } else if (volcan.equals("1506455257749")) {
                nobrevolcan_r2 = "Volcán Ticsani";
            } else if (volcan.equals("1506455257753")) {
                nobrevolcan_r2 = "Volcán Casiri";
            } else if (volcan.equals("1506455257755")) {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni";
            } else if (volcan.equals("1506455257757")) {
                nobrevolcan_r2 = "Volcán Quimsachata";
            } else if (volcan.equals("1506455259126")) {
                nobrevolcan_r2 = "Volcán Yucamane";
            } else if (volcan.equals("1506455259128")) {
                nobrevolcan_r2 = "Volcán Misti";
            } else {
                nobrevolcan_r2 = "Volcán";
            }


            Intent intent = new Intent(getApplicationContext(), Alertareporteactividad.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);


            if (valorsonido.length() > 4) {

                if(valorentero <= 6) {
                    String channelId =  "canal_sonido_alarmas_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else if(valorentero <= 7) {
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.beep2);
                    String channelId = "canal_sonido_notificacion_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else{

                    String file_ramdom = getApplicationContext().getFilesDir() + "/"+"ramdom_number_file";
                    try {
                        FileInputStream fileramdom = new FileInputStream(new File(file_ramdom));
                        InputStreamReader InputRead_ramdom = new InputStreamReader(fileramdom);
                        char[] inputBuffer = new char[READ_BLOCK_SIZE];
                        int charRead;
                        charRead = InputRead_ramdom.read(inputBuffer);
                        valorramdomesound= String.copyValueOf(inputBuffer, 0, charRead);
                        InputRead_ramdom.close();
                        Log.d(TAG, "RAMDOM: " + valorramdomesound);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String stingh  = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);

                    String channelId = "canal_sonido_personalizado_id"+valorramdomesound;
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

            }

            else{

                String channelId = "canal_sin_sonido_con_vibracion_id";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("Reporte Ordinario")
                        .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                        .setAutoCancel(true)
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }

        else {

            String tiponotificacion2 = remoteMessage.split("&")[0];
            String volcan = remoteMessage.split("&")[1];
            String fecha = remoteMessage.split("&")[2];
            String hora = remoteMessage.split("&")[3];
            String simulacro = remoteMessage.split("&")[4];
            String horautc = remoteMessage.split("&")[5];
            String reportepdf = remoteMessage.split("&")[6];
            String coloralerta = remoteMessage.split("&")[7];
            String analisis = remoteMessage.split("&")[8];
            String conclusiones = remoteMessage.split("&")[9];

            String asubstring = fecha.substring(0, 10);


            Log.d("VOLCANEX22:",volcan);


            String nobrevolcan_r2;
            if (volcan.equals("1493157379002")) {
                nobrevolcan_r2 = "Volcán Ubinas";
            } else if (volcan.equals("1493157381161")) {
                nobrevolcan_r2 = "Volcán Sabancaya";
            } else if (volcan.equals("1506454510537")) {
                nobrevolcan_r2 = "Volcán Sara Sara";
            } else if (volcan.equals("1506455245814")) {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato";
            } else if (volcan.equals("1506455248101")) {
                nobrevolcan_r2 = "Volcán Andahua";
            } else if (volcan.equals("1506455249661")) {
                nobrevolcan_r2 = "Volcán Coropuna";
            } else if (volcan.equals("1506455251429")) {
                nobrevolcan_r2 = "Volcán Huambo";
            } else if (volcan.equals("1506455253382")) {
                nobrevolcan_r2 = "Volcán Chachani";
            } else if (volcan.equals("1506455254838")) {
                nobrevolcan_r2 = "Volcán Tutupaca";
            } else if (volcan.equals("1506455256229")) {
                nobrevolcan_r2 = "Volcán Huaynaputina";
            } else if (volcan.equals("1506455257749")) {
                nobrevolcan_r2 = "Volcán Ticsani";
            } else if (volcan.equals("1506455257753")) {
                nobrevolcan_r2 = "Volcán Casiri";
            } else if (volcan.equals("1506455257755")) {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni";
            } else if (volcan.equals("1506455257757")) {
                nobrevolcan_r2 = "Volcán Quimsachata";
            } else if (volcan.equals("1506455259126")) {
                nobrevolcan_r2 = "Volcán Yucamane";
            } else if (volcan.equals("1506455259128")) {
                nobrevolcan_r2 = "Volcán Misti";
            } else {
                nobrevolcan_r2 = "Volcán";
            }

            if (valorsonido.length() > 4) {

                if(valorentero <= 6) {

                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.alarmasonido);
                    Intent intent = new Intent(getApplicationContext(), Alertareporteactividad.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("NOTIFICACIONDATA", remoteMessage);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else if(valorentero <= 7){
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.beep2);
                    Intent intent = new Intent(getApplicationContext(), Alertareporteactividad.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("NOTIFICACIONDATA", remoteMessage);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }


                else {

                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);

                    Intent intent = new Intent(getApplicationContext(), Alertareporteactividad.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("NOTIFICACIONDATA", remoteMessage);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }


            }
            else{

                Intent intent = new Intent(getApplicationContext(), Alertareporteactividad.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("NOTIFICACIONDATA", remoteMessage);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Reporte Ordinario")
                        .setContentText(nobrevolcan_r2  + " " +  asubstring + " " + "|" +  hora)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(new long[] { 50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }
    }





    public void enviarnotificaciondatossismos(String remoteMessage) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String tiponotificacion = remoteMessage.split("&")[0];
            String longitud = remoteMessage.split("&")[1];
            String latitud = remoteMessage.split("&")[2];
            String epicentro = remoteMessage.split("&")[3];
            String profundo = remoteMessage.split("&")[4];
            String referencia = remoteMessage.split("&")[5];
            String magnitud = remoteMessage.split("&")[6];
            String publicar = remoteMessage.split("&")[7];
            String intensidad = remoteMessage.split("&")[8];
            String hora_local = remoteMessage.split("&")[9];
            String fecha = remoteMessage.split("&")[10];


            Intent intent = new Intent(getApplicationContext(), pagedivisor.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            if (valorsonido.length() > 4){
                if(valorentero <= 6){
                    String channelId = "canal_sonido_alarmas_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else if(valorentero <= 7){
                    String channelId = "canal_sonido_notificacion_id";
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else{

                    String file_ramdom = getApplicationContext().getFilesDir() + "/"+"ramdom_number_file";
                    try {
                        FileInputStream fileramdom = new FileInputStream(new File(file_ramdom));
                        InputStreamReader InputRead_ramdom = new InputStreamReader(fileramdom);
                        char[] inputBuffer = new char[READ_BLOCK_SIZE];
                        int charRead;
                        charRead = InputRead_ramdom.read(inputBuffer);
                        valorramdomesound= String.copyValueOf(inputBuffer, 0, charRead);
                        InputRead_ramdom.close();
                        Log.d(TAG, "RAMDOM: " + valorramdomesound);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar");
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);

                    String channelId = "canal_sonido_personalizado_id"+valorramdomesound;
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
            }
            else {
                String  channelId = "canal_sin_sonido_con_vibracion_id";
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                        .setContentText("APP VOLCANES IGP")
                        .setAutoCancel(true)
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }

        }

        else {
            String tiponotificacion = remoteMessage.split("&")[0];
            String longitud = remoteMessage.split("&")[1];
            String latitud = remoteMessage.split("&")[2];
            String epicentro = remoteMessage.split("&")[3];
            String profundo = remoteMessage.split("&")[4];
            String referencia = remoteMessage.split("&")[5];
            String magnitud = remoteMessage.split("&")[6];
            String publicar = remoteMessage.split("&")[7];
            String intensidad = remoteMessage.split("&")[8];
            String hora_local = remoteMessage.split("&")[9];
            String fecha = remoteMessage.split("&")[10];

            Intent intent = new Intent(getApplicationContext(), pagedivisor.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            if (valorsonido.length() > 4) {

                if(valorentero == 6){
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.alarmasonido);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else if(valorentero == 7){
                    Uri sonido_notificacion_android = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.beep2);
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                    Random random = new Random();
                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }

                else{
                    String stingh = valorringtone.toString();
                    String lastChar = stingh.substring(0,2);
                    String lastCharrw = stingh.substring(0, (stingh.length() - 1));
                    Uri sonido_notificacion_android  = Uri.parse(lastCharrw);

                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                     Random random = new Random();

                    notificationManager.notify(random.nextInt(100), notificationBuilder.build());
                }
            }
            else {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                        .setContentText("APP VOLCANES IGP")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(new long[] {50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100})
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                Random random = new Random();
                notificationManager.notify(random.nextInt(100), notificationBuilder.build());
            }
        }
    }









}
