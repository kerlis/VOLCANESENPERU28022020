package peru.volcanes.volcanesper;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
//import android.widget.ProgressBar;
import android.widget.ProgressBar;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.kml.KmlLayer;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FragmentWithOneImage extends Fragment implements OnMapReadyCallback, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnInfoWindowClickListener {
    private static final String TAG = FragmentWithOneImage.class.getSimpleName();
    LayoutInflater inflater = null;
    private String title;
    private int image;
    RelativeLayout titulopantalla_detalles;
    SupportMapFragment sMapFragment;
    Button mk;
    String magnitud2, magnitud3;
    private LocationManager lm;
    private Location location;
    double longitude = -16.31537405101501;
    double latitude = -71.94828379488575;
    private FirebaseDatabase database;
    private static final int REQUEST_PERMISSION = 1;
    private GoogleMap map;
    Button satelite, terreno, localizacion;
    Double magnitude;
    String refe, magni;
    String volcanestado;
    int mWidthScreen;
    int mHeightScreen;
    int tamano;
    FrameLayout contenedor;
    RelativeLayout tituloscolor;
    String codigosnip;
    private DatabaseReference mFirebaseDatabase;
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
    String fr;
    public Context context;
    TextView markerRating;
    TextView markerRating2;
    String datox;
    String zoomex;
    CameraPosition cameraPosition;
    String zoomex2;
    String zoomex3;
    BitmapDescriptor icon;
    String nombre;
    private ProgressBar spinner;
    String ShowOrHideWebViewInitialUse = "show";
    private static final int SPINNER_WIDTH = 100;
    private static final int SPINNER_HEIGHT = 100;

    public static FragmentWithOneImage newInstance(String title, int resImage) {
        FragmentWithOneImage fragment = new FragmentWithOneImage();
        Bundle args = new Bundle();
        args.putInt("image", resImage);
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getArguments().getInt("image", 0);
        title = getArguments().getString("title");
        //   map.setOnCameraMoveListener(getCameraChangeListener2());
        //   getCameraChangeListener3();




/*
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:27992087142:android:ce3b6448250083d1") // Required for Analytics.
                .setApiKey("AIzaSyADUe90ULnQDuGShD9W23RDP0xmeDc6Mvw") // Required for Auth.
                .setDatabaseUrl("https://myproject.firebaseio.com") // Required for RTDB.
                .build();
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_img, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.txtMain);
        spinner = (ProgressBar)view.findViewById(R.id.progressBar1);
        tvLabel.setText(title);
        database = FirebaseDatabase.getInstance();
        sMapFragment = SupportMapFragment.newInstance();
        FragmentManager sFm = getFragmentManager();
        sFm.beginTransaction().add(R.id.map, sMapFragment).commit();
        sMapFragment.getMapAsync(this);
        satelite = (Button) view.findViewById(R.id.satelite);
        terreno = (Button) view.findViewById(R.id.terreno);
        Typeface fontAwesomeFont = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
        localizacion = (Button) view.findViewById(R.id.localizacion);
        Button datox = (Button) view.findViewById(R.id.localizacion);
        datox.setTypeface(fontAwesomeFont);
        TextView nivel = (TextView) view.findViewById(R.id.niveldealerta);
        nivel.setText("Nivel" + "\n" + "de Alerta" + "\n" + "Volcánica");
        contenedor = (FrameLayout) view.findViewById(R.id.map);
        titulopantalla_detalles = (RelativeLayout) view.findViewById(R.id.titulopantalla_detalles);
        initMaps();
        mk = (Button) view.findViewById(R.id.button);
        mk = (Button) view.findViewById(R.id.button);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgMain);
        imageView.setImageResource(image);
        return view;
    }

    public void initMaps() {
        /*
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        } else {
            lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 60000, this);
        }
        //  map.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity().getApplication(), R.raw.style_json));
        */
    }

    public void onMapReady(final GoogleMap map) {
        // CameraPosition posicion = map.getCameraPosition();
        MapStyleOptions style = MapStyleOptions.loadRawResourceStyle(
                getContext(), R.raw.style_json);
        map.setMapStyle(style);

        this.map = map;
        if (lm != null) {
            if (location != null) {
            }
        }
        latitude = -16.31537405101501;
        longitude = -71.94828379488575;
        LatLng latLng = new LatLng(latitude, longitude);
        if (latLng != null) {
            map.clear();
        }

        //  map.setOnCameraMoveListener(this);

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(getActivity().getApplication(), R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        // Position the map's camera near Sydney, Australia.


        //map.addMarker(new MarkerOptions().position(latLng).title("Epicentro").anchor(0.5f, 0.5f)).showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7));
        //  map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.animateCamera(CameraUpdateFactory.zoomTo(7), 1500, null);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setIndoorLevelPickerEnabled(true);
        map.getUiSettings().setTiltGesturesEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        //KmlLayer layer = new KmlLayer(map,R.raw.nom);
        satelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                selectItem1();
            }
        });
        terreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                selectItem2();
            }
        });
        localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 7));
            }
        });

