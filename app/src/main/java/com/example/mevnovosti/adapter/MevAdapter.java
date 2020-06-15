package com.example.mevnovosti.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mevnovosti.R;
import com.example.mevnovosti.model.Novosti;
import com.example.mevnovosti.ui.detalji.DetaljiActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MevAdapter extends RecyclerView.Adapter<MevAdapter.MevViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Novosti> novostiArrayList;
    private ArrayList<Novosti> mevArrayListFiltered;
    private FragmentManager manager = null;

    //the constructor keeps Context and ArrayList as parametars
    public MevAdapter(Context context, ArrayList novostiArrayList) {
        this.context = context;
        this.novostiArrayList = novostiArrayList;
        this.mevArrayListFiltered = novostiArrayList;


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
        holder.textViewTekst.setText(mevArrayListFiltered.get(position).getPodNaslov());
        holder.textViewDatumObajava.setText(dateAndTimeFormat(mevArrayListFiltered.get(position).getDatumObjave()));

        //new activity
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, DetaljiActivity.class);
                i.putExtra("image_url", mevArrayListFiltered.get(position).getSlika());
                i.putExtra("name_url", mevArrayListFiltered.get(position).getNaslov());
                i.putExtra("opis_url", mevArrayListFiltered.get(position).getTekst());
                i.putExtra("datum_url", dateAndTimeFormat(mevArrayListFiltered.get(position).getDatumObjave()));

                context.startActivity(i);

            }
        });



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
        String imagePath = novostiArrayList.get(position).getSlika();
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
                    mevArrayListFiltered = novostiArrayList;
                } else {
                    ArrayList<Novosti> IsFiltered = new ArrayList<>();
                    for (Novosti row : novostiArrayList) {
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

                mevArrayListFiltered = (ArrayList<Novosti>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MevViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNaslov, textViewTekst, textViewDatumObajava;
        public ImageView imageView;
        public Button btnShare;
        public CardView cardView;
        public LinearLayout linearLayout;


        public MevViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);

            //share button
            btnShare = (Button) itemView.findViewById(R.id.share);

            //linearlayout
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);

            //always visible items
            textViewNaslov = (TextView) itemView.findViewById(R.id.textView_naslov);
            textViewTekst = (TextView) itemView.findViewById(R.id.textView_tekst);
            textViewDatumObajava = (TextView) itemView.findViewById(R.id.textView_datum_objave);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

        }
    }
}
