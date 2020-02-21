package peru.volcanes.volcanesper
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
class Firebasemessagingthird : FirebaseMessagingService() {
    var TAG_REGISTER: String? = null
    var valorsonido: String? = null
    var valornotificacion: String? = null
    var valortipo: String? = null
    var valorringtone: String? = null
    var sonido_notificacion = null
    var valorentero:Int = 0
    var valorramdomesound: String? = null


    val random = Random()
    val valorenterox:Int = random.nextInt(100)
    val valorenteroxstring: String = valorenterox.toString()


    companion object {
        private val TAG = Firebasemessagingthird::class.java.simpleName
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        //FirebaseMessaging.getInstance().subscribeToTopic("VOLCANESPERU30015")


        val valor3: String? = remoteMessage!!.getData().get("cuerpo")
        val valorextraido: String = valor3!!.split("&")[0]
        val volcan: String = valor3!!.split("&")[1]
        val tipodevento: String = valor3!!.split("&")[2]
        val fecha: String = valor3!!.split("&")[3]
        val hora: String = valor3!!.split("&")[4]
        val observaciones: String = valor3!!.split("&")[5]
        val simulacro: String = valor3!!.split("&")[6]
        val horautc: String = valor3!!.split("&")[7]


        Log.i("LLEGO EL MENSAJE",valor3)

        var file2 = InputStreamReader(openFileInput("vibrar_file"))
        var br2 = BufferedReader(file2)
        var line2 = br2.readLine()
        var all2 = StringBuilder()
        while (line2 != null) {
            all2.append(line2 + "\n")
            line2 = br2.readLine()
        }
        br2.close()
        file2.close()
        valorsonido = all2.toString()


        val file3 = InputStreamReader(openFileInput("notificar_file"))
        val br3 = BufferedReader(file3)
        var line3 = br3.readLine()
        val all3 = StringBuilder()
        while (line3 != null) {
            all3.append(line3 + "\n")
            line3 = br3.readLine()
        }
        br3.close()
        file3.close()
        valornotificacion = all3.toString()


        val file4 = InputStreamReader(openFileInput("tipo_file"))
        val br4 = BufferedReader(file4)
        var line4 = br4.readLine()
        val all4 = StringBuilder()
        while (line4 != null) {
            all4.append(line4 + "\n")
            line4 = br4.readLine()
        }
        br4.close()
        file4.close()
        valortipo = all4.toString()
        valorentero = valortipo!!.length


        val file5 = InputStreamReader(openFileInput("ring_tone_file"))
        val br5 = BufferedReader(file5)
        var line5 = br5.readLine()
        val all5 = StringBuilder()
        while (line5 != null) {
            all5.append(line5 + "\n")
            line5 = br5.readLine()
        }
        br5.close()
        file5.close()
        valorringtone = all5.toString()





        val file6 = InputStreamReader(openFileInput("ramdom_number_file"))
        val br6 = BufferedReader(file6)
        var line6 = br6.readLine()
        val all6 = StringBuilder()
        while (line6 != null) {
            all6.append(line6 + "\n")
            line6 = br6.readLine()
        }
        br6.close()
        file6.close()
        valorramdomesound = all6.toString()


        if (valorextraido.equals("n01")){

            if (valornotificacion!!.length > 4){
                enviarnotificaciondatosalertalahar(remoteMessage.getData().get("cuerpo"));
            }
            else{
                norecibir()
            }
        }

        else if(valorextraido.equals("n02")){

            if (valornotificacion!!.length > 4){
                enviarnotificaciondatosreporteextraordinario(remoteMessage.getData().get("cuerpo"))
            }
            else{
                norecibir()
            }

        }

        else if(valorextraido.equals("n03")){

            if (valornotificacion!!.length > 4){
                sendNotificationalertadecenizas(remoteMessage.getData().get("cuerpo"))
            }
            else{
                norecibir()
            }

        }
        else if(valorextraido.equals("n04")){

            if (valornotificacion!!.length > 4){
                enviarnotificaciondatosreporteordinario(remoteMessage.getData().get("cuerpo"))
            }
            else{
                norecibir()
            }

        }
        else if(valorextraido.equals("n05")){

            if (valornotificacion!!.length > 4){
                enviarnotificaciondatossismos(remoteMessage.getData().get("cuerpo"))
            }
            else{
                norecibir()
            }

        }
        else{
            if (valornotificacion!!.length > 4){
                enviarnotificaciondatosalertalahar(remoteMessage.getData().get("cuerpo"))
            }
            else{
                norecibir()
            }
        }




    }


