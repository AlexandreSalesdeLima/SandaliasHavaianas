package com.example.alexandre.sandaliashavaianas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.alexandre.sandaliashavaianas.Cless.Havaiana;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Wilma on 03/11/2015.
 */
public class HavaianaAdapter extends ArrayAdapter<Havaiana> {

    public HavaianaAdapter(Context context, List<Havaiana> havaianas){
        super(context, 0, havaianas);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Havaiana havaiana = getItem(position);
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_havaiana, null);
            holder = new ViewHolder();
            holder.imgCapa = (ImageView)convertView.findViewById(R.id.imgCapa);
            holder.txtNome = (TextView)convertView.findViewById(R.id.txtNome);
            holder.txtvalor = (TextView)convertView.findViewById(R.id.txtValor);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

            Picasso.with(getContext())
                    .load(havaiana.capa)
                    .into(holder.imgCapa);

        holder.txtNome.setText(havaiana.nome);
        holder.txtvalor.setText(havaiana.valor);
        return convertView;
    }

    class ViewHolder{
        ImageView imgCapa;
        TextView txtNome;
        TextView txtvalor;
    }
}
