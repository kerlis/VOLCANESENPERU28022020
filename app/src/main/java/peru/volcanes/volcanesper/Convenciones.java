package peru.volcanes.volcanesper;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class Convenciones extends FragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button mk;
    RelativeLayout informacion;
    RelativeLayout ajustes;
    RelativeLayout compartir;
    RelativeLayout convenciones;
    TextView iconohora;
    TextView iconoubicacion;
    RelativeLayout blocke1a;
    RelativeLayout blocke2a;
    RelativeLayout blocke4a;
    RelativeLayout blocke5a;
    RelativeLayout blocke9;
    RelativeLayout menuright;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout mDrawerBlock;
    ImageView sliderz;
    TextView descripcion1;
    TextView descripcion2;
    TextView descripcion3;
    TextView descripcion34;
    TextView descripcion34muyalto;
    TextView descripcion34alto;
    TextView descripcion34moderado;
    TextView descripcion34bajo;
    TextView descripcion34alertacenizas;
    TextView descripcion34lahares;
    TextView descripcion34reporteex;
    TextView descripcion34volcanescudo;
    TextView descripcion34volcanconoescoria;
    TextView descripcion34volcanestratovolcan;
    TextView descripcion34volcancaldero;
    TextView descripcion34volcandomodelava;
    TextView descripcion34volcanfisuras;
    TextView hawaiano;
    TextView estromboliano;
    TextView Vulcaniano;
    TextView Subpliniano;
    TextView pliniano;
    TextView Ultrapliniano;
    TextView Sísmico;
    TextView Deformación;
    TextView Geoquímico;
    TextView Visual;
    TextView electrico;
    TextView altafrecuencia;
    TextView largoperiodo;
    TextView tornillos;
    TextView espasmodicos;
    TextView armonico;
    TextView explosion;
    TextView hibridos;
    TextView indiceex;
    TextView fumarolas;
    TextView glaciar;
    TextView mapasismico;
    TextView dispersion;
    TextView horautc;
    TextView metodologiatext;
    RelativeLayout blocke6a;
    TextView clickaqui;
    String enlacepdf;

    RelativeLayout blocke12;
    RelativeLayout blocke92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_convenciones);

        Snackbar.make(findViewById(android.R.id.content),"Mantén actualizada la aplicación", Snackbar.LENGTH_LONG)
                .setAction("Play store", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(getApplicationContext(), "Configurado", Toast.LENGTH_LONG).show();


                        Intent intent = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                        try {
                            startActivity(new Intent(intent)
                                    .setPackage("com.android.vending"));
                        } catch (android.content.ActivityNotFoundException exception) {
                            startActivity(intent);
                        }
                        //whatever you want to do when "Action" is clicked in the SnackBar
                    }
                }).show();



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
                Intent intent = new Intent(Convenciones.this,Contactar.class);
                startActivity(intent);
            }
        });


        blocke12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Convenciones.this,Ultimasnotificaciones.class);
                startActivity(intent);
            }
        });

        blocke1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Convenciones.this, pagedivisor.class);
                startActivity(intent);
            }
        });
        blocke2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Convenciones.this, Informacion.class);
                startActivity(intent);
            }
        });
        blocke4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Convenciones.this, Notificacionesconfig.class);
                startActivity(intent);
            }
        });
        blocke5a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Convenciones.this, Convenciones.class);
                startActivity(intent);
            }
        });

        blocke9 = (RelativeLayout) findViewById(R.id.blocke9);
        blocke9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Convenciones.this, Listadoredessociales.class);
                startActivity(intent);
            }
        });

        blocke6a.setOnClickListener(new View.OnClickListener() {
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
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
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

        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        clickaqui = (TextView) findViewById(R.id.clickaqui);
        clickaqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Convenciones.this, Pdfmetodologia.class);
                startActivity(intent);
            }
        });

        enlacepdf = "http://ovs.igp.gob.pe/sites/ovs.igp.gob.pe/files/pdf/Investigacion/otros/evaluacion_del_riesgo_volcanico_en_el_peru.pdf";


        clickaqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmap(enlacepdf,
                        enlacepdf);
            }
        });


        metodologiatext = (TextView) findViewById(R.id.metodologiatext);
        metodologiatext.setText(Html.fromHtml("<i>La metodología para la determinación de los niveles de riesgo volcánico relativo y asignación de puntajes está detallada en el estudio de  “Evaluación del Riesgo Volcánico en el Sur del Perú.</i>"));




        descripcion1 = (TextView) findViewById(R.id.descripcion1);
        descripcion1.setText(Html.fromHtml("<b align=right> Alerta verde: </b> El volcán está inactivo debido a que sus condiciones son estables. La población puede desarrollar sus actividades con normalidad."));
        /*descripcion1.setText(Html.fromHtml("<b align=right> Alerta verde: </b> el volcán permanece tranquilo. La población puede desarrollar sus actividades con normalidad."));*/

        descripcion2 = (TextView) findViewById(R.id.descripcion2);
        descripcion2.setText(Html.fromHtml("<b align=right> Alerta amarilla: </b> El volcán incrementa su actividad sísmica y se observa  la presencia de fumarolas. Existe la posibilidad de que se produzcan explosiones y emisiones de ceniza. Estar alerta a las recomendaciones de las autoridades y mantenerse alejados del volcán."));
        /*descripcion2.setText(Html.fromHtml("<b align=right> Alerta amarilla: </b> el volcán presenta signos de intranquilidad (fumarolas y mayor cantidad de sismos) o inició una nueva erupción, con pequeñas explosiones y emisiones de ceniza. Estar alerta a las recomendaciones de las autoridades y mantenerse alejado del volcán."));*/

        descripcion3 = (TextView) findViewById(R.id.descripcion3);
        descripcion3.setText(Html.fromHtml("<b align=right> Alerta naranja: </b> El volcán aumenta de manera significativa su actividad eruptiva. Se observa el incremento de la actividad sísmica, la ocurrencia de explosiones frecuentes y la emisión de cenizas y bloques balísticos. Es posible el desarrollo de lahares. La población debe seguir las recomendaciones de las autoridades y mantenerse alejados del volcán."));
        /*descripcion3.setText(Html.fromHtml("<b align=right> Alerta naranja: </b> el volcán registra una variación significativa en su actividad eruptiva (explosiones frecuentes, posibles Lahares). Seguir las recomendaciones de las autoridades y mantenerse alejado del volcán."));*/

        descripcion34 = (TextView) findViewById(R.id.descripcion34);
        descripcion34.setText(Html.fromHtml("<b align=right> Alerta roja: </b> El volcán está en plena erupción critica con el consecuente peligro de erupción explosiva violenta. La sismicidad se ha incrementado notablemente. La erupción genera grandes columnas de cenizas y la eyección de bloques balísticos. Existe posibilidad del desarrollo de flujos piroclásticos y lahares que pueden descender por  los flancos del volcán a gran velocidad. La población debe estar muy atenta a las indicaciones de las autoridades ante una posible evacuación. "));
        /*descripcion34.setText(Html.fromHtml("<b align=right> Alerta roja: </b> el volcán está en plena erupción y expulsando grandes volúmenes de productos volcánicos (ceniza, bloques balísticos, flujos piroclásticos y Lahares). Posible evacuación."));*/





        descripcion34muyalto = (TextView) findViewById(R.id.descripcion34muyalto);
        descripcion34muyalto.setText(Html.fromHtml("<b align=right> Muy alto: </b> después del análisis de factores de peligro y vulnerabilidad, se concluye que los volcanes Sabancaya, Misti, Ubinas y Coropuna representan un alto riesgo para las poblaciones cercanas. Los puntajes obtenidos por ellos fueron de 240.0 a190.8."));

        descripcion34alto = (TextView) findViewById(R.id.descripcion34alto);
        descripcion34alto.setText(Html.fromHtml("<b align=right> Alto: </b> después del análisis de factores de peligro y vulnerabilidad, se concluye que los volcanes Yucamane, Tutupaca, Huaynaputina, Ticsani y Chachani representan un alto riesgo para las poblaciones cercanas. Los puntajes obtenidos por ellos fueron de 162.6 a121.6."));

        descripcion34moderado = (TextView) findViewById(R.id.descripcion34moderado);
        descripcion34moderado.setText(Html.fromHtml("<b align=right> Moderado: </b> después del análisis de factores de peligro y vulnerabilidad, se concluye que los volcanes Sara Sara, Casiri y Quimsachata representan un moderado riesgo para las poblaciones cercanas. Los puntajes obtenidos por ellos fueron de 67.5 a 42.8."));

        descripcion34bajo = (TextView) findViewById(R.id.descripcion34bajo);
        descripcion34bajo.setText(Html.fromHtml("<b align=right> Bajo: </b> después del análisis de factores de peligro y vulnerabilidad, se concluye que los volcanes Cerro Purupuruni, Andahua, Huambo y Cerro Auquihuato representan un bajo riesgo para las poblaciones cercanas. Los puntajes obtenidos por ellos fueron de 27.3 a 10.5."));

        descripcion34alertacenizas = (TextView) findViewById(R.id.descripcion34alertacenizas);
        descripcion34alertacenizas.setText(Html.fromHtml("<b align=right> Alerta de cenizas: </b> comunicado que advierte la ocurrencia de un evento explosivo o emisiones de ceniza en el volcán, además de señalar la dirección de dispersión de este material y las posibles áreas afectadas por dicho fenómeno."));

        descripcion34lahares = (TextView) findViewById(R.id.descripcion34lahares);
        descripcion34lahares.setText(Html.fromHtml("<b align=right> Alerta de Lahares: </b> comunicado que advierte el descenso de flujos compuestos por agua, piedras, ceniza y otros materiales volcánicos, además de señalar el flanco por el cual discurren y las áreas de posible afectación."));

        descripcion34reporteex = (TextView) findViewById(R.id.descripcion34reporteex);
        descripcion34reporteex.setText(Html.fromHtml("<b align=right> Reporte extraordinario: </b> informe excepcional que contiene información destacada sobre cambios repentinos en la actividad del volcán."));

        descripcion34volcanescudo = (TextView) findViewById(R.id.descripcion34volcanescudo);
        descripcion34volcanescudo.setText(Html.fromHtml("<b align=right> Volcanes escudo: </b> se forman por la emisión de lavas muy fluidas y se caracterizan por tener un cráter de gran diámetro y un cono de pendiente suave. Ej: volcán Kilauea en Hawái."));

        descripcion34volcanconoescoria = (TextView) findViewById(R.id.descripcion34volcanconoescoria);
        descripcion34volcanconoescoria.setText(Html.fromHtml("<b align=right> Conos de escoria: </b> estos volcanes están formados por material piroclástico que suele producir erupciones explosivas moderadas. Tienen laderas empinadas, son pequeños y, por lo general, permanecen activos por poco tiempo. Ej: valle de Andahua."));

        descripcion34volcanestratovolcan = (TextView) findViewById(R.id.descripcion34volcanestratovolcan);
        descripcion34volcanestratovolcan.setText(Html.fromHtml("<b align=right> Estratovolcán: </b> tienen un cono bien desarrollado y constituido por la acumulación de lava y productos piroclásticos alrededor del cráter. Se forman por la repetición de numerosas erupciones. Ej: volcán Misti."));

        descripcion34volcancaldero = (TextView) findViewById(R.id.descripcion34volcancaldero);
        descripcion34volcancaldero.setText(Html.fromHtml("<b align=right> Caldera: </b> cuando el cráter de un volcán supera el kilómetro de diámetro, se denomina caldera volcánica. Tienen una forma circular y, en su gran mayoría, se  forman por el hundimiento producido después de una erupción explosiva. Ej. volcán Ubinas cuenta con una caldera."));

        descripcion34volcandomodelava = (TextView) findViewById(R.id.descripcion34volcandomodelava);
        descripcion34volcandomodelava.setText(Html.fromHtml("<b align=right> Domo de lava: </b>  también conocido como domo tapón, es un montículo circular que se origina en una erupción lenta de lava viscosa. Los domos pueden alcanzar alturas de varios cientos de metros y crecen lentamente durante meses o incluso años. Ej: volcán Ticsani cuenta con 3 domos."));

        descripcion34volcanfisuras = (TextView) findViewById(R.id.descripcion34volcanfisuras);
        descripcion34volcanfisuras.setText(Html.fromHtml("<b align=right> Fisuras: </b> los volcanes fisurales son hendiduras más o menos lineales expuestas en la corteza terrestre. Es a través de esta hendidura por donde se expulsa la lava, habitualmente sin actividad explosiva. Ej: volcán Laki en Islandia."));

        hawaiano = (TextView) findViewById(R.id.hawaiano);
        hawaiano.setText(Html.fromHtml("<b align=right> Hawaiano: </b> sus lavas son muy fluidas y forman grandes flujos e incluso lagos de lava. Los gases son liberados en forma pasiva. Las erupciones violentas son raras y los gases pueden impulsar fuentes de lava que llegan a alcanzar los 500 m de altura."));

        estromboliano = (TextView) findViewById(R.id.estromboliano);
        estromboliano.setText(Html.fromHtml("<b align=right> Estromboliano: </b> se caracteriza por una actividad constante de explosiones que expulsan lava pastosa en estado incandescente. Son acompañadas por ríos de lava y emisión de gases que, por lo general, no superan los 500 m de altura."));

        Vulcaniano = (TextView) findViewById(R.id.Vulcaniano);
        Vulcaniano.setText(Html.fromHtml("<b align=right> Vulcaniano: </b> menos frecuente y más violenta debido a que el magma es más viscoso y, por lo tanto, la liberación de los gases más difícil. Van acompañados por una gran nube de gases cargados de ceniza y fragmentos de rocas que alcanzan de 10 a 20 km de altura."));

        Subpliniano = (TextView) findViewById(R.id.Subpliniano);
        Subpliniano.setText(Html.fromHtml("<b align=right> Subpliniano: </b> muy explosivo, con columnas eruptivas de hasta unos 30 km de altura. Genera la emisión de grandes volúmenes de tefra (fragmentos sólidos como ceniza y otros de mayor dimensión)."));

        pliniano = (TextView) findViewById(R.id.pliniano);
        pliniano.setText(Html.fromHtml("<b align=right> Pliniano: </b> son altamente explosivos y eyectan pómez, característico de magmas muy ricos en sílice. La columna puede alcanzar hasta unos 50 km de altura. A menudo son acompañadas por el colapso de la parte superior del edificio volcánico."));

        Ultrapliniano = (TextView) findViewById(R.id.Ultrapliniano);
        Ultrapliniano.setText(Html.fromHtml("<b align=right> Ultrapliniano: </b> la columna eruptiva se eleva sobre los 40 km. Según estudios, la erupción de la Caldera Diamante, que alberga al volcán Maipo en Argentina, tuvo una erupción de este tipo hace 450.000 años. El volumen de piroclastos alcanzó hasta 500 km."));

        Sísmico = (TextView) findViewById(R.id.Sísmico);
        Sísmico.setText(Html.fromHtml("<b align=right> Sísmico: </b> estudia y analiza los eventos sísmicos generados por la actividad volcánica. Los instrumentos utilizados para captar y registrar las señales sísmicas son conocidos como sismómetros."));

        Deformación = (TextView) findViewById(R.id.Deformación);
        Deformación.setText(Html.fromHtml("<b align=right> Deformación: </b> estudia las deformaciones en la superficie del edificio volcánico debido a su actividad interna. Para esto se utiliza datos obtenidos con equipos de alta precisión como Sistemas de Posicionamiento Global (GPS), inclinómetros e imágenes de satélite y de radar InSAR."));

        Geoquímico = (TextView) findViewById(R.id.Geoquímico);
        Geoquímico.setText(Html.fromHtml("<b align=right> Geoquímico: </b> realiza el muestreo de los gases, condensados, temperatura de las fumarolas y de las fuentes termales próximas a los volcanes. Los instrumentos utilizados son DOAS, sensores térmicos, termómetros, medidores de pH, etc."));

        Visual = (TextView) findViewById(R.id.Visual);
        Visual.setText(Html.fromHtml("<b align=right> Visual: </b> consiste en el seguimiento por medio visual sobre la evolución en los procesos de emanación de gases, cenizas, deformaciones, derrumbes y fracturas. Los instrumentos utilizados son cámaras científicas, cámaras infrarrojas, imágenes satelitales, entre otros."));

        electrico = (TextView) findViewById(R.id.electrico);
        electrico.setText(Html.fromHtml("<b align=right> Eléctrico: </b> consiste en el registro de señales eléctricas naturales medidas desde la superficie. Puede emplearse para delimitar la estructura interna del volcán o para monitorear cambios en acuíferos pocos profundos por la cercanía en la magma. Los instrumentos utilizados son electrodos, multímetros, data loggers científicos, etc."));

        indiceex = (TextView) findViewById(R.id.indiceex);
        indiceex.setText(Html.fromHtml("Es una escala logarítmica que va del 0 al 8 y que permite medir la explosividad relativa de las erupciones volcánicas. Para ello, toma en cuenta la cantidad del contenido eyectado, la altura del material lanzado a la atmósfera y la duración de la erupción."));

        altafrecuencia = (TextView) findViewById(R.id.altafrecuencia);
        altafrecuencia.setText(Html.fromHtml("<b align=right> Sismos Volcano-Tectónicos (VT): </b> están relacionados a fractura de rocas. Se generan por esfuerzos asociados a la inyección y transporte de fluidos en el conducto y/o zonas próximas al edificio volcánico."));

        largoperiodo = (TextView) findViewById(R.id.largoperiodo);
        largoperiodo.setText(Html.fromHtml("<b align=right> Sismos de Largo Periodo (LP): </b>  asociados al movimiento de fluidos."));

        tornillos = (TextView) findViewById(R.id.tornillos);
        tornillos.setText(Html.fromHtml("<b align=right> Tornillos: </b>  relacionados al paso de fluidos por una cavidad resonante."));

        espasmodicos = (TextView) findViewById(R.id.espasmodicos);
        espasmodicos.setText(Html.fromHtml("<b align=right> Tremor espasmódico: </b>  asociados a una perturbación del sistema hidrotermal en una etapa preexplosiva. Pueden relacionarse también a la emisión de ceniza durante la etapa explosiva."));

        armonico = (TextView) findViewById(R.id.armonico);
        armonico.setText(Html.fromHtml("<b align=right> Tremor armónico: </b> relacionado al ascenso del magma y/o al tránsito de fluidos por una cavidad resonante."));

        explosion = (TextView) findViewById(R.id.explosion);
        explosion.setText(Html.fromHtml("<b align=right> Explosión: </b>  sismo de gran energía asociado a expulsion de materiales volcánicos."));

        hibridos = (TextView) findViewById(R.id.hibridos);
        hibridos.setText(Html.fromHtml("<b align=right> Híbridos: </b>  generalmente se trata de eventos asociados al ascenso de magma en sus últimos tramos (pocos kilómetros) antes de llegar a la superficie."));

        fumarolas = (TextView) findViewById(R.id.fumarolas);
        fumarolas.setText(Html.fromHtml("Son respiraderos por los cuales escapa el gas volcánico a la atmósfera. Las fumarolas pueden liberarse a lo largo de grietas diminutas o fisuras largas. Pueden persistir durante décadas o siglos si están por encima de una fuente de calor persistente o desaparecen dentro de semanas a meses si se producen encima de un depósito volcánico fresco que rápidamente se enfría."));

        glaciar = (TextView) findViewById(R.id.glaciar);
        glaciar.setText(Html.fromHtml("Es una gruesa masa de hielo que se origina en la superficie terrestre por acumulación, compactación y recristalización de la nieve, mostrando evidencias de flujo en el pasado o en la actualidad."));

        mapasismico = (TextView) findViewById(R.id.mapasismico);
        mapasismico.setText(Html.fromHtml("Producto que muestra en 3D la distribución de los sismos ocurridos en el área del volcán en un periodo de tiempo determinado."));

        dispersion = (TextView) findViewById(R.id.dispersion);
        dispersion.setText(Html.fromHtml("Basado en modelos númericos, muestran el desplazamiento teórico de las cenizas tras la ocurrencia de eventos explosivos."));

        horautc = (TextView) findViewById(R.id.horautc);
        horautc.setText(Html.fromHtml("UTC es el tiempo estándar comúnmente utilizado en todo el mundo. Los centros de cronometraje del mundo han acordado mantener sus escalas de tiempo estrechamente sincronizadas, o coordinadas, de allí el nombre Coordinated Universal Time. Ej.: las 14:00 horas en Perú son las 19:00 UTC."));


        ImageView alertadelaharimage = (ImageView) findViewById(R.id.icono33lahares);
        ImageView mimageView = (ImageView) findViewById(R.id.foto);
        ImageView mimageView2 = (ImageView) findViewById(R.id.icono33reporteex);
        ImageView mimageView3 = (ImageView) findViewById(R.id.icono33volcanescudo);
        ImageView mimageView4 = (ImageView) findViewById(R.id.icono33volcanconoescoria);
        ImageView mimageView5 = (ImageView) findViewById(R.id.icono33volcanestratovolcan);
        ImageView mimageView6 = (ImageView) findViewById(R.id.icono33volcancaldero);
        ImageView mimageView7 = (ImageView) findViewById(R.id.icono33volcandomodelava);
        ImageView mimageView8 = (ImageView) findViewById(R.id.icono33volcanfisuras);
        ImageView mimageView9 = (ImageView) findViewById(R.id.icono33volcavo);
        ImageView mimageView10 = (ImageView) findViewById(R.id.icono33estromboliano);
        ImageView mimageView11 = (ImageView) findViewById(R.id.icono33Vulcaniano);
        ImageView mimageView12 = (ImageView) findViewById(R.id.icono33Subpliniano);
        ImageView mimageView13 = (ImageView) findViewById(R.id.icono33volcanpliniano);
        ImageView mimageView14 = (ImageView) findViewById(R.id.icono33Ultrapliniano);
        ImageView mimageView15 = (ImageView) findViewById(R.id.icono33Sísmico);
        ImageView mimageView16 = (ImageView) findViewById(R.id.icono33Deformación);
        ImageView mimageView17 = (ImageView) findViewById(R.id.icono33Geoquímico);
        ImageView mimageView18 = (ImageView) findViewById(R.id.icono33Visual);
        ImageView mimageView19 = (ImageView) findViewById(R.id.icono33indiceex);
        ImageView mimageView20 = (ImageView) findViewById(R.id.icono33dispersion);
        ImageView mimageView21 = (ImageView) findViewById(R.id.icono33mapasismico);
        ImageView mimageView22 = (ImageView) findViewById(R.id.icono33glaciar);
        ImageView mimageView23 = (ImageView) findViewById(R.id.icono33fumarolas);
        ImageView mimageView24 = (ImageView) findViewById(R.id.icono33tornillos);
        ImageView mimageView25 = (ImageView) findViewById(R.id.icono33explosion);
        ImageView mimageView26 = (ImageView) findViewById(R.id.icono33hibridos);
        ImageView mimageView27 = (ImageView) findViewById(R.id.icono33altalargoperiodo);
        ImageView mimageView28 = (ImageView) findViewById(R.id.icono33altafrecuencia);
        ImageView mimageView29 = (ImageView) findViewById(R.id.icono33Eléctrico);
        ImageView mimageView30 = (ImageView) findViewById(R.id.icono33horautc);
        ImageView mimageView31 = (ImageView) findViewById(R.id.icono33espasmodicos);
        ImageView mimageView32 = (ImageView) findViewById(R.id.icono33armonico);
        mimageView.setImageResource(R.drawable.alertadecenizas);
        alertadelaharimage.setImageResource(R.drawable.alertadelahares);
        mimageView2.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.reporteextraordinario, 90, 90));
        mimageView3.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.volcanesescudo, 90, 90));
        mimageView4.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.conodeescoria, 90, 90));
        mimageView5.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.estratovolcan, 90, 90));
        mimageView6.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.caldera, 90, 90));
        mimageView7.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.domodelava, 90, 90));
        mimageView8.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.fisuras, 90, 90));
        mimageView9.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.hawaiano, 90, 90));
        mimageView10.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.estromboliano, 90, 90));
        mimageView11.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.vulcaniano, 90, 90));
        mimageView12.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.subpliniano, 90, 90));
        mimageView13.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.pliniano, 90, 90));
        mimageView14.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.ultrapliniano, 90, 90));
        mimageView15.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.monitoreosismico, 90, 90));
        mimageView16.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.deformacion, 90, 90));
        mimageView17.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.geoquimico, 90, 90));
        mimageView18.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.monitoreovisual, 90, 90));
       // mimageView19.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.iindice_explosividad, 150, 150));
        mimageView19.setImageResource(R.drawable.iindice_explosividad);
        mimageView20.setImageResource(R.drawable.pronosticodispersion);
        mimageView21.setImageResource(R.drawable.mapasismico3d);
        mimageView22.setImageResource(R.drawable.glaciar);
        mimageView23.setImageResource(R.drawable.fumarolas);
        mimageView24.setImageResource(R.drawable.tornillo);
        mimageView25.setImageResource(R.drawable.hibrido);
        mimageView26.setImageResource(R.drawable.explosion);


        mimageView27.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.largoperiodo, 90, 90));
        mimageView28.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.altafrecuencia, 90, 90));
        mimageView29.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.monitoreoelectrico, 90, 90));
        mimageView30.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.relojes, 90, 90));
        mimageView31.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.espasmodico, 90, 90));
        mimageView32.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.armonico, 90, 90));
    }



    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    private void openmap(String...details)
    {
        Intent i=new Intent(Convenciones.this,Pdfmetodologia.class);
        i.putExtra("ENLACE",details[0]);
        Convenciones.this.startActivity(i);
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