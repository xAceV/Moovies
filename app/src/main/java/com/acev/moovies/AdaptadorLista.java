package com.acev.moovies;

import android.content.Context;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.acev.moovies.Config.API_POSTER_URL;

/**
 * Created by Daniel on 14/05/2017.
 */

public class AdaptadorLista extends BaseAdapter {
    private Context context;
    private List<MovGen> items;

    public AdaptadorLista(Context context, ArrayList<MovGen> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.layout_lista, parent, false);
        }

        // Obtener datos a insertar
        final MovGen item = this.items.get(position);
        final String id = item.getId();
        final String average = item.getAverage();
        final String backdrop = item.getBackdrop();
        final String poster = item.getPoster();
        final String release_date = item.getRelease_date();
        final String title = item.getTitle();

        ImageView ivPoster = (ImageView) rowView.findViewById(R.id.ivPoster);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        TextView tvAverage = (TextView) rowView.findViewById(R.id.tvAverage);
        TextView tvRelease = (TextView) rowView.findViewById(R.id.tvRelease);

        tvTitle.setText(title);
        tvRelease.setText(release_date);
        tvAverage.setText(String.format("%1$,.1f", Double.parseDouble(average)));
        Picasso.with(context).load(API_POSTER_URL.replace("<IMG_PATH>", poster)).into(ivPoster);


        // TODO: OnClickListener -> Detalle
        // Abrir vista detalle de cada película
        RelativeLayout rlElemento = (RelativeLayout) rowView.findViewById(R.id.rlElemento);
        final View finalRowView = rowView;
        rlElemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(finalRowView, "Click en: " + id + " (" + title + ")", Snackbar.LENGTH_LONG).show();
            }
        });

        return rowView;
    }
}
