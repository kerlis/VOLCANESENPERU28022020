package peru.volcanes.volcanesper;


import android.app.DownloadManager;
        import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
        import android.net.Uri;
        import android.security.keystore.KeyGenParameterSpec;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
        import android.widget.Toast;

        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.security.GeneralSecurityException;



public class  Servicenotdhn extends IntentService {

    public Servicenotdhn() {



        super("Intentuploadservice");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String username = intent.getStringExtra("text");
        Log.d("datoz: ","WWWWWW" + username);

        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String username = intent.getStringExtra("text");
        Log.d("datozh: ","WWWWWW" + username);
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();


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


/*
        val intent = Intent(context, Ultimosismo4::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val channelId = "canal_sonido_alarmas_sismos_id"
        val notificationBuilder = NotificationCompat.Builder(context!!, channelId)
                                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(Color.parseColor("#001665"))
                .setContentTitle("Sismo Perú...........")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(5, notificationBuilder.build())

*/

 /*
        KeyGenParameterSpec keyGenParameterSpec =  MasterKeys.AES256_GCM_SPEC;
        String masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
        String fileToWrite = "my_sensitive_data.txt";
        try {
            EncryptedFile encryptedFile = new EncryptedFile(
                    new File(directory, fileToWrite),
                    this,
                    masterKeyAlias,
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build();
            // Write to a file.
            BufferedWriter writer = new BufferedWriter(new FileWriter(encryptedFile));
            writer.write("MY SUPER-SECRET INFORMATION");
        } catch (GeneralSecurityException gse) {
            // Error occurred getting or creating keyset.
        } catch (IOException ex) {
            // Error occurred opening file for writing.
        }
*/


/*
        try{
            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse("http://www.zidsworld.com/wp-content/uploads/2018/06/cat_1530281469.jpg");
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                    .setDestinationInExternalPublicDir(DIRECTORY_PICTURES + File.separator, file)
                    .setTitle(file).setDescription(getString(R.string.save_img))
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            dm.enqueue(request);
         //   Toast.makeText(getApplicationContext(), getString(R.string.downloading_img), Toast.LENGTH_LONG).show();
        } catch (IllegalStateException ex) {
            Toast.makeText(getApplicationContext(),"Storage Error", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        } catch (Exception ex) {
            // just in case, it should never be called anyway
            Toast.makeText(getApplicationContext(),"Unable to save image", Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
*/


        return super.onStartCommand(intent,flags,startId);

    }
}