    private fun enviarnotificaciondatosalertalahar(remoteMessage: String?) {

        registrarnotificaionsqlite(remoteMessage!!, valorenteroxstring)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val tiponotificacion2: String = remoteMessage!!.split("&")[0]
            val volcan: String = remoteMessage!!.split("&")[1]
            val tipodevento: String = remoteMessage!!.split("&")[2]
            val fecha: String = remoteMessage!!.split("&")[3]
            val hora: String = remoteMessage!!.split("&")[4]
            val horautc: String = remoteMessage!!.split("&")[5]
            val observaciones: String = remoteMessage!!.split("&")[6]
            val simulacro: String = remoteMessage!!.split("&")[7]
            val asubstring = fecha.substring(0, 10)
            var nobrevolcan_r2: String

            if (volcan == "1493157379002") {
                nobrevolcan_r2 = "Volcán Ubinas"
            } else if (volcan == "1493157381161") {
                nobrevolcan_r2 = "Volcán Sabancaya"
            } else if (volcan == "1506454510537") {
                nobrevolcan_r2 = "Volcán Sara Sara"
            } else if (volcan == "1506455245814") {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato"
            } else if (volcan == "1506455248101") {
                nobrevolcan_r2 = "Volcán Andahua"
            } else if (volcan == "1506455249661") {
                nobrevolcan_r2 = "Volcán Coropuna"
            } else if (volcan == "1506455251429") {
                nobrevolcan_r2 = "Volcán Huambo"
            } else if (volcan == "1506455253382") {
                nobrevolcan_r2 = "Volcán Chachani"
            } else if (volcan == "1506455254838") {
                nobrevolcan_r2 = "Volcán Tutupaca"
            } else if (volcan == "1506455256229") {
                nobrevolcan_r2 = "Volcán Huaynaputina"
            } else if (volcan == "1506455257749") {
                nobrevolcan_r2 = "Volcán Ticsani"
            } else if (volcan == "1506455257753") {
                nobrevolcan_r2 = "Volcán Casiri"
            } else if (volcan == "1506455257755") {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni"
            } else if (volcan == "1506455257757") {
                nobrevolcan_r2 = "Volcán Quimsachata"
            } else if (volcan == "1506455259126") {
                nobrevolcan_r2 = "Volcán Yucamane"
            } else if (volcan == "1506455259128") {
                nobrevolcan_r2 = "Volcán Misti"
            } else {
                nobrevolcan_r2 = "Volcán"
            }

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)
            //  .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

            val intent = Intent(this, Alertadatoslahar::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)


