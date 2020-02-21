package peru.volcanes.volcanesper;


import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ChildEventListener extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Adding a childevent listener to firebase
        Firebase myFirebaseRef = new Firebase("https://volcanesperu-d84cf.firebaseio.com/");
        myFirebaseRef.child("ultimosreportesdeactividades").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Do something using DataSnapshot say call Notification


                String channelId = "default_notification_channel_id";

enviarnotificacion();




            }

            @Override
            public void onCancelled(FirebaseError error) {
                Log.e("The read failed: ", error.getMessage());

                enviarnotificacion();

            }
        });

        return super.onStartCommand(intent,flags,startId);

    }


     public void onCancelled(FirebaseError firebaseError) {
        Log.e("The read failed: ", firebaseError.getMessage());
    }

    public  void enviarnotificacion(){
        String channelId = "default_notification_channel_id";

        Intent intent2 = new Intent(this, pagedivisor.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent2, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(5, builder.build());
    }

}
