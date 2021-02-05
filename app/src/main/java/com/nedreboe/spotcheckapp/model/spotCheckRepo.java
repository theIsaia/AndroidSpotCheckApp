package com.nedreboe.spotcheckapp.model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class spotCheckRepo {

    private spotCheckDao spotCheckDao;
    private LiveData<List<SpotCheck>> allSpotChecks;
    private LiveData<List<SpotCheck>> recentSpotChecks;

    public spotCheckRepo(Application application){
        spotCheckRoomDatabase db = spotCheckRoomDatabase.getDatabase(application);
        spotCheckDao = db.spotCheckDao();
        allSpotChecks = spotCheckDao.getAllSpotChecks();
        recentSpotChecks = spotCheckDao.getRecentSpotChecks();
    }

    public LiveData<List<SpotCheck>> getAllSpotChecks() {return allSpotChecks;}
    public LiveData<List<SpotCheck>> getRecentSpotChecks() {return recentSpotChecks;}
    public LiveData<List<SpotCheck>> searchSpotChecks2(String name){
        return spotCheckDao.searchSpotChecks2(name);
    }

    public void insert(SpotCheck spotCheck){
        new InsertAsyncTask(spotCheckDao).execute(spotCheck);
    }

    public void update(SpotCheck spotCheck) {
        new updateAsyncTask(spotCheckDao).execute(spotCheck);
    }

    private static class InsertAsyncTask extends AsyncTask<SpotCheck, Void, Void>{
        private spotCheckDao asyncTaskDao;

        InsertAsyncTask(spotCheckDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SpotCheck... spotChecks) {
            asyncTaskDao.insert(spotChecks[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<SpotCheck, Void, Void>{
        private spotCheckDao asyncTaskDao;

        updateAsyncTask(spotCheckDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SpotCheck... spotChecks) {
            asyncTaskDao.update(spotChecks[0]);
            return null;
        }
    }






}
