package peru.volcanes.volcanesper;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import peru.volcanes.volcanesper.m_model.volcanes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class volcanesperu extends AppCompatActivity implements OnMapReadyCallback,LocationListener,ActivityCompat.OnRequestPermissionsResultCallback,GoogleMap.OnInfoWindowClickListener {
    RelativeLayout titulopantalla_detalles;
    SupportMapFragment sMapFragment;
    Button mk;
    String magnitud2, magnitud3;
    private LocationManager lm;
    private Location location;
    double longitude = -15.4938976;
    double latitude = -71.3804336;
    private FirebaseDatabase database;
    private static final int REQUEST_PERMISSION = 1;
    private GoogleMap map;
    Button satelite, terreno, localizacion;
    Double magnitude;
    String refe, magni;
    String volcanestado;
    private static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION};
    int mWidthScreen;
    int mHeightScreen;
    int tamano;
    FrameLayout contenedor;
    RelativeLayout tituloscolor;
    private DatabaseReference mFirebaseDatabase;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private RelativeLayout mDrawerBlock;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a,blocke9;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ImageView sliderz;
    RelativeLayout blocke6a;
    RelativeLayout blocke12;
    RelativeLayout blocke92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volcanesperu);

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
                Intent intent = new Intent(volcanesperu.this,Contactar.class);
                startActivity(intent);
            }
        });


        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanesperu.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });


        blocke1a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanesperu.this,pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanesperu.this,Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanesperu.this,Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanesperu.this,Convenciones.class);
                startActivity(intent);
            }
        });


        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(volcanesperu.this,Listadoredessociales.class);
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

        database = FirebaseDatabase.getInstance();

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

        TextView nivel = (TextView) findViewById(R.id.niveldealerta);
        nivel.setText("Nivel"+"\n"+"de alerta");

        contenedor = (FrameLayout) findViewById(R.id.map);
        titulopantalla_detalles = (RelativeLayout) findViewById(R.id.titulopantalla_detalles);

        initMaps();
        mk = (Button) findViewById(R.id.button);
        mk = (Button) findViewById(R.id.button);
    }

    public void initMaps() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions();
        } else {
            lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 60000, this);
        }
    }

    public void onMapReady(final GoogleMap map) {
        this.map = map;
        if (lm != null) {
            if (location != null) {

            }
        }

        latitude = -15.4938976;
        longitude = -71.3804336;

        LatLng latLng = new LatLng(latitude,longitude);
        if (latLng != null){
            map.clear();
        }
        map.addMarker(new MarkerOptions().position(latLng).title("Epicentro").anchor(0.5f, 0.5f)).showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.animateCamera(CameraUpdateFactory.zoomTo(6), 1500, null);
        map.getUiSettings().setZoomControlsEnabled(true);

        map.getUiSettings().setIndoorLevelPickerEnabled(true);
        map.getUiSettings().setTiltGesturesEnabled(true);
        map.getUiSettings().setCompassEnabled(true);

        satelite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        terreno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            }
        });

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
                    Toast.makeText(this, "Autorizado", Toast.LENGTH_SHORT).show();
                    initMaps();

                } else {
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSION);
        }
    }

    public void loadMarker() {
        FirebaseDatabase.getInstance();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mFirebaseDatabase = database.getReference("actividadvolcanica").child("volcanes");
        mFirebaseDatabase.keepSynced(true);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String outputDate = simpleDateFormat.format(calendar.getTime());
        mFirebaseDatabase.limitToLast(100).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                //  map.clear();
                for (final DataSnapshot dataSnapshot1 : dataSnapshots) {
                    final volcanes local = dataSnapshot1.getValue(volcanes.class);

                    final Double lati, longi;
                    refe = local.getAltura();
                    magnitud3 = local.getEstado();
                    magni = magnitud3;
                    double w;
                    try {
                        w = new Double(magni);
                    } catch (NumberFormatException e) {
                        w = 0;
                    }
                    if (w == 0) {
                        magnitud2 = "0";
                    } else {
                        magnitud2 = magni;
                    }

                    lati = Double.parseDouble(local.getLatitud());
                    longi = Double.parseDouble(local.getLongitud());

                    magnitude = Double.parseDouble(magnitud2);

                    final BitmapDescriptor icon;
                    if (magnitude <= 1) {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_verde);
                    }
                    else if (magnitude > 1 && magnitude == 2) {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_amarillo);
                    }
                    else if (magnitude > 2 && magnitude == 3) {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_naranja);
                    }
                    else if (magnitude > 3 && magnitude == 4) {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_rojo);
                    }
                    else {
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.volcano_naranja);
                    }

                    map.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title(local.getNombre() + " "+"Profundidad : "+local.getNombre()+"  >>>").snippet(local.getCodigo()+","+local.getEstado()+","+local.getActividadreciente()+","+local.getAltura()+","+local.getTipo()+","+local.getNombre()+","+local.getNombre()).icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
                    map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {
                            String de3 = marker.getSnippet();

                         final String codigosnip = de3.split(",")[0];
                            String estadosnip = de3.split(",")[1];
                            String actividadrecientedato = de3.split(",")[2];
                            String alturadato = de3.split(",")[3];
                            String tipodato = de3.split(",")[4];
                            String nombredato = de3.split(",")[5];
                            String nombredato2 = de3.split(",")[6];

                            View v = getLayoutInflater().inflate(R.layout.customwindow, null);
                            LatLng latLng = marker.getPosition();
                            TextView nombre = (TextView) v.findViewById(R.id.nombre);
                            TextView altura = (TextView) v.findViewById(R.id.altura);
                            TextView tipo = (TextView) v.findViewById(R.id.tipo);
                            TextView kilometros = (TextView) v.findViewById(R.id.kilometros);

                            ImageView imagen = (ImageView) v.findViewById(R.id.imagen);
                            tituloscolor = (RelativeLayout) v.findViewById(R.id.titulo);

                            double y;
                            try {
                                y = new Integer(estadosnip);
                            } catch (NumberFormatException e) {
                                y = 0;
                            }

                            if (y == 0){
                                volcanestado = "0";
                            }
                            else
                            {
                                volcanestado = estadosnip;
                            }

                            final Integer volcanestados = Integer.parseInt(volcanestado);
                            if (volcanestados <= 1) {
                                imagen.setImageResource(R.drawable.volcano_verde);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.verdeigp));
                            } else if (volcanestados > 1 && volcanestados == 2) {
                                imagen.setImageResource(R.drawable.volcano_amarillo);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
                            } else if (volcanestados > 2 && volcanestados == 3) {
                                imagen.setImageResource(R.drawable.volcano_naranja);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.naranjas));
                            } else if (volcanestados > 3 && volcanestados == 4) {
                                imagen.setImageResource(R.drawable.volcano_rojo);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.rojoigp));
                            } else {
                                imagen.setImageResource(R.drawable.volcano_naranja);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.naranjas));
                            }

                            nombre.setText("Volcán : "+nombredato2);
                            altura.setText("Tipo : "+alturadato);
                            tipo.setText("Altura : "+tipodato);
                            kilometros.setText("Actividad : "+tipodato);

                            nombre.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    detalles_volcan(codigosnip);
                                }
                            });
                            return v;
                        }

                        private void detalles_volcan(String...details)
                        {
                            Intent i=new Intent(volcanesperu.this,volcanmenu.class);
                            i.putExtra("ACTIVIDAD_RECIENTE",details[0]);
                            volcanesperu.this.startActivity(i);
                        }
                    });

                map.setOnInfoWindowClickListener(volcanesperu.this);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        String marka = marker.getSnippet();

        final String codigosnip = marka.split(",")[0];
        String estadosnip = marka.split(",")[1];
        String actividadrecientedato = marka.split(",")[2];
        String alturadato = marka.split(",")[3];
        String tipodato = marka.split(",")[4];
        String nombredato = marka.split(",")[5];
        String nombredato2 = marka.split(",")[6];

        Intent i=new Intent(volcanesperu.this,volcanmenu.class);
        i.putExtra("ACTIVIDAD_RECIENTE",codigosnip);
        volcanesperu.this.startActivity(i);
        Toast.makeText(this, "Info window clicked : "+codigosnip,
                Toast.LENGTH_SHORT).show();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
             //   fragment = new ConnectFragment();
                break;
            case 1:
               // fragment = new FixturesFragment();
                break;
            case 2:
                //fragment = new TableFragment();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

}