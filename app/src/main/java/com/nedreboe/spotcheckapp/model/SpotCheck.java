package com.nedreboe.spotcheckapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SpotCheck {
    @PrimaryKey
    @NonNull
    private int spotCheckId;
    @NonNull
    private String date;
    @NonNull
    private String location;
    @NonNull
    private String carModel;
    @NonNull
    private String carRegNr;
    @NonNull
    private String spotCheckResult;
    @NonNull
    private String notes;
    @NonNull
    private boolean hasExported;

    public SpotCheck(@NonNull String date,
                     @NonNull  String location,
                     @NonNull String carModel,
                     @NonNull String carRegNr,
                     @NonNull String spotCheckResult,
                     @NonNull String notes,
                     @NonNull boolean hasExported){
        this.date = date;
        this.location = location;
        this.carModel = carModel;
        this.carRegNr = carRegNr;
        this.spotCheckResult = spotCheckResult;
        this.notes = notes;
        //randomized spotCheckID. technically its flawed and can fail if it Somehow manages to roll the same number twice in a row. but with 10 digits its extremely unlikely to happen
        this.spotCheckId = (int)(Math.random() * (1000000000 - 0 + 1))+1;
        this.hasExported = hasExported;
        //(int)(Math.random() * (1000000000 - 0 + 1))+1
    }


    public int getSpotCheckId() {
        return spotCheckId;
    }
    public String getDate() {
        return date;
    }
    public String getLocation() {
        return location;
    }
    public String getCarModel() {
        return carModel;
    }
    public String getCarRegNr() {
        return carRegNr;
    }
    public String getSpotCheckResult() {
        return spotCheckResult;
    }
    public String getNotes() {
        return notes;
    }
    public boolean isHasExported() {
        return hasExported;
    }

    public void setSpotCheckId(int spotCheckId) {
        this.spotCheckId = spotCheckId;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public void setLocation(@NonNull String location) {
        this.location = location;
    }

    public void setCarModel(@NonNull String carModel) {
        this.carModel = carModel;
    }

    public void setCarRegNr(@NonNull String carRegNr) {
        this.carRegNr = carRegNr;
    }

    public void setSpotCheckResult(@NonNull String spotCheckResult) {
        this.spotCheckResult = spotCheckResult;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setHasExported(boolean hasExported) {
        this.hasExported = hasExported;
    }
}
