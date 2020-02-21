package peru.volcanes.volcanesper
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Firebasemessagingvolcanespruebas : FirebaseInstanceIDService(){
    val TAG = "PushNotifService"

    internal  var elvalor5: String = "a"
    internal  var elvalor26: String = "bb"
    internal  var valorcero: String = "0"
    internal var orden: String? = null
    internal var Message: String? = null
    internal var ko: String? = null
    internal var ajustes:String? = null
    internal var tipo:String? = null
    internal var r: Int? = null
    internal var s:Int? = null
    internal var datou = "valueu"


    override fun onTokenRefresh() {
       val token = FirebaseInstanceId.getInstance().getToken()
            registerToken(token!!)
            ver()
            contador()
            valoresconfiguracion()
            createNotificationChannel()
            createNotificationChannellahar()
    }

    private fun createNotificationChannel() {
        val channelId = "some_channel_id"
        val channelName = "Some Channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }

    private fun createNotificationChannellahar() {
        val channelId = "some_channel_id_lahar"
        val channelName = "Some Channel"
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.default_notification_channel_name_lahar)
            val description = getString(R.string.channel_description_lahar)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }

    private fun registerToken(token: String) {
        val client = OkHttpClient()
        val body = FormBody.Builder()
                .add("Token", token)
                .build()
        val request = Request.Builder()
                .url("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token=$token")
                .post(body)
                .build()
        consulta("http://intranet.igp.gob.pe/eLdZpqDtLN/index.php?Token=$token")
        guardartoken(token)
        try {
            client.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun consulta(urlString: String) {
          var json: String
          try {
            val url = URL(urlString)
            var urlConnection: HttpURLConnection? = null
            var bufferedReader: BufferedReader? = null
            urlConnection = url.openConnection() as HttpURLConnection
            bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            json = bufferedReader.readLine()
            //  Toast.makeText(getApplicationContext(), json, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "NOMBRE DE TEMA: $json")
            //();
            ver2(json)
            urlConnection.disconnect()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun ver2(dato: String) {
        val file_namex = "datos_ordences"
        try {
            val fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE)
            fileOutputStream.write(dato.toByteArray())
            FirebaseMessaging.getInstance().subscribeToTopic(dato)
            fileOutputStream.close()
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun guardartoken(token: String) {
        val file_namex = "eltoken"
        try {
            val fileOutputStream = openFileOutput(file_namex, MODE_PRIVATE)
            fileOutputStream.write(token.toByteArray())
            fileOutputStream.close()
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun ver() {
        val Message5 = "$elvalor5,"
        val Message6 = elvalor26 + ","
        val file_name = "datos_configuracion"
        try {
            val fileOutputStream = openFileOutput(file_name,  MODE_PRIVATE)
            fileOutputStream.write(Message5.toByteArray())
            fileOutputStream.write(Message6.toByteArray())
            fileOutputStream.close()
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun contador() {
        val Message3 = valorcero
        val file_name = "valorcero"
        try {
            val fileOutputStream = openFileOutput(file_name, MODE_PRIVATE)
            fileOutputStream.write(Message3.toByteArray())
            fileOutputStream.close()
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun valoresconfiguracion() {

        val file_vibrar = "vibrar_file"
        val file_sonido = "sonido_file"
        val file_notificar = "notificar_file"
        val file_tipo = "tipo_file"
        val file_ringtone = "ring_tone_file"
        val file_nombre_ringtone = "nombre_ringtone__file"
        val vibrar_val = "11111"
        val sonido_val = "A"
        val notificacion_val = "11111"
        val ringtone_val = "A"
        val tiposonidoval = "11111"
        val nombre_ringtone_val = "A"

        try {

            val fileOutputStream_vibrar = openFileOutput(file_vibrar,  MODE_PRIVATE)
            fileOutputStream_vibrar.write(vibrar_val.toByteArray())


            val fileOutputStream_sonido = openFileOutput(file_sonido,  MODE_PRIVATE)
            fileOutputStream_sonido.write(sonido_val.toByteArray())


            val fileOutputStream_notificar = openFileOutput(file_notificar, MODE_PRIVATE)
            fileOutputStream_notificar.write(notificacion_val.toByteArray())


            val fileOutputStream_sonidotipo = openFileOutput(file_tipo, MODE_PRIVATE)
            fileOutputStream_sonidotipo.write(tiposonidoval.toByteArray())


            val fileOutputStream_ring_tone = openFileOutput(file_ringtone, MODE_PRIVATE)
            fileOutputStream_ring_tone.write(ringtone_val.toByteArray())


            val fileOutputStream_nombre_ring_tone = openFileOutput(file_nombre_ringtone, MODE_PRIVATE)
            fileOutputStream_nombre_ring_tone.write(nombre_ringtone_val.toByteArray())


            fileOutputStream_nombre_ring_tone.close()

            fileOutputStream_ring_tone.close()


            fileOutputStream_sonidotipo.close()

            fileOutputStream_notificar.close()

            fileOutputStream_vibrar.close()

            fileOutputStream_sonido.close()


        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}