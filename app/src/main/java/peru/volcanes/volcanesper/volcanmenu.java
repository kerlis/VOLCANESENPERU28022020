package peru.volcanes.volcanesper;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import peru.volcanes.volcanesper.m_model.volcanes;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class volcanmenu extends AppCompatActivity {
    private static final String CHROME_PACKAGE = "com.android.chrome";
    private CustomTabsServiceConnection ctConnection;
    private CustomTabsSession customTabsSession;
    SupportMapFragment sMapFragment;
    private GoogleMap map;
    private static final int REQUEST_PERMISSION = 1;
    private FirebaseDatabase database;
    private static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION};
    RelativeLayout menuright;
    String actividad_reciente, estado, nombre, ubicacion;
    ImageView imagen;
    String codigodevolcan;
    String estadodevolcan;
    RelativeLayout bloque_01;
    TextView nombrevolcan;
    TextView alertacenizasicon;
    TextView camara;
    TextView camara2;
    TextView mapa;
    TextView trayectoria_cenizas;
    TextView alertacenizas_text;
    TextView camara_text;
    TextView mapa_text;
    TextView trayectoria_cenizas_text;
    TextView trayectoria_cenizas_text2;
    TextView trayectoria_cenizas_text3;
    TextView trayectoria_cenizas_text222;
    String urlimagen;
    RelativeLayout bloque5;
    RelativeLayout bloque_titulo;
    ImageView foto;
    TextView irdetalles;
    RelativeLayout bloquedetallesvolcan;
    RelativeLayout bloque1;
    RelativeLayout bloque6;
    RelativeLayout bloque4;
    RelativeLayout bloque7;
    RelativeLayout blocke3a;
    String altura;
    String codigo;
    String diametro;
    String estados;
    String glaciares;
    String imagenes;
    String latitudes;
    String longitudes;
    String monitores;
    String nombres;
    String tipo;
    String tipicaerupcion;
    String fechaactualizacion;
    String horaactualizacion;
    String alertavolcanica;
    String camaraurl;
    String sismogramaurl;
    String resena;
    String region;
    String ultimaerupcion;
    String mapasismico;
    String proyeccionsenamhiurl;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerBlock;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a,blocke9;
    RelativeLayout bloque62;
    GyroscopeObserver gyroscopeobserver;
    PanoramaImageView panoramaImage;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ImageView sliderz;
    private DatabaseReference mFirebaseDatabase;
    private final int SPLASH_DISPLAY_LENGTH = 4000;
    Button slideButton,b1, b2,b3,b4;
    SlidingDrawer slidingDrawer;
    TextView slideup;
    RelativeLayout options;
    RelativeLayout botoncuadrado;
    TextView actividadactual;
    TextView regiontext;
    TextView nombrevolcanboton;
    ImageView estado_volcan;
    RelativeLayout bloquenombre;
    TextView descarga;
    DownloadManager descargar;
    RelativeLayout blocke6a;
    RelativeLayout bloque42;


    RelativeLayout blocke12;
    RelativeLayout blocke92;


    String val;
    String menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8;
    LinearLayout pronostico_menu2;
    LinearLayout opciones72;
    LinearLayout pronostico_menu;
    LinearLayout sismogramas_menu;
    LinearLayout reportes_menu;
    LinearLayout alertas_menu;
    LinearLayout camara_menu ;
    LinearLayout mapasismic_menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model);
        slideup = (TextView) findViewById(R.id.openslide);
        options = (RelativeLayout) findViewById(R.id.options);

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (slidingDrawer.isOpened()) {
                    slidingDrawer.animateClose();
                } else {
                    slidingDrawer.animateOpen();
                }

            }
        });

        slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer);
        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened()
            {
                // slideButton.setBackgroundResource(R.drawable.down_arrow_icon);
            //  slidingDrawer.setBackgroundResource(R.drawable.amarillo_volcanes);
            }
        });

        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener()
        {
            @Override
            public void onDrawerClosed()
            {
                //  slideButton.setBackgroundResource(R.drawable.upwar_arrow_icon);
                slidingDrawer.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        descarga = (TextView) findViewById(R.id.descarga);
        gyroscopeobserver = new GyroscopeObserver();
        gyroscopeobserver.setMaxRotateRadian(Math.PI/9);

        database = FirebaseDatabase.getInstance();

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
                 Intent intent = new Intent(volcanmenu.this,Contactar.class);
                 startActivity(intent);
             }
         });



         blocke12.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(volcanmenu.this,Ultimasnotificaciones.class);
                 startActivity(intent);
             }
         });


        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanmenu.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanmenu.this,Informacion.class);
                startActivity(intent);
            }
        });


        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanmenu.this,Notificacionesconfig.class);
               // Intent intent = new Intent(volcanmenu.this,Configuraciones.class);

                startActivity(intent);
            }
        });


        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanmenu.this,Convenciones.class);
                startActivity(intent);
            }
        });

        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanmenu.this,Listadoredessociales.class);
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

        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
     //   Typeface font = Typeface.createFromAsset(getAssets(), "weathericons-regular-webfont.ttf");

        descarga.setTypeface(fontAwesomeFont);
        slideup.setTypeface(fontAwesomeFont);

        //  alertacenizasicon = (TextView) findViewById(R.id.alertacenizasicon);
        camara = (TextView) findViewById(R.id.camara);
         camara2 = (TextView) findViewById(R.id.camara2);

         camara2.setTypeface(fontAwesomeFont);

        mapa = (TextView) findViewById(R.id.mapa);
        //   trayectoria_cenizas = (TextView) findViewById(R.id.trayectoria_cenizas);

        alertacenizas_text = (TextView) findViewById(R.id.alertacenizas_text);
        camara_text = (TextView) findViewById(R.id.camara_text);
        mapa_text = (TextView) findViewById(R.id.mapa_text);
        trayectoria_cenizas_text = (TextView) findViewById(R.id.trayectoria_cenizas_text);
        trayectoria_cenizas_text2 = (TextView) findViewById(R.id.trayectoria_cenizas_text2);
        trayectoria_cenizas_text3 = (TextView) findViewById(R.id.trayectoria_cenizas_text3);
         trayectoria_cenizas_text222 = (TextView) findViewById(R.id.trayectoria_cenizas_text222);

        bloquedetallesvolcan = (RelativeLayout) findViewById(R.id.bloquedetallesvolcan);

        blocke3a = (RelativeLayout) findViewById(R.id.bloque3);
        bloque5 = (RelativeLayout) findViewById(R.id.bloque5);
        bloque1 = (RelativeLayout) findViewById(R.id.bloque1);
        bloque6 = (RelativeLayout) findViewById(R.id.bloque6);
        bloque4 = (RelativeLayout) findViewById(R.id.bloque4);
        bloque7 = (RelativeLayout) findViewById(R.id.bloque7);

        bloque62 = (RelativeLayout) findViewById(R.id.bloque62);
        bloque42 = (RelativeLayout) findViewById(R.id.bloque42);


       // foto = (ImageView) findViewById(R.id.foto);
       // nombrevolcanboton = (TextView) findViewById(R.id.nombrevolcanboton);

        actividadactual = (TextView) findViewById(R.id.actividadactual);

      //  botoncuadrado = (RelativeLayout) findViewById(R.id.botoncuadrado);
        regiontext = (TextView) findViewById(R.id.region);
        // bloque_02 = (RelativeLayout) findViewById(R.id.bloque_02);
        irdetalles = (TextView) findViewById(R.id.irdetalles);
     //   alertacenizas_text.setText(" Alertas de Ceniza");
      //  camara_text.setText(" Cámara en tiempo real ");
      //  mapa_text.setText("Actividad");
     //   trayectoria_cenizas_text.setText(" Pronóstico de dispersión ");
     //   trayectoria_cenizas_text2.setText(" Reportes de actividad ");
     //   trayectoria_cenizas_text3.setText(" Sismogramas tiempo real ");
    //     trayectoria_cenizas_text222.setText("Pronóstico de dispersión");

        camara.setTypeface(fontAwesomeFont);
        mapa.setTypeface(fontAwesomeFont);
        irdetalles.setTypeface(fontAwesomeFont);

        bloque_titulo = (RelativeLayout) findViewById(R.id.bloque_titulo);
        Intent i = this.getIntent();
        codigodevolcan = i.getExtras().getString("ACTIVIDAD_RECIENTE");
        nombrevolcan = (TextView) findViewById(R.id.titulo);
        detecta_sismo();
        bloque_01 = (RelativeLayout) findViewById(R.id.bloque_01);
        imagen = (ImageView) findViewById(R.id.estado_volcan);
        mostrar_ult_sismo();

        menu_config(codigodevolcan);


    }

















    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeobserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeobserver.unregister();
    }

    public void detecta_sismo() {
        if (mFirebaseDatabase == null) {
            FirebaseDatabase.getInstance();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            mFirebaseDatabase = database.getReference("actividadvolcanica").child("volcanes");
        }
        else {
            FirebaseDatabase.getInstance();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            mFirebaseDatabase = database.getReference("actividadvolcanica").child("volcanes");
        }

        mFirebaseDatabase.keepSynced(true);
        mFirebaseDatabase.orderByKey().equalTo(codigodevolcan).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                volcanes sreporte = dataSnapshot.getValue(volcanes.class);
                guardar_pref(sreporte);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public void guardar_pref(volcanes sreporte) {
        SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("inactividadreciente", sreporte.getActividadreciente());
        editor.putString("inestado", sreporte.getEstado());
        editor.putString("innombre", sreporte.getNombre());
        editor.putString("inubicacion", sreporte.getMonitoreo());
        editor.putString("inimagen", sreporte.getImagen());
        editor.putString("inaltura", sreporte.getAltura());
        editor.putString("incodigo", sreporte.getCodigo());
        editor.putString("indiametro", sreporte.getDiametrocrater());
        editor.putString("inestados", sreporte.getEstado());
        editor.putString("inglaciares", sreporte.getGlaciares());
        editor.putString("inimagenes", sreporte.getImagen());
        editor.putString("inlatitudes", sreporte.getLatitud());
        editor.putString("inlongitudes", sreporte.getLongitud());
        editor.putString("inmonitores", sreporte.getMonitoreo());
        editor.putString("innombres", sreporte.getNombre());
        editor.putString("intipo", sreporte.getTipo());
        editor.putString("intipicaerupcion", sreporte.getTipicaerupcion());
        editor.putString("infechaactualizacion", sreporte.getFechaactualizacion());
        editor.putString("inhoraactualizacion", sreporte.getHoraactualizacion());
        editor.putString("inalertavolcanica", sreporte.getAlertavolcanica());
        editor.putString("insismograma", sreporte.getSismograma());
        editor.putString("incamara", sreporte.getCamaraurl());

        editor.putString("inresena", sreporte.getResena());
        editor.putString("inregion", sreporte.getRegion());
        editor.putString("inultimaerupcion", sreporte.getUltimaerupcion());
        editor.putString("mapasismico", sreporte.getMapasismico());

        editor.putString("proyeccionsenamhiurl", sreporte.getProyeccionsenamhiurl());

        editor.apply();
        mostrar_ult_sismo();
    }

    public void mostrar_ult_sismo() {
        SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        actividad_reciente = prefs.getString("inactividadreciente", "");
        estado = prefs.getString("inestado", "");
        nombre = prefs.getString("innombre", "");
        ubicacion = prefs.getString("inubicacion", "");
        urlimagen = prefs.getString("inimagen", "");
        altura = prefs.getString("inaltura", "");
        codigo = prefs.getString("incodigo", "");
        diametro = prefs.getString("indiametro", "");
        estados = prefs.getString("inestados", "");
        glaciares = prefs.getString("inglaciares", "");
        imagenes = prefs.getString("inimagenes", "");
        latitudes = prefs.getString("inlatitudes", "");
        longitudes = prefs.getString("inlongitudes", "");
        monitores = prefs.getString("inmonitores", "");
        nombres = prefs.getString("innombres", "");
        tipo = prefs.getString("intipo", "");
        tipicaerupcion = prefs.getString("intipicaerupcion", "");
        fechaactualizacion = prefs.getString("infechaactualizacion", "");
        horaactualizacion = prefs.getString("inhoraactualizacion", "");
        alertavolcanica = prefs.getString("inalertavolcanica", "");
        camaraurl = prefs.getString("incamara", "");
        sismogramaurl = prefs.getString("insismograma", "");
        resena  = prefs.getString("inresena", "");
        region  = prefs.getString("inregion", "");
        ultimaerupcion = prefs.getString("inultimaerupcion", "");
        mapasismico = prefs.getString("mapasismico", "");
        proyeccionsenamhiurl = prefs.getString("proyeccionsenamhiurl", "");



         if (nombre.equals("Andahua")){
             nombrevolcan.setText("Volcanes de "+"\n"+nombre);

         }
         else if(nombre.equals("Huambo")){
             nombrevolcan.setText("Volcanes de "+"\n"+nombre);

         }
         else{
             nombrevolcan.setText("Volcán"+"\n"+nombre);

         }

       // nombrevolcanboton.setText(nombre);
      //  actividadactual.setText(actividad_reciente);



        /*--------VISUALIZACION DE MENU----------*/
         opciones72 = (LinearLayout) findViewById(R.id.opciones72);
         pronostico_menu2 = (LinearLayout) findViewById(R.id.opciones2);
         pronostico_menu = (LinearLayout) findViewById(R.id.opciones3);
         sismogramas_menu = (LinearLayout) findViewById(R.id.opciones4);
         reportes_menu = (LinearLayout) findViewById(R.id.opciones5);
         alertas_menu = (LinearLayout) findViewById(R.id.opciones6);
         camara_menu = (LinearLayout) findViewById(R.id.opciones7);
         mapasismic_menu = (LinearLayout) findViewById(R.id.opciones8);

          val = String.valueOf(codigo);


     /*--------VISUALIZACION DE MENU----------*/


        actividadactual.setText(Html.fromHtml("<b>Actividad actual: </b>"+actividad_reciente));
        regiontext.setText(Html.fromHtml("<b>Región: </b>"+region));

        panoramaImage = (PanoramaImageView) findViewById(R.id.panorama);
        panoramaImage.setGyroscopeObserver(gyroscopeobserver);


        if (nombre.equals("Ubinas")) {
            panoramaImage.setImageResource(R.drawable.volcanubinas);
        }

        else if(nombre.equals("Sabancaya")) {
            panoramaImage.setImageResource(R.drawable.volcansabancaya);
        }

        else if(nombre.equals("Sara Sara")) {
            panoramaImage.setImageResource(R.drawable.volcansarasara);
        }

        else if(nombre.equals("Cerro Auquihuato")) {
            panoramaImage.setImageResource(R.drawable.volcanauquihuato);
        }

        else if(nombre.equals("Andahua")) {
            panoramaImage.setImageResource(R.drawable.volcanandahua);
        }

        else if(nombre.equals("Coropuna")) {
            panoramaImage.setImageResource(R.drawable.volcancoropuna);
        }

        else if(nombre.equals("Huambo")) {
            panoramaImage.setImageResource(R.drawable.volcanhuambo);
        }

        else if(nombre.equals("Chachani")) {
            panoramaImage.setImageResource(R.drawable.volcanchachani);
        }

        else if(nombre.equals("Tutupaca")) {
            panoramaImage.setImageResource(R.drawable.volcantutupaca);
        }

        else if(nombre.equals("Huaynaputina")) {
            panoramaImage.setImageResource(R.drawable.volcanhuaynaputina);
        }

        else if(nombre.equals("Ticsani")) {
            panoramaImage.setImageResource(R.drawable.volcanticsani);
        }

        else if(nombre.equals("Casiri")) {
            panoramaImage.setImageResource(R.drawable.volcancasiri);
        }

        else if(nombre.equals("Cerros Purupuruni")) {
            panoramaImage.setImageResource(R.drawable.volcanpurupuruni);
        }

        else if(nombre.equals("Quimsachata")) {
            panoramaImage.setImageResource(R.drawable.volcanquimsachata);
        }

        else if(nombre.equals("Yucamane")) {
            panoramaImage.setImageResource(R.drawable.volcanyucamane);
        }
        else if(nombre.equals("Misti")) {
            panoramaImage.setImageResource(R.drawable.volcanmisti);
        }
        else{
            panoramaImage.setImageResource(R.drawable.volcantutupaca);

        }




/*
        if(urlimagen != null && !urlimagen.isEmpty())
        {
            Picasso.with(getApplicationContext()).load(urlimagen)
                    .resize(9500, 4000)
                    .onlyScaleDown()
                    .into(foto);
        }
        */




    double y;
    try

    {
        y = new Integer(estado);
    }

    catch(
    NumberFormatException e
    )

    {
        y = 0;
    }

    if(y==0)

    {
        estadodevolcan = "0";
    }

    else

    {
        estadodevolcan = estado;
    }

    final Integer volcanestados = Integer.parseInt(estadodevolcan);

    if(volcanestados<=1) {
        imagen.setImageResource(R.drawable.volcano_verde);
        bloque_01.setBackgroundColor(getResources().getColor(R.color.verdeigp));
         bloque_titulo.setBackgroundColor(getResources().getColor(R.color.verdeigp));

       // bloquenombre.setBackgroundColor(getResources().getColor(R.color.transparenteverde));
        // tituloscolor.setBackgroundColor(getResources().getColor(R.color.verdeigp));


        irdetalles.setBackgroundResource(R.drawable.fondoicono3);


    }

    else if(volcanestados>1&&volcanestados==2) {
        imagen.setImageResource(R.drawable.volcano_amarillo);
        //  tituloscolor.setBackgroundColor(getResources().getColor(R.color.amarillo));
        bloque_01.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
        bloque_titulo.setBackgroundColor(getResources().getColor(R.color.orangeyellow));

        irdetalles.setBackgroundResource(R.drawable.fondoicono2);

      //  bloquenombre.setBackgroundColor(getResources().getColor(R.color.transparenteamarillo));

    }

    else if(volcanestados>2&&volcanestados==3) {
        imagen.setImageResource(R.drawable.volcano_naranja);
        //  tituloscolor.setBackgroundColor(getResources().getColor(R.color.naranjas));
        bloque_01.setBackgroundColor(getResources().getColor(R.color.naranjas));
      bloque_titulo.setBackgroundColor(getResources().getColor(R.color.naranjas));

        irdetalles.setBackgroundResource(R.drawable.fondoicono);

      //  bloquenombre.setBackgroundColor(getResources().getColor(R.color.transparentenaranja));

    }

    else if(volcanestados>3&&volcanestados==4) {
        imagen.setImageResource(R.drawable.volcano_rojo);
        //  tituloscolor.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        bloque_01.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        bloque_titulo.setBackgroundColor(getResources().getColor(R.color.rojoigp));

        irdetalles.setBackgroundResource(R.drawable.fondoicono4);
      //  bloquenombre.setBackgroundColor(getResources().getColor(R.color.transparenterojo));

    }
    else {
        imagen.setImageResource(R.drawable.volcano_naranja);
        // tituloscolor.setBackgroundColor(getResources().getColor(R.color.naranjas));
        bloque_01.setBackgroundColor(getResources().getColor(R.color.naranjas));
        bloque_titulo.setBackgroundColor(getResources().getColor(R.color.naranjas));

        irdetalles.setBackgroundResource(R.drawable.fondoicono);
     //   bloquenombre.setBackgroundColor(getResources().getColor(R.color.transparentenaranja));

    }


        descarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                descargar = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri =  Uri.parse(urlimagen);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = descargar.enqueue(request);
                Toast.makeText(volcanmenu.this,"Descargando imagen del  volcán  " + nombre, Toast.LENGTH_LONG).show();

            }
        });






        ctConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient
                    customTabsClient) {
                customTabsClient.warmup(0);
                customTabsSession = getSession(customTabsClient);
                customTabsSession.mayLaunchUrl(Uri.parse(proyeccionsenamhiurl), null, null);
            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                //nothing here
            }
        };

        CustomTabsClient.bindCustomTabsService(this, CHROME_PACKAGE, ctConnection);
        bloquenombre = (RelativeLayout) findViewById(R.id.bloquenombre);