            if (valorsonido!!.length > 4) {

                if(valorentero <= 6) {
                    val channelId = "canal_sonido_alarmas_id"
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                     val notificationManager = NotificationManagerCompat.from(applicationContext)


                   // val random = Random()
                   // notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else if(valorentero <= 7){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                    val channelId = "canal_sonido_notificacion_id"
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                  //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                     val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else{

                    val file6 = InputStreamReader(openFileInput("ramdom_number_file"))
                    val br6 = BufferedReader(file6)
                    var line6 = br6.readLine()
                    val all6 = StringBuilder()
                    while (line6 != null) {
                        all6.append(line6)
                        line6 = br6.readLine()
                    }
                    br6.close()
                    file6.close()
                    valorramdomesound = all6.toString()


                    var stingh: String = valorringtone.toString()
                    val lastChar = stingh.substring(0,2)
                    val lastCharrw = stingh.substring(0, (stingh.length - 1))
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                    val sonido_notificacion_android  = Uri.parse(lastCharrw)

                    //val sonido_notificacion_android  = Uri.parse(valorringtone)



                    val channelId = "canal_sonido_personalizado_id"+valorramdomesound
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                   /// val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())

                    Log.v(TAG_REGISTER, " Valor pi :$channelId")

                }

            }
            else{

                val channelId =  "canal_sin_sonido_con_vibracion_id"
                val notificationBuilder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("Alerta de Lahar")
                        .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                        .setAutoCancel(true)
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
               // val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val notificationManager = NotificationManagerCompat.from(applicationContext)

                //val random = Random()
                //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                notificationManager.notify(valorenterox, notificationBuilder.build())


            }
        }



        else {
            val tiponotificacion2: String = remoteMessage!!.split("&")[0]
            val volcan: String = remoteMessage!!.split("&")[1]
            val tipodevento: String = remoteMessage!!.split("&")[2]
            val fecha: String = remoteMessage!!.split("&")[3]
            val hora: String = remoteMessage!!.split("&")[4]
            val horautc: String = remoteMessage!!.split("&")[5]
            val observaciones: String = remoteMessage!!.split("&")[6]
            val simulacro: String = remoteMessage!!.split("&")[7]

            val asubstring = fecha.substring(0, 10)
            var nobrevolcan_r2: String
            if (volcan == "1493157379002") {
                nobrevolcan_r2 = "Volcán Ubinas"
            } else if (volcan == "1493157381161") {
                nobrevolcan_r2 = "Volcán Sabancaya"
            } else if (volcan == "1506454510537") {
                nobrevolcan_r2 = "Volcán Sara Sara"
            } else if (volcan == "1506455245814") {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato"
            } else if (volcan == "1506455248101") {
                nobrevolcan_r2 = "Volcán Andahua"
            } else if (volcan == "1506455249661") {
                nobrevolcan_r2 = "Volcán Coropuna"
            } else if (volcan == "1506455251429") {
                nobrevolcan_r2 = "Volcán Huambo"
            } else if (volcan == "1506455253382") {
                nobrevolcan_r2 = "Volcán Chachani"
            } else if (volcan == "1506455254838") {
                nobrevolcan_r2 = "Volcán Tutupaca"
            } else if (volcan == "1506455256229") {
                nobrevolcan_r2 = "Volcán Huaynaputina"
            } else if (volcan == "1506455257749") {
                nobrevolcan_r2 = "Volcán Ticsani"
            } else if (volcan == "1506455257753") {
                nobrevolcan_r2 = "Volcán Casiri"
            } else if (volcan == "1506455257755") {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni"
            } else if (volcan == "1506455257757") {
                nobrevolcan_r2 = "Volcán Quimsachata"
            } else if (volcan == "1506455259126") {
                nobrevolcan_r2 = "Volcán Yucamane"
            } else if (volcan == "1506455259128") {
                nobrevolcan_r2 = "Volcán Misti"
            } else {
                nobrevolcan_r2 = "Volcán"
            }

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)


            val intent = Intent(this, Alertadatoslahar::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT)



            if (valorsonido!!.length > 4) {
                if (valorentero == 6) {
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)
                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else if(valorentero == 7){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)
                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else{


                    var stingh: String = valorringtone.toString()
                    val lastChar = stingh.substring(0,2)
                    val lastCharrw = stingh.substring(0, (stingh.length - 1))
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                    val sonido_notificacion_android  = Uri.parse(lastCharrw)


                  //  val sonido_notificacion_android  = Uri.parse(valorringtone)
                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Alerta de Lahar")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())





                }
            }

            else{
                val notificationBuilder = NotificationCompat.Builder(this)
                        .setContentTitle("Alerta de Lahar")
                        .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                //val random = Random()
                //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                notificationManager.notify(valorenterox, notificationBuilder.build())


            }
         }


    }






    private fun sendNotificationalertadecenizas(remoteMessage: String?) {

        registrarnotificaionsqlite(remoteMessage!!, valorenteroxstring)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val tiponotificacion2: String = remoteMessage!!.split("&")[0]
            val volcan: String = remoteMessage!!.split("&")[1]
            val pueblos: String = remoteMessage!!.split("&")[2]
            val tipodevento: String = remoteMessage!!.split("&")[3]
            val direccion: String = remoteMessage!!.split("&")[4]
            val radio:String = remoteMessage!!.split("&")[5]
            val fecha: String = remoteMessage!!.split("&")[6]
            val hora: String = remoteMessage!!.split("&")[7]

            val horautc: String = remoteMessage!!.split("&")[8]

            val recomendaciones: String = remoteMessage!!.split("&")[9]
            val observaciones: String = remoteMessage!!.split("&")[10]
            val simulacro: String = remoteMessage!!.split("&")[11]
          //  val asubstring = fecha.substring(0, 10)

            val asubstring = fecha

            var nobrevolcan_r2: String

            if (volcan == "1493157379002") {
                nobrevolcan_r2 = "Volcán Ubinas"
            } else if (volcan == "1493157381161") {
                nobrevolcan_r2 = "Volcán Sabancaya"
            } else if (volcan == "1506454510537") {
                nobrevolcan_r2 = "Volcán Sara Sara"
            } else if (volcan == "1506455245814") {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato"
            } else if (volcan == "1506455248101") {
                nobrevolcan_r2 = "Volcán Andahua"
            } else if (volcan == "1506455249661") {
                nobrevolcan_r2 = "Volcán Coropuna"
            } else if (volcan == "1506455251429") {
                nobrevolcan_r2 = "Volcán Huambo"
            } else if (volcan == "1506455253382") {
                nobrevolcan_r2 = "Volcán Chachani"
            } else if (volcan == "1506455254838") {
                nobrevolcan_r2 = "Volcán Tutupaca"
            } else if (volcan == "1506455256229") {
                nobrevolcan_r2 = "Volcán Huaynaputina"
            } else if (volcan == "1506455257749") {
                nobrevolcan_r2 = "Volcán Ticsani"
            } else if (volcan == "1506455257753") {
                nobrevolcan_r2 = "Volcán Casiri"
            } else if (volcan == "1506455257755") {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni"
            } else if (volcan == "1506455257757") {
                nobrevolcan_r2 = "Volcán Quimsachata"
            } else if (volcan == "1506455259126") {
                nobrevolcan_r2 = "Volcán Yucamane"
            } else if (volcan == "1506455259128") {
                nobrevolcan_r2 = "Volcán Misti"
            } else {
                nobrevolcan_r2 = "Volcán"
            }

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

            //     .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

            val intent = Intent(this, Alertando::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
           // val channelId = getString(R.string.default_notification_channel_id)
           // val channelName = getString(R.string.default_notification_channel_name)


            if (valorsonido!!.length > 4) {

                if(valorentero <= 6) {

                    val channelId = "canal_sonido_alarmas_id"
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                   // val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else if(valorentero <= 7){

                    val channelId = "canal_sonido_notificacion_id"
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else{

                    val file6 = InputStreamReader(openFileInput("ramdom_number_file"))
                    val br6 = BufferedReader(file6)
                    var line6 = br6.readLine()
                    val all6 = StringBuilder()
                    while (line6 != null) {
                        all6.append(line6)
                        line6 = br6.readLine()
                    }
                    br6.close()
                    file6.close()
                    valorramdomesound = all6.toString()



                    val channelId = "canal_sonido_personalizado_id"+valorramdomesound
                    var stingh: String = valorringtone.toString()
                    val lastChar = stingh.substring(0,2)
                    val lastCharrw = stingh.substring(0, (stingh.length - 1))
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                    val sonido_notificacion_android  = Uri.parse(lastCharrw)

                    //val sonido_notificacion_android  = Uri.parse(valorringtone)
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }

            }
            else{

                val channelId = "canal_sin_sonido_con_vibracion_id"

                val notificationBuilder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("Alerta de Dispersión de Cenizas")
                        .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                        .setAutoCancel(true)
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
             //  val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val notificationManager = NotificationManagerCompat.from(applicationContext)

                //val random = Random()
                //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                notificationManager.notify(valorenterox, notificationBuilder.build())



            }

        }


          else {
            val tiponotificacion2: String = remoteMessage!!.split("&")[0]
            val volcan: String = remoteMessage!!.split("&")[1]
            val pueblos: String = remoteMessage!!.split("&")[2]
            val tipodevento: String = remoteMessage!!.split("&")[3]
            val direccion: String = remoteMessage!!.split("&")[4]
            val radio:String = remoteMessage!!.split("&")[5]
            val fecha: String = remoteMessage!!.split("&")[6]
            val hora: String = remoteMessage!!.split("&")[7]

            val horautc: String = remoteMessage!!.split("&")[8]

            val recomendaciones: String = remoteMessage!!.split("&")[9]
            val observaciones: String = remoteMessage!!.split("&")[10]
            val simulacro: String = remoteMessage!!.split("&")[11]

          //  val asubstring = fecha.substring(0, 10)

            val asubstring = fecha

            var nobrevolcan_r2: String
            if (volcan == "1493157379002") {
                nobrevolcan_r2 = "Volcán Ubinas"
            } else if (volcan == "1493157381161") {
                nobrevolcan_r2 = "Volcán Sabancaya"
            } else if (volcan == "1506454510537") {
                nobrevolcan_r2 = "Volcán Sara Sara"
            } else if (volcan == "1506455245814") {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato"
            } else if (volcan == "1506455248101") {
                nobrevolcan_r2 = "Volcán Andahua"
            } else if (volcan == "1506455249661") {
                nobrevolcan_r2 = "Volcán Coropuna"
            } else if (volcan == "1506455251429") {
                nobrevolcan_r2 = "Volcán Huambo"
            } else if (volcan == "1506455253382") {
                nobrevolcan_r2 = "Volcán Chachani"
            } else if (volcan == "1506455254838") {
                nobrevolcan_r2 = "Volcán Tutupaca"
            } else if (volcan == "1506455256229") {
                nobrevolcan_r2 = "Volcán Huaynaputina"
            } else if (volcan == "1506455257749") {
                nobrevolcan_r2 = "Volcán Ticsani"
            } else if (volcan == "1506455257753") {
                nobrevolcan_r2 = "Volcán Casiri"
            } else if (volcan == "1506455257755") {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni"
            } else if (volcan == "1506455257757") {
                nobrevolcan_r2 = "Volcán Quimsachata"
            } else if (volcan == "1506455259126") {
                nobrevolcan_r2 = "Volcán Yucamane"
            } else if (volcan == "1506455259128") {
                nobrevolcan_r2 = "Volcán Misti"
            } else {
                nobrevolcan_r2 = "Volcán"
            }

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)


            val intent = Intent(this, Alertando::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT)
            //val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            if (valorsonido!!.length > 4) {

                if(valorentero <= 6) {
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else if(valorentero <= 7){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else{

                    var stingh: String = valorringtone.toString()
                    val lastChar = stingh.substring(0,2)
                    val lastCharrw = stingh.substring(0, (stingh.length - 1))
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                    val sonido_notificacion_android  = Uri.parse(lastCharrw)

                   // val sonido_notificacion_android  = Uri.parse(valorringtone)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Alerta de Dispersión de Cenizas")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())

                }


            }
            else{

                val notificationBuilder = NotificationCompat.Builder(this)
                        .setContentTitle("Alerta de Dispersión de Cenizas")
                        .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                //val random = Random()
                //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                notificationManager.notify(valorenterox, notificationBuilder.build())


            }



         }

    }



    private fun isNotificationVisible()  {
        val notificationIntent = Intent(this, pagedivisor::class.java)
        val test = PendingIntent.getActivity(this, 77, notificationIntent, PendingIntent.FLAG_NO_CREATE)

        Log.d("DATO JK: ",test.toString())


     }


    private fun sendNotification() {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationBuilder: NotificationCompat.Builder? = null
        var currentNotificationID: Int = 0
        val  notificationManager: NotificationManager? = null

        notificationBuilder!!.setContentIntent(contentIntent)
        val notification = notificationBuilder.build()
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
        notification.defaults = notification.defaults or Notification.DEFAULT_SOUND
        currentNotificationID++
        var notificationId = currentNotificationID
        if (notificationId == Integer.MAX_VALUE - 1)
            notificationId = 0
        notificationManager!!.notify(notificationId, notification)
    }

    private fun enviarnotificaciondatosreporteextraordinario(remoteMessage: String?) {

        registrarnotificaionsqlite(remoteMessage!!, valorenteroxstring)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                val tiponotificacion2: String = remoteMessage!!.split("&")[0]
                val volcan: String = remoteMessage!!.split("&")[1]
                val fecha: String = remoteMessage!!.split("&")[2]
                val hora: String = remoteMessage!!.split("&")[3]
                val simulacro: String = remoteMessage!!.split("&")[4]
                val horautc: String = remoteMessage!!.split("&")[5]
                val reportepdf: String = remoteMessage!!.split("&")[6]
                val coloralerta: String = remoteMessage!!.split("&")[7]
                val analisis: String = remoteMessage!!.split("&")[8]
                val conclusiones: String = remoteMessage!!.split("&")[9]

                // val asubstring = fecha


                val asubstring = fecha.substring(0, 10)
                var nobrevolcan_r2: String

                if (volcan == "1493157379002") {
                    nobrevolcan_r2 = "Volcán Ubinas"
                } else if (volcan == "1493157381161") {
                    nobrevolcan_r2 = "Volcán Sabancaya"
                } else if (volcan == "1506454510537") {
                    nobrevolcan_r2 = "Volcán Sara Sara"
                } else if (volcan == "1506455245814") {
                    nobrevolcan_r2 = "Volcán Cerro Auquihuato"
                } else if (volcan == "1506455248101") {
                    nobrevolcan_r2 = "Volcán Andahua"
                } else if (volcan == "1506455249661") {
                    nobrevolcan_r2 = "Volcán Coropuna"
                } else if (volcan == "1506455251429") {
                    nobrevolcan_r2 = "Volcán Huambo"
                } else if (volcan == "1506455253382") {
                    nobrevolcan_r2 = "Volcán Chachani"
                } else if (volcan == "1506455254838") {
                    nobrevolcan_r2 = "Volcán Tutupaca"
                } else if (volcan == "1506455256229") {
                    nobrevolcan_r2 = "Volcán Huaynaputina"
                } else if (volcan == "1506455257749") {
                    nobrevolcan_r2 = "Volcán Ticsani"
                } else if (volcan == "1506455257753") {
                    nobrevolcan_r2 = "Volcán Casiri"
                } else if (volcan == "1506455257755") {
                    nobrevolcan_r2 = "Volcán Cerros Purupuruni"
                } else if (volcan == "1506455257757") {
                    nobrevolcan_r2 = "Volcán Quimsachata"
                } else if (volcan == "1506455259126") {
                    nobrevolcan_r2 = "Volcán Yucamane"
                } else if (volcan == "1506455259128") {
                    nobrevolcan_r2 = "Volcán Misti"
                } else {
                    nobrevolcan_r2 = "Volcán"
                }
                val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                val intent = Intent(this, Alertareporteactividad::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("NOTIFICACIONDATA", remoteMessage)
                val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

                if (valorsonido!!.length > 4) {

                    if(valorentero <= 6) {
                        val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)

                        val channelId = "canal_sonido_alarmas_id"
                        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setColor(Color.parseColor("#001665"))
                                .setContentTitle("Reporte Extraordinario")
                                .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent)
                        //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        val notificationManager = NotificationManagerCompat.from(applicationContext)

                        //val random = Random()
                        //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                        notificationManager.notify(valorenterox, notificationBuilder.build())


                    }

                    else if(valorentero <= 7) {
                        val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                        val channelId = "canal_sonido_notificacion_id"
                        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setColor(Color.parseColor("#001665"))
                                .setContentTitle("Reporte Extraordinario")
                                .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent)
                       // val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        val notificationManager = NotificationManagerCompat.from(applicationContext)

                        //val random = Random()
                        //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                        notificationManager.notify(valorenterox, notificationBuilder.build())

                    }

                    else{


                        val file6 = InputStreamReader(openFileInput("ramdom_number_file"))
                        val br6 = BufferedReader(file6)
                        var line6 = br6.readLine()
                        val all6 = StringBuilder()
                        while (line6 != null) {
                            all6.append(line6)
                            line6 = br6.readLine()
                        }
                        br6.close()
                        file6.close()
                        valorramdomesound = all6.toString()



                        var stingh: String = valorringtone.toString()
                        val lastChar = stingh.substring(0,2)
                        val lastCharrw = stingh.substring(0, (stingh.length - 1))
                        Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                        val sonido_notificacion_android  = Uri.parse(lastCharrw)

                        val channelId = "canal_sonido_personalizado_id"+valorramdomesound

                        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setColor(Color.parseColor("#001665"))
                                .setContentTitle("Reporte Extraordinario")
                                .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent)
                        //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        val notificationManager = NotificationManagerCompat.from(applicationContext)

                        //val random = Random()
                        //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                        notificationManager.notify(valorenterox, notificationBuilder.build())


                    }


                    }
                else{

                    val channelId = "canal_sin_sonido_con_vibracion_id"
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                            .setContentIntent(pendingIntent)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }

            }



         else {
            val tiponotificacion2: String = remoteMessage!!.split("&")[0]
            val volcan: String = remoteMessage!!.split("&")[1]
            val fecha: String = remoteMessage!!.split("&")[2]
            val hora: String = remoteMessage!!.split("&")[3]
            val simulacro: String = remoteMessage!!.split("&")[4]
            val horautc: String = remoteMessage!!.split("&")[5]
            val reportepdf: String = remoteMessage!!.split("&")[6]
            val coloralerta: String = remoteMessage!!.split("&")[7]
            val analisis: String = remoteMessage!!.split("&")[8]
            val conclusiones: String = remoteMessage!!.split("&")[9]

            val asubstring = fecha.substring(0, 10)
            var nobrevolcan_r2: String
            if (volcan == "1493157379002") {
                nobrevolcan_r2 = "Volcán Ubinas"
            } else if (volcan == "1493157381161") {
                nobrevolcan_r2 = "Volcán Sabancaya"
            } else if (volcan == "1506454510537") {
                nobrevolcan_r2 = "Volcán Sara Sara"
            } else if (volcan == "1506455245814") {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato"
            } else if (volcan == "1506455248101") {
                nobrevolcan_r2 = "Volcán Andahua"
            } else if (volcan == "1506455249661") {
                nobrevolcan_r2 = "Volcán Coropuna"
            } else if (volcan == "1506455251429") {
                nobrevolcan_r2 = "Volcán Huambo"
            } else if (volcan == "1506455253382") {
                nobrevolcan_r2 = "Volcán Chachani"
            } else if (volcan == "1506455254838") {
                nobrevolcan_r2 = "Volcán Tutupaca"
            } else if (volcan == "1506455256229") {
                nobrevolcan_r2 = "Volcán Huaynaputina"
            } else if (volcan == "1506455257749") {
                nobrevolcan_r2 = "Volcán Ticsani"
            } else if (volcan == "1506455257753") {
                nobrevolcan_r2 = "Volcán Casiri"
            } else if (volcan == "1506455257755") {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni"
            } else if (volcan == "1506455257757") {
                nobrevolcan_r2 = "Volcán Quimsachata"
            } else if (volcan == "1506455259126") {
                nobrevolcan_r2 = "Volcán Yucamane"
            } else if (volcan == "1506455259128") {
                nobrevolcan_r2 = "Volcán Misti"
            } else {
                nobrevolcan_r2 = "Volcán"
            }

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)



            val intent = Intent(this, Alertareporteactividad::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT)
            //val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            //val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)
            if (valorsonido!!.length > 4) {

                if(valorentero <= 6) {
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else if(valorentero <= 7) {
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }
                else{

                    var stingh: String = valorringtone.toString()
                    val lastChar = stingh.substring(0,2)
                    val lastCharrw = stingh.substring(0, (stingh.length - 1))
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                    val sonido_notificacion_android  = Uri.parse(lastCharrw)


                    //val sonido_notificacion_android  = Uri.parse(valorringtone)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Reporte Extraordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }


                }
            else{
                val notificationBuilder = NotificationCompat.Builder(this)
                        .setContentTitle("Reporte Extraordinario")
                        .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                //val random = Random()
                //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                notificationManager.notify(valorenterox, notificationBuilder.build())



            }


       }

    }


    private fun enviarnotificaciondatosreporteordinario(remoteMessage: String?) {

        registrarnotificaionsqlite(remoteMessage!! , valorenteroxstring)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val tiponotificacion2: String = remoteMessage!!.split("&")[0]
            val volcan: String = remoteMessage!!.split("&")[1]
            val fecha: String = remoteMessage!!.split("&")[2]
            val hora: String = remoteMessage!!.split("&")[3]
            val simulacro: String = remoteMessage!!.split("&")[4]
            val horautc: String = remoteMessage!!.split("&")[5]
            val reportepdf: String = remoteMessage!!.split("&")[6]
            val coloralerta: String = remoteMessage!!.split("&")[7]
            val analisis: String = remoteMessage!!.split("&")[8]
            val conclusiones: String = remoteMessage!!.split("&")[9]

            // val fecha_subs = fecha.substring(0, 10)

            val asubstring = fecha.substring(0, 10)
            var nobrevolcan_r2: String

            if (volcan == "1493157379002") {
                nobrevolcan_r2 = "Volcán Ubinas"
            } else if (volcan == "1493157381161") {
                nobrevolcan_r2 = "Volcán Sabancaya"
            } else if (volcan == "1506454510537") {
                nobrevolcan_r2 = "Volcán Sara Sara"
            } else if (volcan == "1506455245814") {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato"
            } else if (volcan == "1506455248101") {
                nobrevolcan_r2 = "Volcán Andahua"
            } else if (volcan == "1506455249661") {
                nobrevolcan_r2 = "Volcán Coropuna"
            } else if (volcan == "1506455251429") {
                nobrevolcan_r2 = "Volcán Huambo"
            } else if (volcan == "1506455253382") {
                nobrevolcan_r2 = "Volcán Chachani"
            } else if (volcan == "1506455254838") {
                nobrevolcan_r2 = "Volcán Tutupaca"
            } else if (volcan == "1506455256229") {
                nobrevolcan_r2 = "Volcán Huaynaputina"
            } else if (volcan == "1506455257749") {
                nobrevolcan_r2 = "Volcán Ticsani"
            } else if (volcan == "1506455257753") {
                nobrevolcan_r2 = "Volcán Casiri"
            } else if (volcan == "1506455257755") {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni"
            } else if (volcan == "1506455257757") {
                nobrevolcan_r2 = "Volcán Quimsachata"
            } else if (volcan == "1506455259126") {
                nobrevolcan_r2 = "Volcán Yucamane"
            } else if (volcan == "1506455259128") {
                nobrevolcan_r2 = "Volcán Misti"
            } else {
                nobrevolcan_r2 = "Volcán"
            }


            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)


            val intent = Intent(this, Alertareporteactividad::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

            if (valorsonido!!.length > 4) {

                if(valorentero <= 6) {
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)

                    val channelId =  "canal_sonido_alarmas_id"
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }

                else if(valorentero <= 7) {
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                    val channelId = "canal_sonido_notificacion_id"
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }

                else{


                    val file6 = InputStreamReader(openFileInput("ramdom_number_file"))
                    val br6 = BufferedReader(file6)
                    var line6 = br6.readLine()
                    val all6 = StringBuilder()
                    while (line6 != null) {
                        all6.append(line6)
                        line6 = br6.readLine()
                    }
                    br6.close()
                    file6.close()
                    valorramdomesound = all6.toString()

                    var stingh: String = valorringtone.toString()
                    val lastChar = stingh.substring(0,2)
                    val lastCharrw = stingh.substring(0, (stingh.length - 1))
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                    val sonido_notificacion_android  = Uri.parse(lastCharrw)

                    val channelId = "canal_sonido_personalizado_id"+valorramdomesound
                    val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }

            }

            else{

                val channelId = "canal_sin_sonido_con_vibracion_id"
                val channelName = getString(R.string.canal_sin_sonido_con_vibracion_nombre)


                val notificationBuilder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("Reporte Ordinario")
                        .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                        .setAutoCancel(true)
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val notificationManager = NotificationManagerCompat.from(applicationContext)

                //val random = Random()
                //notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                notificationManager.notify(valorenterox, notificationBuilder.build())



            }

        }


        else {



            val tiponotificacion2: String = remoteMessage!!.split("&")[0]
            val volcan: String = remoteMessage!!.split("&")[1]
            val fecha: String = remoteMessage!!.split("&")[2]
            val hora: String = remoteMessage!!.split("&")[3]
            val simulacro: String = remoteMessage!!.split("&")[4]
            val horautc: String = remoteMessage!!.split("&")[5]
            val reportepdf: String = remoteMessage!!.split("&")[6]
            val coloralerta: String = remoteMessage!!.split("&")[7]
            val analisis: String = remoteMessage!!.split("&")[8]
            val conclusiones: String = remoteMessage!!.split("&")[9]

            val asubstring = fecha.substring(0, 10)
            var nobrevolcan_r2: String
            if (volcan == "1493157379002") {
                nobrevolcan_r2 = "Volcán Ubinas"
            } else if (volcan == "1493157381161") {
                nobrevolcan_r2 = "Volcán Sabancaya"
            } else if (volcan == "1506454510537") {
                nobrevolcan_r2 = "Volcán Sara Sara"
            } else if (volcan == "1506455245814") {
                nobrevolcan_r2 = "Volcán Cerro Auquihuato"
            } else if (volcan == "1506455248101") {
                nobrevolcan_r2 = "Volcán Andahua"
            } else if (volcan == "1506455249661") {
                nobrevolcan_r2 = "Volcán Coropuna"
            } else if (volcan == "1506455251429") {
                nobrevolcan_r2 = "Volcán Huambo"
            } else if (volcan == "1506455253382") {
                nobrevolcan_r2 = "Volcán Chachani"
            } else if (volcan == "1506455254838") {
                nobrevolcan_r2 = "Volcán Tutupaca"
            } else if (volcan == "1506455256229") {
                nobrevolcan_r2 = "Volcán Huaynaputina"
            } else if (volcan == "1506455257749") {
                nobrevolcan_r2 = "Volcán Ticsani"
            } else if (volcan == "1506455257753") {
                nobrevolcan_r2 = "Volcán Casiri"
            } else if (volcan == "1506455257755") {
                nobrevolcan_r2 = "Volcán Cerros Purupuruni"
            } else if (volcan == "1506455257757") {
                nobrevolcan_r2 = "Volcán Quimsachata"
            } else if (volcan == "1506455259126") {
                nobrevolcan_r2 = "Volcán Yucamane"
            } else if (volcan == "1506455259128") {
                nobrevolcan_r2 = "Volcán Misti"
            } else {
                nobrevolcan_r2 = "Volcán"
            }


            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

            if (valorsonido!!.length > 4) {

                if(valorentero <= 6) {
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)

                    val intent = Intent(this, Alertareporteactividad::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra("NOTIFICACIONDATA", remoteMessage);
                    val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                            PendingIntent.FLAG_ONE_SHOT)
                    // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }

                else if(valorentero <= 7){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                    val intent = Intent(this, Alertareporteactividad::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra("NOTIFICACIONDATA", remoteMessage);
                    val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                            PendingIntent.FLAG_ONE_SHOT)
                    // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager



                    //val random = Random()
                   // notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                    notificationManager.notify(valorenterox, notificationBuilder.build())


                }


                else {

                    var stingh: String = valorringtone.toString()
                    val lastChar = stingh.substring(0,2)
                    val lastCharrw = stingh.substring(0, (stingh.length - 1))
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                    val sonido_notificacion_android  = Uri.parse(lastCharrw)


                    //val sonido_notificacion_android  = Uri.parse(valorringtone)

                    val intent = Intent(this, Alertareporteactividad::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra("NOTIFICACIONDATA", remoteMessage);
                    val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                            PendingIntent.FLAG_ONE_SHOT)
                    // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("Reporte Ordinario")
                            .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())

                }


            }
            else{
                val intent = Intent(this, Alertareporteactividad::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("NOTIFICACIONDATA", remoteMessage);
                val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT)
                // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val notificationBuilder = NotificationCompat.Builder(this)
                        .setContentTitle("Reporte Ordinario")
                        .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                //val random = Random()
                //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                notificationManager.notify(valorenterox, notificationBuilder.build())


            }


        }
    }


    private fun enviarnotificaciondatossismos(remoteMessage: String?) {

        registrarnotificaionsqlite(remoteMessage!!, valorenteroxstring)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val tiponotificacion: String = remoteMessage!!.split("&")[0]

            val longitud: String = remoteMessage!!.split("&")[1]
            val latitud: String = remoteMessage!!.split("&")[2]
            val epicentro: String = remoteMessage!!.split("&")[3]
            val profundo: String = remoteMessage!!.split("&")[4]
            val referencia: String = remoteMessage!!.split("&")[5]
            val magnitud: String = remoteMessage!!.split("&")[6]
            val publicar: String = remoteMessage!!.split("&")[7]
            val intensidad: String = remoteMessage!!.split("&")[8]
            val hora_local: String = remoteMessage!!.split("&")[9]
            val fecha: String = remoteMessage!!.split("&")[10]

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

            val alarmSound = Uri.parse("content://media/internal/audio/media/38")



            val intent = Intent(this, pagedivisor::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

          //  val channelId = "some_channel_id";
          //  val channelName = getString(R.string.channel_name)



            if (valorsonido!!.length > 4){

                if(valorentero <= 6){

                    val channelId = "canal_sonido_alarmas_id"
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)
                    var notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                    notificationManager.notify(valorenterox, notificationBuilder.build())

                }

                else if(valorentero <= 7){

                    val channelId = "canal_sonido_notificacion_id"
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)
                    var notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                    notificationManager.notify(valorenterox, notificationBuilder.build())

                }

                else{


                    val file6 = InputStreamReader(openFileInput("ramdom_number_file"))
                    val br6 = BufferedReader(file6)
                    var line6 = br6.readLine()
                    val all6 = StringBuilder()
                    while (line6 != null) {
                        all6.append(line6)
                        line6 = br6.readLine()
                    }
                    br6.close()
                    file6.close()
                    valorramdomesound = all6.toString()




                    var stingh: String = valorringtone.toString()
                    val lastChar = stingh.substring(0,2)
                    val lastCharrw = stingh.substring(0, (stingh.length - 1))
                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")
                    val sonido_notificacion_android  = Uri.parse(lastCharrw)


                    val channelId = "canal_sonido_personalizado_id"+valorramdomesound
                    var notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    val notificationManager = NotificationManagerCompat.from(applicationContext)

                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                    notificationManager.notify(valorenterox, notificationBuilder.build())

                }

            }


            else {
                val channelId = "canal_sin_sonido_con_vibracion_id"
                var notificationBuilder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                        .setContentText("APP VOLCANES IGP")
                        .setAutoCancel(true)
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                //val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val notificationManager = NotificationManagerCompat.from(applicationContext)

                //val random = Random()
                ///notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                notificationManager.notify(valorenterox, notificationBuilder.build())

            }

        }





        else {
            val tiponotificacion: String = remoteMessage!!.split("&")[0]

            val longitud: String = remoteMessage!!.split("&")[1]
            val latitud: String = remoteMessage!!.split("&")[2]
            val epicentro: String = remoteMessage!!.split("&")[3]
            val profundo: String = remoteMessage!!.split("&")[4]
            val referencia: String = remoteMessage!!.split("&")[5]
            val magnitud: String = remoteMessage!!.split("&")[6]
            val publicar: String = remoteMessage!!.split("&")[7]
            val intensidad: String = remoteMessage!!.split("&")[8]
            val hora_local: String = remoteMessage!!.split("&")[9]
            val fecha: String = remoteMessage!!.split("&")[10]

            val defaultSoundUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)


            val alarmSound = Uri.parse("content://media/internal/audio/media/38")


            val intent = Intent(this, pagedivisor::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("NOTIFICACIONDATA", remoteMessage);
            val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT)

            // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            if (valorsonido!!.length > 4) {

                if(valorentero == 6){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())

                }


                else if(valorentero == 7){
                    val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.beep2)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                   // notificationManager.notify(random.nextInt(100), notificationBuilder.build())

                    notificationManager.notify(valorenterox, notificationBuilder.build())

                }

                else{
                    // val sonido_notificacion_android  = Uri.parse(valorringtone)

                    var stingh: String = valorringtone.toString()

                    //var stingh2: String = "38"


                    //  val de:Int = stingh.length

                    val lastChar = stingh.substring(0,2)


                    //val lastCharr= StringUtils.substring("string", 0, -1);

                    val lastCharrw = stingh.substring(0, (stingh.length - 1))


                    Log.v(TAG_REGISTER, " Valor pux :$lastChar")


                    //val sonido_notificacion_android  = Uri.parse("content://media/internal/audio/media/$lastCharrw")

                    val sonido_notificacion_android  = Uri.parse(lastCharrw)

                    val notificationBuilder = NotificationCompat.Builder(this)
                            .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                            .setContentText("APP VOLCANES IGP")
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setColor(Color.parseColor("#001665"))
                            .setSound(sonido_notificacion_android)
                            .setContentIntent(pendingIntent)
                            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    //val random = Random()
                    //notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                    notificationManager.notify(valorenterox, notificationBuilder.build())

                }
            }
            else {
                val notificationBuilder = NotificationCompat.Builder(this)
                        .setContentTitle("NOTIFICACIÓN DE PRUEBA")
                        .setContentText("APP VOLCANES IGP")
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setVibrate(longArrayOf(50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100, 50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100,50, 100))
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                //val random = Random()
                //notificationManager.notify(random.nextInt(100), notificationBuilder.build())


                notificationManager.notify(valorenterox, notificationBuilder.build())

            }
        }
    }



    private fun registrarnotificaionsqlite(dato: String, ramdomvaluenot: String){
        val conn = Conexionsql(applicationContext, "bd_notificaciones", null, 1)
        val db = conn.writableDatabase
        val values = ContentValues()
        //values.put(Utilidades.CAMPO_ID,  );
        values.put(Utilidades.CAMPO_MENSAJE,  dato)
        val idresultante = db.insert(Utilidades.TABLA_NOTIFICACIONES, Utilidades.CAMPO_ID, values)
        Log.d("el resultante: ", "Resultante:$idresultante")


        val Message5 = dato
        val file_namex = "ultima_notificacion"
        try {
            val fileOutputStream = openFileOutput(file_namex, Context.MODE_PRIVATE)
            fileOutputStream.write(Message5.toByteArray())
            fileOutputStream.close()
            //  Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun norecibir() {
        Log.v(TAG_REGISTER, "ENVIO DE NOTIFICACIONES BLOQUEADO")

    }

}


