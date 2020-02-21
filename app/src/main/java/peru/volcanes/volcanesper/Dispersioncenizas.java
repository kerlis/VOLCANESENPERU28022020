package peru.volcanes.volcanesper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import peru.volcanes.volcanesper.m_model.dispersion;
import peru.volcanes.volcanesper.m_model.erupciones;
import peru.volcanes.volcanesper.m_ui.Trayectoriasreferenciales;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
public class Dispersioncenizas extends FragmentActivity {
    SupportMapFragment sMapFragment;
    private GoogleMap map;
    private static final int REQUEST_PERMISSION = 1;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase database;
    Double alerta_double;
    RelativeLayout titulopantalla_detalles;
    String estado_texto, ubicacion_texto, imagen_texto, camaraulr_texto, sismograma_texto;
    TextView codigo, latitud, longitud;
    Double latitud_d, longitud_d;
    DatabaseReference mFirebaseDatabase2;
    DatabaseReference mFirebaseDatabase3;
    DatabaseReference mFirebaseDatabase4;
    Trayectoriasreferenciales adapter3;
    ListView alertacenizas;
    ImageView estado_volcan;
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
    ArrayList<peru.volcanes.volcanesper.m_model.sismos> objetosismos = new ArrayList<peru.volcanes.volcanesper.m_model.sismos>();
    ArrayList<erupciones> objetoerupciones = new ArrayList<erupciones>();
    ArrayList<dispersion> objetoalertacenizas = new ArrayList<dispersion>();
    Button mk, mk2;
    TextView time;
    TextView text_volcan;
    TextView text_status;
    RelativeLayout informacion;
    RelativeLayout ajustes;
    RelativeLayout compartir;
    RelativeLayout convenciones;
    RelativeLayout sitio_web;
    RelativeLayout camara_web;
    private double longitude = -75.816650;
    private double latitude = -11.544259;
    Double latitud_dato;
    Double longitud_dato;
    TextView fecha_actualizacion;
    private static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION};
    private LocationManager lm;
    private Location location;
    RelativeLayout menuright;
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
        ArrayList<dispersion> objetoalertacenizas = new ArrayList<dispersion>();
        setContentView(R.layout.activity_reportedispersion);
        database = FirebaseDatabase.getInstance();
        Intent i = this.getIntent();
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
        mapasismico = i.getExtras().getString("MAPASISMICO");
        alerta_text = i.getExtras().getString("ALERTA");
        camaraurls = i.getExtras().getString("CAMARAURL");
        sismogramaurls = i.getExtras().getString("SISMOGRAMA");
        proyecionsenahmi = i.getExtras().getString("PROYECCIONSENAHMI");
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
                Intent intent = new Intent(Dispersioncenizas.this,Contactar.class);
                startActivity(intent);
            }
        });




        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Dispersioncenizas.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });


        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Dispersioncenizas.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Dispersioncenizas.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Dispersioncenizas.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Dispersioncenizas.this,Convenciones.class);
                startActivity(intent);
            }
        });

        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Dispersioncenizas.this,Listadoredessociales.class);
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

        latitud_dato = Double.parseDouble(latitud_text);
        longitud_dato = Double.parseDouble(longitud_text);
        alerta_double = Double.parseDouble(estado_text);
        latitud_d = Double.parseDouble(latitud_text);
        longitud_d = Double.parseDouble(longitud_text);
        text_volcan = (TextView) findViewById(R.id.text_volcan);
        titulopantalla_detalles = (RelativeLayout) findViewById(R.id.titulopantalla_detalles);
        text_volcan.setText("Volcán "+nombre_text);
        estado_volcan = (ImageView) findViewById(R.id.estado_volcan);

        if (alerta_double   <= 1) {
            estado_volcan.setImageResource(R.drawable.volcano_verde);
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.verdeigp));
        }
        else if (alerta_double > 1 && alerta_double == 2) {
            estado_volcan.setImageResource(R.drawable.volcano_amarillo);
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
        }
        else if (alerta_double > 2 && alerta_double == 3) {
            estado_volcan.setImageResource(R.drawable.volcano_naranja);
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.naranjas));
        }
        else if (alerta_double > 3 && alerta_double == 4) {
            estado_volcan.setImageResource(R.drawable.volcano_rojo);
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        }
        else {
            estado_volcan.setImageResource(R.drawable.volcano_rojo);
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        }


        mFirebaseDatabase4 = database.getReference("actividadvolcanica").child("volcanes").child(codigo_text).child("dispersionesdecenizas");
        mFirebaseDatabase4.keepSynced(true);

        alertacenizas = (ListView) findViewById(R.id.listado_cenizas);
        adapter3 = new Trayectoriasreferenciales(Dispersioncenizas.this,retreive());
        alertacenizas.setAdapter(adapter3);
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        time = (TextView) findViewById(R.id.time);
        mk = (Button) findViewById(R.id.button);

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
                slidingDrawer.animateClose();

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

        } else {
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
                slidingDrawer.animateClose();

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
                slidingDrawer.animateClose();

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
                slidingDrawer.animateClose();

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
        Intent i=new Intent(Dispersioncenizas.this,Dispersioncenizas.class);
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

        Dispersioncenizas.this.startActivity(i);
    }



    private void openmap(String...details)
    {
        Intent i=new Intent(Dispersioncenizas.this,Sismosengeneral.class);
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

        Dispersioncenizas.this.startActivity(i);
    }


    private void openmap2(String...details)
    {
        Intent i=new Intent(Dispersioncenizas.this,sismosengeneralgoogle.class);
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
        Dispersioncenizas.this.startActivity(i);
    }


    private void alerta_cenizas(String...details) {
        Intent i=new Intent(Dispersioncenizas.this,Reportesalertascenizastwo.class);
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

        Dispersioncenizas.this.startActivity(i);
    }

    private void camara_web(String...details) {
        Intent i=new Intent(Dispersioncenizas.this,Volcancamara.class);
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

        Dispersioncenizas.this.startActivity(i);
    }

    private void sismograma_web(String...details) {
        Intent i=new Intent(Dispersioncenizas.this,Sismogramas.class);
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

        Dispersioncenizas.this.startActivity(i);
    }

    private void reportes(String...details) {
        Intent i=new Intent(Dispersioncenizas.this,Reportesdeactividad.class);
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

        Dispersioncenizas.this.startActivity(i);
    }

    public ArrayList<dispersion> retreive() {
        FirebaseDatabase.getInstance();
        mFirebaseDatabase4.keepSynced(true);
        mFirebaseDatabase4.orderByChild("orden").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dispersion objetoalertaceniza = dataSnapshot.getValue(dispersion.class);
                objetoalertacenizas.add(objetoalertaceniza);
                alertacenizas.setAdapter(adapter3);

                //for (int i = 19, j = objetoalertacenizas.size() - 1; i == j; i++) {
                //    objetoalertacenizas.add(i, objetoalertacenizas.remove(j));
                //    Collections.reverse(objetoalertacenizas);
                // }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                dispersion objetoalertaceniza = dataSnapshot.getValue(dispersion.class);
                objetoalertacenizas.add(objetoalertaceniza);
                alertacenizas.setAdapter(adapter3);

                //for (int i = 9, j = objetoalertacenizas.size() - 1; i == j; i++) {
                //   objetoalertacenizas.add(i, objetoalertacenizas.remove(j));
                //  Collections.reverse(objetoalertacenizas);
                // }
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
        mFirebaseDatabase4.keepSynced(true);
        return objetoalertacenizas;
    }
    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
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
