package com.nedreboe.spotcheckapp.model.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.nedreboe.spotcheckapp.model.SpotCheck;
import com.nedreboe.spotcheckapp.model.spotCheckRepo;

import java.util.List;
public class spotCheckViewModel extends AndroidViewModel{

    private spotCheckRepo spotCheckRepo;
    private LiveData<List<SpotCheck>> allSpotChecks;
    private LiveData<List<SpotCheck>> recentSpotChecks;
    public LiveData<List<SpotCheck>> searchSpotChecks2;
    public MutableLiveData<String> filterLiveData2 = new MutableLiveData<>();

    public spotCheckViewModel (@NonNull Application application) {
        super(application);
        spotCheckRepo = new spotCheckRepo(application);
        allSpotChecks = spotCheckRepo.getAllSpotChecks();
        recentSpotChecks = spotCheckRepo.getRecentSpotChecks();
        //Passes the search term down to the repo
        searchSpotChecks2 = Transformations.switchMap(filterLiveData2,
                name -> spotCheckRepo.searchSpotChecks2(name));

    }

    public LiveData<List<SpotCheck>> getAllSpotChecks() {
        return allSpotChecks;
    }
    public LiveData<List<SpotCheck>> getRecentSpotChecks() {
        return recentSpotChecks;
    }

    public void insert (SpotCheck spotCheck) {spotCheckRepo.insert(spotCheck);}
    public void update (SpotCheck spotCheck) {spotCheckRepo.update(spotCheck);}
}

