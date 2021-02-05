package com.nedreboe.spotcheckapp.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface spotCheckDao {
    @Insert
    void insert(SpotCheck spotcheck);

    @Update
    void update(SpotCheck spotcheck);

    @Query("SELECT * FROM SpotCheck ORDER BY date DESC")
    LiveData<List<SpotCheck>> getAllSpotChecks();

    @Query("SELECT * FROM SpotCheck WHERE hasExported=0 ORDER BY date DESC")
    LiveData<List<SpotCheck>> getRecentSpotChecks();

    //Search Query that searches for the keyword :name against all the fields
    @Query("SELECT * FROM SpotCheck WHERE location||carModel||carRegNr||spotCheckResult||date LIKE '%' || :name || '%' ORDER BY date DESC")
    LiveData<List<SpotCheck>>searchSpotChecks2(String name);

}
