package peru.volcanes.volcanesper;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
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
import android.os.Handler;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sismogramas extends FragmentActivity  implements ActivityCompat.OnRequestPermissionsResultCallback{
    String urlcamara;
    String nombre;
    String estadovolcan;
    WebView webView;
    RelativeLayout informacion;
    RelativeLayout convenciones;
    Button mk;
    RelativeLayout menuright;
    Double alerta_double;
    RelativeLayout titulopantalla;
    String magnitud2;
    ImageView estado_volcan;
    TextView text_volcan;
    RelativeLayout titulopantalla_detalles;
    private ProgressDialog pd;
    private TextView tv;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerBlock;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
  //  Toolbar toolbar;
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
    RelativeLayout blocke12;
    TextView texto;




    /*
    String proyecionsenahmi;
    RelativeLayout bloquenombre;
    RelativeLayout bloque62;
    TextView trayectoria_cenizas_text222;
*/
    private ProgressBar spinner;
    String ShowOrHideWebViewInitialUse = "show";
    @SuppressLint({"WrongViewCast", "SetJavaScriptEnabled"})


    String proyecionsenahmi;
    RelativeLayout bloquenombre;
    RelativeLayout bloque62;
    TextView trayectoria_cenizas_text222;
    String proyeccionsenamhiurl;
    RelativeLayout bloque42;
    TextView camara2;
    FrameLayout listado;
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
         getWindow().requestFeature(Window.FEATURE_NO_TITLE);

         setContentView(R.layout.content_sismogramas);
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

       ///  proyecionsenahmi = i.getExtras().getString("PROYECCIONSENAHMI");

       //  listado = (FrameLayout) findViewById(R.id.listado);
        // listado.bringToFront();

       //  texto = (TextView) findViewById(R.id.texto);
      //   texto.setText("Registro sísmico del volcán " +  nombre_text + ", el cual muestra los eventos registrados en tiempo real por las estaciones de vigilancia permanente del IGP.");

         proyeccionsenamhiurl = i.getExtras().getString("PROYECCIONSENAHMI");

         trayectoria_cenizas_text222 = (TextView) findViewById(R.id.trayectoria_cenizas_text222);
         //trayectoria_cenizas_text222.setText("Pronóstico de dispersión");

            /*--------VISUALIZACION DE MENU----------*/

          pronostico_menu2 = (LinearLayout) findViewById(R.id.opciones2);
          opciones72 = (LinearLayout) findViewById(R.id.opciones72);

          pronostico_menu = (LinearLayout) findViewById(R.id.opciones3);
          sismogramas_menu = (LinearLayout) findViewById(R.id.opciones4);
          reportes_menu = (LinearLayout) findViewById(R.id.opciones5);
          alertas_menu = (LinearLayout) findViewById(R.id.opciones6);
          camara_menu = (LinearLayout) findViewById(R.id.opciones7);
          mapasismic_menu = (LinearLayout) findViewById(R.id.opciones8);

           val = String.valueOf(codigo_text);

       int valw = View.VISIBLE;





         menu_config();
        // vertoast();




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
                 Intent intent = new Intent(Sismogramas.this,Contactar.class);
                 startActivity(intent);
             }
         });



         blocke12.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Sismogramas.this,Ultimasnotificaciones.class);
                 startActivity(intent);
             }
         });



         blocke1a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Sismogramas.this,pagedivisor.class);
                 startActivity(intent);
             }
         });
         blocke2a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Sismogramas.this,Informacion.class);
                 startActivity(intent);
             }
         });
         blocke4a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Sismogramas.this,Notificacionesconfig.class);
                 startActivity(intent);
             }
         });
         blocke5a.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Sismogramas.this,Convenciones.class);
                 startActivity(intent);
             }
         });


         blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
         blocke9.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View arg0) {
                 Intent intent = new Intent(Sismogramas.this,Listadoredessociales.class);
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

         Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");


         camara2 = (TextView) findViewById(R.id.camara2);

         camara2.setTypeface(fontAwesomeFont);

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
                         mapasismico,
                         proyeccionsenamhiurl
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
                             mapasismico,
                             proyeccionsenamhiurl);

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


         /*
         webView = (WebView)findViewById(R.id.activity_main_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(sismogramaurls);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
*/




    //    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         titulopantalla = (RelativeLayout) findViewById(R.id.titulopantalla);
         estado_volcan = (ImageView) findViewById(R.id.estado_volcan);
         text_volcan = (TextView) findViewById(R.id.text_volcan);
         mk= (Button) findViewById(R.id.button);
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

        titulopantalla_detalles = (RelativeLayout) findViewById(R.id.titulopantalla_detalles);
        alerta_double =  Double.parseDouble(magnitud2);
        text_volcan.setText("Volcán"+" "+nombre_text);
        if (alerta_double <= 1) {
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
            estado_volcan.setImageResource(R.drawable.volcano_rojo);
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.rojoigp));
        }
        else {
            estado_volcan.setImageResource(R.drawable.volcano_rojo);
            titulopantalla_detalles.setBackgroundColor(getResources().getColor(R.color.rojo_oscuro));
        }

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


         bloque62 = (RelativeLayout) findViewById(R.id.bloque62);
         bloque42 = (RelativeLayout) findViewById(R.id.bloque42);


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



         webView = (WebView)findViewById(R.id.activity_main_webview);
         spinner = (ProgressBar)findViewById(R.id.progressBar1);
         webView.setWebViewClient(new CustomWebViewClient());
         webView.getSettings().setJavaScriptEnabled(true);
         webView.getSettings().setDomStorageEnabled(true);
         webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
         webView.getSettings().setBuiltInZoomControls(true);

         webView.loadUrl(sismogramaurls);



     }


    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                webview.setVisibility(webview.INVISIBLE);
            }
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            ShowOrHideWebViewInitialUse = "hide";
            spinner.setVisibility(View.GONE);
            view.setVisibility(webView.VISIBLE);
            super.onPageFinished(view, url);
        }
    }




    private void openDetailActivity(String...details) {
        Intent i=new Intent(Sismogramas.this,Dispersioncenizas.class);
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
        Sismogramas.this.startActivity(i);
    }

    private void openmap(String...details)
    {
        Intent i=new Intent(Sismogramas.this,Sismosengeneral.class);
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
        Sismogramas.this.startActivity(i);
    }


    private void openmap2(String...details)
    {
        Intent i=new Intent(Sismogramas.this,sismosengeneralgoogle.class);
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
        Sismogramas.this.startActivity(i);
    }

    private void alerta_cenizas(String...details) {
        Intent i=new Intent(Sismogramas.this,Reportesalertascenizastwo.class);
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
        Sismogramas.this.startActivity(i);
    }

    private void camara_web(String...details) {
        Intent i=new Intent(Sismogramas.this,Volcancamara.class);
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
        Sismogramas.this.startActivity(i);
    }

    private void sismograma_web(String...details) {
        Intent i=new Intent(Sismogramas.this,Sismogramas.class);
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
        Sismogramas.this.startActivity(i);
    }

    private void reportes(String...details) {
        Intent i=new Intent(Sismogramas.this,Reportesdeactividad.class);
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
        Sismogramas.this.startActivity(i);
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
      //  builder.addMenuItem(getString(R.string.menu1), createIntent(R.string.menu1, 1));
      //  builder.addMenuItem(getString(R.string.menu2), createIntent(R.string.menu2, 2));
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


    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }
    private Handler handler = new Handler() {
         /*
        public void handleMessage(Message msg) {
            pd.dismiss();
            tv.setText(pi_string);
        }*/
    };



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