package peru.volcanes.volcanesper.m_ui;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import peru.volcanes.volcanesper.R;
import peru.volcanes.volcanesper.m_model.erupciones;
import java.util.ArrayList;
public class ErupcionesAdapter extends BaseAdapter {
    TextView year;
    TextView inicial_final;
    TextView tipo_actividad;
    TextView fuente;
    public Context c;
    private ArrayList<erupciones> objetoerupciones;
    public ErupcionesAdapter(Context c, ArrayList<erupciones> objetoerupciones) {
        this.c = c;
        this.objetoerupciones = objetoerupciones;
    }
    @Override
    public int getCount() {
        return objetoerupciones.size();
    }
    @Override
    public Object getItem(int pos) {
        return objetoerupciones.get(pos);
    }
    @Override
    public long getItemId(int pos) {
        return pos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.modelo_erupciones,viewGroup,false);
        }
        fuente = (TextView) convertView.findViewById(R.id.fuente);
        year = (TextView) convertView.findViewById(R.id.year);
        tipo_actividad = (TextView) convertView.findViewById(R.id.tipo_actividad);
        final erupciones s= (erupciones) this.getItem(position);
        year.setText(s.getYear());
        tipo_actividad.setText(s.getObservaciones());
        if (s.getFuente().length() > 3) {
            fuente.setText("Fuente: " + s.getFuente());
        }
        else{
            fuente.setText(" ");
        }
        ViewGroup.LayoutParams params = convertView.getLayoutParams();
       params.height = 160;
        convertView.setLayoutParams(params);
        return convertView;
    }
}