/*
        bloque62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openTab();
            }
        });
*/

        bloque62.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(proyeccionsenamhiurl));
                startActivity(intent);
            }
        });



/*
        bloque42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTabMapa();
            }
        });

*/


        bloque42.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(mapasismico));
                startActivity(intent);
            }
        });





        bloquedetallesvolcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingDrawer.animateClose();

                detalles_volcan(actividad_reciente,
                        estado,
                        nombre,
                        ubicacion,
                        imagenes,
                        altura,
                        codigo,
                        diametro,
                        glaciares,
                        imagenes,
                        latitudes,
                        longitudes,
                        monitores,
                        tipo,
                        tipicaerupcion,
                        fechaactualizacion,
                        horaactualizacion,
                        alertavolcanica,
                        camaraurl,
                        sismogramaurl,
                        resena,
                        ultimaerupcion,
                        mapasismico,
                        proyeccionsenamhiurl
                        );
            }
        });

        bloque5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingDrawer.animateClose();

                alerta_cenizas(actividad_reciente,
                        estado,
                        nombres,
                        ubicacion,
                        urlimagen,
                        altura,
                        codigo,
                        diametro,
                        glaciares,
                        imagenes,
                        latitudes,
                        longitudes,
                        monitores,
                        tipo,
                        tipicaerupcion,
                        fechaactualizacion,
                        horaactualizacion,
                        alertavolcanica,
                        camaraurl,
                        sismogramaurl,
                        mapasismico,
                        proyeccionsenamhiurl
                        );
            }
        });






        if (mapasismico.length() == 1) {
            //bloque1.setBackgroundColor(getResources().getColor(R.color.verdeigp));


            bloque1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slidingDrawer.animateClose();
                    openmap2(actividad_reciente,
                            estados,
                            nombres,
                            ubicacion,
                            imagenes,
                            altura,
                            codigo,
                            diametro,
                            glaciares,
                            imagenes,
                            latitudes,
                            longitudes,
                            monitores,
                            tipo,
                            tipicaerupcion,
                            fechaactualizacion,
                            horaactualizacion,
                            alertavolcanica,
                            camaraurl,
                            sismogramaurl,
                            mapasismico,
                            proyeccionsenamhiurl);

                }
            });


        }
        else{
            bloque1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slidingDrawer.animateClose();

                    openmap(actividad_reciente,
                            estados,
                            nombres,
                            ubicacion,
                            imagenes,
                            altura,
                            codigo,
                            diametro,
                            glaciares,
                            imagenes,
                            latitudes,
                            longitudes,
                            monitores,
                            tipo,
                            tipicaerupcion,
                            fechaactualizacion,
                            horaactualizacion,
                            alertavolcanica,
                            camaraurl,
                            sismogramaurl,
                            mapasismico,
                            proyeccionsenamhiurl);
                }
            });
        }

        blocke3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingDrawer.animateClose();

                reportes(actividad_reciente,
                        estado,
                        nombres,
                        ubicacion,
                        urlimagen,
                        altura,
                        codigo,
                        diametro,
                        glaciares,
                        imagenes,
                        latitudes,
                        longitudes,
                        monitores,
                        tipo,
                        tipicaerupcion,
                        fechaactualizacion,
                        horaactualizacion,
                        alertavolcanica,
                        camaraurl,
                        sismogramaurl,
                        mapasismico,
                        proyeccionsenamhiurl);
            }
        });

        bloque4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingDrawer.animateClose();

                camara_web(actividad_reciente,
                        estados,
                        nombres,
                        ubicacion,
                        imagenes,
                        altura,
                        codigo,
                        diametro,
                        glaciares,
                        imagenes,
                        latitudes,
                        longitudes,
                        monitores,
                        tipo,
                        tipicaerupcion,
                        fechaactualizacion,
                        horaactualizacion,
                        alertavolcanica,
                        camaraurl,
                        sismogramaurl,
                        mapasismico,
                        proyeccionsenamhiurl);
            }
        });

        bloque6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingDrawer.animateClose();


                openDetailActivity(actividad_reciente,
                        estados,
                        nombres,
                        ubicacion,
                        imagenes,
                        altura,
                        codigo,
                        diametro,
                        glaciares,
                        imagenes,
                        latitudes,
                        longitudes,
                        monitores,
                        tipo,
                        tipicaerupcion,
                        fechaactualizacion,
                        horaactualizacion,
                        alertavolcanica,
                        camaraurl,
                        sismogramaurl,
                        mapasismico,
                        proyeccionsenamhiurl);
            }
        });

        bloque7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingDrawer.animateClose();

                sismograma_web(actividad_reciente,
                        estados,
                        nombres,
                        ubicacion,
                        imagenes,
                        altura,
                        codigo,
                        diametro,
                        glaciares,
                        imagenes,
                        latitudes,
                        longitudes,
                        monitores,
                        tipo,
                        tipicaerupcion,
                        fechaactualizacion,
                        horaactualizacion,
                        alertavolcanica,
                        camaraurl,
                        sismogramaurl,
                        mapasismico,
                        proyeccionsenamhiurl);
            }
        });
    }

    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(volcanmenu.this,Dispersioncenizas.class);
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
        volcanmenu.this.startActivity(i);
    }

    private void openmap(String...details)
    {
        Intent i=new Intent(volcanmenu.this,Sismosengeneral.class);
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
        volcanmenu.this.startActivity(i);
    }


    private void openmap2(String...details)
    {
        Intent i=new Intent(volcanmenu.this,sismosengeneralgoogle.class);
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
        volcanmenu.this.startActivity(i);
    }


    private void detalles_volcan(String...details){
        Intent i=new Intent(volcanmenu.this,Detalles.class);
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
        i.putExtra("RESENA",details[20]);
        i.putExtra("ULTIMAERUPCION",details[21]);
        i.putExtra("MAPASISMICO",details[22]);
        i.putExtra("PROYECCIONSENAHMI",details[23]);
        volcanmenu.this.startActivity(i);
    }



    private void alerta_cenizas(String...details)
    {
        Intent i=new Intent(volcanmenu.this,Reportesalertascenizastwo.class);

//        Intent i=new Intent(volcanmenu.this,Reportealertadecenizas.class);
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
        volcanmenu.this.startActivity(i);
    }

    private void camara_web(String...details)
    {
        Intent i=new Intent(volcanmenu.this,Volcancamara.class);
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
        volcanmenu.this.startActivity(i);
    }

    private void sismograma_web(String...details)
    {
        Intent i=new Intent(volcanmenu.this,Sismogramas.class);
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
        volcanmenu.this.startActivity(i);
    }

    private void reportes(String...details)
    {
        Intent i=new Intent(volcanmenu.this,Reportesdeactividad.class);
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
        volcanmenu.this.startActivity(i);
    }









    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ctConnection != null) {
            unbindService(ctConnection);
        }
    }

    private CustomTabsSession getSession(CustomTabsClient customTabsClient) {
        if (customTabsClient != null) {
            return customTabsClient.newSession(new CustomTabsCallback() {
        /*@Override
        public void onNavigationEvent(int navigationEvent, Bundle extras) {
          super.onNavigationEvent(navigationEvent, extras);
        }*/
            });
        }
        return null;
    }

