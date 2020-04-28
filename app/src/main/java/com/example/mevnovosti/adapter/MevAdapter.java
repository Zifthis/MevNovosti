package com.example.mevnovosti.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mevnovosti.R;
import com.example.mevnovosti.model.MevModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MevAdapter extends RecyclerView.Adapter<MevAdapter.MevViewHolder> implements Filterable {

    private Context context;
    private ArrayList<MevModel> mevModelArrayList;
    private ArrayList<MevModel> mevArrayListFiltered;
    private FragmentManager manager = null;

    //the constructor keeps Context and ArrayList as parametars
    public MevAdapter(Context context, ArrayList mevModelArrayList) {
        this.context = context;
        this.mevModelArrayList = mevModelArrayList;
        this.mevArrayListFiltered = mevModelArrayList;

    }


    public MevViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mev_list, parent, false);


        return new MevViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MevViewHolder holder, int position) {

        //animation bind
        holder.imageView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_animation));
        holder.textViewNaslov.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_animation));
        holder.textViewTekst.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_animation));
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_animation));


        //biding views
        holder.textViewNaslov.setText(mevArrayListFiltered.get(position).getNaslov());
        holder.textViewTekst.setText(mevArrayListFiltered.get(position).getTekst());
        holder.textViewDatumObajava.setText(dateAndTimeFormat(mevArrayListFiltered.get(position).getDatumObjave()));


        //connecting share button with code
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "MEV OBAVIJEST: \n\n\n" +
                        holder.textViewNaslov.getText() +
                        holder.getAdapterPosition() +
                        "\nSADRZAJ: " +
                        holder.textViewTekst.getText() +
                        "\n\n\nhttps://www.mev.hr/studenti/");
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "Podjeli"));
            }
        });


        //glide library
        String imagePath = "www.mev.hr" + mevModelArrayList.get(position).getSlika();
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.mev_logo)
                .into(holder.imageView);

    }

    //time & date formating
    public static String dateAndTimeFormat(String date) {
        String newDate = "";
        try {
            //timezone change
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat outDF = new SimpleDateFormat("dd.MM.yyyy");
            outDF.setTimeZone(TimeZone.getDefault());
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            String changed = outDF.format(df.parse(date));
            newDate = changed;
            Calendar cal = Calendar.getInstance();
            cal.setTime(outDF.parse(changed));

            return newDate;
        } catch (Exception e) {
            e.printStackTrace();
            return newDate;
        }
    }


    //size of fragmets
    @Override
    public int getItemCount() {
        return mevArrayListFiltered.size();
    }

    //SEARCH filter by input string
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String string = constraint.toString();
                if (string.isEmpty()) {
                    mevArrayListFiltered = mevModelArrayList;
                } else {
                    ArrayList<MevModel> IsFiltered = new ArrayList<>();
                    for (MevModel row : mevModelArrayList) {
                        if (row.getNaslov().toLowerCase().contains(string.toLowerCase())) {
                            IsFiltered.add(row);
                        }
                    }

                    mevArrayListFiltered = IsFiltered;

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values = mevArrayListFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mevArrayListFiltered = (ArrayList<MevModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MevViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNaslov, textViewTekst, textViewDatumObajava;
        public ImageView imageView;
        public Button btnShare;
        public CardView cardView;


        public MevViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);

            //share button
            btnShare = (Button) itemView.findViewById(R.id.share);

            //always visible items
            textViewNaslov = (TextView) itemView.findViewById(R.id.textView_naslov);
            textViewTekst = (TextView) itemView.findViewById(R.id.textView_tekst);
            textViewDatumObajava = (TextView) itemView.findViewById(R.id.textView_datum_objave);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }
}
