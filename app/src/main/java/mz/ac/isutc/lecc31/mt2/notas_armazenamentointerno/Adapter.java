package mz.ac.isutc.lecc31.mt2.notas_armazenamentointerno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<Nota> notas;
    private Context context;
    private LayoutInflater inflater;

    public Adapter(ArrayList<Nota> notas, Context context) {
        this.notas = notas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_item_list, null);

        TextView t = convertView.findViewById(R.id.titulo);
        t.setText(notas.get(position).getTitulo()+"");

        return convertView;
    }
}
