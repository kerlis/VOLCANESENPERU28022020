package peru.volcanes.volcanesper;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Alertareporteactividad extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView pueblos;
    TextView volcan;
    TextView tipodeevento;
    TextView direccion;
    TextView radio;
    TextView fecha;
    TextView hora;
    TextView observacicones;
    TextView recomendaciones;
    TextView simulacro;
    String pueblos_dat;
    String volcan_dat;
    String tipodeevento_dat;
    String direccion_dat;
    String radio_dat;
    String fecha_dat;
    String hora_dat;
    String observacicones_dat;
    String recomendaciones_dat;
    String simulacro_dat;
    String nobrevolcan;
    String tipodenotificacion_dat;
    String horautc_dat;
    String reporteactividad_dat;
    String fin_dato;
    String inicio_dato;
    String tiempo_dato;
    String volcan_dato;
    String codigo_dato;
    String estado_dato;
    String itemcodigo_dato;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerBlock;
    ImageView sliderz;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    RelativeLayout blocke9;
    RelativeLayout blocke6a;
    TextView visualizar;
    TextView compartirte;
    TextView descargar;
    RelativeLayout compartirfile;
    RelativeLayout visualizaerfile;
    RelativeLayout descargarfile;
    DownloadManager descarga;
    TextView titulo;
    ImageView estado_volcan;
    String coloralerta_dat,analisis_dat,conclusiones_dat;
    TextView analisis,conclusiones,niveldealerta;
    String reportesubscrosubs2,reportesubscrosubs3;
    String dato;
    RelativeLayout blocke12;
    RelativeLayout blocke92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datosalertareporteactividad);

        compartirfile= (RelativeLayout) findViewById(R.id.b6);
        visualizaerfile= (RelativeLayout) findViewById(R.id.bb7);
        descargarfile= (RelativeLayout) findViewById(R.id.b5);
        titulo = (TextView) findViewById(R.id.titulo);


        analisis = (TextView) findViewById(R.id.analisis);
        conclusiones = (TextView) findViewById(R.id.conclusiones);
        niveldealerta = (TextView) findViewById(R.id.niveldealerta);




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
                Intent intent = new Intent(Alertareporteactividad.this,Contactar.class);
                startActivity(intent);
            }
        });



        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertareporteactividad.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });


        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertareporteactividad.this,pagedivisor.class);
                startActivity(intent);

              //  Intent myIntent2 = new Intent(getApplicationContext(), Alertareporteactividad.class);
               // Alertareporteactividad.this.finish(myIntent2);
                Alertareporteactividad.this.finish();

            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertareporteactividad.this,Informacion.class);
                startActivity(intent);
                Alertareporteactividad.this.finish();
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertareporteactividad.this,Notificacionesconfig.class);
                startActivity(intent);
                Alertareporteactividad.this.finish();

            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertareporteactividad.this,Convenciones.class);
                startActivity(intent);
                Alertareporteactividad.this.finish();

            }
        });


        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertareporteactividad.this,Listadoredessociales.class);
                startActivity(intent);
                Alertareporteactividad.this.finish();

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

        volcan = (TextView) findViewById(R.id.volcan);
        //tipodeevento = (TextView) findViewById(R.id.tipodeevento);
        fecha = (TextView) findViewById(R.id.fecha);
        hora = (TextView) findViewById(R.id.hora);
        simulacro = (TextView) findViewById(R.id.simulacro);


        Intent i=this.getIntent();
        dato = i.getExtras().getString("NOTIFICACIONDATA");

        tipodenotificacion_dat = dato.split("&")[0];
        volcan_dat = dato.split("&")[1];
        //tipodeevento_dat = i.getExtras().getString("TIPODEEVENTO");
        fecha_dat = dato.split("&")[2];
        hora_dat = dato.split("&")[3];
        simulacro_dat = dato.split("&")[4];
        horautc_dat = dato.split("&")[5];
        reporteactividad_dat = dato.split("&")[6];

        coloralerta_dat = dato.split("&")[7];
        analisis_dat = dato.split("&")[8];
        conclusiones_dat = dato.split("&")[9];

        analisis.setText("Periodo de análisis: " + analisis_dat);
        conclusiones.setText(conclusiones_dat);

        if (coloralerta_dat.equals("verde")){
            niveldealerta.setText(" Nivel de alerta: VERDE");
             niveldealerta.setTextColor(getResources().getColor(R.color.verdeigptext));

        }
        else if(coloralerta_dat.equals("naranja")){
            niveldealerta.setText(" Nivel de alerta: NARANJA");
            niveldealerta.setTextColor(getResources().getColor(R.color.naranjaigptext));

        }
        else if(coloralerta_dat.equals("rojo")){
            niveldealerta.setText(" Nivel de alerta: ROJA");
            niveldealerta.setTextColor(getResources().getColor(R.color.rojoigptext2));

        }
        else if(coloralerta_dat.equals("amarillo")){
            niveldealerta.setText(" Nivel de alerta: AMARILLA");
            niveldealerta.setTextColor(getResources().getColor(R.color.amarilloigptext));

        }
        else{
            niveldealerta.setTextColor(getResources().getColor(R.color.verdeigptext));

        }

        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        visualizar = (TextView) findViewById(R.id.visualizar);
        compartirte = (TextView) findViewById(R.id.compartir);
        descargar = (TextView) findViewById(R.id.descargar);
        visualizar.setTypeface(fontAwesomeFont);
        compartirte.setTypeface(fontAwesomeFont);
        descargar.setTypeface(fontAwesomeFont);
        String hora_subs = hora_dat.substring(0, hora_dat.length() - 1);
        String hora_subs2 = hora_subs.substring(1);

        String fecha_subs = fecha_dat.substring(0, 10);
        String va =  String.valueOf(volcan_dat);

        estado_volcan  = (ImageView) findViewById(R.id.estado_volcan);

        if(va.equals("1493157379002")){
            nobrevolcan = "Volcán Ubinas";
            volcan.setText("Volcán Ubinas");
            estado_volcan.setImageResource(R.drawable.ubinas_circle);
        }
        else if(va.equals("1493157381161")) {
            nobrevolcan = "Volcán Sabancaya";
            volcan.setText("Volcán Sabancaya");
            estado_volcan.setImageResource(R.drawable.sabancaya_circle);
        }

        else if(va.equals("1506454510537")) {
            nobrevolcan = "Volcán Sara Sara";
            volcan.setText("Volcán Sara Sara");
            estado_volcan.setImageResource(R.drawable.sarasara_circle);
        }
        else if(va.equals("1506455245814")) {
            nobrevolcan = "Volcán Cerro Auquihuato";
            volcan.setText("Volcán Cerro Auquihuato");
            estado_volcan.setImageResource(R.drawable.cerro_auquihuato_circle);
        }
        else if(va.equals("1506455248101")) {
            volcan.setText("Volcán Andahua");
            nobrevolcan = "Volcán Andahua";
            estado_volcan.setImageResource(R.drawable.andahua_circle);
        }

        else if(va.equals("1506455249661")) {
            nobrevolcan = "Volcán Coropuna";
            volcan.setText("Volcán Coropuna");
            estado_volcan.setImageResource(R.drawable.coropuna_circle);
        }

        else if(va.equals("1506455251429")) {
            nobrevolcan = "Volcán Huambo";
            volcan.setText("Volcán Huambo");
            estado_volcan.setImageResource(R.drawable.huambo_circle);
        }

        else if(va.equals("1506455253382")) {
            nobrevolcan = "Volcán Chachani";
            volcan.setText("Volcán Chachani");
            estado_volcan.setImageResource(R.drawable.chachani_circle);
        }

        else if(va.equals("1506455254838")) {
            nobrevolcan = "Volcán Tutupaca";
            volcan.setText("Volcán Tutupaca");
            estado_volcan.setImageResource(R.drawable.tutupaca_circle);
        }

        else if(va.equals("1506455256229")) {
            nobrevolcan = "Volcán Huaynaputina";
            volcan.setText("Volcán Huaynaputina");
            estado_volcan.setImageResource(R.drawable.huaynaputina_circle);
        }

        else if(va.equals("1506455257749")) {
            nobrevolcan = "Volcán Ticsani";
            volcan.setText("Volcán Ticsani");
            estado_volcan.setImageResource(R.drawable.ticsani_circle);
        }

        else if(va.equals("1506455257753")) {
            nobrevolcan = "Volcán Casiri";
            volcan.setText("Volcán Casiri");
            estado_volcan.setImageResource(R.drawable.casiri_circle);
        }

        else if(va.equals("1506455257755")) {
            nobrevolcan = "Volcán Cerros Purupuruni";
            volcan.setText("Volcán Cerros Purupuruni");
            estado_volcan.setImageResource(R.drawable.cerros_purupuruni_circle);

        }

        else if(va.equals("1506455257757")) {
            nobrevolcan = "Volcán Quimsachata";
            volcan.setText("Volcán Quimsachata");
            estado_volcan.setImageResource(R.drawable.quimsachata_circle);

        }
        else if(va.equals("1506455259126")) {
            nobrevolcan = "Volcán Yucamane";
            volcan.setText("Volcán Yucamane");
            estado_volcan.setImageResource(R.drawable.yucamane_circle);

        }
        else if(va.equals("1506455259128")) {
            nobrevolcan = "Volcán Misti";
            volcan.setText("Volcán Misti");
            estado_volcan.setImageResource(R.drawable.misti_circle);

        }
        else {
            nobrevolcan = "Volcán";
        }



      //  Toast.makeText(Alertareporteactividad.this,reporteactividad_dat , Toast.LENGTH_LONG).show();



        if (tipodenotificacion_dat.equals("n02")){
            titulo.setText("Reporte Extraordinario de Actividad");
        }
        else if(tipodenotificacion_dat.equals("n04")){
            titulo.setText("Reporte Ordinario de Actividad ");
        }
        else{
            titulo.setText("Reporte Extraordinario de Actividad");

        }


        fecha.setText("Fecha: " + fecha_subs);
        hora.setText("Hora local: " + hora_dat + "/ Hora UTC:" + horautc_dat);
        volcan.setText(nobrevolcan);
        simulacro.setText("Simulacro: " + simulacro_dat);
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
   /* */
        visualizaerfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veralerta(reporteactividad_dat);
            }
        });

        descargarfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String urlpdf = reporteactividad_dat.substring(1);

                descarga = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri =  Uri.parse(reporteactividad_dat);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = descarga.enqueue(request);
            }
        });



        compartirfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                String shareBody =   "Reporte de actividad volcanica - "   + nobrevolcan + "\n" +
                       // "Tipo de evento: " + tipodeevento_subs2 + "\n" +
                        "Fecha: " +  fecha_dat + "\n" +
                        "Hora local: " + hora_dat + " / Hora UTC: " + horautc_dat + "\n" +
                        "URL reporte PDF: " + reporteactividad_dat + "\n" +
                        "Simulacro: " + simulacro_dat + "\n";
                String shareSub =   "Reporte de actividad volcanica - "   + nobrevolcan + "\n" +
                       // "Tipo de evento: " + tipodeevento_subs2 + "\n" +
                        "Fecha: " +  fecha_dat + "\n" +
                        "Hora local: " + hora_dat + " / Hora UTC: " + horautc_dat + "\n" +
                        "URL reporte PDF: " + reporteactividad_dat + "\n" +
                        "Simulacro: " + simulacro_dat + "\n";
                //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareSub);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
    }

    private void veralerta(String...details) {
        Intent i=new Intent(this,Pdfviewreportedeactividad.class);
        i.putExtra("PDFREPORTE",details[0]);
        startActivity(i);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}