/*
        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions rectOptions = new PolylineOptions()


                .add(new LatLng(-82.660249,7.312399))
                .add(new LatLng(-82.660249,7.064369))
                .add(new LatLng(-82.660249,6.508306))
                .add(new LatLng(-82.660249,5.779232))
                .add(new LatLng(-82.660249,5.000161))
                .add(new LatLng(-82.660249,4.509122))
                .add(new LatLng(-82.660249,4.018087))
                .add(new LatLng(-82.660249,3.541057))
                .add(new LatLng(-82.591249,3.06403));

// Get back the mutable Polyline
        Polyline polyline = map.addPolyline(rectOptions);
        */
 //.add(new LatLng(-33.666,151.195))
        //.add(new LatLng(-18.142,178.431))
        //.add(new LatLng(21.291,-157.821))
        //.add(new LatLng(37.423,-122.091))




/*
                .add(new LatLng(-3.231429,-81.5854))
                .add(new LatLng(-2.986426,-81.6384))
                .add(new LatLng(-2.42342,-81.5184))
                .add(new LatLng(-1.800414,-81.5854))
                .add(new LatLng(-1.08141,-81.4014))
                .add(new LatLng(-0.270408,-81.2154))
                .add(new LatLng(0.445592,-80.8734))
               .add(new LatLng( 1.06659,-80.5604))
                .add(new LatLng(1.562588,-80.2784))
                .add(new LatLng(2.087583,-79.7774))
                .add(new LatLng(2.44058,-79.4644))
                .add(new LatLng(2.793576,-79.1514))
                .add(new LatLng(3.405568,-78.6485))
                .add(new LatLng(4.079558,-78.3285))

                .add(new LatLng(4.845543,-78.0665))
                .add(new LatLng(5.506273,-77.9472))
                .add(new LatLng(5.848907,-77.9832))
                .add(new LatLng(6.066516,-78.0365))

                .add(new LatLng(6.135514,-78.0724))
                .add(new LatLng(6.267893,-78.1273))
                .add(new LatLng(6.362457,-78.1678))
                .add(new LatLng(6.452501,-78.2038))
                .add(new LatLng(6.547029,-78.2578))
                .add(new LatLng(6.62804,-78.3118))
                .add(new LatLng(6.727034,-78.3838))
                .add(new LatLng(6.848499,-78.4558))
                .add(new LatLng(6.983423,-78.5458))
                .add(new LatLng(7.1228033,-78.631))


*/
/*
        map.addPolyline(new PolylineOptions().geodesic(true)


                .add(new LatLng(-3.231429,-81.585497))
                .add(new LatLng(-2.986426,-81.638499))
                .add(new LatLng(-2.42342,-81.518496))
                .add(new LatLng(-1.800414,-81.585497))
                .add(new LatLng(-1.08141,-81.401499))
                .add(new LatLng(-0.270408,-81.215495))
                .add(new LatLng(0.445592,-80.873499))
                .add(new LatLng(1.06659,-80.560496))
                .add(new LatLng(1.562588,-80.278498))
                .add(new LatLng(2.087583,-79.777499))
                .add(new LatLng(2.44058,-79.464495))
                .add(new LatLng(2.793576,-79.151499))
                .add(new LatLng(3.405568,-78.648501))
                .add(new LatLng(4.079558,-78.328501))
                .add(new LatLng(4.845543,-78.0665))
                .add(new LatLng(5.506273,-77.947283))
                .add(new LatLng(5.848907,-77.983287))
                .add(new LatLng(6.066516,-78.036501))
                .add(new LatLng(6.135514,-78.072497))
                .add(new LatLng(6.267893,-78.127305))
                .add(new LatLng(6.362457,-78.16781))
                .add(new LatLng(6.452501,-78.203814))
                .add(new LatLng(6.547029,-78.25782))
                .add(new LatLng(6.62804,-78.311827))
                .add(new LatLng(6.727034,-78.383836))
                .add(new LatLng(6.848499,-78.455844))
                .add(new LatLng(6.983423,-78.545855))
                .add(new LatLng(7.122803,-78.631366))
                .width(3)
                .color(Color.parseColor("#FF3E0D"))




        );


 */
        KmlLayer layer = null;
        try {
            layer = new KmlLayer(map, R.raw.mapa, getActivity());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            layer.addLayerToMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }




        // map.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        map.setPadding(0, 100, 0, 110);

        loadMarker();

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



    public GoogleMap.OnCameraMoveListener getCameraChangeListener3() {
        return new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                CameraPosition cameraPosition = map.getCameraPosition();
                if (cameraPosition.zoom > 6.0) {
                    map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                } else {
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        };
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


        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            int count = 0;

            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                final Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                //  map.clear();
                for (final DataSnapshot dataSnapshot1 : dataSnapshots) {
                    final volcanes local = dataSnapshot1.getValue(volcanes.class);
                    final Double lati, longi;
                    count ++;
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
                    //  var iconGen = new IconGenerator(FragmentWithOneImage);

                    lati = Double.parseDouble(local.getLatitud());
                    longi = Double.parseDouble(local.getLongitud());
                    magnitude = Double.parseDouble(magnitud2);
                    // final BitmapDescriptor icon;

                    //  String nombre;
                    nombre = local.getNombre();

                    //   String s = String.valueOf(map.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener()).getCameraPosition().zoom);


                    //  String tipomapa = String.valueOf(map.getMapType());

                    if (magnitude <= 1) {
                        //icon = BitmapDescriptorFactory.fromResource(R.drawable.verde_volcanes);

                        icon = BitmapDescriptorFactory.fromBitmap(createStoreMarker(nombre,magnitude));
                    } else if (magnitude > 1 && magnitude == 2) {
                        //icon = BitmapDescriptorFactory.fromResource(R.drawable.amarillo_volcanes);
                        icon = BitmapDescriptorFactory.fromBitmap(createStoreMarker(nombre,magnitude));
                    } else if (magnitude > 2 && magnitude == 3) {
                        //icon = BitmapDescriptorFactory.fromResource(R.drawable.orange_volcanes);
                        icon = BitmapDescriptorFactory.fromBitmap(createStoreMarker(nombre,magnitude));

                    } else if (magnitude > 3 && magnitude == 4) {
                        //icon = BitmapDescriptorFactory.fromResource(R.drawable.red_black_volcanes);
                        icon = BitmapDescriptorFactory.fromBitmap(createStoreMarker(nombre,magnitude));

                    } else {
                        //icon = BitmapDescriptorFactory.fromResource(R.drawable.orange_volcanes);
                        icon = BitmapDescriptorFactory.fromBitmap(createStoreMarker(nombre,magnitude));

                    }

                    //.icon(BitmapDescriptorFactory.fromBitmap(createStoreMarker()));
                    // map.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title(local.getNombre() + " "+"Profundidad : "+local.getNombre()+"  >>>").snippet(local.getCodigo()+","+local.getEstado()+","+local.getActividad_reciente()+","+local.getAltura()+","+local.getTipo()+","+local.getNombre()+","+local.getNombre()).icon(icon).anchor(0.5f, 0.5f)).showInfoWindow();
                    map.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title(local.getNombre()).snippet(local.getCodigo() + "," + local.getEstado() + "," + local.getActividadreciente() + "," + local.getAltura() + "," + local.getTipo() + "," + local.getNombre() + "," + local.getNombre() + "," + local.getRegion()).icon(icon).anchor(0.5f, 0.5f));
                    map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoWindow(Marker marker) {
                            // View v = inflater.inflate(R.layout.customwindow, null);
                            String de3 = marker.getSnippet();
                            codigosnip = de3.split(",")[0];
                            String estadosnip = de3.split(",")[1];
                            String actividadrecientedato = de3.split(",")[2];
                            String alturadato = de3.split(",")[3];
                            String tipodato = de3.split(",")[4];
                            String nombredato = de3.split(",")[5];
                            String nombredato2 = de3.split(",")[6];
                            String region = de3.split(",")[7];

                            Typeface fontAwesomeFont = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
                            View v = getActivity().getLayoutInflater().inflate(R.layout.customwindow, null);
                            LatLng latLng = marker.getPosition();
                            TextView nombre = (TextView) v.findViewById(R.id.nombre);
                            TextView altura = (TextView) v.findViewById(R.id.altura);
                            // TextView tipo = (TextView) v.findViewById(R.id.tipo);
                            TextView kilometros = (TextView) v.findViewById(R.id.kilometros);
                            TextView gogo = (TextView) v.findViewById(R.id.gogo);
                            gogo.setTypeface(fontAwesomeFont);
                            ImageView imagen = (ImageView) v.findViewById(R.id.imagen);
                            tituloscolor = (RelativeLayout) v.findViewById(R.id.titulo);
                            double y;
                            try {
                                y = new Integer(estadosnip);
                            } catch (NumberFormatException e) {
                                y = 0;
                            }
                            if (y == 0) {
                                volcanestado = "0";
                            } else {
                                volcanestado = estadosnip;
                            }

                            final Integer volcanestados = Integer.parseInt(volcanestado);
                            if (volcanestados <= 1) {
                                imagen.setImageResource(R.drawable.volcano_verde);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.verdeigp));
                                //  gogo.setBackgroundColor(getResources().getColor(R.color.verdeigp));
                                gogo.setTextColor(getResources().getColor(R.color.verdeigp));
                            } else if (volcanestados > 1 && volcanestados == 2) {
                                imagen.setImageResource(R.drawable.volcano_amarillo);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.orangeyellow));
                                gogo.setTextColor(getResources().getColor(R.color.orangeyellow));
                            } else if (volcanestados > 2 && volcanestados == 3) {
                                imagen.setImageResource(R.drawable.volcano_naranja);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.naranjas));
                                gogo.setTextColor(getResources().getColor(R.color.naranjas));
                            } else if (volcanestados > 3 && volcanestados == 4) {
                                imagen.setImageResource(R.drawable.volcano_rojo);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.rojoigp));
                                gogo.setTextColor(getResources().getColor(R.color.rojoigp));
                            } else {
                                imagen.setImageResource(R.drawable.volcano_naranja);
                                tituloscolor.setBackgroundColor(getResources().getColor(R.color.naranjas));
                                gogo.setTextColor(getResources().getColor(R.color.naranjas));
                            }

                            nombre.setText("Volcán: " + nombredato2);
                            altura.setText("Región: " + region);
                            // tipo.setText("Altura: "+alturadato );
                            kilometros.setText("Actividad: " + actividadrecientedato);
                            nombre.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    detalles_volcan(codigosnip);
                                }
                            });
                            altura.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    detalles_volcan(codigosnip);
                                }
                            });
                            /*
                            tipo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    detalles_volcan(codigosnip);
                                }
                            });*/
                            kilometros.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    detalles_volcan(codigosnip);
                                }
                            });

                            return v;
                        }

                        @Override
                        public View getInfoContents(Marker marker) {
                            return null;
                        }

                        private void detalles_volcan(String... details) {
                            Intent i = new Intent(getActivity(), volcanmenu.class);
                            i.putExtra("ACTIVIDAD_RECIENTE", details[0]);
                            getActivity().startActivity(i);
                        }
                    });

                    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            Intent i = new Intent(getActivity(), volcanmenu.class);
                            i.putExtra("ACTIVIDAD_RECIENTE", codigosnip);
                            getActivity().startActivity(i);
                        }
                    });
                }

                // Toast.makeText(getActivity(), "Info window clicked : "+count,Toast.LENGTH_SHORT).show();

                if(count >= dataSnapshot.getChildrenCount()) {

                    spinner.setVisibility(View.GONE);
                    //stop progress bar here
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    public void openTab(View view) {
        String url = "https://www.danielme.com";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getContext(), Uri.parse(url));
    }


    private Bitmap createStoreMarker(String dato, Double mmagnitud) {
        final View markerLayout = getActivity().getLayoutInflater().inflate(R.layout.marcador, null);
        markerRating = (TextView) markerLayout.findViewById(R.id.marker_text);
        markerRating.setText(dato);
        datox = dato;

        ImageView markerImage = (ImageView) markerLayout.findViewById(R.id.marker_image);

        markerImage.setImageResource(R.drawable.volcan_amarillo_sm);

        if (mmagnitud <= 1) {
            markerImage.setImageResource(R.drawable.volcano_verde);
        }
        else if (mmagnitud > 1 && mmagnitud == 2) {
            markerImage.setImageResource(R.drawable.volcano_amarillo);
        }
        else if (mmagnitud > 2 && mmagnitud == 3) {
            markerImage.setImageResource(R.drawable.volcano_naranja);
        }
        else if (mmagnitud > 3 && mmagnitud == 4) {
            markerImage.setImageResource(R.drawable.volcano_rojo);
        }
        else {
        }

        markerLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        markerLayout.layout(0, 0, markerLayout.getMeasuredWidth(), markerLayout.getMeasuredHeight());

        final Bitmap bitmap = Bitmap.createBitmap(markerLayout.getMeasuredWidth(), markerLayout.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        markerLayout.draw(canvas);
        return bitmap;
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
        String region = marka.split(",")[7];
        Intent i=new Intent(getActivity(),volcanmenu.class);
        i.putExtra("ACTIVIDAD_RECIENTE",codigosnip);
        getActivity().startActivity(i);
        //  Toast.makeText(getActivity(), "Info window clicked : "+codigosnip,Toast.LENGTH_SHORT).show();
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

    private void selectItem1(){
        map.setMapType(map.MAP_TYPE_HYBRID);
    }

    private void selectItem2(){
        map.setMapType(map.MAP_TYPE_NORMAL);
    }
}