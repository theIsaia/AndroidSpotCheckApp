package com.nedreboe.spotcheckapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.nedreboe.spotcheckapp.model.SpotCheck;
import com.nedreboe.spotcheckapp.model.viewModel.spotCheckViewModel;

import java.util.ArrayList;
import java.util.List;

public class spotCheckSearch extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SpotCheckListAdapter adapter;
    private ArrayList<SpotCheck>spotCheckList = new ArrayList();

    private spotCheckViewModel spotCheckViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_check_search);

        recyclerView = findViewById(R.id.RecyclerSpotCheck2);
        adapter = new SpotCheckListAdapter(this, spotCheckList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        spotCheckViewModel = ViewModelProviders.of(this).get(spotCheckViewModel.class);
        spotCheckViewModel.searchSpotChecks2.observe(this, new Observer<List<SpotCheck>>() {
            @Override
            public void onChanged(List<SpotCheck> spotChecks) {
                spotCheckList.clear();
                for (SpotCheck spotCheck: spotChecks){
                    spotCheckList.add(spotCheck);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }
    //performs the search and sends it down the ROOM model via Viewmodel -> Repo -> Dao
    public void performSearch(View view) {
        EditText searchWordField = findViewById(R.id.searchTerm);
        String searchWord = searchWordField.getText().toString();
        Log.d("searchTerm",searchWord);
        spotCheckViewModel.filterLiveData2.setValue(searchWord);
    }
}
