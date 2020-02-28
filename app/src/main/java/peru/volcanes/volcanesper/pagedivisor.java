package peru.volcanes.volcanesper;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.net.Network;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class pagedivisor extends AppCompatActivity {
    RelativeLayout titulopantalla_detalles;
    SupportMapFragment sMapFragment;
    Button mk;
    private FirebaseDatabase database;
    private static final int REQUEST_PERMISSION = 1;
    private static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION};
    FrameLayout contenedor;
    RelativeLayout tituloscolor;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerBlock;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    RelativeLayout blocke9;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ImageView sliderz;
    TextView opcion1;
    TextView opcion2;
    RelativeLayout celda1;
    RelativeLayout celda2;
    TextView iconomapas;
    TextView iconolistados;
    RelativeLayout cell1;
    RelativeLayout cell2;
    Context mContext;

    RelativeLayout cell1border;
    RelativeLayout cell2border;
    RelativeLayout blocke12;
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference mFirebaseDatabasex;
    private DatabaseReference mFirebaseDatabase4;
    private FirebaseDatabase mFirebaseInstance;
    RelativeLayout blocke6a;

    RelativeLayout blocke92;
    ImageView imagen;

    String reciente_text_sabancaya, estado_text_sabancaya, nombre_text_sabancaya, ubicacion_sabancaya, urlimagenes_sabancaya,  altura_text_sabancaya, codigo_text_sabancaya, diametro_text_sabancaya, glaciares_text_sabancaya, imagen_text_sabancaya,
            latitud_text_sabancaya,    longitud_text_sabancaya,  monitoreo_text_sabancaya, tipo_text_sabancaya, tipica_erupcion_text_sabancaya, fecha_actualizacion_text_sabancaya, hora_actualizacion_text_sabancaya,alerta_text_sabancaya,
            camaraurls_sabancaya,sismogramaurls_sabancaya, resena_text_sabancaya,ultimaerupcion_sabancaya,mapasismico_sabancaya, proyeccionsenamhiurl_sabancaya;

    String reciente_text_ubinas, estado_text_ubinas, nombre_text_ubinas, ubicacion_ubinas, urlimagenes_ubinas,  altura_text_ubinas, codigo_text_ubinas, diametro_text_ubinas, glaciares_text_ubinas, imagen_text_ubinas,
            latitud_text_ubinas,    longitud_text_ubinas,  monitoreo_text_ubinas, tipo_text_ubinas, tipica_erupcion_text_ubinas, fecha_actualizacion_text_ubinas, hora_actualizacion_text_ubinas,alerta_text_ubinas,
            camaraurls_ubinas,sismogramaurls_ubinas, resena_text_ubinas,ultimaerupcion_ubinas,mapasismico_ubinas, proyeccionsenamhiurl_ubinas;

    private AnimatedVectorDrawable tickToCross, crossToTick;
    private boolean isTick = true;
    private static Context context;
    FragmentPagerAdapter adapterViewPager;


    Button verdatos;
    private String k;

    Button comprobarconexion;

    Button comprobarinsercion;
    Button vertoken;
    Button vermemoria;

    String valortoken;

    String valormemoria;

    private static final String TAG = pagedivisor.class.getSimpleName();

    private static final int READ_BLOCK_SIZE = 100;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
         setContentView(R.layout.pagedivisor);

        comprobarconexion = findViewById(R.id.comprobarconexion);
        comprobarinsercion = findViewById(R.id.lastrecord);

         vertoken = findViewById(R.id.vertoken);

         vermemoria = findViewById(R.id.vermemoria);

         /*
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
                 .setInitialDelay(5, TimeUnit.SECONDS)
                 .setConstraints(constraints)
                 .build();
         WorkManager.getInstance().enqueue(uploadWorkRequest);
         */



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



       // crear_canal_sonido_alarma();
       // crear_canal_sonido_notificacion();
       // crear_canal_sin_sonido_con_vibracion();

        //eliminar canales antiguos
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String id = "some_channel_id_lahar";
        String id2 = "default_channel_id";
        String id3 = "fcm_default_channel";
        // String id4 = "canal_sonido_alarmas_id";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          //  notificationManager.deleteNotificationChannel(id);
         //   notificationManager.deleteNotificationChannel(id2);
         //   notificationManager.deleteNotificationChannel(id3);
           // notificationManager.deleteNotificationChannel(id4);
        }

        verdatos = findViewById(R.id.verdatos);
        ImageView   imagen3 = findViewById(R.id.vectori);
        Animatable animatable = (Animatable) imagen3.getDrawable();
        if (animatable.isRunning())
            animatable.stop();
        else
            animatable.start();

        reciente_text_ubinas = "Explosiones con expulsión de ceniza, sismos por fractura de rocas al interior del volcán y por ascenso de magma.";
        estado_text_ubinas = "3";
        nombre_text_ubinas = "Ubinas";
        ubicacion_ubinas = "Moquegua";
        urlimagenes_ubinas = "http://arteypixel.com/fotosvolcanes/volcan-ubinas.jpg";
        altura_text_ubinas = "5672 m s.n.m";
        codigo_text_ubinas = "1493157379002";
        diametro_text_ubinas = "3 km";
        glaciares_text_ubinas = "No";
        imagen_text_ubinas = "http://arteypixel.com/fotosvolcanes/volcan-ubinas.jpg";
        latitud_text_ubinas = "-16.3433";
        longitud_text_ubinas = "-70.8992";
        monitoreo_text_ubinas = "Sísmico, visual, geodésico";
        tipo_text_ubinas = "Estratovolcán";
        tipica_erupcion_text_ubinas =  "explosiva";
        fecha_actualizacion_text_ubinas =  "Jueves, Abril 15, 2017";
        hora_actualizacion_text_ubinas = "6:30 pm";
        alerta_text_ubinas = "7";
        camaraurls_ubinas = "http://intranet.igp.gob.pe/fotos-tiempo-real/camara_ubinas2.php";
        sismogramaurls_ubinas = "http://intranet.igp.gob.pe/fotos-tiempo-real/helicorde_ubinas.php";
        resena_text_ubinas = "El volcán Ubinas (-16.3433 / -70.8992; 5672 m s.n.m.) se ubica en la región Moquegua, provincia de General Sánchez Cerro, a 6 km al noreste del distrito de Ubinas y a 70 km al este de la ciudad de Arequipa. En la zona valle abajo del volcán habitan alrededor de 5000 habitantes distribuidos entre los pueblos de Querapi, Ubinas, Sacohaya, Anascapa, Tonohaya, San Miguel, Huatagua, Huarina, Matalaque, Yalagua, etc. Dichos poblados están ubicados dentro del radio de influencia del volcán Ubinas, cuya actividad socioeconómica principal es la ganadería y agricultura. La actividad volcánica del Ubinas ocurrió desde el Pleistoceno superior prolongándose hasta el Holoceno. Desde 1550, ha presentado 26 eventos volcánicos (Thouret et al. 2005; Rivera, 2010) con una recurrencia de 2 a 6 erupciones por siglo. Los episodios eruptivos fueron de tipo vulcanianos y plinianos; dichos registros eruptivos datan desde los 980±60 años (Rivera M., Thouret J.-C., Samaniego P., Le Pennec J. 2013). A lo largo de su historia eruptiva, el volcán Ubinas ha presentado flujos piroclásticos, colapsos de caldera y flujos de lava datados en los últimos 370 ka. Así también, se han encontrado indicios de erupciones plinianas de hace 14 Ka y 7.5 Ka AC (Rivera, 2000). Se tiene registro de avalanchas de escombros de 1.6 km3 como consecuencia de un segundo colapso del flanco sur del volcán, producido por una erupción explosiva de poco volumen ocurrida alrededor de 3800 años a. C. (Thouret, 2004). Los últimos procesos eruptivos de este siglo ocurrieron entre 2006 y 2009 y entre 2013 y 2016, con Índices de Explosividad Volcánica de entre 2 y 3 que generaron columnas eruptivas de hasta 6 km de altura sobre el cráter y grandes volúmenes de ceniza emitida.";
        ultimaerupcion_ubinas= "Del 2013 al 2017";
        mapasismico_ubinas= "https://youtu.be/gOLsfv38hKc";
        proyeccionsenamhiurl_ubinas = "https://www.senamhi.gob.pe/site/volcan/?p=Ubinas";





        reciente_text_sabancaya = "Explosiones con expulsión de ceniza, sismos por fractura de rocas al interior del volcán y por ascenso de magma.";
        estado_text_sabancaya = "3";
        nombre_text_sabancaya = "Sabancaya";
        ubicacion_sabancaya = "Arequipa";
        urlimagenes_sabancaya = "http://arteypixel.com/fotosvolcanes/volcan-sabancaya.jpg";
        altura_text_sabancaya = "5960 m s.n.m";
        codigo_text_sabancaya = "1493157381161";
        diametro_text_sabancaya = "3 km";
        glaciares_text_sabancaya = "No";
        imagen_text_sabancaya = "http://arteypixel.com/fotosvolcanes/volcan-sabancaya.jpg";
        latitud_text_sabancaya = "-15.7867";
        longitud_text_sabancaya = "-71.8560";
        monitoreo_text_sabancaya = "Sísmico, visual, geodésico";
        tipo_text_sabancaya = "Estratovolcán";
        tipica_erupcion_text_sabancaya =  "explosiva";
        fecha_actualizacion_text_sabancaya =  "Jueves, Abril 15, 2017";
        hora_actualizacion_text_sabancaya = "6:30 pm";
        alerta_text_sabancaya = "5";
        camaraurls_sabancaya = "http://intranet.igp.gob.pe/fotos-tiempo-real/camara_sabancaya2.php";
        sismogramaurls_sabancaya = "http://intranet.igp.gob.pe/fotos-tiempo-real/helicorde_sabancaya.php";
        resena_text_sabancaya = " E l volcán Sabancaya (-15.7867 / -71.8560; 5960 m s.n.m.) se encuentra a 19 km del pueblo de Maca (valle del río Colca) y a 76 km al noroeste de la ciudad de Arequipa. Es un estratovolcán activo de edad Holocénica (Thouret et al. 1994; Gerbe & Thouret 2004) perteneciente al complejo volcánico Ampato–Sabancaya. Está constituido por una sucesión de flujos y coladas de lavas en bloques de composición andesítica que cubren una superficie aproximada de 70 km2 (Huamán et al., 1993). En la cumbre del edificio, con hielo y nieve la mayor parte del tiempo, se encuentra un cráter activo de aproximadamente 350 m de diámetro. La historia eruptiva del Sabancaya muestra tanto periodos efusivos como violentos periodos explosivos, pero en los tiempos más recientes su comportamiento ha sido sobre todo efusivo, tal como se puede apreciar en las amplias coladas de lava en bloques que se distribuyen por varios kilómetros alrededor del cráter. La actividad volcánica data desde el Pleistoceno tardío al Holoceno, presentando varias etapas de erupciones explosivas de tipo vulcaniano durante el Holoceno (Gerbe and Thouret, 2004). Erupciones de este volcán han sido datadas desde los 6600 años a. C. (Global Volcanism Program, 2013; Jay et al., 2015), con Índices de Explosividad Volcánica (IEV) de entre 1-3. En los últimos 4-5 Ka (Samaniego et al., 2016), se han registrado al menos 6 erupciones. Recientemente, con ocasión de revisión de crónicas históricas, se conoció que el volcán tuvo actividad explosiva en 1752 y 1784.        .                         .                                      .                .          Entre 1990 y 1998 (Siebert et al., 2010), se registró una nueva erupción de este volcán, la cual produjo explosiones de tipo vulcaniana y freatomagmáticas que generaron columnas de ceniza de entre 5 km y 7 km de altura, alcanzando un IEV igual a 2 y 3 (Siebert 2010, Gerbe & Thouret 2004). Recientemente, en febrero de 2013, el Sabancaya inició un periodo de intranquilidad volcánica, registrándose desde entonces alta sismicidad y emisiones de vapor de agua y gases de manera intermitente. En agosto de 2016, se registró la primera explosión de este nuevo proceso eruptivo. Dentro del área de influencia del complejo Ampato-Sabancaya existen más de 20 centros poblados. Por el adyacente valle del Colca, considerado uno de los principales lugares turísticos del país, pasa el principal canal de irrigación del sur del Perú (canal Majes). Así también, una importante línea de transmisión eléctrica, la línea Socabaya-Mantaro del sistema eléctrico interconectado del sur del Perú, atraviesa sus inmediaciones. Las principales actividades económicas de la zona son la agricultura, la ganadería y el turismo, esta última, desarrollada en torno al cañón del Colca, viene experimentando un crecimiento acelerado.";
        ultimaerupcion_sabancaya= "En curso";
        mapasismico_sabancaya= "https://youtu.be/fnIhwZzGDO8";
        proyeccionsenamhiurl_sabancaya = "https://www.senamhi.gob.pe/site/volcan/?p=Ubinas";




        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verpopup();
            }
        });

        pagedivisor.context = getApplicationContext();

        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        iconomapas = (TextView) findViewById(R.id.iconomapa);
        iconolistados = (TextView) findViewById(R.id.iconolistado);
        iconomapas.setTypeface(fontAwesomeFont);
        iconolistados.setTypeface(fontAwesomeFont);
        opcion1 = (TextView) findViewById(R.id.opcion1);
        opcion2 = (TextView) findViewById(R.id.opcion2);
        celda1 = (RelativeLayout) findViewById(R.id.cell1);
        celda2 = (RelativeLayout) findViewById(R.id.cell2);

        final ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        //   vpPager.setPageTransformer(true, new RotateUpTransformer());
        cell1border = (RelativeLayout) findViewById(R.id.cell1border);
        cell2border = (RelativeLayout) findViewById(R.id.cell2border);

        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageSelected(int pageNumber) {
                if(pageNumber == 0){
                    cell1border.setBackgroundColor(getResources().getColor(R.color.celesteigp));
                    cell2border.setBackgroundColor(getResources().getColor(R.color.igp));
                }
                else if(pageNumber == 1) {
                    cell2border.setBackgroundColor(getResources().getColor(R.color.celesteigp));
                    cell1border.setBackgroundColor(getResources().getColor(R.color.igp));
                }
                else{
                    cell2border.setBackgroundColor(getResources().getColor(R.color.celesteigp));
                    cell1border.setBackgroundColor(getResources().getColor(R.color.igp));
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });



        if(vpPager.getCurrentItem() == 0) {
            cell1border.setBackgroundColor(getResources().getColor(R.color.celesteigp));
            cell2border.setBackgroundColor(getResources().getColor(R.color.igp));
        }
        else if(vpPager.getCurrentItem() == 1) {
            cell2border.setBackgroundColor(getResources().getColor(R.color.celesteigp));
            cell1border.setBackgroundColor(getResources().getColor(R.color.igp));
        }
        else{
            cell2border.setBackgroundColor(getResources().getColor(R.color.celesteigp));
            cell1border.setBackgroundColor(getResources().getColor(R.color.igp));
        }

        celda2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                vpPager.setCurrentItem(1, true);
                cell1border.setBackgroundColor(getResources().getColor(R.color.igp));
                cell2border.setBackgroundColor(getResources().getColor(R.color.celesteigp));
                imagen3.setVisibility(View.GONE);

            }
        });

        celda1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vpPager.setCurrentItem(0, true);
                cell2border.setBackgroundColor(getResources().getColor(R.color.igp));
                cell1border.setBackgroundColor(getResources().getColor(R.color.celesteigp));

                imagen3.setVisibility(View.VISIBLE);


                //  FragmentWithTwoImages.newInstance("", R.drawable.volcan_amarillo_sm, R.drawable.volcan_amarillo_sm);
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
                Intent intent = new Intent(pagedivisor.this,Contactar.class);
                startActivity(intent);
            }
        });

         comprobarconexion.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Conexionsql conn = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
                 SQLiteDatabase db = conn.getWritableDatabase();
                 ContentValues values = new ContentValues();
                 //values.put(Utilidades.CAMPO_ID,  );
                 values.put(Utilidades.CAMPO_MENSAJE, "mensaje1&&mensaje1&&mensaje3&&mensaje4&&mensaje5&&mensaje6");
                 Long idresultante = db.insert(Utilidades.TABLA_NOTIFICACIONES, Utilidades.CAMPO_ID, values);
                 Toast.makeText(getApplicationContext(), "Resultante:" + idresultante + "", Toast.LENGTH_LONG);
                 Log.d("el resultante: ","Resultante:" + idresultante + ""   );
             }
         });

         comprobarinsercion.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Conexionsql conn = new Conexionsql(getApplicationContext(),"bd_notificaciones", null,1 );
                 SQLiteDatabase db = conn.getReadableDatabase();
                 ArrayList<String> array_list = new ArrayList<String>();

                 Cursor res = db.rawQuery( "select * from "+Utilidades.TABLA_NOTIFICACIONES + " ORDER BY id DESC LIMIT 1", null );
                 res.moveToFirst();

                 String aqui =  res.getString(1);

                 while(res.isAfterLast() == false) {
                     array_list.add(res.getString(res.getColumnIndex("mensaje")));
                     res.moveToNext();
                 }

                 Toast.makeText(getApplicationContext(), "Resultante DATOS:" + array_list.toString() + "", Toast.LENGTH_LONG);
                 Log.d("DATOS: ", array_list.toString());
                 Log.d("DATOS: ", aqui);


                 //Random random = new Random();
                 //Integer d = random.nextInt(20);
                 //String ds = String.valueOf(d);
                 //Log.d("VALORF RANDOM",ds);


             }
         });

         vertoken.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 String file_ramdom = getApplicationContext().getFilesDir() + "/"+"eltoken";
                 try {
                     FileInputStream fileramdom = new FileInputStream(new File(file_ramdom));
                     InputStreamReader InputRead_ramdom = new InputStreamReader(fileramdom);
                     char[] inputBuffer = new char[READ_BLOCK_SIZE];
                     int charRead;
                     charRead = InputRead_ramdom.read(inputBuffer);
                     valortoken = String.copyValueOf(inputBuffer, 0, charRead);
                     InputRead_ramdom.close();
                     Log.d(TAG, "VALOR TOKEN: " + valortoken);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         });


         vermemoria.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 String file_memoria = getApplicationContext().getFilesDir() + "/"+"ultima_notificacion";
                 try {
                     FileInputStream filememoria = new FileInputStream(new File(file_memoria));
                     InputStreamReader InputRead_memoria = new InputStreamReader(filememoria);
                     char[] inputBuffer = new char[READ_BLOCK_SIZE];
                     int charRead;
                     charRead = InputRead_memoria.read(inputBuffer);
                     valormemoria = String.copyValueOf(inputBuffer, 0, charRead);
                     InputRead_memoria.close();
                     Log.d(TAG, "VALOR MEMORIA: " + valormemoria);
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         });










        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(pagedivisor.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });



        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(pagedivisor.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(pagedivisor.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(pagedivisor.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(pagedivisor.this,Convenciones.class);
                startActivity(intent);
            }
        });


        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(pagedivisor.this,Listadoredessociales.class);
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






        verdatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
         Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();

                //ShortcutBadger.applyCount(context, 0);
                String file2 = "vibrar_file";
                try {
                    FileInputStream fileIn = openFileInput(file2);
                    InputStreamReader InputRead = new InputStreamReader(fileIn);
                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                    int charRead;
                    charRead = InputRead.read(inputBuffer);
                    String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                    k = readstring;
                    InputRead.close();
                    StringTokenizer st = new StringTokenizer(k, ",");
                    String ajustes = st.nextToken();
                    String tipo = st.nextToken();

                    Log.d(TAG, "valor: json: " + k);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(getApplicationContext(), "Configurado" + k, Toast.LENGTH_LONG).show();

/*
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
*/

            }
        });










    }

    public   class MyPagerAdapter extends FragmentPagerAdapter {
        private   int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {

            return NUM_ITEMS;
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 1:
                    //  return FragmentWithOneImage.newInstance("", R.drawable.volcan_amarillo_sm);
                    return FragmentWithTwoImages.newInstance("",  R.drawable.volcan_amarillo_sm,  R.drawable.volcan_amarillo_sm);

                case 0:
                    return FragmentWithOneImage.newInstance("",  R.drawable.volcan_amarillo_sm);

                //  return FragmentWithTwoImages.newInstance("", R.drawable.volcan_amarillo_sm, R.drawable.volcan_amarillo_sm);

                default:return null;
            }
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + position;
        }
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }

    public void cargar(View v){
        Animatable animatable = (Animatable)  ((ImageView) v).getDrawable();
        if (animatable.isRunning())
            animatable.stop();
        else
            animatable.start();
    }


    public void verpopup() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertanaranja, null);

        dialogBuilder.setView(dialogView);
        Button cerrar = dialogView.findViewById(R.id.close);

        Button icono1 = dialogView.findViewById(R.id.icono1);
        Button icono2 = dialogView.findViewById(R.id.icono2);
        Button icono3 = dialogView.findViewById(R.id.icono3);
        Button icono4 = dialogView.findViewById(R.id.icono4);
        Button icono5 = dialogView.findViewById(R.id.icono5);
        Button icono6 = dialogView.findViewById(R.id.icono6);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        cerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                alertDialog.hide();
            }
        });

        icono1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                camara_sabancaya(reciente_text_sabancaya,
                        estado_text_sabancaya,
                        nombre_text_sabancaya,
                        ubicacion_sabancaya,
                        urlimagenes_sabancaya,
                        altura_text_sabancaya,
                        codigo_text_sabancaya,
                        diametro_text_sabancaya,
                        glaciares_text_sabancaya,
                        imagen_text_sabancaya,
                        latitud_text_sabancaya,
                        longitud_text_sabancaya,
                        monitoreo_text_sabancaya,
                        tipo_text_sabancaya,
                        tipica_erupcion_text_sabancaya,
                        fecha_actualizacion_text_sabancaya,
                        hora_actualizacion_text_sabancaya,
                        alerta_text_sabancaya,
                        camaraurls_sabancaya,
                        sismogramaurls_sabancaya,
                        mapasismico_sabancaya,
                        proyeccionsenamhiurl_sabancaya);
            }
        });


        icono2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                sismograma_sabancaya(reciente_text_sabancaya,
                        estado_text_sabancaya,
                        nombre_text_sabancaya,
                        ubicacion_sabancaya,
                        urlimagenes_sabancaya,
                        altura_text_sabancaya,
                        codigo_text_sabancaya,
                        diametro_text_sabancaya,
                        glaciares_text_sabancaya,
                        imagen_text_sabancaya,
                        latitud_text_sabancaya,
                        longitud_text_sabancaya,
                        monitoreo_text_sabancaya,
                        tipo_text_sabancaya,
                        tipica_erupcion_text_sabancaya,
                        fecha_actualizacion_text_sabancaya,
                        hora_actualizacion_text_sabancaya,
                        alerta_text_sabancaya,
                        camaraurls_sabancaya,
                        sismogramaurls_sabancaya,
                        mapasismico_sabancaya,
                        proyeccionsenamhiurl_sabancaya);
            }
        });

        icono3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                reporte_sabancaya(reciente_text_sabancaya,
                        estado_text_sabancaya,
                        nombre_text_sabancaya,
                        ubicacion_sabancaya,
                        urlimagenes_sabancaya,
                        altura_text_sabancaya,
                        codigo_text_sabancaya,
                        diametro_text_sabancaya,
                        glaciares_text_sabancaya,
                        imagen_text_sabancaya,
                        latitud_text_sabancaya,
                        longitud_text_sabancaya,
                        monitoreo_text_sabancaya,
                        tipo_text_sabancaya,
                        tipica_erupcion_text_sabancaya,
                        fecha_actualizacion_text_sabancaya,
                        hora_actualizacion_text_sabancaya,
                        alerta_text_sabancaya,
                        camaraurls_sabancaya,
                        sismogramaurls_sabancaya,
                        mapasismico_sabancaya,
                        proyeccionsenamhiurl_sabancaya);
            }
        });


        icono4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                camara_ubinas(reciente_text_ubinas,
                        estado_text_ubinas,
                        nombre_text_ubinas,
                        ubicacion_ubinas,
                        urlimagenes_ubinas,
                        altura_text_ubinas,
                        codigo_text_ubinas,
                        diametro_text_ubinas,
                        glaciares_text_ubinas,
                        imagen_text_ubinas,
                        latitud_text_ubinas,
                        longitud_text_ubinas,
                        monitoreo_text_ubinas,
                        tipo_text_ubinas,
                        tipica_erupcion_text_ubinas,
                        fecha_actualizacion_text_ubinas,
                        hora_actualizacion_text_ubinas,
                        alerta_text_ubinas,
                        camaraurls_ubinas,
                        sismogramaurls_ubinas,
                        mapasismico_ubinas,
                        proyeccionsenamhiurl_ubinas);
            }
        });

        icono5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                sismograma_ubinas(reciente_text_ubinas,
                        estado_text_ubinas,
                        nombre_text_ubinas,
                        ubicacion_ubinas,
                        urlimagenes_ubinas,
                        altura_text_ubinas,
                        codigo_text_ubinas,
                        diametro_text_ubinas,
                        glaciares_text_ubinas,
                        imagen_text_ubinas,
                        latitud_text_ubinas,
                        longitud_text_ubinas,
                        monitoreo_text_ubinas,
                        tipo_text_ubinas,
                        tipica_erupcion_text_ubinas,
                        fecha_actualizacion_text_ubinas,
                        hora_actualizacion_text_ubinas,
                        alerta_text_ubinas,
                        camaraurls_ubinas,
                        sismogramaurls_ubinas,
                        mapasismico_ubinas,
                        proyeccionsenamhiurl_ubinas);
            }
        });

        icono6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                reporte_ubinas(reciente_text_ubinas,
                        estado_text_ubinas,
                        nombre_text_ubinas,
                        ubicacion_ubinas,
                        urlimagenes_ubinas,
                        altura_text_ubinas,
                        codigo_text_ubinas,
                        diametro_text_ubinas,
                        glaciares_text_ubinas,
                        imagen_text_ubinas,
                        latitud_text_ubinas,
                        longitud_text_ubinas,
                        monitoreo_text_ubinas,
                        tipo_text_ubinas,
                        tipica_erupcion_text_ubinas,
                        fecha_actualizacion_text_ubinas,
                        hora_actualizacion_text_ubinas,
                        alerta_text_ubinas,
                        camaraurls_ubinas,
                        sismogramaurls_ubinas,
                        mapasismico_ubinas,
                        proyeccionsenamhiurl_ubinas);
            }
        });

    }

    private void camara_ubinas(String...details) {
        Intent i=new Intent(pagedivisor.this,Volcancamara.class);
        i.putExtra("ACTIVIDAD_RECIENTE",details[0]);
        i.putExtra("ESTADO",details[1]);
        i.putExtra("NOMBRES",details[2]);
        i.putExtra("UBICACION",details[3]);
        i.putExtra("URLIMAGEN",details[4]);
        i.putExtra("ALTURA",details[5]);
        i.putExtra("CODIGO",details[6]);
        i.putExtra("DIAMETRO",details[7]);
        i.putExtra("GLACIARES",details[8]);
        i.putExtra("IMAGEN",details[9]);
        i.putExtra("LATITUD",details[10]);
        i.putExtra("LONGITUD",details[11]);
        i.putExtra("MONITOREO",details[12]);
        i.putExtra("TIPO",details[13]);
        i.putExtra("TIPICA_ERUPCION",details[14]);
        i.putExtra("FECHAACTUALIZACION",details[15]);
        i.putExtra("HORAACTUALIZACION",details[16]);
        i.putExtra("ALERTA",details[17]);
        i.putExtra("CAMARAURL",details[18]);
        i.putExtra("SISMOGRAMA",details[19]);
        i.putExtra("MAPASISMICO",details[20]);
        i.putExtra("PROYECCIONSENAHMI",details[21]);
        pagedivisor.this.startActivity(i);
    }

    private void sismograma_ubinas(String...details) {
        Intent i=new Intent(pagedivisor.this,Sismogramas.class);
        i.putExtra("ACTIVIDAD_RECIENTE",details[0]);
        i.putExtra("ESTADO",details[1]);
        i.putExtra("NOMBRES",details[2]);
        i.putExtra("UBICACION",details[3]);
        i.putExtra("URLIMAGEN",details[4]);
        i.putExtra("ALTURA",details[5]);
        i.putExtra("CODIGO",details[6]);
        i.putExtra("DIAMETRO",details[7]);
        i.putExtra("GLACIARES",details[8]);
        i.putExtra("IMAGEN",details[9]);
        i.putExtra("LATITUD",details[10]);
        i.putExtra("LONGITUD",details[11]);
        i.putExtra("MONITOREO",details[12]);
        i.putExtra("TIPO",details[13]);
        i.putExtra("TIPICA_ERUPCION",details[14]);
        i.putExtra("FECHAACTUALIZACION",details[15]);
        i.putExtra("HORAACTUALIZACION",details[16]);
        i.putExtra("ALERTA",details[17]);
        i.putExtra("CAMARAURL",details[18]);
        i.putExtra("SISMOGRAMA",details[19]);
        i.putExtra("MAPASISMICO",details[20]);
        i.putExtra("PROYECCIONSENAHMI",details[21]);
        pagedivisor.this.startActivity(i);
    }

    private void reporte_ubinas(String...details) {
        Intent i=new Intent(pagedivisor.this,Reportesdeactividad.class);
        i.putExtra("ACTIVIDAD_RECIENTE",details[0]);
        i.putExtra("ESTADO",details[1]);
        i.putExtra("NOMBRES",details[2]);
        i.putExtra("UBICACION",details[3]);
        i.putExtra("URLIMAGEN",details[4]);
        i.putExtra("ALTURA",details[5]);
        i.putExtra("CODIGO",details[6]);
        i.putExtra("DIAMETRO",details[7]);
        i.putExtra("GLACIARES",details[8]);
        i.putExtra("IMAGEN",details[9]);
        i.putExtra("LATITUD",details[10]);
        i.putExtra("LONGITUD",details[11]);
        i.putExtra("MONITOREO",details[12]);
        i.putExtra("TIPO",details[13]);
        i.putExtra("TIPICA_ERUPCION",details[14]);
        i.putExtra("FECHAACTUALIZACION",details[15]);
        i.putExtra("HORAACTUALIZACION",details[16]);
        i.putExtra("ALERTA",details[17]);
        i.putExtra("CAMARAURL",details[18]);
        i.putExtra("SISMOGRAMA",details[19]);
        i.putExtra("MAPASISMICO",details[20]);
        i.putExtra("PROYECCIONSENAHMI",details[21]);
        pagedivisor.this.startActivity(i);
    }

    private void camara_sabancaya(String...details) {
        Intent i=new Intent(pagedivisor.this,Volcancamara.class);
        i.putExtra("ACTIVIDAD_RECIENTE",details[0]);
        i.putExtra("ESTADO",details[1]);
        i.putExtra("NOMBRES",details[2]);
        i.putExtra("UBICACION",details[3]);
        i.putExtra("URLIMAGEN",details[4]);
        i.putExtra("ALTURA",details[5]);
        i.putExtra("CODIGO",details[6]);
        i.putExtra("DIAMETRO",details[7]);
        i.putExtra("GLACIARES",details[8]);
        i.putExtra("IMAGEN",details[9]);
        i.putExtra("LATITUD",details[10]);
        i.putExtra("LONGITUD",details[11]);
        i.putExtra("MONITOREO",details[12]);
        i.putExtra("TIPO",details[13]);
        i.putExtra("TIPICA_ERUPCION",details[14]);
        i.putExtra("FECHAACTUALIZACION",details[15]);
        i.putExtra("HORAACTUALIZACION",details[16]);
        i.putExtra("ALERTA",details[17]);
        i.putExtra("CAMARAURL",details[18]);
        i.putExtra("SISMOGRAMA",details[19]);
        i.putExtra("MAPASISMICO",details[20]);
        i.putExtra("PROYECCIONSENAHMI",details[21]);
        pagedivisor.this.startActivity(i);
    }

    private void sismograma_sabancaya(String...details) {
        Intent i=new Intent(pagedivisor.this,Sismogramas.class);
        i.putExtra("ACTIVIDAD_RECIENTE",details[0]);
        i.putExtra("ESTADO",details[1]);
        i.putExtra("NOMBRES",details[2]);
        i.putExtra("UBICACION",details[3]);
        i.putExtra("URLIMAGEN",details[4]);
        i.putExtra("ALTURA",details[5]);
        i.putExtra("CODIGO",details[6]);
        i.putExtra("DIAMETRO",details[7]);
        i.putExtra("GLACIARES",details[8]);
        i.putExtra("IMAGEN",details[9]);
        i.putExtra("LATITUD",details[10]);
        i.putExtra("LONGITUD",details[11]);
        i.putExtra("MONITOREO",details[12]);
        i.putExtra("TIPO",details[13]);
        i.putExtra("TIPICA_ERUPCION",details[14]);
        i.putExtra("FECHAACTUALIZACION",details[15]);
        i.putExtra("HORAACTUALIZACION",details[16]);
        i.putExtra("ALERTA",details[17]);
        i.putExtra("CAMARAURL",details[18]);
        i.putExtra("SISMOGRAMA",details[19]);
        i.putExtra("MAPASISMICO",details[20]);
        i.putExtra("PROYECCIONSENAHMI",details[21]);
        pagedivisor.this.startActivity(i);
    }

    private void reporte_sabancaya(String...details) {
        Intent i=new Intent(pagedivisor.this,Reportesdeactividad.class);
        i.putExtra("ACTIVIDAD_RECIENTE",details[0]);
        i.putExtra("ESTADO",details[1]);
        i.putExtra("NOMBRES",details[2]);
        i.putExtra("UBICACION",details[3]);
        i.putExtra("URLIMAGEN",details[4]);
        i.putExtra("ALTURA",details[5]);
        i.putExtra("CODIGO",details[6]);
        i.putExtra("DIAMETRO",details[7]);
        i.putExtra("GLACIARES",details[8]);
        i.putExtra("IMAGEN",details[9]);
        i.putExtra("LATITUD",details[10]);
        i.putExtra("LONGITUD",details[11]);
        i.putExtra("MONITOREO",details[12]);
        i.putExtra("TIPO",details[13]);
        i.putExtra("TIPICA_ERUPCION",details[14]);
        i.putExtra("FECHAACTUALIZACION",details[15]);
        i.putExtra("HORAACTUALIZACION",details[16]);
        i.putExtra("ALERTA",details[17]);
        i.putExtra("CAMARAURL",details[18]);
        i.putExtra("SISMOGRAMA",details[19]);
        i.putExtra("MAPASISMICO",details[20]);
        i.putExtra("PROYECCIONSENAHMI",details[21]);
        pagedivisor.this.startActivity(i);
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



}