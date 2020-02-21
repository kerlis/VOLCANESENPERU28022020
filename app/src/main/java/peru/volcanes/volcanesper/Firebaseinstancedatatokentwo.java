package peru.volcanes.volcanesper;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import static android.content.ContentValues.TAG;

public class Firebaseinstancedatatokentwo extends FirebaseInstanceIDService {

    String elvalor = "a";
    String elvalor2 = "bb";
    String valorcero = "0";
    String orden;
    String Message;
    String ko,ajustes,tipo;
    Integer r,s;
    String json;
    String datou = "valueu";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.i("EL TOKEN: ",token);


        registerToken(token);
        ver();
        contador();
        //createNotificationChannel();
        valoresconfiguracion();
        Log.d(TAG, "EL NUEVO TOKEN ES: ${token}");
        crear_canal_sonido_alarma();
        crear_canal_sonido_notificacion();
        crear_canal_sin_sonido_con_vibracion();
        default_channel();
        checktable();
        establecerworkmanager();
    }

    private void establecerworkmanager(){

        Constraints constraints = null;
        constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                //.setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
                .build();

        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(Periodwork.class)
                .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS)
                .setInitialDelay(7, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance().enqueue(uploadWorkRequest);



        //WorkManager.cancelWorkById(uploadWorkRequest.getId());

    }

    private void checktable(){
        Conexionsql conn = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from "+Utilidades.TABLA_NOTIFICACIONES + " ORDER BY id DESC LIMIT 1", null );
        res.moveToFirst();
        if(res.getCount() != 0){
            Log.d("HAY DATOS","HAY DATOS");
        }
        else{
            Log.d("NO NO HAY DATOS","NO NO HAY DATOS");
            Conexionsql conn2 = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
            SQLiteDatabase db2 = conn2.getWritableDatabase();
            ContentValues values = new ContentValues();
            //values.put(Utilidades.CAMPO_ID,  );
            values.put(Utilidades.CAMPO_MENSAJE, "mensaje1&&mensaje1&&mensaje3&&mensaje4&&mensaje5&&mensaje6");
            Long idresultante = db2.insert(Utilidades.TABLA_NOTIFICACIONES, Utilidades.CAMPO_ID, values);
            //Toast.makeText(getApplicationContext(), "Resultante:" + idresultante + "", Toast.LENGTH_LONG);
            Log.d("el resultante: ","Resultante:" + idresultante + ""   );


            try {
                FileOutputStream fileOutputStream_tipo = getApplicationContext().openFileOutput("ultima_notificacion", Context.MODE_PRIVATE);
                fileOutputStream_tipo.write("mensaje1&&mensaje1&&mensaje3&&mensaje4&&mensaje5&&mensaje6".getBytes());
                fileOutputStream_tipo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }

    private void default_channel() {
        String channelId = getString(R.string.default_notification_channel_id);
        Uri sonido_notificacion_android = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarmasonido);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.default_notification_channel_name);
            String description = getString(R.string.default_notification_channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setSound(sonido_notificacion_android, audioAttributes);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void crear_canal_sonido_alarma() {
        String channelId = getString(R.string.canal_sonido_alarmas_id);
        Uri sonido_notificacion_android = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarmasonido);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_sonido_alarmas_id_nombre);
            String description = getString(R.string.canal_sonido_alarmas_id_descripcion);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setSound(sonido_notificacion_android, audioAttributes);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void crear_canal_sonido_notificacion() {
        String channelId = getString(R.string.canal_sonido_notificacion_id);
        Uri sonido_notificacion_android = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.beep2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_sonido_notificacion_nombre);
            String description = getString(R.string.canal_sonido_notificacion_descripcion);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            channel.setSound(sonido_notificacion_android, audioAttributes);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void crear_canal_sonido_personalizado() {

        try {
            FileInputStream fileInputStream =  openFileInput("ring_tone_file");
            InputStreamReader inputStreamReader =  new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =  new StringBuffer();
            try {
                while ((Message = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(Message);
                }
                ko = stringBuffer.toString();

                /*
                StringTokenizer st = new StringTokenizer(stringBuffer.toString(), ",");
                ajustes = st.nextToken();
                tipo = st.nextToken();
                r = ajustes.length();
                s = tipo.length();
                if (r == 1){
                    showNotification(remoteMessage.getData().get("message"));
                }
                else {
                    String bb = "";
                }
                */
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        /*
        File file5 = InputStreamReader(openFileInput("ring_tone_file"));
        String br5 = BufferedReader(file5);
        String line5 = br5.readLine();
        String all5 = StringBuilder();
        while (line5 != null) {
            all5.append(line5 + "\n");
            line5 = br5.readLine();
        }
        br5.close();
        file5.close();
        valorringtone = all5.toString();
        */

        String stingh = ko;
        String lastChar = stingh.substring(0,2);
        String  lastCharrw = stingh.substring(0, (stingh.length() - 1));
      //  Log.v(TAG_REGISTER, " Valor pux :$lastChar");
        Uri  sonido_notificacion_android2  = Uri.parse(lastCharrw);


        String channelId = getString(R.string.canal_sonido_personalizado_id);
        //CharSequence channelName = "Some Channel";
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        //Uri sonido_notificacion_android = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarmasonido);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_sonido_personalizado_nombre);
            String description = getString(R.string.canal_sonido_personalizado__descripcion);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            channel.setSound(sonido_notificacion_android2, audioAttributes);

            //channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
           // channel.enableVibration(true);


            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void crear_canal_sin_sonido_con_vibracion() {

        String channelId = getString(R.string.canal_sin_sonido_con_vibracion_id);
        Uri sonido_notificacion_android = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.alarmasonido);

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.canal_sin_sonido_con_vibracion_nombre);
            String description = getString(R.string.canal_sin_sonido_con_vibracion_descripcion);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
             channel.setVibrationPattern(new long[]{50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100});
             channel.enableVibration(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

         }
    }

    private void registerToken(String token) {

        Log.e("EL TOKEN: ",token);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();
        Request request = new Request.Builder()
                .url("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token="+token)
                .post(body)
                .build();
        //  vale .url("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token="+token)
        //  .url("http://arteypixel.com/envio_notificaciones/register.php?Token="+token)

        consulta("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token="+token);
        guardartoken(token);

        //   vale  consulta("http://arteypixel.com/envio_notificaciones/register.php?Token="+token);


        // FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESD");
        //http://intranet.igp.gob.pe/eLdZpqDtLN
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }









    public void consulta(String urlString)  {
        try {
//                    URL url = new URL("http://arteypixel.com/envio_notificaciones/register.php");

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = null;
            BufferedReader bufferedReader = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            json = bufferedReader.readLine();
            //  Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "fdfdfd json: " + json);
            //();
            ver2(json);
            urlConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //   }
        //  });
    }
    /**/



    private void ver2(String dato) {
        String Message5 = dato;
        String file_namex = "datos_ordences";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE);
            fileOutputStream.write(Message5.getBytes());

            FirebaseMessaging.getInstance().subscribeToTopic(Message5);

            //FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESPERU500033344");


            Random random = new Random();
            Integer d = random.nextInt(10);
            String ds = String.valueOf(d);
            Log.d("VALORF RANDOM",ds + " /////" + dato );
            FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESPERU"+ds);




           // FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESPERU500033344");




            //  FirebaseMessaging.getInstance().subscribeToTopic("TEMAVOLCANESANDROIDYIOSIGP");


            //  fileOutputStream.write(Message7.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    private void guardartoken(String token) {
        String Message5 = token;
        String file_namex = "eltoken";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE);
            fileOutputStream.write(Message5.getBytes());


            //  FirebaseMessaging.getInstance().subscribeToTopic(Message5);


            //  fileOutputStream.write(Message7.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void ver() {
        String Message3 = elvalor + ",";
        String Message4 = elvalor2 + ",";
        String file_name = "datos_configuracion";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message3.getBytes());
            fileOutputStream.write(Message4.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void contador() {
        String Message3 = valorcero;
        String file_name = "valorcero";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message3.getBytes());
            fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void valoresconfiguracion() {

        //  String file_name = "datos_configuracion";

        String file_ultimanotificacion = "ultima_notificacion";

        String file_ramdom = "ramdom_number_file";

        String file_vibrar = "vibrar_file";
        String file_sonido = "sonido_file";
        String file_notificar = "notificar_file";
        String file_tipo = "tipo_file";
        String file_ringtone = "ring_tone_file";


        String file_nombre_ringtone = "nombre_ringtone__file";


        String ultimanotificacion_val =  "notexist";


        String vibrar_val =  "11111";
        String sonido_val =  "A";
        String notificacion_val =  "11111";
        String ringtone_val =  "A";

        String tiposonidoval =  "11111";
        String nombre_ringtone_val =  "A";


        try {
            // FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            //fileOutputStream.write(parametro1.getBytes());


            // fileOutputStream.write(parametro2.getBytes());
            //  fileOutputStream.write(parametro3.getBytes());
            // fileOutputStream.write(parametro4.getBytes());
            //  fileOutputStream.write(parametro5.getBytes());
            //  fileOutputStream.write(parametro6.getBytes());

            FileOutputStream fileOutputStream_ultimanot = openFileOutput(file_ultimanotificacion, MODE_PRIVATE);
            fileOutputStream_ultimanot.write(ultimanotificacion_val.getBytes());



            FileOutputStream fileOutputStream_ramdom = openFileOutput(file_ramdom, MODE_PRIVATE);
            fileOutputStream_ramdom.write(vibrar_val.getBytes());

            FileOutputStream fileOutputStream_vibrar = openFileOutput(file_vibrar, MODE_PRIVATE);
            fileOutputStream_vibrar.write(vibrar_val.getBytes());


            FileOutputStream fileOutputStream_sonido = openFileOutput(file_sonido, MODE_PRIVATE);
            fileOutputStream_sonido.write(sonido_val.getBytes());


            FileOutputStream fileOutputStream_notificar = openFileOutput(file_notificar, MODE_PRIVATE);
            fileOutputStream_notificar.write(notificacion_val.getBytes());


            FileOutputStream fileOutputStream_sonidotipo = openFileOutput(file_tipo, MODE_PRIVATE);
            fileOutputStream_sonidotipo.write(tiposonidoval.getBytes());


            FileOutputStream fileOutputStream_ring_tone = openFileOutput(file_ringtone, MODE_PRIVATE);
            fileOutputStream_ring_tone.write(ringtone_val.getBytes());


            FileOutputStream fileOutputStream_nombre_ring_tone = openFileOutput(file_nombre_ringtone, MODE_PRIVATE);
            fileOutputStream_nombre_ring_tone.write(nombre_ringtone_val.getBytes());


            fileOutputStream_nombre_ring_tone.close();

            fileOutputStream_ring_tone.close();


            fileOutputStream_sonidotipo.close();

            fileOutputStream_notificar.close();

            fileOutputStream_vibrar.close();

            fileOutputStream_sonido.close();

            // fileOutputStream.close();
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}