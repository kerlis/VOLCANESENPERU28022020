package peru.volcanes.volcanesper;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sismosengeneralgoogle extends FragmentActivity implements OnMapReadyCallback,LocationListener,ActivityCompat.OnRequestPermissionsResultCallback,  NavigationView.OnNavigationItemSelectedListener {
    SupportMapFragment sMapFragment;
    Button mk;
    String latitud, longitud, nombre, altura, estado,alertavolcanica;
    String magnitud2,magnitud3;
    String fecha_sci, hora_sci, latitud_sci, longitud_sci, magnitud_sci, profundidad_sci;
    private LocationManager lm;
    private Location location;
    private double longitude = -75.816650;
    private double latitude = -11.544259;
    private FirebaseDatabase database;
    private static final int REQUEST_PERMISSION = 1;
    private DatabaseReference mFirebaseDatabase;
    private GoogleMap map;
    Button satelite,terreno,localizacion;
    String codigo;
    Double valor_alerta_volcanica;
    Double latitudd;
    Double longitudd;
    TextView volcan_text_name;
    RelativeLayout titulopantalla_detalles;
    String refe,magni;
    private static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION};
    String alerta_texto;
    Double alerta_double;
    RelativeLayout informacion;
    RelativeLayout ajustes;
    RelativeLayout compartir;
    RelativeLayout convenciones;
    RelativeLayout menuright;
    String cantidad;
    Integer variacion;
    ImageView estado_volcan;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerBlock;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ImageView sliderz;
    RelativeLayout blocke3a;
    String reciente_text, estado_text, nombre_text, ubicacion, urlimagenes,  altura_text, codigo_text, diametro_text, glaciares_text, imagen_text,
            latitud_text,    longitud_text,  monitoreo_text, tipo_text, tipica_erupcion_text, fecha_actualizacion_text, hora_actualizacion_text,alerta_text,
            camaraurls,sismogramaurls,mapasismico;
    RelativeLayout bloque1;
    RelativeLayout bloque3a;
    RelativeLayout bloque4;
    RelativeLayout bloque5;
    RelativeLayout bloque6;
    RelativeLayout bloque7;
    RelativeLayout blocke9;
    SlidingDrawer slidingDrawer;
    TextView slideup;
    RelativeLayout options;
    TextView alertacenizas_text;
    TextView camara_text;
    TextView mapa_text;
    TextView trayectoria_cenizas_text;
    TextView trayectoria_cenizas_text2;
    TextView trayectoria_cenizas_text3;
    TextView camara;
    TextView mapa;
    RelativeLayout blocke6a;
    String proyecionsenahmi;
    RelativeLayout bloquenombre;
    RelativeLayout bloque62;
    TextView trayectoria_cenizas_text222;

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
        setContentView(R.layout.contentsismosvolcangoogle);
        database = FirebaseDatabase.getInstance();
        Intent i=this.getIntent();
        reciente_text = i.getExtras().getString("ACTIVIDAD_RECIENTE");
        estado_text = i.getExtras().getString("ESTADO");
        nombre_text = i.getExtras().getString("NOMBRES");
        ubicacion = i.getExtras().getString("UBICACION");
        urlimagenes = i.getExtras().getString("URLIMAGEN");
        altura_text = i.getExtras().getString("ALTURA");
        codigo_text = i.getExtras().getString("CODIGO");
        diametro_text = i.getExtras().getString("DIAMETRO");
        glaciares_text = i.getExtras().getString("GLACIARES");
        imagen_text = i.getExtras().getString("IMAGEN");
        latitud_text = i.getExtras().getString("LATITUD");
        longitud_text = i.getExtras().getString("LONGITUD");
        monitoreo_text = i.getExtras().getString("MONITOREO");
        tipo_text = i.getExtras().getString("TIPO");
        tipica_erupcion_text = i.getExtras().getString("TIPICA_ERUPCION");
        fecha_actualizacion_text = i.getExtras().getString("FECHAACTUALIZACION");
        hora_actualizacion_text = i.getExtras().getString("HORAACTUALIZACION");
        alerta_text = i.getExtras().getString("ALERTA");
        camaraurls = i.getExtras().getString("CAMARAURL");
        sismogramaurls = i.getExtras().getString("SISMOGRAMA");
        mapasismico = i.getExtras().getString("MAPASISMICO");
        proyecionsenahmi = i.getExtras().getString("PROYECCIONSENAHMI");
        //Toast.makeText(this,nombre_text, Toast.LENGTH_SHORT).show();
           /*--------VISUALIZACION DE MENU----------*/
        pronostico_menu = (LinearLayout) findViewById(R.id.opciones3);
        sismogramas_menu = (LinearLayout) findViewById(R.id.opciones4);
        reportes_menu = (LinearLayout) findViewById(R.id.opciones5);
        alertas_menu = (LinearLayout) findViewById(R.id.opciones6);
        camara_menu = (LinearLayout) findViewById(R.id.opciones7);
        mapasismic_menu = (LinearLayout) findViewById(R.id.opciones8);
         val = String.valueOf(codigo_text);
        menu_config();

        /*
        if (val.equals("1493157379002")){
            pronostico_menu.setVisibility(View.VISIBLE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.VISIBLE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.VISIBLE);
        }
        else if(val.equals("1493157381161")){
            pronostico_menu.setVisibility(View.VISIBLE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.VISIBLE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.VISIBLE);
        }
        else if(val.equals("1506454510537")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455245814")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455248101")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.GONE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455249661")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455251429")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.GONE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455253382")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455254838")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455256229")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455257749")){
            pronostico_menu.setVisibility(View.VISIBLE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.VISIBLE);
        }
        else if(val.equals("1506455257753")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455257755")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.GONE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455257757")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.GONE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455259126")){
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455259128")){
            pronostico_menu.setVisibility(View.VISIBLE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.VISIBLE);
        }
        else{
            pronostico_menu.setVisibility(View.VISIBLE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.VISIBLE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.VISIBLE);
        }
        */
     /*--------VISUALIZACION DE MENU----------*/
        sMapFragment = SupportMapFragment.newInstance();
        FragmentManager sFm = getSupportFragmentManager();
        sFm.beginTransaction().add(R.id.map, sMapFragment).commit();
        sMapFragment.getMapAsync(this);
        satelite = (Button) findViewById(R.id.satelite);
        terreno = (Button) findViewById(R.id.terreno);
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        localizacion = (Button) findViewById(R.id.localizacion);
        Button datox = (Button) findViewById(R.id.localizacion);
        datox.setTypeface(fontAwesomeFont);
        estado_volcan = (ImageView) findViewById(R.id.estado_volcan);
        titulopantalla_detalles = (RelativeLayout) findViewById(R.id.titulopantalla_detalles);
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
                Intent intent = new Intent(sismosengeneralgoogle.this,Contactar.class);
                startActivity(intent);
            }
        });



        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(sismosengeneralgoogle.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });





        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(sismosengeneralgoogle.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(sismosengeneralgoogle.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(sismosengeneralgoogle.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(sismosengeneralgoogle.this,Convenciones.class);
                startActivity(intent);
            }
        });


        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(sismosengeneralgoogle.this,Listadoredessociales.class);
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
            }
        });

        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener()
        {
            @Override
            public void onDrawerClosed()
            {
                slidingDrawer.setBackgroundColor(Color.TRANSPARENT);
            }
        });


        double w;
        try {
            w = new Double(estado_text);
        } catch (NumberFormatException e) {
            w = 0;
        }
        if (w == 0){
            magnitud2 = "0";
        }
        else
        {
            magnitud2 = estado_text;
        }

        latitudd= Double.parseDouble(latitud_text);
        longitudd= Double.parseDouble(longitud_text);
        volcan_text_name = (TextView) findViewById(R.id.volcannombre);
        volcan_text_name.setText("Volcán "+ nombre_text);
        titulopantalla_detalles = (RelativeLayout) findViewById(R.id.titulopantalla_detalles);
        initMaps();
        mk= (Button) findViewById(R.id.button);
        valor_alerta_volcanica = Double.parseDouble(alerta_text);
        alerta_texto = alertavolcanica;
        alerta_double =  Double.parseDouble(magnitud2);


        if (alerta_double    <= 1) {
            estado_volcan.setImageResource(R.drawable.volcano_verde);
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.verdeigp));
        }
        else if (alerta_double  > 1 && alerta_double == 2) {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
            estado_volcan.setImageResource(R.drawable.volcano_amarillo);
        }
        else if (alerta_double  > 2 && alerta_double == 3) {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.naranjas));
            estado_volcan.setImageResource(R.drawable.volcano_naranja);
        }

        else if (alerta_double  > 3 && alerta_double == 4) {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.rojoigp));
            estado_volcan.setImageResource(R.drawable.volcano_rojo);
        }
        else {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.rojoigp));
            estado_volcan.setImageResource(R.drawable.volcano_rojo);
        }

        bloque1 = (RelativeLayout) findViewById(R.id.bloque1);
        bloque3a = (RelativeLayout) findViewById(R.id.bloque3);
        bloque5 = (RelativeLayout) findViewById(R.id.bloque5);
        bloque4 = (RelativeLayout) findViewById(R.id.bloque4);
        bloque6 = (RelativeLayout) findViewById(R.id.bloque6);
        bloque7 = (RelativeLayout) findViewById(R.id.bloque7);

        slideup = (TextView) findViewById(R.id.openslide);
        camara = (TextView) findViewById(R.id.camara);
        mapa = (TextView) findViewById(R.id.mapa);
        alertacenizas_text = (TextView) findViewById(R.id.alertacenizas_text);
        camara_text = (TextView) findViewById(R.id.camara_text);
        mapa_text = (TextView) findViewById(R.id.mapa_text);
        trayectoria_cenizas_text = (TextView) findViewById(R.id.trayectoria_cenizas_text);
        trayectoria_cenizas_text2 = (TextView) findViewById(R.id.trayectoria_cenizas_text2);
        trayectoria_cenizas_text3 = (TextView) findViewById(R.id.trayectoria_cenizas_text3);
        camara.setTypeface(fontAwesomeFont);
        mapa.setTypeface(fontAwesomeFont);
        slideup.setTypeface(fontAwesomeFont);

        bloque5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alerta_cenizas(reciente_text,
                        estado_text,
                        nombre_text,
                        ubicacion,
                        urlimagenes,
                        altura_text,
                        codigo_text,
                        diametro_text,
                        glaciares_text,
                        imagen_text,
                        latitud_text,
                        longitud_text,
                        monitoreo_text,
                        tipo_text,
                        tipica_erupcion_text,
                        fecha_actualizacion_text,
                        hora_actualizacion_text,
                        alerta_text,
                        camaraurls,
                        sismogramaurls,
                        mapasismico
                );
            }
        });

        if (mapasismico.length() == 1) {
            //bloque1.setBackgroundColor(getResources().getColor(R.color.verdeigp));
            bloque1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slidingDrawer.animateClose();
                    openmap2(reciente_text,
                            estado_text,
                            nombre_text,
                            ubicacion,
                            urlimagenes,
                            altura_text,
                            codigo_text,
                            diametro_text,
                            glaciares_text,
                            imagen_text,
                            latitud_text,
                            longitud_text,
                            monitoreo_text,
                            tipo_text,
                            tipica_erupcion_text,
                            fecha_actualizacion_text,
                            hora_actualizacion_text,
                            alerta_text,
                            camaraurls,
                            sismogramaurls,
                            mapasismico);
                }
            });
        }
        else{
            bloque1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    slidingDrawer.animateClose();
                    openmap(reciente_text,
                            estado_text,
                            nombre_text,
                            ubicacion,
                            urlimagenes,
                            altura_text,
                            codigo_text,
                            diametro_text,
                            glaciares_text,
                            imagen_text,
                            latitud_text,
                            longitud_text,
                            monitoreo_text,
                            tipo_text,
                            tipica_erupcion_text,
                            fecha_actualizacion_text,
                            hora_actualizacion_text,
                            alerta_text,
                            camaraurls,
                            sismogramaurls,
                            mapasismico);
                }
            });
        }

        bloque3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportes(reciente_text,
                        estado_text,
                        nombre_text,
                        ubicacion,
                        urlimagenes,
                        altura_text,
                        codigo_text,
                        diametro_text,
                        glaciares_text,
                        imagen_text,
                        latitud_text,
                        longitud_text,
                        monitoreo_text,
                        tipo_text,
                        tipica_erupcion_text,
                        fecha_actualizacion_text,
                        hora_actualizacion_text,
                        alerta_text,
                        camaraurls,
                        sismogramaurls,
                        mapasismico);
            }
        });

        bloque4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camara_web(reciente_text,
                        estado_text,
                        nombre_text,
                        ubicacion,
                        urlimagenes,
                        altura_text,
                        codigo_text,
                        diametro_text,
                        glaciares_text,
                        imagen_text,
                        latitud_text,
                        longitud_text,
                        monitoreo_text,
                        tipo_text,
                        tipica_erupcion_text,
                        fecha_actualizacion_text,
                        hora_actualizacion_text,
                        alerta_text,
                        camaraurls,
                        sismogramaurls,
                        mapasismico);
            }
        });

        bloque6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailActivity(reciente_text,
                        estado_text,
                        nombre_text,
                        ubicacion,
                        urlimagenes,
                        altura_text,
                        codigo_text,
                        diametro_text,
                        glaciares_text,
                        imagen_text,
                        latitud_text,
                        longitud_text,
                        monitoreo_text,
                        tipo_text,
                        tipica_erupcion_text,
                        fecha_actualizacion_text,
                        hora_actualizacion_text,
                        alerta_text,
                        camaraurls,
                        sismogramaurls,
                        mapasismico);
            }
        });

        bloque7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sismograma_web(reciente_text,
                        estado_text,
                        nombre_text,
                        ubicacion,
                        urlimagenes,
                        altura_text,
                        codigo_text,
                        diametro_text,
                        glaciares_text,
                        imagen_text,
                        latitud_text,
                        longitud_text,
                        monitoreo_text,
                        tipo_text,
                        tipica_erupcion_text,
                        fecha_actualizacion_text,
                        hora_actualizacion_text,
                        alerta_text,
                        camaraurls,
                        sismogramaurls,
                        mapasismico);
            }
        });
    }

    private void openDetailActivity(String...details) {
        Intent i=new Intent(sismosengeneralgoogle.this,Dispersioncenizas.class);
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
        sismosengeneralgoogle.this.startActivity(i);
    }

    private void openmap(String...details)
    {
        Intent i=new Intent(sismosengeneralgoogle.this,Sismosengeneral.class);
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

        sismosengeneralgoogle.this.startActivity(i);
    }

    private void openmap2(String...details)
    {
        Intent i=new Intent(sismosengeneralgoogle.this,sismosengeneralgoogle.class);
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
        sismosengeneralgoogle.this.startActivity(i);
    }

    private void alerta_cenizas(String...details) {
        Intent i=new Intent(sismosengeneralgoogle.this,Reportesalertascenizastwo.class);
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
        sismosengeneralgoogle.this.startActivity(i);
    }

    private void camara_web(String...details) {
        Intent i=new Intent(sismosengeneralgoogle.this,Volcancamara.class);
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
        sismosengeneralgoogle.this.startActivity(i);
    }

    private void sismograma_web(String...details) {
        Intent i=new Intent(sismosengeneralgoogle.this,Sismogramas.class);
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
        sismosengeneralgoogle.this.startActivity(i);
    }

    private void reportes(String...details) {
        Intent i=new Intent(sismosengeneralgoogle.this,Reportesdeactividad.class);
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
        sismosengeneralgoogle.this.startActivity(i);
    }

    public void initMaps(){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        } else {
            lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 60000, this);
        }
    }

    public void onMapReady(final GoogleMap map2) {
        this.map = map2;
        if (lm != null) {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map2.setMyLocationEnabled(true);
        }
        map2.setTrafficEnabled(true);
        map2.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 11));
        map2.animateCamera(CameraUpdateFactory.zoomTo(11), 2000, null);
        map2.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map2.getUiSettings().setZoomControlsEnabled(true);
        map2.getUiSettings().setIndoorLevelPickerEnabled(true);
        map2.getUiSettings().setTiltGesturesEnabled(true);
        map2.getUiSettings().setCompassEnabled(true);
        BitmapDescriptor icon;
        LatLng latLng = new LatLng(latitudd,longitudd);

        if (valor_alerta_volcanica   <= 1) {
            icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_verde);
        }
        else if (valor_alerta_volcanica  > 1 && valor_alerta_volcanica == 2) {
            icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_amarillo);
        }
        else if (valor_alerta_volcanica  > 2 && valor_alerta_volcanica == 3) {
            icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_amarillo);
        }
        else if (valor_alerta_volcanica  > 3 && valor_alerta_volcanica == 4) {
            icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_rojo);
        }
        else {
            icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_rojo);
        }

        map2.addMarker(new MarkerOptions().position(latLng).title(nombre).snippet("Altura :"+altura).icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
        map2.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));
        map2.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map2.getUiSettings().setZoomControlsEnabled(true);
        map2.getUiSettings().setIndoorLevelPickerEnabled(true);
        map2.getUiSettings().setTiltGesturesEnabled(true);
        map2.getUiSettings().setCompassEnabled(true);
        //map2.getUiSettings().setMyLocationButtonEnabled(true);
        map2.getUiSettings().setAllGesturesEnabled(true);
        map2.setMyLocationEnabled(true);
        satelite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                map2.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        terreno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                map2.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

        localizacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                map2.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitudd, longitudd), 11));
            }
        });

        map.setPadding(0, 0, 0, 100);


        loadMarker();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onLocationChanged(Location arg0) {
    }
    @Override
    public void onProviderDisabled(String arg0) {
    }
    @Override
    public void onProviderEnabled(String arg0) {
    }
    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Autorizado", Toast.LENGTH_SHORT).show();
                    initMaps();

                } else {
                    Toast.makeText(this,"Permissão negada", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
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

    public void loadMarker(){
        final   DatabaseReference locais = database.getReference("actividadvolcanica").child("volcanes").child("1493157379002").child("sismos");
        locais.limitToLast(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                //  map.clear();
                for (final DataSnapshot dataSnapshot1 : dataSnapshots) {
                    final sismos local = dataSnapshot1.getValue(sismos.class);

                    final Double lati,longi;
                    refe = local.getProfundidad();
                    magnitud3 = local.getMagnitud();

                    //evaluar dato magnitud
                    magni = magnitud3;
                    double w;
                    try {
                        w = new Double(magni);
                    } catch (NumberFormatException e) {
                        w = 0;
                    }
                    if (w == 0){
                        magnitud2 = "0";
                    }
                    else
                    {
                        magnitud2 = magni;
                    }
                    lati = Double.parseDouble(local.getLatitud());
                    longi = Double.parseDouble(local.getLongitud());
                    final Double magnitude = Double.parseDouble(magnitud2);
                    int height = 100;
                    int width = 100;
                    BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.volcan_amarillo_sm);
                    Bitmap b=bitmapdraw.getBitmap();
                    Bitmap.createScaledBitmap(b, width, height, false);
                    final BitmapDescriptor icon;
                    if (magnitude > 0.0 && magnitude <= 2.5){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }
                    else if (magnitude > 2.5 && magnitude <= 3.0){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }
                    else if (magnitude > 3.0 && magnitude <= 3.5){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }
                    else if (magnitude > 3.0 && magnitude <= 3.5){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }
                    else if (magnitude > 3.5 && magnitude <= 3.6){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }
                    else {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }
                    map.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title(local.getMagnitud() + " "+"Profundidad : "+local.getProfundidad()+"  >>>").snippet("Fecha : "+local.getFecha()+"\n  Hora"+local.getHora()).icon(icon).anchor(0.5f, 0.5f));
                    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        public void onInfoWindowClick(Marker marker) {
                            final double de4 = marker.getPosition().latitude;
                            String latitudl;
                            latitudl = String.valueOf(de4);
                            String ff=(".equalTo(latitudl)");
                            mFirebaseDatabase = database.getReference("actividadvolcanica").child("volcanes").child("messages");
                            mFirebaseDatabase.orderByChild("latitud").equalTo(latitudl).limitToLast(5).addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    sismos sreporte = dataSnapshot.getValue(sismos.class);
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
                                public void guardar_pref(sismos sreporte) {
                                    SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("sisfecha", sreporte.getFecha());
                                    editor.putString("sishora", sreporte.getHora());
                                    editor.putString("sislatitud", sreporte.getLatitud());
                                    editor.putString("sislongitud", sreporte.getLongitud());
                                    editor.putString("sismagnitud", sreporte.getMagnitud());
                                    editor.putString("sisprofundidad", sreporte.getProfundidad());
                                    editor.apply();
                                    mostrar_ult_sismo();
                                }
                                public void mostrar_ult_sismo() {
                                    SharedPreferences prefs = getSharedPreferences("ultsismo", Context.MODE_PRIVATE);
                                    fecha_sci = prefs.getString("sisfecha", "");
                                    hora_sci = prefs.getString("sishora", "");
                                    latitud_sci = prefs.getString("sislatitud", "");
                                    longitud_sci = prefs.getString("sislongitud", "");
                                    magnitud_sci = prefs.getString("sismagnitud", "");
                                    profundidad_sci = prefs.getString("sisprofundidad", "");

                                    AlertDialog.Builder builder = new AlertDialog.Builder(sismosengeneralgoogle.this);
                                    builder.setTitle("Sismo volcan : " + nombre);
                                    builder.setMessage("Referencia : " +fecha_sci + de4 +"\n" +
                                            "Fecha local : " + fecha_sci +"\n" +
                                            "Hora local : " + hora_sci + "\n" +
                                            "Profundidad : " + profundidad_sci+" km"+ "\n" +
                                            "Intensidad : " + magnitud_sci+ "\n" +
                                            "Magnitud : " + magnitud_sci+ "\n" +
                                            "Ubicación : " + latitud_sci+ ", " + longitud_sci);
                                    builder.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    builder.setCancelable(true);
                                    builder.create().show();
                                }
                            });
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private void menu_config() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        mDatabase.child("actividadvolcanica").child("volcanes").child(val).child("menu").orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
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