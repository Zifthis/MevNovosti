package com.example.mevnovosti.adapter;

import android.content.Context;
import android.content.Intent;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mevnovosti.R;
import com.example.mevnovosti.model.MevModel;

import java.util.ArrayList;

public class MevAdapter extends RecyclerView.Adapter<MevAdapter.MevViewHolder> implements Filterable {

    private Context context;
    private ArrayList<MevModel> mevModelArrayList;
    private ArrayList<MevModel> mevArrayListFiltered;


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
        holder.textViewPodnaslov.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_animation));
        holder.circleImage.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_animation));
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale));

        //biding views
        holder.textViewNaslov.setText(mevArrayListFiltered.get(position).getNaslov());
        holder.textViewPodnaslov.setText(mevArrayListFiltered.get(position).getPodNaslov());
        holder.textViewTekst.setText(mevArrayListFiltered.get(position).getTekst());
        holder.textViewDatumObajava.setText(mevArrayListFiltered.get(position).getDatumObjave());
        holder.textViewDatumNovosti.setText(mevArrayListFiltered.get(position).getDatumNovosti());


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


        //expands CardView on button click
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                    holder.expandableView.setVisibility(View.VISIBLE);
                    holder.button.setBackgroundResource(R.drawable.ic_keyboard_arrow_down);
                } else {
                    TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                    holder.expandableView.setVisibility(View.GONE);
                    holder.button.setBackgroundResource(R.drawable.ic_keyboard_arrow_up);
                }
            }
        });

        //glide library
        String imagePath = "www.mev.hr" + mevModelArrayList.get(position).getSlika();
        Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.mev_logo)
                .into(holder.imageView);

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

        public TextView textViewNaslov, textViewPodnaslov, textViewTekst, textViewDatumObajava, textViewDatumNovosti;
        public ImageView imageView, circleImage;
        public Button button, btnShare;
        public CardView cardView;
        public ConstraintLayout expandableView;


        public MevViewHolder(View itemView) {
            super(itemView);

            //expanded items
            expandableView = (ConstraintLayout) itemView.findViewById(R.id.expandableView);
            button = (Button) itemView.findViewById(R.id.btn);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            //share button
            btnShare = (Button) itemView.findViewById(R.id.share);

            //always visible items
            textViewNaslov = (TextView) itemView.findViewById(R.id.textView_naslov);
            textViewPodnaslov = (TextView) itemView.findViewById(R.id.textView_podnaslov);
            textViewTekst = (TextView) itemView.findViewById(R.id.textView_tekst);
            textViewDatumObajava = (TextView) itemView.findViewById(R.id.textView_datum_objave);
            textViewDatumNovosti = (TextView) itemView.findViewById(R.id.textView_datum_novosti);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            circleImage = (ImageView) itemView.findViewById(R.id.circleImage);

        }
    }
}
