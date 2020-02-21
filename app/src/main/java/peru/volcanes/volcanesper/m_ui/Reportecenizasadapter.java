package peru.volcanes.volcanesper.m_ui;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import peru.volcanes.volcanesper.Pdfvieweralertas;
import peru.volcanes.volcanesper.R;
import peru.volcanes.volcanesper.m_model.reportealertacenizas;
import java.util.ArrayList;
public class Reportecenizasadapter extends BaseAdapter {
    TextView fecha_hora;
    TextView evento;
    TextView alerta;
    public Context c;
    TextView descargar;
    DownloadManager descarga;
    private ArrayList<reportealertacenizas> objetoreportes;
    TextView compartir;
    TextView visualizar;
    RelativeLayout compartirfile;
    RelativeLayout visualizaerfile;
    RelativeLayout descargarfile;
    public Reportecenizasadapter(Context c, ArrayList<reportealertacenizas> objetoreportes) {
        this.c = c;
        this.objetoreportes = objetoreportes;
    }
    @Override
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
        convertView= LayoutInflater.from(c).inflate(R.layout.modeloreportecenizas,viewGroup,false);
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
        final reportealertacenizas s= (reportealertacenizas) this.getItem(position);
        fecha_hora.setText("Alerta de ceniza");
        evento.setText(s.getNombrereporte());
        visualizaerfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                veralerta(s.getFecha(),
                        s.getNombrereporte(),
                        s.getPdfurl(),
                        s.getCodigovolcan());
            }
        });
        descargarfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descarga = (DownloadManager) c.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri =  Uri.parse(s.getPdfurl());
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
                String shareBody = "Reporte de actividad volcanica " + s.getNombrereporte();
                String shareSub = s.getPdfurl();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareSub);
                c.startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
        return convertView;
    }
    private void veralerta(String...details) {
        Intent i=new Intent(c,Pdfvieweralertas.class);
        i.putExtra("FECHA",details[0]);
        i.putExtra("NOMBRE",details[1]);
        i.putExtra("PDFURL",details[2]);
        i.putExtra("CODIGOVOLCAN",details[3]);
        c.startActivity(i);
    }
}