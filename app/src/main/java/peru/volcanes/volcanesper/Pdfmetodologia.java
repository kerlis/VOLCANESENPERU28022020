package peru.volcanes.volcanesper;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Pdfmetodologia extends FragmentActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    TextView titulo_trayectorias;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    RelativeLayout blocke9;
    RelativeLayout blocke6a;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ImageView sliderz;
    private CharSequence mTitle;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    private RelativeLayout mDrawerBlock;
    private CharSequence mDrawerTitle;
    private ProgressBar spinner;

    private static final String TAG = Pdfmetodologia.class.getSimpleName();
    RelativeLayout blocke12;
    PDFView pdfView;
    private static final String CHROME_PACKAGE = "com.android.chrome";
    private CustomTabsServiceConnection ctConnection;
    private CustomTabsSession customTabsSession;
    RelativeLayout blocke92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.content_pdfmetodologia);
        titulo_trayectorias = (TextView) findViewById(R.id.titulo_trayectorias);
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
                Intent intent = new Intent(Pdfmetodologia.this,Contactar.class);
                startActivity(intent);
            }
        });




        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Pdfmetodologia.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });



        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Pdfmetodologia.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Pdfmetodologia.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Pdfmetodologia.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Pdfmetodologia.this,Convenciones.class);
                startActivity(intent);
            }
        });

        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Pdfmetodologia.this,Listadoredessociales.class);
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

        /// String de = "https://stackoverflow.com/questions/4623508/how-to-set-the-font-style-to-bold-italic-and-underlined-in-an-android-textview";
        Intent i = this.getIntent();
        String valor = i.getExtras().getString("ENLACE");
        String  valor2 = i.getExtras().getString("ENLACE");

        String  valor3 = "http://ovs.igp.gob.pe/sites/ovs.igp.gob.pe/files/pdf/Investigacion/otros/evaluacion_del_riesgo_volcanico_en_el_peru.pdf";

        pdfView = (PDFView) findViewById(R.id.pdfView);
        new RetrevePDFStream().execute(valor3);



      //  webView = (WebView)findViewById(R.id.vistaweb);
        //spinner = (ProgressBar)findViewById(R.id.progressBar1);
      ////  webView.setWebViewClient(new CustomWebViewClient());
      //  webView.getSettings().setJavaScriptEnabled(true);
       // webView.getSettings().setDomStorageEnabled(true);
      //  webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

      //  webView.loadUrl(valor);
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

    private PendingIntent createIntent(int text, int number) {
        Intent intent = new Intent(this, CustomTabsBroadcastReceiver.class);
        intent.putExtra("text", getString(text));
        return PendingIntent.getBroadcast(this, number, intent, 0);
    }



    class RetrevePDFStream extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                if (urlConnection.getResponseCode() == 200){
                    inputStream =  new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            catch(IOException e){
                return null;
            }
            return  inputStream;
        }
        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }





    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        mDrawerToggle.syncState();
    }
}
