package peru.volcanes.volcanesper;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;
import peru.volcanes.volcanesper.m_model.erupciones;
import peru.volcanes.volcanesper.m_ui.ErupcionesAdapter;
import peru.volcanes.volcanesper.m_ui.RecyclerAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Detalles extends FragmentActivity implements  OnMapReadyCallback,LocationListener {
    SupportMapFragment sMapFragment;
    private GoogleMap map;
    private static final int REQUEST_PERMISSION = 1;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase database;
    Double alerta_double;
    RelativeLayout titulopantalla_detalles;
    TextView altura, codigo, latitud, longitud, monitoreo;
    Button satelite, terreno, localizacion;
    Double latitud_d, longitud_d;
    DatabaseReference mFirebaseDatabase2;
    DatabaseReference mFirebaseDatabase3;
    DatabaseReference mFirebaseDatabase4;
    ErupcionesAdapter adapter2;
    String magnitud2, magnitud3;
    ListView erupciones;
    ListView sismos;
    ArrayList<erupciones> objetoerupciones = new ArrayList<erupciones>();
    Button mk;
    TextView alura_icon;
    TextView diameter_icon;
    TextView localizacion_icon;
    TextView glaciares_icon;
    TextView text_glaciares;
    TextView text_localizacion;
    TextView text_volcan;
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
    TextView text_actividad_reciente;
    TextView fecha_actualizacion;
    TextView text_tipo;
    TextView text_diametro;
    TextView text_altura;
    String refe,magni;
    private static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION};
    String fecha_sci, hora_sci, latitud_sci, longitud_sci, magnitud_sci, profundidad_sci;
    private LocationManager lm;
    private Location location;
    Double doublesismolongitudvolcan;
    Double doublesismolatitudvolcan;
    Double doublevolcanlatituddesismo;
    Double doublevolcanlongituddesismo;
    Boolean visibilidad;
    RelativeLayout menuright;
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
    TextView documento;
    RelativeLayout bloque1;
    RelativeLayout bloque3a;
    RelativeLayout bloque4;
    RelativeLayout bloque5;
    RelativeLayout bloque6;
    RelativeLayout bloque7;
    RelativeLayout totop;
    TextView textoresena;
    String reciente_text, estado_text, nombre_text, ubicacion, urlimagenes,  altura_text, codigo_text, diametro_text, glaciares_text, imagen_text,
            latitud_text,    longitud_text,  monitoreo_text, tipo_text, tipica_erupcion_text, fecha_actualizacion_text, hora_actualizacion_text,alerta_text,
            camaraurls,sismogramaurls, resena_text,ultimaerupcion,mapasismico;
    ImageView foto;
    ImageView estado_volcan;
    GoogleMap map22;
    TextView texttoup;
    ScrollView myView;
    WebView contexto;
    RelativeLayout blocke6a;


    String proyecionsenahmi;
    RelativeLayout bloquenombre;
    RelativeLayout bloque62;
    TextView trayectoria_cenizas_text222;
    String proyeccionsenamhiurl;
    RelativeLayout bloque42;
    TextView camara2;


    RelativeLayout blocke12;

    FirebaseDatabase databaseerupciones;
    DatabaseReference myRef ;
    List<erupciones> list;
    RecyclerView recycle;
    Button view;
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



    private static final String CHROME_PACKAGE = "com.android.chrome";
    private CustomTabsServiceConnection ctConnection;
    private CustomTabsSession customTabsSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_detalles);
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
        resena_text = i.getExtras().getString("RESENA");
        ultimaerupcion= i.getExtras().getString("ULTIMAERUPCION");
        mapasismico= i.getExtras().getString("MAPASISMICO");

        proyeccionsenamhiurl = i.getExtras().getString("PROYECCIONSENAHMI");

        myView = (ScrollView) findViewById(R.id.myview);


        totop = (RelativeLayout) findViewById(R.id.totop);



        totop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                myView.setScrollY(0);
            }
        });



       String tempStr = resena_text.substring(0, 1).toUpperCase() + resena_text.substring(1);
        int padding = 10; // in pixels
        Double evaluarvolcan;
        evaluarvolcan = Double.parseDouble(codigo_text);

      contexto = (WebView) findViewById(R.id.parrafo);

        if (nombre_text.equals("Quimsachata")){
            contexto.loadUrl("file:///android_res/raw/quimsachata.html");
        }
        else if(nombre_text.equals("Cerro Auquihuato")){
            contexto.loadUrl("file:///android_res/raw/cerroauquihuato.html");
        }
        else if(nombre_text.equals("Sara Sara")){
            contexto.loadUrl("file:///android_res/raw/sarasara.html");
        }
        else if(nombre_text.equals("Coropuna")){
            contexto.loadUrl("file:///android_res/raw/coropuna.html");
        }
        else if(nombre_text.equals("Andahua")){
            contexto.loadUrl("file:///android_res/raw/andahua.html");
        }
        else if(nombre_text.equals("Huambo")){
            contexto.loadUrl("file:///android_res/raw/huambo.html");
        }
        else if(nombre_text.equals("Sabancaya")){
            contexto.loadUrl("file:///android_res/raw/sabancaya.html");
        }
        else if(nombre_text.equals("Chachani")){
            contexto.loadUrl("file:///android_res/raw/chachani.html");
        }
        else if(nombre_text.equals("Misti")){
            contexto.loadUrl("file:///android_res/raw/misti.html");
        }
        else if(nombre_text.equals("Ubinas")){
            contexto.loadUrl("file:///android_res/raw/ubinas.html");
        }
        else if(nombre_text.equals("Huaynaputina")){
            contexto.loadUrl("file:///android_res/raw/huaynaputina.html");
        }
        else if(nombre_text.equals("Ticsani")){
            contexto.loadUrl("file:///android_res/raw/ticsani.html");
        }
        else if(nombre_text.equals("Tutupaca")){
            contexto.loadUrl("file:///android_res/raw/tutupaca.html");
        }
        else if(nombre_text.equals("Yucamane")){
            contexto.loadUrl("file:///android_res/raw/yacamane.html");
        }
        else if(nombre_text.equals("Cerros Purupuruni")){
            contexto.loadUrl("file:///android_res/raw/cerrospurupuruni.html");
        }
        else if(nombre_text.equals("Casiri")){
            contexto.loadUrl("file:///android_res/raw/casiri.html");
        }
        else {
            contexto.loadUrl("file:///android_res/raw/volcan1.html");
        }

        /*--------VISUALIZACION DE MENU----------*/

        trayectoria_cenizas_text222 = (TextView) findViewById(R.id.trayectoria_cenizas_text222);
        trayectoria_cenizas_text222.setText("Pronóstico de dispersión");

         pronostico_menu2 = (LinearLayout) findViewById(R.id.opciones2);
         opciones72 = (LinearLayout) findViewById(R.id.opciones72);

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
            opciones72.setVisibility(View.VISIBLE);
            pronostico_menu2.setVisibility(View.VISIBLE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.VISIBLE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1493157381161")){
            opciones72.setVisibility(View.VISIBLE);
            pronostico_menu2.setVisibility(View.VISIBLE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.VISIBLE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506454510537")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455245814")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455248101")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.GONE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455249661")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455251429")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.GONE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455253382")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455254838")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455256229")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455257749")){
            opciones72.setVisibility(View.VISIBLE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455257753")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455257755")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.GONE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455257757")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.GONE);
            reportes_menu.setVisibility(View.GONE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.GONE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455259126")){
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else if(val.equals("1506455259128")){
            opciones72.setVisibility(View.VISIBLE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.GONE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }
        else{
            opciones72.setVisibility(View.GONE);
            pronostico_menu2.setVisibility(View.GONE);
            pronostico_menu.setVisibility(View.GONE);
            sismogramas_menu.setVisibility(View.VISIBLE);
            reportes_menu.setVisibility(View.VISIBLE);
            alertas_menu.setVisibility(View.VISIBLE);
            camara_menu.setVisibility(View.VISIBLE);
            mapasismic_menu.setVisibility(View.GONE);
        }

*/

     /*--------VISUALIZACION DE MENU----------*/


        // contexto.loadUrl("file:///android_res/raw/quimsachata.html");


        String hallostring = "hallo";
        String asubstring = hallostring.substring(0, 1);

        SpannableString spannableString = new SpannableString(tempStr);
        spannableString.setSpan(new RelativeSizeSpan(2f),0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       // spannableString.setSpan(new SuperscriptSpanAdjuster(6/9), spannableString.length() - 2, spannableString.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
      // spannableString.setSpan(new BackgroundColorSpan(0xFF0DADFF),0,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
      //  spannableString.setSpan(boldSpan, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        String ted = resena_text;

      //  mBox = new TextView(context);
        String uno = "{0} You will need a {1} to complete this assembly";
        String dos = "E";


      //  formattedSpan.setSpan(new BackgroundColorSpan(0xFF0DADFF),0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      //  formattedSpan.setSpan(new ForegroundColorSpan(Color.BLUE),2, formattedSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      //  formattedSpan.setSpan(new RelativeSizeSpan(3f),0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      //  spannableString.setSpan(new BackgroundColorSpan(0xFF0DADFF),0,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        SpannableString formattedSpan = formatStyles2(dos  , R.style.style0);
       // documento.setText(formattedSpan, TextView.BufferType.SPANNABLE);

        String resena_text2 = resena_text;
        String hallostring2 = resena_text;
        String asubstring2 = hallostring2.substring(0, 4);


        Spannable WordtoSpan = new SpannableString(tempStr);

      // WordtoSpan.setSpan(new RelativeSizeSpan(-10f),0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    //  WordtoSpan.setSpan(new BackgroundColorSpan(0xFF0DADFF),0,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        WordtoSpan.setSpan( new TextAppearanceSpan(this, R.style.style0),  0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
     //  WordtoSpan.setSpan( new SuperscriptSpan(), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        //   ForegroundColorSpan foregroundSpan = new ForegroundColorSpan(Color.RED);
     //   BackgroundColorSpan backgroundSpan = new BackgroundColorSpan(Color.YELLOW);
     //   WordtoSpan.setSpan(foregroundSpan, 0, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
      //  WordtoSpan.setSpan(backgroundSpan, 0, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);




        documento = (TextView) findViewById(R.id.textoresena);
        documento.setText(WordtoSpan);

    //  SpannableString formattedSpan = formatStyles(getString(R.string.my_text), getString(R.string.text_sub0), R.style.style0, getString(R.string.main_text_sub1), R.style.style1);
   //   //textView.setText(formattedSpan, TextView.BufferType.SPANNABLE);



     //  documento.setText(Html.fromHtml("<b>" + "<div background-color='red;'>" + "A" + "</label>" +  resena_text ));








        totop = (RelativeLayout) findViewById(R.id.totop);
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

        estado_volcan = (ImageView) findViewById(R.id.estado_volcan);

         sMapFragment = SupportMapFragment.newInstance();
        FragmentManager sFm = getSupportFragmentManager();
        sFm.beginTransaction().add(R.id.mapx, sMapFragment).commit();
        sMapFragment.getMapAsync(this);






        blocke1a = (RelativeLayout) findViewById(R.id.blocke1);
        blocke2a = (RelativeLayout) findViewById(R.id.blocke2);
        blocke4a = (RelativeLayout) findViewById(R.id.blocke4);
        blocke5a = (RelativeLayout) findViewById(R.id.blocke5);
        blocke6a = (RelativeLayout) findViewById(R.id.blocke6);
        bloque62 = (RelativeLayout) findViewById(R.id.bloque62);
        bloque42 = (RelativeLayout) findViewById(R.id.bloque42);


        blocke12 = (RelativeLayout) findViewById(R.id.blocke12);

        blocke92 = (RelativeLayout) findViewById(R.id.blocke92);


        blocke92.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Contactar.class);
                startActivity(intent);
            }
        });



        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });

        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Convenciones.class);
                startActivity(intent);
            }
        });


        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Listadoredessociales.class);
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

        blocke1a = (RelativeLayout) findViewById(R.id.blocke1);
        blocke2a = (RelativeLayout) findViewById(R.id.blocke2);
        blocke4a = (RelativeLayout) findViewById(R.id.blocke4);
        blocke5a = (RelativeLayout) findViewById(R.id.blocke5);
        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Detalles.this,Convenciones.class);
                startActivity(intent);
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



        texttoup = (TextView) findViewById(R.id.texttoup);
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        texttoup.setTypeface(fontAwesomeFont);
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

        camara2 = (TextView) findViewById(R.id.camara2);

        camara2.setTypeface(fontAwesomeFont);

        /*
        alertacenizas_text.setText(" Alerta de Cenizas ");
        camara_text.setText(" Cámara en tiempo real ");
        mapa_text.setText(" Mapa sísmico ");
        trayectoria_cenizas_text.setText(" Pronóstico de dispersión ");
        trayectoria_cenizas_text2.setText(" Reportes actividad ");
        trayectoria_cenizas_text3.setText(" Sismogramas tiempo real ");
*/

        latitud_dato = Double.parseDouble(latitud_text);
        longitud_dato = Double.parseDouble(longitud_text);
        menuright = (RelativeLayout) findViewById(R.id.imagen3);
        alerta_double = Double.parseDouble(estado_text);
        monitoreo = (TextView) findViewById(R.id.monitoreo);
        text_actividad_reciente = (TextView) findViewById(R.id.text_actividad_reciente);
        text_tipo = (TextView) findViewById(R.id.text_tipo);
        monitoreo.setText(monitoreo_text);
        latitud_d = Double.parseDouble(latitud_text);
        longitud_d = Double.parseDouble(longitud_text);
        satelite = (Button) findViewById(R.id.satelite);
        terreno = (Button) findViewById(R.id.terreno);
        localizacion = (Button) findViewById(R.id.localizacion);
        text_altura = (TextView) findViewById(R.id.altura_label);
        text_diametro = (TextView) findViewById(R.id.text_diametro);
        text_glaciares = (TextView) findViewById(R.id.text_glaciares);
        text_localizacion = (TextView) findViewById(R.id.text_latitud_longitud);
        text_volcan = (TextView) findViewById(R.id.text_volcan);
      //  text_status = (TextView) findViewById(R.id.text_status);
        //fecha_actualizacion = (TextView) findViewById(R.id.fecha_actualizaciones);
        titulopantalla_detalles = (RelativeLayout) findViewById(R.id.titulopantalla_detalles);
       // fecha_actualizacion.setText(fecha_actualizacion_text+" / "+hora_actualizacion_text);
        //text_volcan.setText("Info más detalles"+ "\n" +"Volcán  "+nombre_text);
        text_volcan.setText("Volcán  "+nombre_text);


        text_altura.setText(altura_text);
        text_diametro.setText(ultimaerupcion);
        text_glaciares.setText(glaciares_text);
        text_localizacion.setText("lat.: " + latitud_text + ""  + "\n" + "long.: " + longitud_text);
        text_actividad_reciente.setText(reciente_text);
        text_tipo.setText(tipo_text);



        foto = (ImageView) findViewById(R.id.foto);
        if(urlimagenes != null && !urlimagenes.isEmpty())
        {
            Picasso.with(getApplicationContext()).load(urlimagenes)
                    .resize(9500, 4000)
                    .onlyScaleDown()
                    .into(foto);
        }


        if (alerta_double   <= 1) {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.verdeigp));

            totop.setBackgroundColor(getResources().getColor(R.color.verdeigp));

            // text_status.setText("inactivo");
            estado_volcan.setImageResource(R.drawable.volcano_verde);

        }
        else if (alerta_double  > 1 && alerta_double == 2) {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
            totop.setBackgroundColor(getResources().getColor(R.color.orangeyellow));

            //text_status.setText("minima actividad");
            estado_volcan.setImageResource(R.drawable.volcano_amarillo);

        }
        else if (alerta_double  > 2 && alerta_double == 3) {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.naranjas));
           totop.setBackgroundColor(getResources().getColor(R.color.naranjas));

            //    text_status.setText("minima actividad");
            estado_volcan.setImageResource(R.drawable.volcano_naranja);

        }
        else if (alerta_double  > 3 && alerta_double == 4) {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.rojoigp));
            totop.setBackgroundColor(getResources().getColor(R.color.rojoigp));

            //  text_status.setText("mayor erupción");
            estado_volcan.setImageResource(R.drawable.volcano_rojo);

        }
        else {
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.rojoigp));
            totop.setBackgroundColor(getResources().getColor(R.color.rojoigp));

            //text_status.setText("Explosiva");
            estado_volcan.setImageResource(R.drawable.volcano_rojo);

        }


        recycle = (RecyclerView) findViewById(R.id.recycle);
        databaseerupciones = FirebaseDatabase.getInstance();


        mFirebaseDatabase = database.getReference("actividadvolcanica").child("volcanes").child("messages");
        mFirebaseDatabase2 = database.getReference("actividadvolcanica").child("volcanes").child(codigo_text).child("erupciones");
        mFirebaseDatabase3 = database.getReference("actividadvolcanica").child("volcanes").child("messages");
        mFirebaseDatabase4 = database.getReference("actividadvolcanica").child("volcanes").child(codigo_text).child("alerta_cenizas_ubinas");

        mFirebaseDatabase.keepSynced(true);
        mFirebaseDatabase2.keepSynced(true);
        mFirebaseDatabase4.keepSynced(true);
       // erupciones = (ListView) findViewById(R.id.ultimas_erupciones);
      //  adapter2=new ErupcionesAdapter(Detalles.this,erupcionesx());
       // erupciones.setAdapter(adapter2);

        recycle = (RecyclerView) findViewById(R.id.recycle);
        databaseerupciones = FirebaseDatabase.getInstance();
        // myRef = database.getReference("message");


        myRef = databaseerupciones.getReference("actividadvolcanica").child("volcanes").child(codigo_text).child("erupciones");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<erupciones>();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    erupciones value = dataSnapshot1.getValue(erupciones.class);
                    erupciones fire = new erupciones();
                    String name = value.getFuente();
                    String address = value.getObservaciones();
                    String email = value.getYear();
                    fire.setFuente(name);
                    fire.setObservaciones(email);
                    fire.setYear(address);
                    list.add(fire);


                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list, Detalles.this);
                    RecyclerView.LayoutManager recyce = new GridLayoutManager(Detalles.this, 1);
                    /// RecyclerView.LayoutManager recyce = new LinearLayoutManager(MainActivity.this);
                    // recycle.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                    recycle.setLayoutManager(recyce);
                    recycle.setItemAnimator(new DefaultItemAnimator());
                    recycle.setAdapter(recyclerAdapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });



        localizacion.setTypeface(fontAwesomeFont);
        //initMaps();
        mk= (Button) findViewById(R.id.button);

        bloque3a = (RelativeLayout) findViewById(R.id.bloque3);
        bloque5 = (RelativeLayout) findViewById(R.id.bloque5);
        bloque1 = (RelativeLayout) findViewById(R.id.bloque1);
        bloque6 = (RelativeLayout) findViewById(R.id.bloque6);
        bloque4 = (RelativeLayout) findViewById(R.id.bloque4);
        bloque7 = (RelativeLayout) findViewById(R.id.bloque7);

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
                            mapasismico,
                            proyeccionsenamhiurl);
                }
            });
        }


        bloque3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingDrawer.animateClose();

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
                        mapasismico,
                        proyeccionsenamhiurl);
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
                        mapasismico,
                        proyeccionsenamhiurl);
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
                        mapasismico,
                        proyeccionsenamhiurl);
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
                        mapasismico,
                        proyeccionsenamhiurl);
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

        bloque42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTabMapa();
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



    }


    private SpannableString formatStyles(String value, String sub0, int style0, String sub1, int style1) {
        String tag0 = "{0}";
        int startLocation0 = value.indexOf(tag0);
        value = value.replace(tag0, sub0);

        String tag1 = "{1}";
        int startLocation1 = value.indexOf(tag1);
        if (sub1 != null && !sub1.equals(""))
        {
            value = value.replace(tag1, sub1);
        }

        SpannableString styledText = new SpannableString(value);
        styledText.setSpan(new TextAppearanceSpan(this, style0), startLocation0, startLocation0 + sub0.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (sub1 != null && !sub1.equals(""))
        {
            styledText.setSpan(new TextAppearanceSpan(this, style1), startLocation1, startLocation1 + sub1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return styledText;
    }

    private SpannableString formatStyles2(String value, int style0) {


        SpannableString styledText = new SpannableString(value);
        styledText.setSpan(new TextAppearanceSpan(this, style0), 1, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // if (sub1 != null && !sub1.equals(""))
        //{
        //  styledText.setSpan(new TextAppearanceSpan(this, style1), startLocation1, startLocation1 + sub1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //}

        return styledText;
    }

    private void openDetailActivity(String...details) {
        Intent i=new Intent(Detalles.this,Dispersioncenizas.class);
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


        Detalles.this.startActivity(i);
    }




    private void openmap(String...details)
    {
        Intent i=new Intent(Detalles.this,Sismosengeneral.class);
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


        Detalles.this.startActivity(i);
    }


    private void openmap2(String...details)
    {
        Intent i=new Intent(Detalles.this,sismosengeneralgoogle.class);
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

        Detalles.this.startActivity(i);
    }



    private void alerta_cenizas(String...details) {
        Intent i=new Intent(Detalles.this,Reportesalertascenizastwo.class);
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


        Detalles.this.startActivity(i);
    }

    private void camara_web(String...details) {
        Intent i=new Intent(Detalles.this,Volcancamara.class);
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


        Detalles.this.startActivity(i);
    }

    private void sismograma_web(String...details) {
        Intent i=new Intent(Detalles.this,Sismogramas.class);
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


        Detalles.this.startActivity(i);
    }

    private void reportes(String...details) {
        Intent i=new Intent(Detalles.this,Reportesdeactividad.class);
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



        Detalles.this.startActivity(i);
    }

/*
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
    */

    @Override
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
        map2.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitud_d, longitud_d), 5));
        map2.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        map2.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map2.getUiSettings().setZoomControlsEnabled(true);
        map2.getUiSettings().setIndoorLevelPickerEnabled(true);
        map2.getUiSettings().setTiltGesturesEnabled(true);
        map2.getUiSettings().setCompassEnabled(true);
        BitmapDescriptor icon;
        LatLng latLng2 = new LatLng(latitud_dato,longitud_dato);

        if (alerta_double <= 1) {
           icon  = BitmapDescriptorFactory.fromResource(R.drawable.volcan_verde_sm);
         }

        else if (alerta_double  > 1 && alerta_double == 2) {
            icon  = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
        }

        else if (alerta_double  > 2 && alerta_double <= 3) {
            icon  = BitmapDescriptorFactory.fromResource(R.drawable.volcan_naranja_sm);
        }

        else if (alerta_double  > 3 && alerta_double <= 4) {
            icon  = BitmapDescriptorFactory.fromResource(R.drawable.volcan_rojo_sm);
        }

        else {
             icon  = BitmapDescriptorFactory.fromResource(R.drawable.volcan_rojo_sm);
        }

        MarkerOptions hy = null;

        map2.addMarker(new MarkerOptions().position(latLng2).title("Volcán : "+nombre_text).snippet("Tipo :"+tipo_text).icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
            map2.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                public void onInfoWindowClick(Marker marker2) {
               //     String de3 = marker2.getSnippet();
                 //   final double de4 = marker2.getPosition().latitude;
                   ///7 String latitudl;
                   // latitudl = String.valueOf(de4);

                    AlertDialog.Builder builder2 = new AlertDialog.Builder(Detalles.this);
                    builder2.setTitle("Epicent");
                    builder2.setMessage("Referencia : " +"ddddddd"  +"\n" +
                            "Fecha local : " + "ccccccc" +"\n" +
                            "Hora local : " + "ccccccc"+ "\n" +
                            "Profundidad : " + "ccccccc"+" km"+ "\n" +
                            "Intensidad : " + "ccccccc"+ "\n" +
                            "Magnitud : " + "ccccccc"+ "\n" +
                            "Ubicación : " + "ccccccc"+ ", " + "ccccccc");
                    builder2.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog2, int which) {
                            dialog2.cancel();
                        }
                    });

                    builder2.setCancelable(true);
                    builder2.create().show();

              }
            });

        map2.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 10));
        map2.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map2.getUiSettings().setZoomControlsEnabled(true);
        map2.getUiSettings().setIndoorLevelPickerEnabled(true);
        map2.getUiSettings().setTiltGesturesEnabled(true);
        map2.getUiSettings().setCompassEnabled(true);

        CircleOptions circleOptions = new CircleOptions()
                .center(latLng2)
                .radius(10000)
                .strokeColor(0x85dce775)
                .fillColor(0x55dce775)
                .strokeWidth(3);
        //map2.addCircle(circleOptions);


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
                map2.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitud_dato, longitud_dato), 10));
            }
        });

        loadMarker();
    }




  /*
    public ArrayList<erupciones> erupcionesx() {
        FirebaseDatabase.getInstance();
        mFirebaseDatabase2.keepSynced(true);
        mFirebaseDatabase2.orderByKey().addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                erupciones objetoerupcion = dataSnapshot.getValue(erupciones.class);
                objetoerupciones.add(objetoerupcion);
                int list_cell_size=162;
                ViewGroup.LayoutParams list;
                list = erupciones.getLayoutParams();
                list.height = list_cell_size*objetoerupciones.size();  // response is list items size
                erupciones.setLayoutParams(list);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                erupciones objetoerupcion= dataSnapshot.getValue(erupciones.class);
                objetoerupciones.add(objetoerupcion);
                int list_cell_size=162;
                ViewGroup.LayoutParams list;
                list = erupciones.getLayoutParams();
                list.height = list_cell_size*objetoerupciones.size();  // response is list items size
                erupciones.setLayoutParams(list);
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

        mFirebaseDatabase2.keepSynced(true);
        return objetoerupciones;
    }

*/

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
  /*
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
*/

    public void loadMarker(){
//        final   DatabaseReference locais = database.getReference("volcanes").child(codigo).child("sismos");
        final DatabaseReference locais = database.getReference("actividadvolcanica").child("messages");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String outputDate = simpleDateFormat.format(calendar.getTime());

        locais.limitToLast(100).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                //  map.clear();
                for (final DataSnapshot dataSnapshot1 : dataSnapshots) {
                    final movimientossismicos local = dataSnapshot1.getValue(movimientossismicos.class);

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


                    lati = Double.parseDouble(local.getLat());
                    longi = Double.parseDouble(local.getLon());

                    final Double magnitude = Double.parseDouble(magnitud2);


                    final BitmapDescriptor icon;


                    if (magnitude < 4.5){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }

                    else if (magnitude >= 4.5 && magnitude <= 6.0){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }

                    else if (magnitude > 6){
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }

                    else {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcan_amarillo_sm);
                    }


//                   map.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title(local.getMagnitud() + " "+"Profundidad : "+local.getProfundidad()+"  >>>").snippet("Fecha : "+local.getFechautc()+"\n  Hora"+local.getHorautc()).icon(icon).anchor(0.5f, 0.5f).visible(visibilidad));

                    map.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title(local.getMagnitud() + " "+"Profundidad : "+local.getProfundidad()+"  >>>").snippet("Fecha : "+local.getFechautc()+"\n  Hora"+local.getHorautc()).icon(icon).anchor(0.5f, 0.5f));
                    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        public void onInfoWindowClick(Marker marker) {
                            final double de4 = marker.getPosition().latitude;
                            String latitudl;
                            latitudl = String.valueOf(de4);
                            //mFirebaseDatabase = database.getReference("volcanes").child(codigo).child("sismos");

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

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Detalles.this);
                                    builder.setTitle("Sismo volcan : " );
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

    public void openTab() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.azulbackground));
        builder.setShowTitle(true);
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher5));
       // builder.addMenuItem(getString(R.string.menu1), createIntent(R.string.menu1, 1));
      //  builder.addMenuItem(getString(R.string.menu2), createIntent(R.string.menu2, 2));
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher5);
        //  ic_launcherigp  android.R.drawable.ic_menu_add

        builder.setActionButton(icon, getString(R.string.action), createIntent(R.string.action, 3),
                true);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(proyeccionsenamhiurl));
    }

    public void openTabMapa() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(customTabsSession);
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.azulbackground));
        builder.setShowTitle(true);
        builder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher5));
     //   builder.addMenuItem(getString(R.string.menu1), createIntent(R.string.menu1, 1));
     //   builder.addMenuItem(getString(R.string.menu2), createIntent(R.string.menu2, 2));
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher5);
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