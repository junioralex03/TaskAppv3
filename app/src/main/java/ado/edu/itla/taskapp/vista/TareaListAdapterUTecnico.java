package ado.edu.itla.taskapp.vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Tarea;

public class TareaListAdapterUTecnico extends BaseAdapter {

    private Context context;
    private List<Tarea> tareas;

    public TareaListAdapterUTecnico(Context context, List<Tarea> tareas){
        this.context = context;
        this.tareas = tareas;
    }

    @Override
    public int getCount() {
        return tareas.size();
    }

    @Override
    public Object getItem(int position) {
        return tareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tareas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView =  inflater.inflate(R.layout.ta1rea_listview_row, null, true);
        }

        TextView lblFechaTareaAdaptador = convertView.findViewById(R.id.lblFechaTareaAdaptador);
        TextView lblDescripcionTareaAdaptador = convertView.findViewById(R.id.lblDescripcionTareaAdaptador);
        TextView lblUsuarioAsignadoAdaptador = convertView.findViewById(R.id.lblUsuarioAsignadoAdaptador);
        TextView lblCategoriaAsignadaAdaptador = convertView.findViewById(R.id.lblCategoriaAsignadaAdaptador);
        TextView lblEstadoAsignadoAdaptador = convertView.findViewById(R.id.lblEstadoAsignadoAdaptador);

        Tarea ta = tareas.get(position);

        lblFechaTareaAdaptador.setText(ta.getFecha().toString());
        lblDescripcionTareaAdaptador.setText(ta.getDescripcion());
        lblUsuarioAsignadoAdaptador.setText("Creado por: "+ta.getUsuarioCreador().getNombre());
        lblCategoriaAsignadaAdaptador.setText(ta.getCategoria().getNombre());
        lblEstadoAsignadoAdaptador.setText(ta.getEstado().toString());

        return convertView;
    }
}