package com.nedreboe.spotcheckapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nedreboe.spotcheckapp.model.SpotCheck;

import java.util.ArrayList;

public class SpotCheckListAdapter extends RecyclerView.Adapter<SpotCheckListAdapter.SpotCheckViewHolder> {

    private final ArrayList<SpotCheck> spotChecks;
    private LayoutInflater inflater;

    public SpotCheckListAdapter(Context context, ArrayList<SpotCheck> spotCheck){
        inflater = LayoutInflater.from(context);
        this.spotChecks = spotCheck;
    }

    @NonNull
    @Override
    public SpotCheckListAdapter.SpotCheckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflates the viewItem with the recycler view template called spotcheck_item
        View itemView = inflater.inflate(R.layout.spotcheck_item,parent, false);
        return new SpotCheckListAdapter.SpotCheckViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull SpotCheckListAdapter.SpotCheckViewHolder holder, int position) {
        //Sets all the text fields with the spot check data
        SpotCheck spotcheck = spotChecks.get(position);
        holder.dateTV.setText(spotcheck.getDate());
        holder.carRegTV.setText(spotcheck.getCarRegNr());
        holder.carMakeTV.setText(spotcheck.getCarModel());
        holder.locationTV.setText(spotcheck.getLocation());
        holder.resultTV.setText(spotcheck.getSpotCheckResult());
        holder.notesTV.setText(spotcheck.getNotes());
    }

    @Override
    public int getItemCount() {
        return spotChecks.size();
    }

    public class SpotCheckViewHolder extends RecyclerView.ViewHolder {
        public final TextView dateTV;
        public final TextView carRegTV;
        public final TextView carMakeTV;
        public final TextView locationTV;
        public final TextView resultTV;
        public final TextView notesTV;
        final SpotCheckListAdapter adapter;

        public SpotCheckViewHolder(@NonNull View itemView, SpotCheckListAdapter spotCheckListAdapter) {
            super(itemView);
            //Pre declaring all these to save on computing power
            dateTV = itemView.findViewById(R.id.dateTimeText);
            carRegTV = itemView.findViewById(R.id.carRegText);
            carMakeTV = itemView.findViewById(R.id.carMakeText);
            locationTV = itemView.findViewById(R.id.locationText);
            resultTV = itemView.findViewById(R.id.resultText);
            notesTV = itemView.findViewById(R.id.notesText);
            this.adapter = spotCheckListAdapter;
        }
    }
}
