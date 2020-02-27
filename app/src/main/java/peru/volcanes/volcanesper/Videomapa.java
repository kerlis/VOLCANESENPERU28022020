package peru.volcanes.volcanesper;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Videomapa extends AppCompatActivity {

    String tipodenotificacion_dat,tipodeevento_dat,fecha_dat,hora_dat,observacicones_dat,simulacro_dat,horautc_dat,volcan_dat, sismogramaurls,codigo_tex,val,quebrada_dat, urlvideo;
    WebView webView;

    VideoView videoView;
    Button  playbutton ;
    Button pausebutton;
    String menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8;
    LinearLayout pronostico_menu2;
    LinearLayout opciones72;
    LinearLayout pronostico_menu;
    LinearLayout sismogramas_menu;
    LinearLayout reportes_menu;
    LinearLayout alertas_menu;
    LinearLayout camara_menu ;
    LinearLayout mapasismic_menu;

    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private CharSequence mDrawerTitle;

    RelativeLayout bloque1, bloque3a, bloque4, bloque5, bloque6,  bloque7, blocke9;
    SlidingDrawer slidingDrawer;

    RelativeLayout blocke1a, blocke2a, blocke4a, blocke5a, blocke6a, blocke12, blocke92;
    ImageView sliderz;
    private CharSequence mTitle;
    private String[] mNavigationDrawerItemTitles;
    private RelativeLayout mDrawerBlock;


    TextView slideup;
    RelativeLayout options;
    DownloadManager descarga;


    private ProgressBar spinner;
    String ShowOrHideWebViewInitialUse = "show";
    @SuppressLint({"WrongViewCast", "SetJavaScriptEnabled"})


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videomapa);

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
                Intent intent = new Intent(Videomapa.this,Contactar.class);
                startActivity(intent);
            }
        });



        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Videomapa.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });



        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Videomapa.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Videomapa.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Videomapa.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Videomapa.this,Convenciones.class);
                startActivity(intent);
            }
        });


        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Videomapa.this,Listadoredessociales.class);
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






        pronostico_menu2 = (LinearLayout) findViewById(R.id.opciones2);
        opciones72 = (LinearLayout) findViewById(R.id.opciones72);

        pronostico_menu = (LinearLayout) findViewById(R.id.opciones3);
        sismogramas_menu = (LinearLayout) findViewById(R.id.opciones4);
        reportes_menu = (LinearLayout) findViewById(R.id.opciones5);
        alertas_menu = (LinearLayout) findViewById(R.id.opciones6);
        camara_menu = (LinearLayout) findViewById(R.id.opciones7);
        mapasismic_menu = (LinearLayout) findViewById(R.id.opciones8);


        Intent i = this.getIntent();
        tipodenotificacion_dat = i.getExtras().getString("TIPODENOTIFICACION");
        tipodeevento_dat = i.getExtras().getString("TIPODEEVENTO");
        fecha_dat = i.getExtras().getString("FECHA");
        hora_dat = i.getExtras().getString("HORA");
        horautc_dat = i.getExtras().getString("HORAUTC");
        observacicones_dat = i.getExtras().getString("OBSERVACIONES");
        simulacro_dat = i.getExtras().getString("SIMULACRO");
        volcan_dat = i.getExtras().getString("VOLCAN");

        quebrada_dat = i.getExtras().getString("QUEBRADA");


        val = String.valueOf(volcan_dat);
        int valw = View.VISIBLE;



        if(quebrada_dat.equals("QUEBRADA VOLCANMAYO")){
            urlvideo = "http://arteypixel.com/ubinas_lahar_volcanmayo.mp4";
        }
        else if(quebrada_dat.equals("QUEBRADA HUAYURAY-PINCHOLLO")){
            urlvideo = "http://arteypixel.com/sabancaya_lahar_pinchollo.mp4";
        }
        else if(quebrada_dat.equals("QUEBRADA EL VOLCÁN")){
            urlvideo = "http://arteypixel.com/huaynaputina_lahar_el_volcan.mp4";
        }




        final VideoView videoView;
        videoView = (VideoView)findViewById(R.id.video);
        videoView.setVideoPath(urlvideo);


        MediaController mediaController = new MediaController(this);
        mediaController.setEnabled(true);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();





        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        videoView.setVideoPath("http://arteypixel.com/lahar_volcanmayo.mp4");

        videoView.start();





        playbutton =  findViewById(R.id.playbutton);
        pausebutton =  findViewById(R.id.pausebutton);

        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        playbutton.setTypeface(fontAwesomeFont);
        pausebutton.setTypeface(fontAwesomeFont);



        playbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             videoView.start();
            }
        });


        /*
        pausebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });

*/


        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descarga = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri =  Uri.parse("http://arteypixel.com/lahar_pinchollo.mp4");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = descarga.enqueue(request);
            }
        });





        menu_config();



/*

        webView = (WebView)findViewById(R.id.activity_main_webview);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        webView.setWebViewClient(new CustomWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webView.getSettings().setBuiltInZoomControls(true);

     webView.loadUrl("http://arteypixel.com/videoquebrada.html");


*/

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







    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name, R.string.app_name);
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
