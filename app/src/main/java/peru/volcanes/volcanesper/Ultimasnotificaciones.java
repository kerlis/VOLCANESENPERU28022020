package peru.volcanes.volcanesper;
import  android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Collections;
 import peru.volcanes.volcanesper.m_model.ultimasnotificacionesobjeto;
import peru.volcanes.volcanesper.m_ui.Ultimasnotificacionesadapter;

public class Ultimasnotificaciones extends FragmentActivity {
    DatabaseReference mFirebaseDatabase4;
    private FirebaseDatabase database;
    ListView alertacenizas;
    Ultimasnotificacionesadapter adapter3;





//    ArrayList<peru.volcanes.volcanesper.m_model.reporteactividad> objetoalertacenizas = new ArrayList<reporteactividad>();


    ArrayList<peru.volcanes.volcanesper.m_model.ultimasnotificacionesobjeto> objetoalertacenizas = new ArrayList<ultimasnotificacionesobjeto>();

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerBlock;

    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ImageView sliderz;


    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;

    RelativeLayout blocke12;

    RelativeLayout blocke9;
    RelativeLayout blocke6a;
    RelativeLayout blocke92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_ultimasnotificaciones);

        Snackbar.make(findViewById(android.R.id.content),"Mantén actualizada la aplicación", Snackbar.LENGTH_LONG)
                .setAction("Play store", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        try {
                            startActivity(new Intent(intent)
                                    .setPackage("com.android.vending"));
                        } catch (android.content.ActivityNotFoundException exception) {
                            startActivity(intent);
                        }
                        //whatever you want to do when "Action" is clicked in the SnackBar
                    }
                }).show();



        database = FirebaseDatabase.getInstance();
        sliderz = (ImageView) findViewById(R.id.sliderz);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerBlock = (RelativeLayout) findViewById(R.id.mDrawerBlock);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        sliderz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });



        blocke1a = (RelativeLayout) findViewById(R.id.blocke1);
        blocke2a = (RelativeLayout) findViewById(R.id.blocke2);
        blocke4a = (RelativeLayout) findViewById(R.id.blocke4);
        blocke5a = (RelativeLayout) findViewById(R.id.blocke5);
        blocke6a = (RelativeLayout) findViewById(R.id.blocke6);

        blocke12 = (RelativeLayout) findViewById(R.id.blocke12);

        blocke92 = (RelativeLayout) findViewById(R.id.blocke92);

        blocke92.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimasnotificaciones.this,Contactar.class);
                startActivity(intent);
            }
        });



        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimasnotificaciones.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });

        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimasnotificaciones.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimasnotificaciones.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimasnotificaciones.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimasnotificaciones.this,Convenciones.class);
                startActivity(intent);
            }
        });

        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);

        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Ultimasnotificaciones.this,Listadoredessociales.class);
                startActivity(intent);
            }
        });

        blocke6a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });



        mFirebaseDatabase4 = database.getReference("actividadvolcanica").child("ultimosreportesdeactividades");
        mFirebaseDatabase4.keepSynced(true);
        alertacenizas = (ListView) findViewById(R.id.listado_cenizas);
        adapter3 = new Ultimasnotificacionesadapter(Ultimasnotificaciones.this,retreive());
        alertacenizas.setAdapter(adapter3);
    }







    public ArrayList<ultimasnotificacionesobjeto> retreive() {
        FirebaseDatabase.getInstance();
        mFirebaseDatabase4.keepSynced(true);

        mFirebaseDatabase4.orderByKey().addChildEventListener(new ChildEventListener() {

            //        mFirebaseDatabase4.orderByChild("orden").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ultimasnotificacionesobjeto objetoalertaceniza = dataSnapshot.getValue(ultimasnotificacionesobjeto.class);

                // reporte objetoalertaceniza = dataSnapshot.getValue(reporte.class);
                objetoalertacenizas.add(objetoalertaceniza);
                //for (int i = 19, j = objetoalertacenizas.size() - 1; i == j; i++) {
                //    objetoalertacenizas.add(i, objetoalertacenizas.remove(j));
                //    Collections.reverse(objetoalertacenizas);
                // }



                int iSwapCount = objetoalertacenizas.size() - 1;
                int iPosition = objetoalertacenizas.size()- 1;
                for (int j = 0; j < iSwapCount; j++)
                {
                    Collections.swap(objetoalertacenizas, iPosition, iPosition - 1);
                    iPosition = iPosition - 1;
                }

                alertacenizas.setAdapter(adapter3);


            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Log.d("Datos","cambiaron pi");
                ultimasnotificacionesobjeto objetoalertaceniza = dataSnapshot.getValue(ultimasnotificacionesobjeto.class);

                // reporte objetoalertaceniza = dataSnapshot.getValue(reporte.class);
                objetoalertacenizas.add(objetoalertaceniza);
                //for (int i = 9, j = objetoalertacenizas.size() - 1; i == j; i++) {
                //   objetoalertacenizas.add(i, objetoalertacenizas.remove(j));
                //  Collections.reverse(objetoalertacenizas);
                // }



                int iSwapCount = objetoalertacenizas.size() - 1;
                int iPosition = objetoalertacenizas.size()- 1;
                for (int j = 0; j < iSwapCount; j++)
                {
                    Collections.swap(objetoalertacenizas, iPosition, iPosition - 1);
                    iPosition = iPosition - 1;
                }




                Intent intent2 = new Intent(Ultimasnotificaciones.this, Servicenotdhn.class);
                intent2.putExtra("text", "estos son mis datos");
                startService(intent2);






/*
                val sonido_notificacion_android = Uri.parse("android.resource://" + packageName + "/" + R.raw.alarmasonido)

                val channelId = getString(R.string.canal_sonido_alarmas_id)
                val channelName = getString(R.string.canal_sonido_alarmas_id_nombre)

                val notificationBuilder = NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setColor(Color.parseColor("#001665"))
                        .setContentTitle("Alerta de Lahar")
                        .setContentText(" $nobrevolcan_r2  $asubstring | $hora ")
                        .setAutoCancel(true)
                        .setSound(sonido_notificacion_android)
                        .setContentIntent(pendingIntent)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
                    notificationManager.createNotificationChannel(channel)
                }

                val random = Random()
                notificationManager.notify(random.nextInt(100), notificationBuilder.build())
*/



                alertacenizas.setAdapter(adapter3);


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                Intent intent2 = new Intent(Ultimasnotificaciones.this, Servicenotdhn.class);
                intent2.putExtra("text", "estos son mis datos");
                startService(intent2);




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mFirebaseDatabase4.keepSynced(true);
        return objetoalertacenizas;
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }


}
