package peru.volcanes.volcanesper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LosdatosMainActivity extends AppCompatActivity {
TextView datocontainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losdatos_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelForRecieveMessage();
        }

        Intent i=this.getIntent();
       String dato = i.getExtras().getString("NOTIFICACIONDATA");

        datocontainer = findViewById(R.id.valor);
        datocontainer.setText(dato);

    }



  //  @RequiresApi(Build.VERSION_CODES.O)

    public void createChannelForRecieveMessage() {

        String channelId = getString(R.string.default_notification_channel_id);
        String channelName = getString(R.string.default_notification_channel_name);

     //   notificationManager = getSystemService(NotificationManager::class.java)
    //    notificationManager?.createNotificationChannel(NotificationChannel(channelId,
      //          channelName, NotificationManager.IMPORTANCE_HIGH))



      //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //void showNotification(String title, String content) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId,
                        channelName,
                        NotificationManager.IMPORTANCE_DEFAULT);
               // channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DISCRIPTION");
                //mNotificationManager.createNotificationChannel(channel);
            }



    }

}