/*
    CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
CustomTabActivityHelper.openCustomTab(this, customTabsIntent, uri,
            new CustomTabActivityHelper.CustomTabFallback() {
        @Override
        public void openUri(Activity activity, Uri uri) {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    });

*/

    public void openTab() {
        /*
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.azulbackground));
        builder.setShowTitle(true);
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher6));
      //  builder.addMenuItem(getString(R.string.menu1), createIntent(R.string.menu1, 1));
     //   builder.addMenuItem(getString(R.string.menu2), createIntent(R.string.menu2, 2));
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher6);
        //  ic_launcherigp  android.R.drawable.ic_menu_add

        builder.setActionButton(icon, getString(R.string.action), createIntent(R.string.action, 3),
                true);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(proyeccionsenamhiurl));
        */



        Uri uri = Uri.parse(proyeccionsenamhiurl);

// create an intent builder
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

// Begin customizing
// set toolbar colors
        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

// set start and exit animations
        intentBuilder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setExitAnimations(this, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

// build custom tabs intent
        CustomTabsIntent customTabsIntent = intentBuilder.build();

// launch the url
        customTabsIntent.launchUrl(this, uri);










    }

    public void openTabMapa() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.azulbackground));
        builder.setShowTitle(true);
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher6));
       // builder.addMenuItem(getString(R.string.menu1), createIntent(R.string.menu1, 1));
       // builder.addMenuItem(getString(R.string.menu2), createIntent(R.string.menu2, 2));
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher6);
        //  ic_launcherigp  android.R.drawable.ic_menu_add

        builder.setActionButton(icon, getString(R.string.action), createIntent(R.string.action, 3),
                true);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(mapasismico));
    }


    private PendingIntent createIntent(int text, int number) {
        Intent intent = new Intent(this, CustomTabsBroadcastReceiver.class);
        intent.putExtra("text", getString(text));
        return PendingIntent.getBroadcast(this, number, intent, 0);
    }


    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this,"Permissão negada", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this,PERMISSIONS , REQUEST_PERMISSION);
        }
    }
    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }











    private void menu_config(String filtro) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        mDatabase.child("actividadvolcanica").child("volcanes").child(filtro).child("menu").orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Menudeopciones sreporte = dataSnapshot.getValue(Menudeopciones.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Menudeopciones sreporte = dataSnapshot.getValue(Menudeopciones.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Menudeopciones sreporte = dataSnapshot.getValue(Menudeopciones.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Menudeopciones sreporte = dataSnapshot.getValue(Menudeopciones.class);
                guardar_pref(sreporte);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void guardar_pref(Menudeopciones sreporte) {
        SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("menu1", sreporte.getMenu1());
        editor.putString("menu2", sreporte.getMenu2());
        editor.putString("menu3", sreporte.getMenu3());
        editor.putString("menu4", sreporte.getMenu4());
        editor.putString("menu5", sreporte.getMenu5());
        editor.putString("menu6", sreporte.getMenu6());
        editor.putString("menu7", sreporte.getMenu7());
        editor.putString("menu8", sreporte.getMenu8());
        editor.apply();
        mostrar_menu();
    }

    private void mostrar_menu() {
        SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
        menu1 = prefs.getString("menu1", "");
        menu2 = prefs.getString("menu2", "");
        menu3 = prefs.getString("menu3", "");
        menu4 = prefs.getString("menu4", "");
        menu5 = prefs.getString("menu5", "");
        menu6 = prefs.getString("menu6", "");
        menu7 = prefs.getString("menu7", "");
        menu8 = prefs.getString("menu8", "");

        if(menu1.equals("VISIBLE")){
            opciones72.setVisibility(View.VISIBLE);
        }
        else{
            opciones72.setVisibility(View.GONE);
        }

        if(menu2.equals("VISIBLE")){
            pronostico_menu2.setVisibility(View.VISIBLE);
        }
        else{
            pronostico_menu2.setVisibility(View.GONE);
        }

        if(menu3.equals("VISIBLE")){
            pronostico_menu.setVisibility(View.VISIBLE);
        }
        else{
            pronostico_menu.setVisibility(View.GONE);
        }

        if(menu4.equals("VISIBLE")){
            sismogramas_menu.setVisibility(View.VISIBLE);
        }
        else{
            sismogramas_menu.setVisibility(View.GONE);
        }

        if(menu5.equals("VISIBLE")){
            reportes_menu.setVisibility(View.VISIBLE);
        }
        else{
            reportes_menu.setVisibility(View.GONE);
        }

        if(menu6.equals("VISIBLE")){
            alertas_menu.setVisibility(View.VISIBLE);
        }
        else{
            alertas_menu.setVisibility(View.GONE);
        }

        if(menu7.equals("VISIBLE")){
            camara_menu.setVisibility(View.VISIBLE);
        }
        else{
            camara_menu.setVisibility(View.GONE);
        }

        if(menu8.equals("VISIBLE")){
            mapasismic_menu.setVisibility(View.VISIBLE);
        }
        else{
            mapasismic_menu.setVisibility(View.GONE);
        }
    }




}