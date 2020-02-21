package peru.volcanes.volcanesper.m_ui;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import peru.volcanes.volcanesper.Datosultimanotificacion;
import peru.volcanes.volcanesper.Datosultimasnotificacionescenizas;
import peru.volcanes.volcanesper.R;
import peru.volcanes.volcanesper.Ultimanotificacionlahardatos;
import peru.volcanes.volcanesper.m_model.ultimasnotificacionesobjeto;
import java.util.ArrayList;
public class Ultimasnotificacionesadapter extends BaseAdapter {
    public Context c;
    private ArrayList<ultimasnotificacionesobjeto> objetoreportes;
    TextView fecha;
    TextView volcan;
    TextView tipo;
    ImageView foto;
    String tiponotificacionreportedeactividad;
    String tiponotificacionlahar;
    String tiponotificacioncenizas;

    public Ultimasnotificacionesadapter(Context c, ArrayList<ultimasnotificacionesobjeto> objetoreportes) {
        this.c = c;
        this.objetoreportes = objetoreportes;
    } @Override
    public int getCount() {
        return objetoreportes.size();
    }

    @Override
    public Object getItem(int pos) {
        return objetoreportes.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView= LayoutInflater.from(c).inflate(R.layout.modeloultimosreportes,viewGroup,false);
        final int g = position;

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#F0EFF5"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        fecha= (TextView) convertView.findViewById(R.id.fecha);
        volcan= (TextView) convertView.findViewById(R.id.volcan);
        tipo= (TextView) convertView.findViewById(R.id.tipo);
        foto= (ImageView) convertView.findViewById(R.id.foto);

        final ultimasnotificacionesobjeto s= (ultimasnotificacionesobjeto) this.getItem(position);

        tiponotificacionreportedeactividad = s.getTiponotificacion_reporteactividad();
        tiponotificacionlahar= s.getTiponotificacion_lahar();
        tiponotificacioncenizas=s.getTiponotificacion_cenizas();



        if((tiponotificacionreportedeactividad!="nulo")&&(tiponotificacionlahar.equals("nulo"))&&(tiponotificacioncenizas.equals("nulo"))){

        fecha.setText(s.getFecha_reporteactividad() + " | " + s.getHora_reporteactividad());


            if(tiponotificacionreportedeactividad.equals("n04")){
                tipo.setText("Reporte Ordinario");
            }
            else{
                tipo.setText("Reporte Extraordinario");
            }


            String val = String.valueOf(s.getCodigovolcan_reporteactividad());


            if (val.equals("1493157379002")){
                volcan.setText("UBINAS");
                foto.setImageResource(R.drawable.ubinas_circle);
            }
            else if(val.equals("1493157381161")){
                volcan.setText("SABANCAYA");
                foto.setImageResource(R.drawable.sabancaya_circle);
            }
            else if(val.equals("1506454510537")){
                volcan.setText("SARA SARA");
                foto.setImageResource(R.drawable.sarasara_circle);
            }
            else if(val.equals("1506455245814")){
                volcan.setText("CERRO AUQUIHUATO");
                foto.setImageResource(R.drawable.cerro_auquihuato_circle);
            }
            else if(val.equals("1506455248101")){
                volcan.setText("ANDAHUA");
                foto.setImageResource(R.drawable.andahua_circle);
            }
            else if(val.equals("1506455249661")){
                volcan.setText("COROPUNA");
                foto.setImageResource(R.drawable.coropuna_circle);
            }
            else if(val.equals("1506455251429")){
                volcan.setText("HUAMBO");
                foto.setImageResource(R.drawable.huambo_circle);
            }
            else if(val.equals("1506455253382")){
                volcan.setText("CHACHANI");
                foto.setImageResource(R.drawable.chachani_circle);
            }
            else if(val.equals("1506455254838")){
                volcan.setText("TUTUPACA");
                foto.setImageResource(R.drawable.tutupaca_circle);
            }
            else if(val.equals("1506455256229")){
                volcan.setText("HUAYNAPUTINA");
                foto.setImageResource(R.drawable.huaynaputina_circle);
            }
            else if(val.equals("1506455257749")){
                volcan.setText("TICSANI");
                foto.setImageResource(R.drawable.ticsani_circle);
            }
            else if(val.equals("1506455257753")){
                volcan.setText("CASIRI");
                foto.setImageResource(R.drawable.casiri_circle);
            }
            else if(val.equals("1506455257755")){
                volcan.setText("CERROS PURUPURUNI");
                foto.setImageResource(R.drawable.cerros_purupuruni_circle);
            }
            else if(val.equals("1506455257757")){
                volcan.setText("QUIMSACHATA");
                foto.setImageResource(R.drawable.quimsachata_circle);
            }
            else if(val.equals("1506455259126")){
                volcan.setText("YUCAMANE");
                foto.setImageResource(R.drawable.yucamane_circle);
            }
            else if(val.equals("1506455259128")){
                volcan.setText("MISTI");
                foto.setImageResource(R.drawable.misti_circle);
            }
            else{
                volcan.setText("MISTI");
                foto.setImageResource(R.drawable.misti_circle);
            }

        }

        else if((tiponotificacionreportedeactividad.equals("nulo"))&&(tiponotificacionlahar!="nulo")&&(tiponotificacioncenizas.equals("nulo"))){
            tipo.setText("Alerta de Lahar");

            fecha.setText(s.getFecha_lahar() + " | " + s.getHora_lahar());

            String val = String.valueOf(s.getVolcan_lahar());


            if (val.equals("1493157379002")){
                volcan.setText("UBINAS");
                foto.setImageResource(R.drawable.ubinas_circle);
            }
            else if(val.equals("1493157381161")){
                volcan.setText("SABANCAYA");
                foto.setImageResource(R.drawable.sabancaya_circle);
            }
            else if(val.equals("1506454510537")){
                volcan.setText("SARA SARA");
                foto.setImageResource(R.drawable.sarasara_circle);
            }
            else if(val.equals("1506455245814")){
                volcan.setText("CERRO AUQUIHUATO");
                foto.setImageResource(R.drawable.cerro_auquihuato_circle);
            }
            else if(val.equals("1506455248101")){
                volcan.setText("ANDAHUA");
                foto.setImageResource(R.drawable.andahua_circle);
            }
            else if(val.equals("1506455249661")){
                volcan.setText("COROPUNA");
                foto.setImageResource(R.drawable.coropuna_circle);
            }
            else if(val.equals("1506455251429")){
                volcan.setText("HUAMBO");
                foto.setImageResource(R.drawable.huambo_circle);
            }
            else if(val.equals("1506455253382")){
                volcan.setText("CHACHANI");
                foto.setImageResource(R.drawable.chachani_circle);
            }
            else if(val.equals("1506455254838")){
                volcan.setText("TUTUPACA");
                foto.setImageResource(R.drawable.tutupaca_circle);
            }
            else if(val.equals("1506455256229")){
                volcan.setText("HUAYNAPUTINA");
                foto.setImageResource(R.drawable.huaynaputina_circle);
            }
            else if(val.equals("1506455257749")){
                volcan.setText("TICSANI");
                foto.setImageResource(R.drawable.ticsani_circle);
            }
            else if(val.equals("1506455257753")){
                volcan.setText("CASIRI");
                foto.setImageResource(R.drawable.casiri_circle);
            }
            else if(val.equals("1506455257755")){
                volcan.setText("CERROS PURUPURUNI");
                foto.setImageResource(R.drawable.cerros_purupuruni_circle);
            }
            else if(val.equals("1506455257757")){
                volcan.setText("QUIMSACHATA");
                foto.setImageResource(R.drawable.quimsachata_circle);
            }
            else if(val.equals("1506455259126")){
                volcan.setText("YUCAMANE");
                foto.setImageResource(R.drawable.yucamane_circle);
            }
            else if(val.equals("1506455259128")){
                volcan.setText("MISTI");
                foto.setImageResource(R.drawable.misti_circle);
            }
            else{
                volcan.setText("MISTI");
                foto.setImageResource(R.drawable.misti_circle);
            }

        }



        else if((tiponotificacionreportedeactividad.equals("nulo"))&&(tiponotificacionlahar.equals("nulo"))&&(tiponotificacioncenizas!="nulo")){
            tipo.setText("Alerta de Dispersión de Cenizas");

            fecha.setText(s.getFecha_cenizas() + " | " + s.getHora_cenizas());

            String val = String.valueOf(s.getVolcan_cenizas());


            if (val.equals("1493157379002")){
                volcan.setText("UBINAS");
                foto.setImageResource(R.drawable.ubinas_circle);
            }
            else if(val.equals("1493157381161")){
                volcan.setText("SABANCAYA");
                foto.setImageResource(R.drawable.sabancaya_circle);
            }
            else if(val.equals("1506454510537")){
                volcan.setText("SARA SARA");
                foto.setImageResource(R.drawable.sarasara_circle);
            }
            else if(val.equals("1506455245814")){
                volcan.setText("CERRO AUQUIHUATO");
                foto.setImageResource(R.drawable.cerro_auquihuato_circle);
            }
            else if(val.equals("1506455248101")){
                volcan.setText("ANDAHUA");
                foto.setImageResource(R.drawable.andahua_circle);
            }
            else if(val.equals("1506455249661")){
                volcan.setText("COROPUNA");
                foto.setImageResource(R.drawable.coropuna_circle);
            }
            else if(val.equals("1506455251429")){
                volcan.setText("HUAMBO");
                foto.setImageResource(R.drawable.huambo_circle);
            }
            else if(val.equals("1506455253382")){
                volcan.setText("CHACHANI");
                foto.setImageResource(R.drawable.chachani_circle);
            }
            else if(val.equals("1506455254838")){
                volcan.setText("TUTUPACA");
                foto.setImageResource(R.drawable.tutupaca_circle);
            }
            else if(val.equals("1506455256229")){
                volcan.setText("HUAYNAPUTINA");
                foto.setImageResource(R.drawable.huaynaputina_circle);
            }
            else if(val.equals("1506455257749")){
                volcan.setText("TICSANI");
                foto.setImageResource(R.drawable.ticsani_circle);
            }
            else if(val.equals("1506455257753")){
                volcan.setText("CASIRI");
                foto.setImageResource(R.drawable.casiri_circle);
            }
            else if(val.equals("1506455257755")){
                volcan.setText("CERROS PURUPURUNI");
                foto.setImageResource(R.drawable.cerros_purupuruni_circle);
            }
            else if(val.equals("1506455257757")){
                volcan.setText("QUIMSACHATA");
                foto.setImageResource(R.drawable.quimsachata_circle);
            }
            else if(val.equals("1506455259126")){
                volcan.setText("YUCAMANE");
                foto.setImageResource(R.drawable.yucamane_circle);
            }
            else if(val.equals("1506455259128")){
                volcan.setText("MISTI");
                foto.setImageResource(R.drawable.misti_circle);
            }
            else{
                volcan.setText("MISTI");
                foto.setImageResource(R.drawable.misti_circle);
            }

        }

        if((tiponotificacionreportedeactividad!="nulo")&&(tiponotificacionlahar.equals("nulo"))&&(tiponotificacioncenizas.equals("nulo"))) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    verreportedeactividad(s.getCodigovolcan_reporteactividad(),
                            s.getFecha_reporteactividad(),
                            s.getHora_reporteactividad(),
                            s.getHorautc_reporteactividad(),
                            s.getPdfurl_reporteactividad(),
                            s.getSimulacro_reporteactividad(),
                            s.getTiponotificacion_reporteactividad(),
                            s.getNombrereporte_reporteactividad(),
                            s.getColoralerta_reporteactividad(),
                            s.getAnalisis_reporteactividad(),
                            s.getConclusiones_reporteactividad());
                }
            });
        }

        else if((tiponotificacionreportedeactividad.equals("nulo"))&&(tiponotificacionlahar!="nulo")&&(tiponotificacioncenizas.equals("nulo"))) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    veralertalahar(s.getTiponotificacion_lahar(),
                            s.getVolcan_lahar(),
                            s.getTipodevento_lahar(),
                            s.getFecha_lahar(),
                            s.getHora_lahar(),
                            s.getHorautc_lahar(),
                            s.getObservaciones_lahar(),
                            s.getSimulacro_lahar());
                }
            });
        }

        else if((tiponotificacionreportedeactividad.equals("nulo"))&&(tiponotificacionlahar.equals("nulo"))&&(tiponotificacioncenizas!="nulo")) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    veralertacenizas(s.getTiponotificacion_cenizas(),
                            s.getVolcan_cenizas(),
                            s.getPueblos_cenizas(),
                            s.getTipodevento_cenizas(),
                            s.getDireccion_cenizas(),
                            s.getRadio_cenizas(),
                            s.getFecha_cenizas(),
                            s.getHora_cenizas(),
                            s.getRecomendaciones_cenizas(),
                            s.getObservaciones_cenizas(),
                            s.getSimulacro_cenizas(),
                            s.getHorautc_cenizas());
                }
            });
        }

        return convertView;
    }

    private void verreportedeactividad(String...details) {
        Intent i=new Intent(c,Datosultimanotificacion.class);
        i.putExtra("CODIGOVOLCAN",details[0]);
        i.putExtra("FECHA",details[1]);
        i.putExtra("HORA",details[2]);
        i.putExtra("HORAUTC",details[3]);
        i.putExtra("PDFURL",details[4]);
        i.putExtra("SIMULACRO",details[5]);
        i.putExtra("TIPODENOTIFICACION",details[6]);
        i.putExtra("NOMBREDEREPORTE",details[7]);
        i.putExtra("COLORALERTA",details[8]);
        i.putExtra("ANALISIS",details[9]);
        i.putExtra("CONCLUSIONES",details[10]);
        c.startActivity(i);
    }

    private void veralertalahar(String...details) {
        Intent i=new Intent(c,Ultimanotificacionlahardatos.class);
        i.putExtra("TIPODENOTIFICACION",details[0]);
        i.putExtra("VOLCAN",details[1]);
        i.putExtra("TIPODEEVENTO",details[2]);
        i.putExtra("FECHA",details[3]);
        i.putExtra("HORA",details[4]);
        i.putExtra("HORAUTC",details[5]);
        i.putExtra("OBSERVACIONES",details[6]);
        i.putExtra("SIMULACRO",details[7]);
        c.startActivity(i);
    }

    private void veralertacenizas(String...details) {
        Intent i=new Intent(c,Datosultimasnotificacionescenizas.class);
        i.putExtra("TIPODENOTIFICACION",details[0]);
        i.putExtra("VOLCAN",details[1]);
        i.putExtra("PUEBLOS",details[2]);
        i.putExtra("TIPODEEVENTO",details[3]);
        i.putExtra("DIRECCION",details[4]);
        i.putExtra("RADIO",details[5]);
        i.putExtra("FECHA",details[6]);
        i.putExtra("HORA",details[7]);
        i.putExtra("RECOMENDACIONES",details[8]);
        i.putExtra("OBSERVACIONES",details[9]);
        i.putExtra("SIMULACRO",details[10]);
        i.putExtra("HORAUTC",details[11]);
        c.startActivity(i);
    }

}