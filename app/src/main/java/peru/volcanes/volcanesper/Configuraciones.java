package peru.volcanes.volcanesper;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Configuraciones extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    String Message;
    String v;
    String elvalor;
    String elvalor2;
    int mWidthScreen;
    int mHeightScreen;
    int tamano;
    RadioButton notificar;
    RadioButton nonotificar;
    RadioButton vibrar;
    RadioButton sonido;
    TextView dato_version_numero;
    Button mk;
    String ko,ajustes,tipo;
    Integer r,s;
    String elvalor_defecto = "a";
    String elvalor2_defecto ="bb";
    String Message3_defecto;
    String Message4_defecto;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    RelativeLayout blocke9;
    RelativeLayout menuright;
    RelativeLayout blocke6a;
    RelativeLayout blocke12;

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerBlock;

    ImageView sliderz;
    TextView igpdireccion,ovsdireccion;

    TextView igpweb;
    TextView ovsweb;
    RelativeLayout blocke92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_configuraciones);
        igpdireccion = (TextView) findViewById(R.id.igpdireccion);
        ovsdireccion = (TextView) findViewById(R.id.ovsdireccion);
        igpdireccion.setText("Calle Badajoz N° 169 Urb. Mayorazgo IV Etapa,Ate,\n Lima 15012 - Perú\n" +
                             "+51 1 3172300");
        ovsdireccion.setText("Urb. La Marina B-19, Cayma, \n Arequipa 04017 - Perú\n" +
                             "+51 54 251373");
        igpweb = (TextView) findViewById(R.id.igpweb);
        ovsweb = (TextView) findViewById(R.id.ovsweb);
        igpweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://portal.igp.gob.pe/"));
                startActivity(intent);
            }
        });
        ovsweb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://ovs.igp.gob.pe/instituto-geofisico-peru"));
                startActivity(intent);
            }
        });

        notificar = (RadioButton) findViewById(R.id.notificar);
        nonotificar = (RadioButton) findViewById(R.id.nonotificar);
        vibrar = (RadioButton) findViewById(R.id.vibrar);
        sonido = (RadioButton) findViewById(R.id.sonido);
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
                Intent intent = new Intent(Configuraciones.this,Contactar.class);
                startActivity(intent);
            }
        });



        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });


        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Convenciones.class);
                startActivity(intent);
            }
        });


        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Configuraciones.this,Listadoredessociales.class);
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


        /*
        RelativeLayout videoView = (RelativeLayout) findViewById(R.id.videoView1);
        Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mWidthScreen = display.getWidth();
        mHeightScreen = display.getHeight();

        switch (getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                tamano = (mHeightScreen - 305);
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                tamano = (mHeightScreen - 380);
                break;
            case DisplayMetrics.DENSITY_TV:
                tamano = (mHeightScreen - 479);
                break;
            case DisplayMetrics.DENSITY_HIGH:
                tamano = (mHeightScreen - 530);
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                tamano = (mHeightScreen - 680);
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                tamano = (mHeightScreen - 980);
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                tamano = (mHeightScreen - 1280);
                break;
        }
        videoView.getLayoutParams().height = tamano;
        videoView.requestLayout();
        */



        final PackageManager pm = getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo( this.getPackageName(), 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        String valor_version_code;
        valor_version_code = String.valueOf(versionCode).toString();
      //  dato_version_numero = (TextView) findViewById(R.id.datoversionnumero);
       // dato_version_numero.setText(valor_version_code);
        try {
            FileInputStream fileInputStream_read =  openFileInput("datos_configuracion");
            InputStreamReader inputStreamReader_read =  new InputStreamReader(fileInputStream_read);
            BufferedReader bufferedReader_read = new BufferedReader(inputStreamReader_read);
            StringBuffer stringBuffer_read =  new StringBuffer();
            try {
                while ((Message = bufferedReader_read.readLine())!=null){
                    stringBuffer_read.append(Message);
                }

                ko = stringBuffer_read.toString();
                String ajustes_read = ko.split(",")[0];
                String tipo_read = ko.split(",")[1];

                int leido1 = ajustes_read.length();
                int leido2 = tipo_read.length();


                if(leido1 == 1){
                    notificar.setChecked(true);
                    nonotificar.setChecked(false);
                    elvalor ="a";
                }
                else{
                    notificar.setChecked(false);
                    nonotificar.setChecked(true);
                    elvalor ="bb";
                }

                if(leido2 == 1){
                    vibrar.setChecked(true);
                    sonido.setChecked(false);
                    elvalor2 ="a";
                }
                else{
                    vibrar.setChecked(false);
                    sonido.setChecked(true);
                    elvalor2 ="bb";
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RadioGroup rg = (RadioGroup) findViewById(R.id.grupoopcion1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.notificar:
                        elvalor = "a";
                        break;
                    case R.id.nonotificar:
                        elvalor = "bb";
                        break;

                }
            }
        });

        RadioGroup rg2 = (RadioGroup) findViewById(R.id.grupoopcion2);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.vibrar:
                        elvalor2 = "a";
                        break;
                    case R.id.sonido:
                        elvalor2 = "bb";
                        break;
                }
            }
        });

}

    public void valores_por_defecto(View V){
        try {
            FileInputStream fileInputStream =  openFileInput("datos_configuracion");
            InputStreamReader inputStreamReader =  new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =  new StringBuffer();
            try {
                while ((Message = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(Message);
                }
                ko = stringBuffer.toString();
                StringTokenizer st = new StringTokenizer(stringBuffer.toString(), ",");
                ajustes = st.nextToken();
                tipo = st.nextToken();
                r = ajustes.length();
                s = tipo.length();
                if (r == 1){
                     Message3_defecto = elvalor_defecto+",";
                     Message4_defecto = elvalor2_defecto+",";
                    String file_name = "datos_configuracion";
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
                        fileOutputStream.write(Message3_defecto.getBytes());
                        fileOutputStream.write(Message4_defecto.getBytes());
                        fileOutputStream.close();
                        Toast.makeText(getApplicationContext(),"Configurado", Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    String ff = "";

                    Message3_defecto = elvalor_defecto+",";
                    Message4_defecto = elvalor2_defecto+",";

                    String file_name = "datos_configuracion";
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
                        fileOutputStream.write(Message3_defecto.getBytes());
                        fileOutputStream.write(Message4_defecto.getBytes());
                        fileOutputStream.close();
                        Toast.makeText(getApplicationContext(),"Configurado", Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //************************grabar valores por defecto
    }

    public void write_mesage(View view)
    {
        String Message3 = elvalor+",";
        String Message4 = elvalor2+",";
        String file_name = "datos_configuracion";
        try {
            FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
            fileOutputStream.write(Message3.getBytes());
            fileOutputStream.write(Message4.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(),"Configurado", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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