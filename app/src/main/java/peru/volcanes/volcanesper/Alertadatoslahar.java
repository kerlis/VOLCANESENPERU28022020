package peru.volcanes.volcanesper;
import android.content.Intent;
import android.graphics.Typeface;
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

public class Alertadatoslahar extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView volcan;
    TextView tipodeevento;
    TextView fecha;
    TextView hora;
    TextView observacicones;
    TextView simulacro;
    String volcan_dat;
    String tipodeevento_dat;
    String fecha_dat;
    String hora_dat;
    String observacicones_dat;
    String simulacro_dat;
    String horautc_dat;
    String nobrevolcan;
    String tipodenotificacion_dat;
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
    TextView compartir;
    RelativeLayout compartirfile;
    ImageView estado_volcan;
    String dato;
    RelativeLayout blocke12;
    RelativeLayout blocke92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datosalertalahar);
        compartirfile= (RelativeLayout) findViewById(R.id.b6);
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
                Intent intent = new Intent(Alertadatoslahar.this,Contactar.class);
                startActivity(intent);
            }
        });


        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertadatoslahar.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });

        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertadatoslahar.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertadatoslahar.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertadatoslahar.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertadatoslahar.this,Convenciones.class);
                startActivity(intent);
            }
        });

        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);

        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Alertadatoslahar.this,Listadoredessociales.class);
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

        estado_volcan = (ImageView) findViewById(R.id.estado_volcan);

        volcan = (TextView) findViewById(R.id.volcan);
        tipodeevento = (TextView) findViewById(R.id.tipodeevento);
        fecha = (TextView) findViewById(R.id.fecha);
        hora = (TextView) findViewById(R.id.hora);
        observacicones = (TextView) findViewById(R.id.observacicones);
        simulacro = (TextView) findViewById(R.id.simulacro);


        Intent i=this.getIntent();
        dato = i.getExtras().getString("NOTIFICACIONDATA");
        observacicones.setText(dato);

        tipodenotificacion_dat = dato.split("&")[0];
        volcan_dat = dato.split("&")[1];
        tipodeevento_dat = dato.split("&")[2];
        fecha_dat = dato.split("&")[3];
        hora_dat = dato.split("&")[4];
        horautc_dat = dato.split("&")[5];
        observacicones_dat = dato.split("&")[6];
        simulacro_dat = dato.split("&")[7];


        compartir = (TextView) findViewById(R.id.compartir);
        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        compartir.setTypeface(fontAwesomeFont);


        String va =  String.valueOf(volcan_dat);

        String horautc_subs =  horautc_dat.substring(1);

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
        else if(volcan_dat.equals("1506455248101")) {
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
        else if(volcan_dat.equals("1506455257755")) {
            nobrevolcan = "Volcán Cerros Purupuruni";
            volcan.setText("Volcán Cerros Purupuruni");
            estado_volcan.setImageResource(R.drawable.cerros_purupuruni_circle);

        }
        else if(volcan_dat.equals("1506455257757")) {
            nobrevolcan = "Volcán Quimsachata";
            volcan.setText("Volcán Quimsachata");
            estado_volcan.setImageResource(R.drawable.quimsachata_circle);

        }
        else if(volcan_dat.equals("1506455259126")) {
            nobrevolcan = "Volcán Yucamane";
            volcan.setText("Volcán Yucamane");
            estado_volcan.setImageResource(R.drawable.yucamane_circle);

        }
        else if(volcan_dat.equals("1506455259128")) {
            nobrevolcan = "Volcán Misti";
            volcan.setText("Volcán Misti");
            estado_volcan.setImageResource(R.drawable.misti_circle);

        }
        else {
            nobrevolcan = "Volcán";
        }


        String asubstring = fecha_dat.substring(0, 10);
        tipodeevento.setText("Tipo de evento: " + tipodeevento_dat);
        fecha.setText("Fecha: " + asubstring);
        hora.setText("Hora local: " + hora_dat + " / Hora UTC:" + horautc_dat);
        observacicones.setText(observacicones_dat);
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

        compartirfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String asubstring = fecha_dat.substring(0, 10);
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Alerta de Lahar - "  + nobrevolcan + "\n" +
                        "Tipo de evento: " + tipodeevento_dat + "\n" +
                        "Fecha: " +  asubstring + "\n" +
                        "Hora local: " + hora_dat + " / Hora UTC: " + horautc_dat + "\n" +
                        "Observaciones: " + observacicones_dat + "\n" +
                        "Simulacro: " + simulacro_dat + "\n";
                String shareSub = "Alerta de Lahar - "   + nobrevolcan + "\n" +
                        "Tipo de evento: " + tipodeevento_dat + "\n" +
                        "Fecha: " +  asubstring + "\n" +
                        "Hora local: " + hora_dat + " / Hora UTC: " + horautc_dat + "\n" +
                        "Observaciones: " + observacicones_dat + "\n" +
                        "Simulacro: " + simulacro_dat + "\n";
                //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareSub);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
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
}