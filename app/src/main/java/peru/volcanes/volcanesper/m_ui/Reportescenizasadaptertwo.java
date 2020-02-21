package peru.volcanes.volcanesper.m_ui;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import peru.volcanes.volcanesper.Detallesalertadecenizasvolcan;
import peru.volcanes.volcanesper.R;
import peru.volcanes.volcanesper.m_model.reportealertadecenizastwo;
public class Reportescenizasadaptertwo  extends BaseAdapter {
    TextView fecha_hora;
    TextView evento;
    TextView alerta;
    public Context c;
    TextView descargar;
    DownloadManager descarga;
    private ArrayList<reportealertadecenizastwo> objetoalertastwo;
    TextView compartir;
    TextView visualizar;
    RelativeLayout compartirfile;
    RelativeLayout visualizaerfile;
    RelativeLayout descargarfile;

    public Reportescenizasadaptertwo(Context c, ArrayList<reportealertadecenizastwo> objetoalertastwo) {
        this.c = c;
        this.objetoalertastwo = objetoalertastwo;
    }

    @Override
    public int getCount() {
        return objetoalertastwo.size();
    }
    @Override
    public Object getItem(int pos) {
        return objetoalertastwo.get(pos);
    }
    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView= LayoutInflater.from(c).inflate(R.layout.modelalertacenizastwo,viewGroup,false);
        final int g = position;
        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#F0EFF5"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        compartirfile= (RelativeLayout) convertView.findViewById(R.id.b6);
        visualizaerfile= (RelativeLayout) convertView.findViewById(R.id.bb7);
        descargarfile= (RelativeLayout) convertView.findViewById(R.id.b5);
        compartir = (TextView) convertView.findViewById(R.id.compartir);
        visualizar = (TextView) convertView.findViewById(R.id.visualizar);
        descargar = (TextView) convertView.findViewById(R.id.descargar);
        fecha_hora = (TextView) convertView.findViewById(R.id.fecha_hora);
        evento = (TextView) convertView.findViewById(R.id.evento);
        alerta = (TextView) convertView.findViewById(R.id.alerta);
        Typeface fontAwesomeFont = Typeface.createFromAsset(c.getAssets(),"fontawesome-webfont.ttf");
        alerta.setTypeface(fontAwesomeFont);
        descargar.setTypeface(fontAwesomeFont);
        compartir.setTypeface(fontAwesomeFont);
        visualizar.setTypeface(fontAwesomeFont);
        final reportealertadecenizastwo s= (reportealertadecenizastwo) this.getItem(position);
        fecha_hora.setText("Alerta de dispersión de cenizas");
        evento.setText(s.getFecha() + " " + " " + s.getHora());
        visualizaerfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veralerta(s.getVolcan(),
                        s.getPueblos(),
                        s.getTipodevento(),
                        s.getDireccion(),
                        s.getRadio(),
                        s.getFecha(),
                        s.getHora(),
                        s.getHorautc(),
                        s.getObservaciones(),
                        s.getRecomendaciones(),
                        s.getSimulacro());
            }
        });
        compartirfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Alerta de dispersión de cenizas " +
                        "\n\n" + "Pueblos: " + s.getPueblos() +
                        "\n\n" + "Tipo de evento: " + s.getTipodevento() +
                        "\n\n" + "Dirección: " + s.getDireccion() +
                        "\n\n" + "Radio de dispersión: " + s.getPueblos() +
                        "\n\n" + "Fecha: " + s.getFecha() +
                        "\n\n" + "Hora Local: " + s.getHora() + "/ Hora UTC: " + s.getHorautc() +
                        "\n\n" + "Simulacro" + s.getSimulacro() +
                        "\n\n" +  "Observaciones: " +
                        "\n\n" +  s.getObservaciones() +
                        "\n\n" + "Recomendaciones: " +
                        "\n\n" +  s.getRecomendaciones();
                String shareSub =  "Alerta de dispersión de cenizas " +
                        "\n\n" + "Pueblos: " + s.getPueblos() +
                        "\n\n" + "Tipo de evento: " + s.getTipodevento() +
                        "\n\n" + "Dirección: " + s.getDireccion() +
                        "\n\n" + "Radio de dispersión: " + s.getRadio() +  "km" +
                        "\n\n" + "Fecha: " + s.getFecha() +
                        "\n\n" + "Hora Local: " + s.getHora() + "/ Hora UTC: " + s.getHorautc() +
                        "\n\n" + "Simulacro: " + s.getSimulacro() +
                        "\n\n" +  "Observaciones: " +
                        "\n\n" +  s.getObservaciones() +
                        "\n\n" + "Recomendaciones: " +
                        "\n\n" +  s.getRecomendaciones();
               // sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareSub);
                c.startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
        return convertView;
    }
    private void veralerta(String...details) {
        Intent i=new Intent(c,Detallesalertadecenizasvolcan.class);
        i.putExtra("CODIGOVOLCAN",details[0]);
        i.putExtra("PUEBLOS",details[1]);
        i.putExtra("TIPODEEVENTO",details[2]);
        i.putExtra("DIRECCION",details[3]);
        i.putExtra("RADIO",details[4]);
        i.putExtra("FECHA",details[5]);
        i.putExtra("HORA",details[6]);
        i.putExtra("HORAUTC",details[7]);
        i.putExtra("OBSERVACIONES",details[8]);
        i.putExtra("RECOMENDACIONES",details[9]);
        i.putExtra("SIMULACRO",details[10]);
        c.startActivity(i);
    }
}